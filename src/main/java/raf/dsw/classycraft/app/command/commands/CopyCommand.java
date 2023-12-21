package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.*;

public class CopyCommand extends AbstractCommand {
    private InterClassPainter toCopy;
    private InterClassPainter copied;
    private DiagramView curDiagramView;

    public CopyCommand(InterClassPainter toCopy, DiagramView curDiagramView) {
        this.toCopy = toCopy;
        this.curDiagramView = curDiagramView;
    }

    @Override
    public void doCommand() {
        copyPaint(toCopy, curDiagramView);
    }

    @Override
    public void undoCommand() {
        curDiagramView.getDiagramElementPainters().remove(copied);
        curDiagramView.getDiagram().deleteChild(copied.getDiagramElement());

        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(copied.getInterClass());
    }

    private void copyPaint(DiagramElementPainter elementPainter, DiagramView diagramView) {
        InterClass tmpInterClass = ((InterClassPainter) elementPainter).getInterClass();
        Point newPosition = new Point();

        newPosition.setLocation(tmpInterClass.getPosition().getX() + tmpInterClass.getSize().getWidth(),
                tmpInterClass.getPosition().getY() + tmpInterClass.getSize().getHeight());

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        if(tmpInterClass instanceof Class) {

            Class classCopy = (Class) manufacturer.createInterClass(InterClassType.CLASS, tmpInterClass.getName(), tmpInterClass.getAccess(), newPosition,
                    tmpInterClass.getSize(), ((Class) tmpInterClass).getClassContents(), ((Class) tmpInterClass).isAbstract(), null, null);

            copied = (InterClassPainter) painterManufacturer.createPainter(classCopy);
        }
        else {
            if (tmpInterClass instanceof Enum) {
                Enum enumCopy = (Enum) manufacturer.createInterClass(InterClassType.ENUM, tmpInterClass.getName(), tmpInterClass.getAccess(),
                        tmpInterClass.getPosition(),
                        tmpInterClass.getSize(), null, false, ((Enum) tmpInterClass).getValues(), null);

                copied = (InterClassPainter) painterManufacturer.createPainter(enumCopy);
            } else {
                Interface interfaceCopy = (Interface) manufacturer.createInterClass(InterClassType.INTERFACE, tmpInterClass.getName(), tmpInterClass.getAccess(),
                        newPosition, tmpInterClass.getSize(), null, false, null, ((Interface) tmpInterClass).getMethods());

                copied = (InterClassPainter) painterManufacturer.createPainter(interfaceCopy);
            }
        }
        diagramView.addDiagramElementPainter(copied);
    }
}
