package raf.dsw.classycraft.app.command.commands.connections;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class AddGeneralizationCommand extends AbstractCommand {
    private String newConName;
    private InterClass newConFrom;
    private InterClass newConTo;
    private DiagramElementPainter curDep;
    private DiagramView curDiagramView;

    public AddGeneralizationCommand(String newConName, InterClass newConFrom, InterClass newConTo, DiagramView curDiagramView) {
        this.newConName = newConName;
        this.newConFrom = newConFrom;
        this.newConTo = newConTo;
        this.curDiagramView = curDiagramView;

        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();
        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        Generalization newGeneralization = (Generalization) manufacturer.createConnection(ConnectionType.GENERALIZATION,
                newConName, newConFrom, newConTo, null, ' ', ' ', null);

        curDep = painterManufacturer.createPainter(newGeneralization);
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
