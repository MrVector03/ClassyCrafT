package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.PackageView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Package extends ClassyNodeComposite implements IPublisher {
    public Package(String name) {
        super(name);
    }

    private final List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void setName(String name) {
        super.setName(name);
        // notifySubscribers(new SubscriberNotification(Type.RENAME, this, name));
    }

    public void changeOnProject(String name, ClassyNode el, Type type) {
        if (this.findProject() != el) return;
        if (type.equals(Type.RENAME))
            notifySubscribers(new SubscriberNotification(type, el, name));
        else if (type.equals(Type.CHANGE_AUTHOR))
            notifySubscribers(new SubscriberNotification(type, el, name));
    }

    public void displayOnScreen() {
        notifySubscribers(new SubscriberNotification(Type.OPEN, this));
    }

    public void addDiagramOnScreen(Diagram diagram) {
        notifySubscribers(new SubscriberNotification(Type.ADD, diagram));
    }

    public void removeDiagramFromScreen(Diagram diagram) {
        notifySubscribers(new SubscriberNotification(Type.REMOVE, diagram));
    }

    public void checkRemovalFromScreen() {
        notifySubscribers(new SubscriberNotification(Type.REMOVE, this));
    }

    public void checkRemovalFromScreen(Project project) {
        notifySubscribers(new SubscriberNotification(Type.REMOVE, project));
    }

    @Override
    public void addChild(ClassyNode newChild) {
        if(newChild instanceof Package || newChild instanceof Diagram)
            super.addChild(newChild);
        else
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NODE_CANNOT_BE_ADDED", MessageType.ERROR);
    }

    public ClassyNodeComposite findProject() {
        ClassyNodeComposite project = (ClassyNodeComposite) this.getParent();
        while (!(project instanceof Project)) {
            project = (ClassyNodeComposite) project.getParent();
        }
        return project;
    }

    public void clearSubscribers() {
        this.subscribers.clear();
    }

    public boolean checkSubscriber(ISubscriber subscriber) {
        return this.subscribers.contains(subscriber);
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
        for (ISubscriber subscriber : this.subscribers) {
            subscriber.update(notification);
        }
    }
}
