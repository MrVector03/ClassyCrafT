package raf.dsw.classycraft.app.command.commands.connections;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class AddDependencyCommand extends AbstractCommand {
    private String newConName;
    private String newConType;
    private InterClass newConFrom;
    private InterClass newConTo;
    private DiagramElementPainter curDep;
    private DiagramView curDiagramView;

    public AddDependencyCommand(String newConName, String newConType, InterClass newConFrom, InterClass newConTo, DiagramView curDiagramView) {
        this.newConName = newConName;
        this.newConType = newConType;
        this.newConFrom = newConFrom;
        this.newConTo = newConTo;
        this.curDiagramView = curDiagramView;

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        Dependency newDependency = (Dependency) manufacturer.createConnection(ConnectionType.DEPENDENCY,
                newConName, newConFrom, newConTo, null, ' ', ' ', newConType);

        curDep = painterManufacturer.createPainter(newDependency);
    }

    @Override
    public void doCommand() {
        curDiagramView.addDiagramElementPainter(curDep);
    }

    @Override
    public void undoCommand() {
        curDiagramView.getDiagramElementPainters().remove(curDep);
        curDiagramView.getDiagram().deleteChild(curDep.getDiagramElement());

        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(curDep.getDiagramElement());
    }
}
