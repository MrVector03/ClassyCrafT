package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections;

import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;

public class Aggregation extends Connection {
    private String varName;
    private char cardFrom;
    private char cardTo;

    public Aggregation(String name, InterClass from, InterClass to, String varName, char cardFrom, char cardTo) {
        super(name, from, to);
        this.varName = varName;
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
    }

    public String getVarName() {
        return varName;
    }

    public char getCardFrom() {
        return cardFrom;
    }

    public char getCardTo() {
        return cardTo;
    }

    public void setVarName(String varName) {
        this.varName = varName;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setCardFrom(char cardFrom) {
        this.cardFrom = cardFrom;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setCardTo(char cardTo) {
        this.cardTo = cardTo;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }
}
