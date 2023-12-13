package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class ClassyAbstractFactory {
    public abstract InterClass createInterClass(InterClassType interClassType, String name, Access access, Point2D position,
                                                Dimension size, ArrayList<ClassContent> classContents, boolean isAbstract,
                                                ArrayList<String> values, ArrayList<Method> methods);

    public abstract Connection createConnection(ConnectionType connectionType, String name, InterClass from, InterClass to,
                                                String varName, char cardFrom, char cardTo, String type);
}
