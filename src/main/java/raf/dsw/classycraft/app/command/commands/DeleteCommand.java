package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand {
    private List<DiagramElementPainter> toDelete = new ArrayList<DiagramElementPainter>();
    private List<ConnectionPainter> toDeleteCon = new ArrayList<ConnectionPainter>();
    private DiagramView curDiagramView;

    public DeleteCommand(ArrayList<DiagramElementPainter> toDelete, DiagramView curDiagramView) {
        this.toDelete.addAll(toDelete);
        this.curDiagramView = curDiagramView;
    }

    @Override
    public void doCommand() {
        toDeleteCon.clear();

        for(DiagramElementPainter dep : toDelete)
            if(dep instanceof InterClassPainter)
                toDeleteCon.addAll(curDiagramView.testConnections(((InterClassPainter) dep).getInterClass()));

        for(DiagramElementPainter dep : toDelete) {
            curDiagramView.getDiagramElementPainters().remove(dep);
            curDiagramView.getDiagram().deleteChild(dep.getDiagramElement());
            ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(dep.getDiagramElement());
        }
    }

    @Override
    public void undoCommand() {
        for(DiagramElementPainter dep : toDelete) {
            curDiagramView.addDiagramElementPainter(dep);
        }

        for(ConnectionPainter cp : toDeleteCon) {
            curDiagramView.addDiagramElementPainter(cp);
        }
    }
}
