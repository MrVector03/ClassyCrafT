package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNode implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();

    public Diagram(String name) {
        super(name);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        notifySubscribers(new SubscriberNotification(Type.RENAME, this, name));
    }

    public void addToScreen() {
        ((Package) getParent()).addDiagramOnScreen(this);
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : this.subscribers)
            subscriber.update(notification);
    }
}
