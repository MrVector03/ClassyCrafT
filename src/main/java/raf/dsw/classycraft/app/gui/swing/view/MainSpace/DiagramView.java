package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;

import javax.swing.*;

public class DiagramView extends JPanel implements ISubscriber {
    private final TabbedPane tabbedPane;
    private final Diagram diagram;
    private String name;

    public DiagramView(Diagram diagram, TabbedPane tabbedPane) {
        this.diagram = diagram;
        this.name = diagram.getName();
        this.tabbedPane = tabbedPane;
        diagram.addSubscriber(this);
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

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public void update(Object notification) {
        SubscriberNotification n = (SubscriberNotification) notification;
        // NOTIFICATION FOR RENAME = [TYPE.RENAME, DIAGRAM, NEW NAME]
        if (n.getType().equals(Type.RENAME)) {
            this.setName(n.getMsg());
            this.tabbedPane.renameDiagram(n.getClassyNode(), n.getMsg());

        }

    }
}
