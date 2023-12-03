package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TabView extends JSplitPane {
    private final DiagramView diagramView;
    private WorkSpaceButtons buttons;
    public TabView(DiagramView diagramView, WorkSpaceButtons buttons) {
        super(JSplitPane.HORIZONTAL_SPLIT, diagramView, buttons);
        setDividerLocation(625);
        this.diagramView = diagramView;
    }

    public DiagramView getDiagramView() {
        return diagramView;
    }

}
