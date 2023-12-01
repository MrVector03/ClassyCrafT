package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import javax.swing.*;
import java.awt.*;

public class TabView extends JPanel {
    private final DiagramView diagramView;
    private final WorkSpaceButtons buttons = new WorkSpaceButtons();

    public TabView(DiagramView diagramView) {
        this.diagramView = diagramView;

        add(diagramView, BorderLayout.CENTER);
        add(this.buttons, BorderLayout.EAST);
    }
}
