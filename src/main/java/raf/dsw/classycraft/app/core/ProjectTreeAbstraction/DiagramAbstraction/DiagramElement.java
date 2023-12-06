package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode {
    private Color color;
    private Stroke stroke;

    public DiagramElement(String name) {
        super(name);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}
