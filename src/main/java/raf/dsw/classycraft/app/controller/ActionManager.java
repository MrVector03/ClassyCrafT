package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.tree.AddNodeAction;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private AddNodeAction addNodeAction;
    public ActionManager() {
        aboutUsAction = new AboutUsAction();
        exitAction = new ExitAction();
        addNodeAction = new AddNodeAction();
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
}
