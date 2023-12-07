package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import javax.swing.*;

public class TabView extends JSplitPane {
    private final DiagramView diagramView;
    public TabView(DiagramView diagramView, WorkSpaceButtons buttons) {
        super(JSplitPane.HORIZONTAL_SPLIT, diagramView, buttons);
        setDividerLocation(625);
        this.diagramView = diagramView;
    }

    public DiagramView getDiagramView() {
        return diagramView;
    }

}
