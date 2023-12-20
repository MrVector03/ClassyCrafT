package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AddInterfaceCommand extends AbstractCommand {
    private String newInterfaceName;
    private Access newInterfaceAccess;
    private ArrayList<Method> newInterfaceMethods;
    private Dimension newInterfaceDimensions;
    private Interface newInterface;
    private Point2D newInterfacePosition;
    private DiagramView curDiagram;
    private DiagramElementPainter curDep;

    public AddInterfaceCommand(String newInterfaceName, Access newInterfaceAccess, ArrayList<Method> newInterfaceMethods, Dimension newInterfaceDimensions, Point2D newInterfacePosition, DiagramView curDiagram) {
        this.newInterfaceName = newInterfaceName;
        this.newInterfaceAccess = newInterfaceAccess;
        this.newInterfaceMethods = newInterfaceMethods;
        this.newInterfaceDimensions = newInterfaceDimensions;
        this.newInterfacePosition = newInterfacePosition;
        this.curDiagram = curDiagram;

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        newInterface = (Interface) manufacturer.createInterClass(InterClassType.INTERFACE, newInterfaceName, newInterfaceAccess,
                new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()), newInterfaceDimensions,
                null, false, null, newInterfaceMethods);

        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();
        curDep = painterManufacturer.createPainter(newInterface);
    }

    @Override
    public void doCommand() {
        curDiagram.addDiagramElementPainter(curDep);
    }

    @Override
    public void undoCommand() {
        curDiagram.getDiagramElementPainters().remove(curDep);
        curDiagram.getDiagram().deleteChild(curDep.getDiagramElement());

        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(newInterface);
    }
}
