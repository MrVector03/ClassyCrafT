package raf.dsw.classycraft.app.core.ProjectTreeAbstraction;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;

import java.util.ArrayList;

public abstract class ClassyNode implements IPublisher {
    private String name;
    private ClassyNode parent;
    private ArrayList<ISubscriber> subscribers = new ArrayList<ISubscriber>();

    public ClassyNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // public abstract void setName(String name) {};

    public void setName(String name) {
        this.name = name;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber subscriber : subscribers)
            subscriber.update(notification);
    }
}
