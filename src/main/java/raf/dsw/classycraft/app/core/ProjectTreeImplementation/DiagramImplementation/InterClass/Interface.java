package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.util.ArrayList;

public class Interface extends InterClass {
    private ArrayList<Method> methods;

    public Interface(String name, Access access, Point2D position, Dimension scale, ArrayList<Method> methods) {
        super(name, access, position, scale);
        this.methods = methods;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    @Override
    public void convertToCode(FileWriter fileWriter, ArrayList<Connection> connections) {

    }
}
