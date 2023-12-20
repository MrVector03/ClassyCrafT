package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AddEnumCommand extends AbstractCommand {
    private String newEnumName;
    private Access newEnumAccess;
    private Dimension newEnumDimensions;
    private ArrayList<String> newEnumValues;
    private Enum newEnum;
    private Point2D newEnumPosition;
    private DiagramView curDiagram;
    private DiagramElementPainter curDep;

    public AddEnumCommand(String newEnumName, Access newEnumAccess, Dimension newEnumDimensions, ArrayList<String> newEnumValues, Point2D newEnumPosition, DiagramView curDiagram) {
        this.newEnumName = newEnumName;
        this.newEnumAccess = newEnumAccess;
        this.newEnumDimensions = newEnumDimensions;
        this.newEnumValues = newEnumValues;
        this.newEnumPosition = newEnumPosition;
        this.curDiagram = curDiagram;

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();
        newEnum = (Enum) manufacturer.createInterClass(InterClassType.ENUM, newEnumName, newEnumAccess,
                new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()),
                newEnumDimensions, null, false, newEnumValues, null);

        curDep = painterManufacturer.createPainter(newEnum);
    }

    @Override
    public void doCommand() {
        curDiagram.addDiagramElementPainter(curDep);
    }

    @Override
    public void undoCommand() {
        curDiagram.getDiagramElementPainters().remove(curDep);
        curDiagram.getDiagram().deleteChild(curDep.getDiagramElement());

        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(newEnum);
    }
}
