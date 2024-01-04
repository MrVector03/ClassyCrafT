package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.TemporarySelectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SelectionState implements State, IPublisher {

    private final List<ISubscriber> subscribers = new ArrayList<>();

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        diagramView.getSelectedElements().clear();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position))
                diagramView.selectElement(dep);
        }
        markSelectedElements(diagramView);
        startingPoint = position;
        notifySubscribers(new StateNotification(diagramView));
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        diagramView.getSelectedElements().clear();
        startingPoint = position;
        notifySubscribers(new StateNotification(diagramView));
    }

    @SuppressWarnings("ALL")
    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        endingPoint = startingPosition;

        diagramView.removeAllSelectionPainters();

        TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof InterClassPainter) {
                    Point2D point = ((InterClassPainter) dep).getInterClass().getPosition();
                    Dimension size = ((InterClassPainter) dep).getInterClass().getSize();
                    Point2D endPoint = new Point2D.Double(point.getX() + size.getWidth(), point.getY() + size.getHeight());
                    if (dep.elementAt(startingPosition)
                            || testIntersectionLines(startingPoint, endingPoint, point, endPoint, (InterClassPainter) dep)
                            || selectionPainter.elementAt(point)
                            || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY()))
                            || selectionPainter.elementAt(new Point2D.Double(point.getX(), point.getY() + size.height))
                            || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY() + size.height)))
                        diagramView.selectElement(dep);
                    else {
                        diagramView.unselectElement(dep);
                    }
            } else if (dep instanceof ConnectionPainter && ((ConnectionPainter) dep).elementAtShape(selectionPainter.getRectangle()))
                diagramView.selectElement(dep);
            else {
                diagramView.unselectElement(dep);
            }

        }
        markSelectedElements(diagramView);
        diagramView.addDiagramElementPainter(selectionPainter);
        notifySubscribers(new StateNotification(diagramView));
    }

    @SuppressWarnings("ALL")
    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        endingPoint = endingPosition;
        diagramView.removeAllSelectionPainters();
        TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof InterClassPainter) {
                Point2D point = ((InterClassPainter) dep).getInterClass().getPosition();
                Dimension size = ((InterClassPainter) dep).getInterClass().getSize();
                Point2D endPoint = new Point2D.Double(point.getX() + size.getWidth(), point.getY() + size.getHeight());
                if (dep.elementAt(endingPosition)
                        || testIntersectionLines(startingPoint, endingPoint, point, endPoint, (InterClassPainter) dep)
                        || selectionPainter.elementAt(point)
                        || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY()))
                        || selectionPainter.elementAt(new Point2D.Double(point.getX(), point.getY() + size.height))
                        || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY() + size.height)))
                    diagramView.selectElement(dep);
                else {
                    diagramView.unselectElement(dep);
                }
            } else if (dep instanceof ConnectionPainter && ((ConnectionPainter) dep).elementAtShape(selectionPainter.getRectangle()))
                diagramView.selectElement(dep);
            else
                diagramView.unselectElement(dep);

        }
        markSelectedElements(diagramView);

        diagramView.addDiagramElementPainter(selectionPainter);

        startingPoint = null;
        endingPoint = null;

        diagramView.getDiagramElementPainters().removeIf(dep -> dep instanceof TemporarySelectionPainter);
        notifySubscribers(new StateNotification(diagramView));
    }

    @Override
    public void classyMouseWheelMoved(Point2D position, DiagramView diagramView, MouseWheelEvent e) {

    }

    public void markSelectedElements(DiagramView diagramView) {
        List<ConnectionPainter> connections = new ArrayList<>();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters())
            if (dep instanceof ConnectionPainter) connections.add((ConnectionPainter) dep);
        List<DiagramElement> selected = new ArrayList<>();

        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof TemporarySelectionPainter) continue;
            if (diagramView.getSelectedElements().contains(dep)) {
                if (dep instanceof InterClassPainter) {
                    selected.add(((InterClassPainter) dep).getInterClass());
                    ((InterClassPainter) dep).getInterClass().setStroke(new BasicStroke(3.0f));
                    ((InterClassPainter) dep).getInterClass().setColor(Color.BLUE);
                } else {
                    selected.add(((ConnectionPainter)dep).getConnection());
                    ((ConnectionPainter) dep).getConnection().setColor(Color.BLUE);
                    ((ConnectionPainter) dep).getConnection().setStroke(new BasicStroke(3.0f));
                }
            } else {
                selected.add(null);
            }
            selectInConnections(connections, selected, diagramView);
        }
    }

    private void selectInConnections(List<ConnectionPainter> connections,
                                     List<DiagramElement> selected, DiagramView diagramView) {

        for (ConnectionPainter cp : connections) {

            int id = diagramView.getDiagramElementPainters().indexOf(cp);

            InterClass from = cp.getConnection().getFrom();
            InterClass to = cp.getConnection().getTo();
            if (from == to) System.out.println("OLD SAME");


            if (selected.contains(from) && selected.contains(to)) {
                if (selected.contains(from) && diagramView.getDiagramElementPainters().get(selected.indexOf(from)) instanceof InterClassPainter) {
                    from = (((InterClassPainter) diagramView.getDiagramElementPainters().get(selected.indexOf(from))).getInterClass());
                }
                if (selected.contains(to) && diagramView.getDiagramElementPainters().get(selected.indexOf(to)) instanceof InterClassPainter) {
                    to = (((InterClassPainter) diagramView.getDiagramElementPainters().get(selected.indexOf(to))).getInterClass());
                    cp.getConnection().setFrom(from);
                    cp.getConnection().setTo(to);
                } else if (selected.contains(from)) {
                    if (selected.contains(from) && diagramView.getDiagramElementPainters().get(selected.indexOf(from)) instanceof InterClassPainter) {
                        from = (((InterClassPainter) diagramView.getDiagramElementPainters().get(selected.indexOf(from))).getInterClass());
                    }
                    cp.getConnection().setFrom(from);
                } else {
                    if (selected.contains(to) && diagramView.getDiagramElementPainters().get(selected.indexOf(to)) instanceof InterClassPainter)
                        to = (((InterClassPainter) diagramView.getDiagramElementPainters().get(selected.indexOf(to))).getInterClass());
                    cp.getConnection().setTo(to);
                }
            }
        }
    }

    @SuppressWarnings("ALL")
    public boolean testIntersectionLines(Point2D selStart, Point2D selEnd, Point2D elStart, Point2D elEnd, InterClassPainter icp) {

        if (selStart.getX() > selEnd.getX() && selStart.getY() > selEnd.getY()) {
            Point2D tmp = selStart;
            selStart = selEnd;
            selEnd = tmp;
        } else if (selStart.getX() > selEnd.getX()) {
            Point2D newStart = new Point2D.Double(selEnd.getX(), selStart.getY());
            Point2D newEnd = new Point2D.Double(selStart.getX(), selEnd.getY());
            selStart = newStart;
            selEnd = newEnd;
        } else if (selStart.getY() > selEnd.getY()) {
            Point2D newStart = new Point2D.Double(selStart.getX(), selEnd.getY());
            Point2D newEnd = new Point2D.Double(selEnd.getX(), selStart.getY());
            selStart = newStart;
            selEnd = newEnd;
        }

        if (((selStart.getX() >= elStart.getX() && selStart.getX() <= elEnd.getX()) &&
                (selEnd.getX() >= elStart.getX() && selEnd.getX() <= elEnd.getX()) &&
                (elStart.getY() >= selStart.getY() && elStart.getY() <= selEnd.getY()) &&
                (elEnd.getY() >= selStart.getY() && elEnd.getY() <= selEnd.getY())) ||
                ((selStart.getX() <= elStart.getX() && selStart.getX() <= elEnd.getX()) &&
                        (selEnd.getX() >= elStart.getX() && selEnd.getX() >= elEnd.getX()) &&
                        (elStart.getY() <= selStart.getY() && elStart.getY() <= selEnd.getY()) &&
                        (elEnd.getY() >= selStart.getY() && elEnd.getY() >= selEnd.getY()))) {
            double m1 = (selEnd.getY() - selStart.getY()) / (selEnd.getX() - selStart.getX());
            double b1 = selStart.getY() - m1 * selStart.getX();

            double m2 = (elEnd.getY() - elStart.getY()) / (elEnd.getX() - elStart.getX());
            double b2 = elStart.getY() - m2 * elStart.getX();

            if (m1 == m2) return false;

            double testX = (b2 - b1) / (m1 - m2);
            double testY = m1 * testX + b1;

            Point2D testPoint = new Point2D.Double(testX, testY);

            return icp.elementAt(testPoint);
        }
        return false;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : this.subscribers)
            subscriber.update(notification);
    }
}
