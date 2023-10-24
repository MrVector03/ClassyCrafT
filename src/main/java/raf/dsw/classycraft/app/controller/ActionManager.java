package raf.dsw.classycraft.app.controller;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    public ActionManager() {
        aboutUsAction = new AboutUsAction();
        exitAction = new ExitAction();
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
}
