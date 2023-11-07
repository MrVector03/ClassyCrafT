package raf.dsw.classycraft.app.core.MessageGenerator;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();
    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void generateMessage(String text, MessageType type)
    {
        Message newMessage = new Message(text, type, LocalDateTime.now());

        notifySubscribers(newMessage);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification instanceof Message)
            for(ISubscriber subscriber : subscribers)
                subscriber.update(notification);
    }
}
