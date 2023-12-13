package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CopyInterClassState implements State {
    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        List<DiagramElementPainter> toCopy = new ArrayList<>();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position))
                toCopy.add(dep);
        }

        if(toCopy.size() == 0)
            return;

        if(toCopy.size() > 1) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CAN_COPY_ONLY_ONE_INTERCLASS", MessageType.WARNING);
            return;
        }

        if(toCopy.get(0).getDiagramElement() instanceof InterClass)
            copyPaint(toCopy.get(0), diagramView);
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        // List<DiagramElementPainter> toCopy = new ArrayList<>();
        // for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
        //     if (dep.elementAt(position))
        //         toCopy.add(dep);
        // }
//
        // if(toCopy.size() == 0)
        //     return;
//
        // if(toCopy.size() > 1) {
        //     ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CAN_COPY_ONLY_ONE_INTERCLASS", MessageType.WARNING);
        //     return;
        // }
//
        // if(toCopy.get(0).getDiagramElement() instanceof InterClass)
        //     copyPaint(toCopy.get(0), diagramView);
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

        if(tmpInterClass instanceof Class) {
            copyElement = new InterClassPainter(new Class(tmpInterClass.getName(), tmpInterClass.getAccess(), newPosition, tmpInterClass.getSize(), ((Class) tmpInterClass).getClassContents(), ((Class) tmpInterClass).isAbstract())); }
        else {
            if (tmpInterClass instanceof Enum)
                copyElement = new InterClassPainter(new Enum(tmpInterClass.getName(), tmpInterClass.getAccess(), tmpInterClass.getPosition(), tmpInterClass.getSize(), ((Enum) tmpInterClass).getValues()));
            else
                copyElement = new InterClassPainter(new Interface(tmpInterClass.getName(), tmpInterClass.getAccess(), newPosition, tmpInterClass.getSize(), ((Interface) tmpInterClass).getMethods()));
        }
        diagramView.addDiagramElementPainter(copyElement);
    }
}
