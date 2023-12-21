package raf.dsw.classycraft.app.command.commands.connections;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class EditGeneralizationCommand extends AbstractCommand {
    private String oldConName;
    private String newConName;
    private Generalization curCon;
    private DiagramView curDiagramView;

    public EditGeneralizationCommand(String oldConName, String newConName, Generalization curDep, DiagramView curDiagramView) {
        this.oldConName = oldConName;
        this.newConName = newConName;
        this.curCon = curDep;
        this.curDiagramView = curDiagramView;
    }

    @Override
    public void doCommand() {
        curCon.setName(newConName);
    }

    @Override
    public void undoCommand() {
        curCon.setName(oldConName);
    }
}
