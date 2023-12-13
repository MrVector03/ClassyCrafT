package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.TemporaryConnection;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.TemporaryConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class AddConnectionState implements State, IPublisher {

    private List<ISubscriber> subscribers = new ArrayList<>();

    private InterClass from;
    private InterClass to;

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position)) {
                from = ((InterClassPainter) dep).getInterClass();
                return;
            }
        }
        notifySubscribers(new StateNotification(diagramView));
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        diagramView.popTemporaryConnectionPainter();
        TemporaryConnection temporaryConnection = new TemporaryConnection("Placeholder", from, startingPosition) {
            @Override
            public InterClass getFrom() {
                return super.getFrom();
            }

            @Override
            public Point2D getTo() {
                return super.getTo();
            }
        };

        TemporaryConnectionPainter connectionPainter = new TemporaryConnectionPainter(temporaryConnection);
        diagramView.addDiagramElementPainter(connectionPainter);
        notifySubscribers(new StateNotification(diagramView));
    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        diagramView.popTemporaryConnectionPainter();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(endingPosition)) {
                if (from != ((InterClassPainter) dep).getInterClass())
                    to = ((InterClassPainter) dep).getInterClass();
                break;
            }
        }

        MainFrame.getInstance().setCurDiagramView(diagramView);
        MainFrame.getInstance().setCurFrom(from);
        MainFrame.getInstance().setCurTo(to);

        MainFrame.getInstance().getChooseConnectionFrame().setVisible(true);

        from = null;
        to = null;

        notifySubscribers(new StateNotification(diagramView));
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

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : this.subscribers)
            subscriber.update(notification);
    }
}
