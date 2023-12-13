package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class DiagramElementPainter {
    protected DiagramElement diagramElement;
    public DiagramElementPainter() {
    }

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(Point2D point);

    public DiagramElement getDiagramElement() {
        return diagramElement;
    }
}
