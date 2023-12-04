package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class InterClass extends DiagramElement{
    private Access access;
    private Point2D position;
    private Dimension size;

    public InterClass(String name, Access access, Point2D position, Dimension size) {
        super(name);
        this.access = access;
        this.position = position;
        this.size = size;
    }

    public Access getAccess() {
        return access;
    }

    public Point2D getPosition() {
        return position;
    }

    public Dimension getSize() {
        return size;
    }
}
