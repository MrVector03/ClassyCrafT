package raf.dsw.classycraft.app.controller;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.controller.tree.*;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeAuthorConfirmAction changeAuthorConfirmAction;
    private PackageChosenAction packageChosenAction;
    private DiagramChosenAction diagramChosenAction;
    public ActionManager() {
        aboutUsAction = new AboutUsAction();
        exitAction = new ExitAction();

        addNodeAction = new AddNodeAction();
        deleteNodeAction = new DeleteNodeAction();
        changeAuthorAction = new ChangeAuthorAction();
        changeAuthorConfirmAction = new ChangeAuthorConfirmAction();
        packageChosenAction = new PackageChosenAction();
        diagramChosenAction = new DiagramChosenAction();

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
}
