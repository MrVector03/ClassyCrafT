package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.PackageViewNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyRepository;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer root;
    private ClassyNodeChildFactory childFactory;

    private final List<ISubscriber> subscribers = new ArrayList<>();

    public ClassyRepositoryImplementation() {
        if(root == null)
            root = new ProjectExplorer();
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

    public void setRoot(ProjectExplorer root) {
        this.root = root;
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
        if (notification instanceof PackageViewNotification) {
            for (ISubscriber subscriber : subscribers)
                subscriber.update(notification);
        }
    }
}
