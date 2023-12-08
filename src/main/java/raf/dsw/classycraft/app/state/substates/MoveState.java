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
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State, IPublisher {

    private final List<ISubscriber> subscribers = new ArrayList<>();

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        diagramView.popTemporarySelectionPainter();
        startingPoint = position;
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        endingPoint = startingPosition;
        Point2D change = new Point2D.Double(endingPoint.getX() - startingPoint.getX(), endingPoint.getY() - startingPoint.getY());
        ArrayList<DiagramElementPainter> changedPainters = this.changeElementCoordinates(change, diagramView);

        notifySubscribers(new MoveNotification(diagramView, changedPainters, change));
        startingPoint = endingPoint;
    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        endingPoint = endingPosition;
        Point2D change = new Point2D.Double(endingPoint.getX() - startingPoint.getX(), endingPoint.getY() - startingPoint.getY());
        ArrayList<DiagramElementPainter> changedPainters = this.changeElementCoordinates(change, diagramView);
        notifySubscribers(new MoveNotification(diagramView, changedPainters, change));
        startingPoint = null;
        endingPoint = null;
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
        return oldPainters;
    }

    public boolean checkCollision(ArrayList<DiagramElementPainter> changedPainters,
                                  ArrayList<DiagramElementPainter> oldPainters) {
        for (DiagramElementPainter changedElement : changedPainters) {
            if (changedElement instanceof InterClassPainter) {
                int avoidIndex = changedPainters.indexOf(changedElement);
                InterClass ogInterClass = ((InterClassPainter) changedElement).getInterClass();
                Point2D newPosition = new Point2D.Double(ogInterClass.getPosition().getX() - 20,
                        ogInterClass.getPosition().getY() - 20);
                Dimension newSize = new Dimension((int) (ogInterClass.getSize().getWidth() + 40),
                        (int) (ogInterClass.getSize().getHeight() + 40)) {
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

                        testPoints.add(new Point2D.Double(ogPos.getX(), ogPos.getY())); // TOP LEFT
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth() / 2, ogPos.getY())); // TOP
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth(), ogPos.getY())); // TOP RIGHT
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth(), ogPos.getY() + size.getHeight() / 2)); // RIGHT
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth(), ogPos.getY() + size.getHeight())); // BOTTOM RIGHT
                        testPoints.add(new Point2D.Double(ogPos.getX() + size.getWidth() / 2, ogPos.getY() + size.getHeight())); // BOTTOM
                        testPoints.add(new Point2D.Double(ogPos.getX(), ogPos.getY() + size.getHeight())); // BOTTOM LEFT
                        testPoints.add(new Point2D.Double(ogPos.getX(), ogPos.getY() + size.getHeight() / 2)); // LEFT

                        for (Point2D testPoint : testPoints) {
                            if (testPainter.elementAt(testPoint)) {
                                System.out.println("colliding");
                                return false;
                            }
                        }
                    }
                }
            }
        }
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
