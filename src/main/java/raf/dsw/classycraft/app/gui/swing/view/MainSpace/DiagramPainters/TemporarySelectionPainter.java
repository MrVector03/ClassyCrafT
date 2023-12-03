package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class TemporarySelectionPainter extends DiagramElementPainter{
    protected Shape shape;

    public TemporarySelectionPainter(Point2D startPoint, Point2D endPoint) {

        shape = new GeneralPath();

        Dimension2D interClassSize = new Dimension((int) Math.abs(startPoint.getX() - endPoint.getX()),
                (int) Math.abs(startPoint.getY() - endPoint.getY()));

        ((GeneralPath)shape).moveTo(startPoint.getX(), startPoint.getY());

        ((GeneralPath)shape).lineTo(startPoint.getX() + interClassSize.getWidth(), startPoint.getY());

        ((GeneralPath)shape).lineTo(startPoint.getX() + interClassSize.getWidth(), startPoint.getY() + interClassSize.getHeight());

        ((GeneralPath)shape).lineTo(startPoint.getX(), startPoint.getY() + interClassSize.getHeight());

        ((GeneralPath)shape).lineTo(startPoint.getX(), startPoint.getY());

        ((GeneralPath)shape).closePath();
    }

    @Override
    public void paint(Graphics2D g) {
        g.draw(shape);
    }

    @Override
    public boolean elementAt(Point2D point) {
        // Point2D interClassPos = ((InterClass)diagramElement).getPosition();
        // Dimension2D interClassSize = ((InterClass)diagramElement).getSize();
//
        // if(point.getX() >= interClassPos.getX() && point.getY() >= interClassPos.getY() && point.getX() <= interClassPos.getX()+interClassSize.getWidth() && point.getY() <= interClassPos.getY() + interClassSize.getHeight())
        //     return true;
//
        return false;
    }
}
