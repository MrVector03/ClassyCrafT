package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class InterClassPainter extends DiagramElementPainter {
    protected Shape shape;

    public InterClassPainter(InterClass interClass) {
        diagramElement = interClass;

        shape = new GeneralPath();

        Point2D interClassPos = ((InterClass)diagramElement).getPosition();
        Dimension2D interClassSize = ((InterClass)diagramElement).getSize();

        ((GeneralPath)shape).moveTo(interClassPos.getX(), interClassPos.getY());

        ((GeneralPath)shape).lineTo(interClassPos.getX() + interClassSize.getWidth(), interClassPos.getY());

        ((GeneralPath)shape).lineTo(interClassPos.getX() + interClassSize.getWidth(), interClassPos.getY() + interClassSize.getHeight());

        ((GeneralPath)shape).lineTo(interClassPos.getX(), interClassPos.getY() + interClassSize.getHeight());

        ((GeneralPath)shape).lineTo(interClassPos.getX(), interClassPos.getY());

        ((GeneralPath)shape).closePath();
    }

    @Override
    public void paint(Graphics2D g) {
        g.draw(shape);
    }

    @Override
    public boolean elementAt(Point2D point) {
        Point2D interClassPos = ((InterClass)diagramElement).getPosition();
        Dimension2D interClassSize = ((InterClass)diagramElement).getSize();

        if(point.getX() >= interClassPos.getX() && point.getY() >= interClassPos.getY() && point.getX() <= interClassPos.getX()+interClassSize.getWidth() && point.getY() <= interClassPos.getY() + interClassSize.getHeight())
            return true;

        return false;
    }
}
