package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Class extends InterClass {
    private boolean isAbstract;

    public Class(String name, Access access, Point2D position, Dimension size, ArrayList<ClassContent> classContents, boolean isAbstract) {
        super(name, access, position, size, classContents);
        this.isAbstract = isAbstract;
    }
}
