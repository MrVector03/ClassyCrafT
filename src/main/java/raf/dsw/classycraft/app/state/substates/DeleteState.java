package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.command.commands.DeleteCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State, IPublisher {

    private final List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        ArrayList<DiagramElementPainter> toDelete = new ArrayList<DiagramElementPainter>();
        int a = 5;
        Rectangle2D rectangle = new Rectangle2D.Double(position.getX()-a, position.getY()-a, a*2, a*2);

        for(DiagramElementPainter diagramElementPainter : diagramView.getDiagramElementPainters()) {
            if (diagramElementPainter.elementAt(position))
                toDelete.add(diagramElementPainter);
            if(diagramElementPainter instanceof ConnectionPainter && ((ConnectionPainter) diagramElementPainter).elementAtShape(rectangle))
                toDelete.add(diagramElementPainter);
        }

        if (toDelete.isEmpty()) return;

        DeleteCommand deleteCommand = new DeleteCommand(toDelete, diagramView);
        ApplicationFramework.getInstance().getCommandManager().addCommand(deleteCommand);

        ((Project) ((Package) diagramView.getDiagram().getParent()).findProject()).makeChange();
        notifySubscribers(new StateNotification(diagramView));
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseWheelMoved(Point2D position, DiagramView diagramView, MouseWheelEvent e) {

    }

    public void immediateDelete() {

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
