package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products;

import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.util.ArrayList;

public abstract class InterClass extends DiagramElement {
    private Access access;
    private Point2D position;
    private Dimension size;

    public InterClass(String name, Access access, Point2D position, Dimension size) {
        super(name);
        super.setColor(Color.BLACK);
        super.setStroke(new BasicStroke());
        this.access = access;
        this.position = position;
        this.size = size;
    }

    public Access getAccess() {
        return access;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void changePosition(Point2D change) {
        this.position.setLocation(position.getX() + change.getX(), position.getY() + change.getY());
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setAccess(Access access) {
        this.access = access;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setSize(Dimension size) {
        this.size = size;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public abstract void convertToCode(FileWriter fileWriter, ArrayList<Connection>connections);
}
