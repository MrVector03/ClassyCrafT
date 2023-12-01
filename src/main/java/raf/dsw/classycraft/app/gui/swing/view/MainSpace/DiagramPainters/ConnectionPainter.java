package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ConnectionPainter extends DiagramElementPainter {
    private Point2D toPointMin = null;
    private Point2D fromPointMin = null;
    public ConnectionPainter() {
        InterClass to = ((Connection)diagramElement).getTo();
        InterClass from = ((Connection)diagramElement).getFrom();

        ArrayList<Point2D> toPoints = new ArrayList<Point2D>();
        toPoints.add(new Point2D.Double(to.getPosition().getX() + to.getSize().getWidth()/2, to.getPosition().getY()));
        toPoints.add(new Point2D.Double(to.getPosition().getX() + to.getSize().getWidth()/2, to.getPosition().getY() + to.getSize().getHeight()));
        toPoints.add(new Point2D.Double(to.getPosition().getX(), to.getPosition().getY() + to.getSize().getHeight()/2));
        toPoints.add(new Point2D.Double(to.getPosition().getX() + to.getSize().getWidth(), to.getPosition().getY() + to.getSize().getHeight()/2));

        ArrayList<Point2D> fromPoints = new ArrayList<Point2D>();
        fromPoints.add(new Point2D.Double(from.getPosition().getX() + from.getSize().getWidth()/2, from.getPosition().getY()));
        fromPoints.add(new Point2D.Double(from.getPosition().getX() + from.getSize().getWidth()/2, from.getPosition().getY() + from.getSize().getHeight()));
        fromPoints.add(new Point2D.Double(from.getPosition().getX(), from.getPosition().getY() + from.getSize().getHeight()/2));
        fromPoints.add(new Point2D.Double(from.getPosition().getX() + from.getSize().getWidth(), from.getPosition().getY() + from.getSize().getHeight()/2));

        double minDist = Double.MAX_VALUE;

        for(Point2D toPoint : toPoints) {
            for (Point2D fromPoint : fromPoints) {
                double dist = Math.sqrt(Math.pow(Math.abs(fromPoint.getX() - toPoint.getX()), 2) + Math.pow(Math.abs(fromPoint.getY() - toPoint.getY()), 2));
                if (dist <= minDist) {
                    minDist = dist;
                    toPointMin = toPoint;
                    fromPointMin = fromPoint;
                }
            }
        }

        //iskoristiti toMin i fromMin za crtanje linija

    }

    @Override
    public void paint(Graphics2D g) {
        g.drawLine((int)fromPointMin.getX(), (int)fromPointMin.getY(), (int)toPointMin.getX(), (int)toPointMin.getY());
    }

    @Override
    public boolean elementAt(Point2D point) {
        return false;
    }
}
