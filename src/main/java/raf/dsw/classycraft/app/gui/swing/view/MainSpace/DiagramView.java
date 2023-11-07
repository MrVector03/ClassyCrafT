package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;

import javax.swing.*;

public class DiagramView extends JPanel implements ISubscriber {
    private final Diagram diagram;
    private String name;

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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Object notification) {

    }
}
