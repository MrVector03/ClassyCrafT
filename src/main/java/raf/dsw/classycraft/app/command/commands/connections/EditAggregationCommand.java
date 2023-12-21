package raf.dsw.classycraft.app.command.commands.connections;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class EditAggregationCommand extends AbstractCommand {
    private String oldConName;
    private String oldConVar;
    private char oldConCardFrom;
    private char oldConCardTo;
    private String newConName;
    private String newConVar;
    private char newConCardFrom;
    private char newConCardTo;
    private Aggregation curCon;
    private DiagramView curDiagramView;

    public EditAggregationCommand(String oldConName, String oldConVar, char oldConCardFrom, char oldConCardTo, String newConName, String newConVar, char newConCardFrom, char newConCardTo, Aggregation curCon, DiagramView curDiagramView) {
        this.oldConName = oldConName;
        this.oldConVar = oldConVar;
        this.oldConCardFrom = oldConCardFrom;
        this.oldConCardTo = oldConCardTo;
        this.newConName = newConName;
        this.newConVar = newConVar;
        this.newConCardFrom = newConCardFrom;
        this.newConCardTo = newConCardTo;
        this.curCon = curCon;
        this.curDiagramView = curDiagramView;
    }

    @Override
    public void doCommand() {
        curCon.setName(newConName);
        curCon.setVarName(newConVar);
        curCon.setCardFrom(newConCardFrom);
        curCon.setCardTo(newConCardTo);
    }

    @Override
    public void undoCommand() {
        curCon.setName(oldConName);
        curCon.setVarName(oldConVar);
        curCon.setCardFrom(oldConCardFrom);
        curCon.setCardTo(oldConCardTo);
    }
}
