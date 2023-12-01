package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Enum extends InterClass {
    private ArrayList<String> values;

    public Enum(String name, Access access, Point2D position, Dimension scale, ArrayList<String> values) {
        super(name, access, position, scale);
        this.values = values;
    }
}