package raf.dsw.classycraft.app.core.logger;

import raf.dsw.classycraft.app.core.MessageGenerator.Message;

public class ConsoleLogger implements Logger {
    @Override
    public void update(Object notification) {
        this.log((Message) notification);
    }

    @Override
    public void log(Message message) {
        System.out.println(LoggerUtility.format(message));
    }
}
