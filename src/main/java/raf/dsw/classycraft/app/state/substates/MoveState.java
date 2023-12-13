package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.MoveNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Area;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State, IPublisher {

    private final List<ISubscriber> subscribers = new ArrayList<>();

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    private boolean revertBack = false;
    private ArrayList<Point2D> revPoints = new ArrayList<>();

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        diagramView.popTemporarySelectionPainter();
        this.revPoints = new ArrayList<>(this.setLastValidPoints(diagramView.getDiagramElementPainters()));
        // System.out.println("new revs");
        startingPoint = position;
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        handleChange(startingPosition, diagramView, false);
        startingPoint = endingPoint;
    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        handleChange(endingPosition, diagramView, true);
        startingPoint = null;
        endingPoint = null;
    }

    public ArrayList<Point2D> setLastValidPoints(ArrayList<DiagramElementPainter> lastValidPoints) {
        ArrayList<Point2D> newPoints = new ArrayList<>();
        for (DiagramElementPainter dep : lastValidPoints) {
            if (dep instanceof InterClassPainter) {
                Point2D position = new Point2D.Double(((InterClassPainter) dep).getInterClass().getPosition().getX(),
                        ((InterClassPainter) dep).getInterClass().getPosition().getY());
                newPoints.add(position);
                // System.out.println("SETUP POS: " + position);
            }
            else
                newPoints.add(null);
        }
        return newPoints;
    }

    private void handleChange(Point2D endingPosition, DiagramView diagramView, boolean release) {
        endingPoint = endingPosition;
        Point2D change = new Point2D.Double(endingPoint.getX() - startingPoint.getX(), endingPoint.getY() - startingPoint.getY());
        ArrayList<DiagramElementPainter> changedPainters = this.changeElementCoordinates(change, diagramView);
        // System.out.println("POINTS BEFORE RUNNING:");
        //for (Point2D point2D : revPoints) {
        //    System.out.println(point2D);
        //}
        notifySubscribers(new MoveNotification(diagramView, changedPainters, change, revertBack, release, revPoints));
    }

    @Override
    public void classyMouseWheelMoved(Point2D position, DiagramView diagramView, MouseWheelEvent e) {

    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    private ArrayList<DiagramElementPainter> changeElementCoordinates(Point2D change, DiagramView diagramView) {
        ArrayList<DiagramElementPainter> changedPainters = diagramView.getDiagramElementPainters();
        ArrayList<DiagramElementPainter> selectedPainters = diagramView.getSelectedElements();
        ArrayList<DiagramElementPainter> oldPainters = diagramView.getDiagramElementPainters();
        List<ConnectionPainter> connections = new ArrayList<>();
        for (DiagramElementPainter dep : changedPainters)
            if (dep instanceof ConnectionPainter) connections.add((ConnectionPainter) dep);
        List<InterClass> copyForFromTo = new ArrayList<>();
        if (selectedPainters.isEmpty()) {
            for (DiagramElementPainter dep : changedPainters) {
                if (dep instanceof ConnectionPainter) {
                    copyForFromTo.add(null);
                    continue;
                }
                copyForFromTo.add(((InterClassPainter) dep).getInterClass());
                if (dep instanceof InterClassPainter) {
                    int id = changedPainters.indexOf(dep);
                    InterClassPainter test = (InterClassPainter) changedPainters.get(id);
                    InterClassPainter icp = new InterClassPainter(((InterClassPainter) dep).getInterClass());
                    icp.getInterClass().changePosition(change);
                    testForConnections(connections, test, icp);
                    changedPainters.set(id, icp);
                }
            }
        } else {
            for (DiagramElementPainter dep : changedPainters) {
                if (dep instanceof ConnectionPainter) {
                    copyForFromTo.add(null);
                    continue;
                }
                if (selectedPainters.contains(dep)) {
                    copyForFromTo.add(((InterClassPainter) dep).getInterClass());
                    int id = changedPainters.indexOf(dep);
                    InterClassPainter test = (InterClassPainter) changedPainters.get(id);
                    InterClassPainter icp = new InterClassPainter(((InterClassPainter) dep).getInterClass());
                    ((InterClassPainter) dep).getInterClass().changePosition(change);
                    // testForConnections(connections, test, icp);
                    changedPainters.set(id, icp);
                    selectedPainters.set(selectedPainters.indexOf(dep), icp);
                } else {
                    copyForFromTo.add(null);
                }
            }
        }
        changedPainters = moveConnections(connections, copyForFromTo, changedPainters);
        if (checkCollision(changedPainters, oldPainters)) {
            return changedPainters;
        }
        return changedPainters;
    }
    public boolean checkCollision(ArrayList<DiagramElementPainter> changedPainters,
                                  ArrayList<DiagramElementPainter> oldPainters) {
        for (DiagramElementPainter changedElement : changedPainters) {
            if (changedElement instanceof InterClassPainter) {
                int avoidIndex = changedPainters.indexOf(changedElement);
                InterClass ogInterClass = ((InterClassPainter) changedElement).getInterClass();
                Point2D newPosition = new Point2D.Double(ogInterClass.getPosition().getX(),
                        ogInterClass.getPosition().getY());
                Dimension newSize = new Dimension((int) (ogInterClass.getSize().getWidth()),
                        (int) (ogInterClass.getSize().getHeight())) {
                };
                InterClassPainter testPainter =
                        new InterClassPainter(new InterClass(ogInterClass.getName(), ogInterClass.getAccess(),
                                newPosition, newSize) {
                    @Override
                    public Access getAccess() {
                        return super.getAccess();
                    }

                    @Override
                    public Point2D getPosition() {
                        return super.getPosition();
                    }

                    @Override
                    public Dimension getSize() {
                        return super.getSize();
                    }

                    @Override
                    public void changePosition(Point2D change) {
                        super.changePosition(change);
                    }

                    @Override
                    public void setAccess(Access access) {
                        super.setAccess(access);
                    }

                    @Override
                    public void setSize(Dimension size) {
                        super.setSize(size);
                    }
                });

                for (int i = 0; i < oldPainters.size(); i++) {
                    if (i == avoidIndex) continue;
                    DiagramElementPainter oldPainter = oldPainters.get(i);
                    if (oldPainter instanceof InterClassPainter && ((InterClassPainter) oldPainter).getInterClass() != ogInterClass) {
                        Point2D ogPos = ((InterClassPainter) oldPainter).getInterClass().getPosition();
                        Dimension size = ((InterClassPainter) oldPainter).getInterClass().getSize();
                        List<Point2D> testPoints = new ArrayList<>();

                         // 0 -> 2: x -= 1
                        testPoints.add(new Point2D.Double(ogPos.getX(), ogPos.getY())); // TOP LEFT
                        testPoints.add(new Point2D.Double(ogPos.getX(), ogPos.getY() + size.getHeight() / 2)); // LEFT
                        testPoints.add(new Point2D.Double(ogPos.getX(), ogPos.getY() + size.getHeight())); // BOTTOM LEFT
//
//
                        // 3 -> 5: x += 1
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth(), ogPos.getY())); // TOP RIGHT
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth(), ogPos.getY() + size.getHeight() / 2)); // RIGHT
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth(), ogPos.getY() + size.getHeight())); // BOTTOM RIGHT
//
                        // 6: y -= 1
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth() / 2, ogPos.getY())); // TOP
//
                        // 7: y += 1
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth() / 2, ogPos.getY() + size.getHeight())); // BOTTOM

                        // PLAN: B
                        //for (int j = 0; j < testPoints.size(); j++) {
                        //    if (testPainter.elementAt(testPoints.get(j))) {
                        //        System.out.println("COLLIDING");
                        //        Point2D currPos = ((InterClassPainter) changedElement).getInterClass().getPosition();
                        //        if (j <= 2) {
                        //            System.out.println("going left");
                        //            ((InterClassPainter) changedElement).getInterClass().changePosition(new Point2D.Double(currPos.getX() - 5, currPos.getY()));
                        //        } else if (j <= 5) {
                        //            System.out.println("going right");
                        //            ((InterClassPainter) changedElement).getInterClass().changePosition(new Point2D.Double(currPos.getX() + 5, currPos.getY()));
                        //        } else if (j == 6) {
                        //            System.out.println("going up");
                        //            ((InterClassPainter) changedElement).getInterClass().changePosition(new Point2D.Double(currPos.getX(), currPos.getY() - 5));
                        //        } else if (j == 7) {
                        //            System.out.println("going down");
                        //            ((InterClassPainter) changedElement).getInterClass().changePosition(new Point2D.Double(currPos.getX(), currPos.getY() + 5));
                        //        }
                        //        System.out.println("CHANGED: " + changedPainters.indexOf(changedElement));
                        //        return false;
                        //    }
                        //}

                        // PLAN: A

                        for (Point2D testPoint : testPoints) {
                            if (testPainter.elementAt(testPoint)) {
                                this.revertBack = true;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        // System.out.println("not colliding");
        this.revertBack = false;
        return true;
    }

    public void testForConnections(List<ConnectionPainter> connections,
                                   InterClassPainter oldVal,InterClassPainter newVal) {
        for (ConnectionPainter cp : connections) {
            if (cp.getConnection().getFrom() == oldVal.getInterClass())
                cp.getConnection().setFrom(newVal.getInterClass());
            if (cp.getConnection().getTo() == oldVal.getInterClass())
                cp.getConnection().setTo(newVal.getInterClass());
        }

    }

    public ArrayList<DiagramElementPainter> moveConnections(List<ConnectionPainter> connections,
                                                            List<InterClass> interClasses, ArrayList<DiagramElementPainter> changedPainters) {
        for (ConnectionPainter cp : connections) {
            int idConnection = changedPainters.indexOf(cp);

            InterClass from = cp.getConnection().getFrom();
            InterClass to = cp.getConnection().getTo();

            int id_from = interClasses.indexOf(from);
            int id_to = interClasses.indexOf(to);

            InterClass newFrom;
            if (interClasses.contains(from) && (changedPainters.get(id_from) instanceof InterClassPainter)) {
                newFrom = ((InterClassPainter) changedPainters.get(id_from)).getInterClass();
            } else newFrom = from;

            InterClass newTo;
            if (interClasses.contains(to) && (changedPainters.get(id_to) instanceof InterClassPainter)) {
                newTo = ((InterClassPainter) changedPainters.get(id_to)).getInterClass();
            } else newTo = to;

            if (newFrom == newTo) return changedPainters;

            cp.getConnection().setFrom(newFrom);
            cp.getConnection().setTo(newTo);

            changedPainters.set(idConnection, cp);
        }
        return changedPainters;
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : this.subscribers)
            subscriber.update(notification);
    }
}
