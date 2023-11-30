package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Interface extends InterClass {
    private ArrayList<Method> methods;

    public Interface(String name, Access access, String name1, Point2D position, Dimension scale, ArrayList<Method> methods) {
        super(name, access, name1, position, scale);
        this.methods = methods;
    }
}
