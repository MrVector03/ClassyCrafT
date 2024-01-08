package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;

public class TabView extends JSplitPane {
    private final DiagramView diagramView;
    public TabView(DiagramView diagramView, WorkSpaceButtons buttons) {
        super(JSplitPane.HORIZONTAL_SPLIT, new DiagramScroll(diagramView, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), buttons);
        setDividerLocation(MainFrame.getInstance().getWidth() - 335);
        this.diagramView = diagramView;
    }


    public DiagramView getDiagramView() {
        return diagramView;
    }

    public void resizeDivider() {
        setDividerLocation(MainFrame.getInstance().getWidth() - 335);
    }

}
