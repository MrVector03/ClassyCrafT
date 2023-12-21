package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Enum extends InterClass {
    private ArrayList<String> values;

    public Enum(String name, Access access, Point2D position, Dimension scale, ArrayList<String> values) {
        super(name, access, position, scale);
        this.values = values;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
        notifySubscribers(new SubscriberNotification(Type.EDIT_DIAGRAM_ELEMENT, this));
    }

    @Override
    public void convertToCode(FileWriter fileWriter, ArrayList<Connection> connections) {
        try {
            fileWriter.write(getAccess().toStringNames().toLowerCase()  + " Enum " + getName() + " {\n");

            for(int i = 0; i < values.size(); i++) {
                if(i == values.size()-1)
                    fileWriter.write(values.get(i) + ";\n");
                else
                    fileWriter.write(values.get(i) + ",");
            }

            fileWriter.write("}\n");

        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(ex.getMessage(), MessageType.ERROR);
        }
    }
}