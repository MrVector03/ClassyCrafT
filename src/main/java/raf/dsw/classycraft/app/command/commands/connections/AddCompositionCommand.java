package raf.dsw.classycraft.app.command.commands.connections;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class AddCompositionCommand extends AbstractCommand {
    private String newConName;
    private String newConVar;
    private char newConCardFrom;
    private char newConCardTo;
    private InterClass newConFrom;
    private InterClass newConTo;
    private DiagramElementPainter curDep;
    private DiagramView curDiagramView;

    public AddCompositionCommand(String newConName, String newConVar, char newConCardFrom, char newConCardTo, InterClass newConFrom, InterClass newConTo, DiagramView curDiagramView) {
        this.newConName = newConName;
        this.newConVar = newConVar;
        this.newConCardFrom = newConCardFrom;
        this.newConCardTo = newConCardTo;
        this.newConFrom = newConFrom;
        this.newConTo = newConTo;
        this.curDiagramView = curDiagramView;

        ClassyAbstractFactory manufacturer = new ClassyManufacturer();
        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        Composition newComposition = (Composition) manufacturer.createConnection(ConnectionType.COMPOSITION,
                newConName, newConFrom, newConTo, newConVar, newConCardFrom, newConCardTo, null);

        curDep = painterManufacturer.createPainter(newComposition);
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
