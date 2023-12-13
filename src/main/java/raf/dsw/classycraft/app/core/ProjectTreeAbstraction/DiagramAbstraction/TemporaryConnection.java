package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;

import java.awt.geom.Point2D;

public abstract class TemporaryConnection extends DiagramElement {
    private InterClass from;
    private Point2D to;

    public TemporaryConnection(String name, InterClass from, Point2D to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public InterClass getFrom() {
        return from;
    }

    public Point2D getTo() {
        return to;
    }
}
