package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.DiagramButtonPanel.InterClassEditConfirmAction;
import raf.dsw.classycraft.app.controller.stateActions.*;
import raf.dsw.classycraft.app.controller.tree.*;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeAuthorConfirmAction changeAuthorConfirmAction;
    private PackageChosenAction packageChosenAction;
    private DiagramChosenAction diagramChosenAction;

    private AddInterClassAction addInterClassAction;
    private AddConnectionAction addConnectionAction;
    private AddElementAction addElementAction;
    private DeleteAction deleteAction;
    private SelectionAction selectionAction;
    private InterClassEditConfirmAction interClassEditConfirmAction;
    private CopyInterClassAction copyInterClassAction;

    public ActionManager() {
        aboutUsAction = new AboutUsAction();
        exitAction = new ExitAction();

        addNodeAction = new AddNodeAction();
        deleteNodeAction = new DeleteNodeAction();
        changeAuthorAction = new ChangeAuthorAction();
        changeAuthorConfirmAction = new ChangeAuthorConfirmAction();
        packageChosenAction = new PackageChosenAction();
        diagramChosenAction = new DiagramChosenAction();

        addInterClassAction = new AddInterClassAction();
        addConnectionAction = new AddConnectionAction();
        addElementAction = new AddElementAction();
        deleteAction = new DeleteAction();
        selectionAction = new SelectionAction();
        copyInterClassAction = new CopyInterClassAction();

        interClassEditConfirmAction = new InterClassEditConfirmAction();
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AddNodeAction getAddNodeAction() {
        return addNodeAction;
    }

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public ChangeAuthorAction getChangeAuthorAction() {
        return changeAuthorAction;
    }

    public ChangeAuthorConfirmAction getChangeAuthorConfirmAction() {
        return changeAuthorConfirmAction;
    }

    public PackageChosenAction getPackageChosenAction() {
        return packageChosenAction;
    }

    public DiagramChosenAction getDiagramChosenAction() {
        return diagramChosenAction;
    }

    public AddInterClassAction getAddInterClassAction() {
        return addInterClassAction;
    }

    public AddConnectionAction getAddConnectionAction() {
        return addConnectionAction;
    }

    public AddElementAction getAddElementAction() {
        return addElementAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public SelectionAction getSelectionAction() {
        return selectionAction;
    }

    public InterClassEditConfirmAction getInterClassEditConfirmAction() {
        return interClassEditConfirmAction;
    }

    public CopyInterClassAction getCopyInterClassAction() {
        return copyInterClassAction;
    }
}
