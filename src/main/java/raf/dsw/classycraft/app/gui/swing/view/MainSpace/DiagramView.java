package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;

import javax.swing.*;

public class DiagramView extends JPanel {
    private final Diagram diagram;
    private final String name;

    public DiagramView(Diagram diagram) {
        this.diagram = diagram;
        this.name = diagram.getName();
    }

    public Diagram getDiagram() {
        return diagram;
    }

    @Override
    public String getName() {
        return name;
    }
}
