package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class InterClass extends DiagramElement{
    private Access access;
    private String name;
    private Point2D position;
    private Dimension scale;

    public InterClass(String name, Access access, String name1, Point2D position, Dimension scale) {
        super(name);
        this.access = access;
        this.name = name1;
        this.position = position;
        this.scale = scale;
    }
}
