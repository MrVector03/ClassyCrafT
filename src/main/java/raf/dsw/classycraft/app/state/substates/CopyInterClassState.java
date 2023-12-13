package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CopyInterClassState implements State, IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        List<DiagramElementPainter> toCopy = new ArrayList<>();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position))
                toCopy.add(dep);
        }

        if(toCopy.isEmpty())
            return;

        if(toCopy.size() > 1) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CAN_COPY_ONLY_ONE_INTERCLASS", MessageType.WARNING);
            return;
        }

        if(toCopy.get(0).getDiagramElement() instanceof InterClass)
            copyPaint(toCopy.get(0), diagramView);

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

    private void copyPaint(DiagramElementPainter elementPainter, DiagramView diagramView) {
        InterClass tmpInterClass = ((InterClassPainter) elementPainter).getInterClass();
        Point newPosition = new Point();

        newPosition.setLocation(tmpInterClass.getPosition().getX() + tmpInterClass.getSize().getWidth(),
                tmpInterClass.getPosition().getY() + tmpInterClass.getSize().getHeight());

        InterClassPainter copyElement;

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        if(tmpInterClass instanceof Class) {

            Class classCopy = (Class) manufacturer.createInterClass(InterClassType.CLASS, tmpInterClass.getName(), tmpInterClass.getAccess(), newPosition,
                    tmpInterClass.getSize(), ((Class) tmpInterClass).getClassContents(), ((Class) tmpInterClass).isAbstract(), null, null);

            copyElement = (InterClassPainter) painterManufacturer.createPainter(classCopy);
        }
        else {
            if (tmpInterClass instanceof Enum) {
                Enum enumCopy = (Enum) manufacturer.createInterClass(InterClassType.ENUM, tmpInterClass.getName(), tmpInterClass.getAccess(),
                        tmpInterClass.getPosition(),
                        tmpInterClass.getSize(), null, false, ((Enum) tmpInterClass).getValues(), null);

                copyElement = (InterClassPainter) painterManufacturer.createPainter(enumCopy);
            } else {
                Interface interfaceCopy = (Interface) manufacturer.createInterClass(InterClassType.INTERFACE, tmpInterClass.getName(), tmpInterClass.getAccess(),
                        newPosition, tmpInterClass.getSize(), null, false, null, ((Interface) tmpInterClass).getMethods());

                copyElement = (InterClassPainter) painterManufacturer.createPainter(interfaceCopy);
            }
        }
        diagramView.addDiagramElementPainter(copyElement);
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
