package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory;


import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.*;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ClassyManufacturer extends ClassyAbstractFactory{


    @Override
    public InterClass createInterClass(InterClassType interClassType, String name, Access access, Point2D position,
                                       Dimension size, ArrayList<ClassContent> classContents, boolean isAbstract,
                                       ArrayList<String> values, ArrayList<Method> methods) {
        if (interClassType.equals(InterClassType.CLASS))
            return new Class(name, access, position, size, classContents, isAbstract);
        else if (interClassType.equals(InterClassType.ENUM))
            return new Enum(name, access, position, size, values);
        else if (interClassType.equals(InterClassType.INTERFACE))
            return new Interface(name, access, position, size, methods);
        return null;
    }

    @Override
    public Connection createConnection(ConnectionType connectionType, String name, InterClass from,
                                       InterClass to, String varName, char cardFrom, char cardTo, String type) {
        if (connectionType.equals(ConnectionType.AGGREGATION))
            return new Aggregation(name, from, to, varName, cardFrom, cardTo);
        else if (connectionType.equals(ConnectionType.COMPOSITION))
            return new Composition(name, from, to, varName, cardFrom, cardTo);
        else if (connectionType.equals(ConnectionType.DEPENDENCY))
            return new Dependency(name, from, to, type);
        else if (connectionType.equals(ConnectionType.GENERALIZATION))
            return new Generalization(name, from, to);
        return null;
    }
}
