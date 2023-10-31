package raf.dsw.classycraft.app.core.MessageGenerator;

import java.awt.*;
import java.time.LocalDateTime;

public class Message {
    private String text;
    private MessageType type;
    private LocalDateTime timestamp;

    public Message(String text, MessageType type, LocalDateTime timestamp) {
        this.text = text;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public MessageType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
