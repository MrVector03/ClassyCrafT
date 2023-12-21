package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    @Override
    public void convertToCode(FileWriter fileWriter, ArrayList<Connection> connections) {
        String s = " ";
        if(isAbstract)
            s = " abstract ";
        try {
            fileWriter.write(getAccess().toStringNames().toLowerCase() + s + "Class " + getName() + " ");

            boolean ext = false, impl = false;
            for(Connection con : connections) {
                if(con instanceof Generalization && con.getFrom().equals(this) && con.getTo() instanceof Class) {
                    if(!ext) {
                        fileWriter.write("extends ");
                        ext = true;
                    }

                    fileWriter.write(con.getTo().getName() + " ");
                }
            }

            for(Connection con : connections) {
                if(con instanceof Generalization && con.getFrom().equals(this) && con.getTo() instanceof Interface) {
                    if(!impl) {
                        fileWriter.write("implements ");
                        impl = true;
                        }
                    fileWriter.write(con.getTo().getName() + " ");
                }
            }

            fileWriter.write("{\n");

            for(ClassContent cc : classContents) {
                fileWriter.write("\t" +cc.getAccess().toStringNames().toLowerCase() + " " + cc.getReturnType() + " " + cc.getName());

                if(cc instanceof Method)
                    fileWriter.write("()");
                fileWriter.write(";\n");
            }

            for(Connection con : connections) {
                if((con instanceof Aggregation && con.getFrom().equals(this))) {
                    if(((Aggregation) con).getCardTo() == '1')
                        fileWriter.write("\tprivate " + con.getTo().getName() + " " + ((Aggregation) con).getVarName() + ";\n");
                    else
                        fileWriter.write("\tprivate List<" + con.getTo().getName() + "> " + ((Aggregation) con).getVarName() + ";\n");
                }

                if((con instanceof Composition && con.getFrom().equals(this))) {
                    if(((Composition) con).getCardTo() == '1')
                        fileWriter.write("\tprivate " + con.getTo().getName() + " " + ((Composition) con).getVarName() + ";\n");
                    else
                        fileWriter.write("\tprivate List<" + con.getTo().getName() + "> " + ((Composition) con).getVarName() + ";\n");
                }
            }

            fileWriter.write("}\n");

        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(ex.getMessage(), MessageType.ERROR);
        }
    }
}
