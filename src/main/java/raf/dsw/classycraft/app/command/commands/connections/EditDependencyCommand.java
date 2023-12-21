package raf.dsw.classycraft.app.command.commands.connections;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class EditDependencyCommand extends AbstractCommand {
    private String oldConName;
    private String oldConType;
    private String newConName;
    private String newConType;
    private Dependency curCon;
    private DiagramView curDiagramView;


    public EditDependencyCommand(String oldConName, String oldConType, String newConName, String newConType, Dependency curCon, DiagramView curDiagramView) {
        this.oldConName = oldConName;
        this.oldConType = oldConType;
        this.newConName = newConName;
        this.newConType = newConType;
        this.curCon = curCon;
        this.curDiagramView = curDiagramView;
    }

    @Override
    public void doCommand() {
        curCon.setName(newConName);
        curCon.setType(newConType);
    }

    @Override
    public void undoCommand() {
        curCon.setName(oldConName);
        curCon.setType(oldConType);
    }
}
