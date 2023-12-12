package raf.dsw.classycraft.app.core.Observer.notifications;

import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

public class StateNotification {
    private final DiagramView diagramView;

    public StateNotification(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    public DiagramView getDiagramView() {
        return diagramView;
    }
}
