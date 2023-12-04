package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

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

    public void rewriteContents(ArrayList<ClassContent> newContents) {
        this.classContents = newContents;
    }
}
