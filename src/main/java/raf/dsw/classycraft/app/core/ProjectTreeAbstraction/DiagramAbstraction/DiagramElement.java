package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode {
    private Color color;
    private int stroke;
    public DiagramElement(String name) {
        super(name);
    }
}
