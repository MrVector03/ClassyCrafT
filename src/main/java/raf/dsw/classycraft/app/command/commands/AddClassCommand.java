package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AddClassCommand extends AbstractCommand {
    private String newClassName;
    private Access newClassAccess;
    private ArrayList<ClassContent> newClassAtributes;
    private Dimension newClassDimensions;
    private boolean newClassIsAbs;
    private Class newClass;
    private Point2D newClassPosition;
    private DiagramView curDiagram;
    private DiagramElementPainter curDep;

    public AddClassCommand(String newClassName, Access newClassAccess, ArrayList<ClassContent> newClassAtributes, Dimension newClassDimensions, boolean newClassIsAbs, Point2D newClassPosition, DiagramView curDiagram) {
        this.newClassName = newClassName;
        this.newClassAccess = newClassAccess;
        this.newClassAtributes = newClassAtributes;
        this.newClassDimensions = newClassDimensions;
        this.newClassIsAbs = newClassIsAbs;
        this.newClassPosition = newClassPosition;
        this.curDiagram = curDiagram;

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();
        newClass = (Class) manufacturer.createInterClass(InterClassType.CLASS, newClassName, newClassAccess,
                newClassPosition, newClassDimensions,
                newClassAtributes, newClassIsAbs, null, null);

        curDep = painterManufacturer.createPainter(newClass);
    }

    @Override
    public void doCommand() {
        curDiagram.addDiagramElementPainter(curDep);
    }

    @Override
    public void undoCommand() {
        curDiagram.getDiagramElementPainters().remove(curDep);
        curDiagram.getDiagram().deleteChild(curDep.getDiagramElement());

        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(newClass);
    }
}
