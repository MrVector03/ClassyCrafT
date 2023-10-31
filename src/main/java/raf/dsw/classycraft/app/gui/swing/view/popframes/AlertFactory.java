package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;

public class AlertFactory {
    public AlertFrame getAlert(Message message) {
        if (message.getType().equals(MessageType.NOTIFICATION))
            return new NotificationFrame(message.getText());
        else if (message.getType().equals(MessageType.ERROR))
            return new ErrorFrame(message.getText());
        else if (message.getType().equals(MessageType.WARNING))
            return new WarningFrame(message.getText());
        else
            throw new IllegalArgumentException("Unsupported alert type: " + message.getType().toString());
    }
}
