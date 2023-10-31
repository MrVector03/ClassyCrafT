package raf.dsw.classycraft.app.core.logger;

import raf.dsw.classycraft.app.core.MessageGenerator.Message;

import java.io.IOException;

public class FileLogger implements Logger {
    @Override
    public void update(Object notification) {
        try {
            this.log((Message) notification);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(Message message) throws IOException {
        LoggerUtility.logInFile(message);
    }
}
