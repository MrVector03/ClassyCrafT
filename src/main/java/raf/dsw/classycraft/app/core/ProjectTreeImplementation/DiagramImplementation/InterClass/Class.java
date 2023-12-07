package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Class extends InterClass {
    private ArrayList<ClassContent> classContents;
    private boolean isAbstract;

    public Class(String name, Access access, Point2D position, Dimension size, ArrayList<ClassContent> classContents, boolean isAbstract) {
        super(name, access, position, size);
        this.classContents = classContents;
        this.isAbstract = isAbstract;
    }

    public ArrayList<ClassContent> getClassContents() {
        return classContents;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void rewriteContents(ArrayList<ClassContent> newContents) {
        this.classContents = newContents;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setClassContents(ArrayList<ClassContent> classContents) {
        this.classContents = classContents;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

}
