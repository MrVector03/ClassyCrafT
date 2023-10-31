package raf.dsw.classycraft.app.core.logger;

import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;

import java.io.IOException;

public interface Logger extends ISubscriber {
    void log(Message message) throws IOException;
}
