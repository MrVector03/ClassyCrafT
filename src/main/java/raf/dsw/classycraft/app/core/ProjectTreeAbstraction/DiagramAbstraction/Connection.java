package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;

import java.awt.*;

public abstract class Connection extends DiagramElement {
    private InterClass from;
    private InterClass to;

    public Connection(String name, InterClass from, InterClass to) {
        super(name);
        super.setColor(Color.BLACK);
        super.setStroke(new BasicStroke());

        this.from = from;
        this.to = to;
    }

    public InterClass getFrom() {
        return from;
    }

    public InterClass getTo() {
        return to;
    }

    public void setFrom(InterClass from) {
        this.from = from;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setTo(InterClass to) {
        this.to = to;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }
}
