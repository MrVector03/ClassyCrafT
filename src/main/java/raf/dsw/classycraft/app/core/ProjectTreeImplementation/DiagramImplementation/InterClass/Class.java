package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Class extends InterClass {
    private ArrayList<ClassContent> classContents;
    private boolean isAbstract;

    public Class(String name, Access access, String name1, Point2D position, Dimension scale, ArrayList<ClassContent> classContents, boolean isAbstract) {
        super(name, access, name1, position, scale);
        this.classContents = classContents;
        this.isAbstract = isAbstract;
    }
}
