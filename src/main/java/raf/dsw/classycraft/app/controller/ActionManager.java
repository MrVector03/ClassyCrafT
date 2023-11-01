package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.tree.AddNodeAction;
import raf.dsw.classycraft.app.controller.tree.DeleteNodeAction;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    public ActionManager() {
        aboutUsAction = new AboutUsAction();
        exitAction = new ExitAction();
        addNodeAction = new AddNodeAction();
        deleteNodeAction = new DeleteNodeAction();
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
}
