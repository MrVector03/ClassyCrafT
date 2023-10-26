package raf.dsw.classycraft.app.core.Observer;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);

    void removeSubscriber(ISubscriber subscriber);

    void notifySubscribers(Object notification);
}
