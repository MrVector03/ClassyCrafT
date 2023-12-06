package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ConnectionPainter extends DiagramElementPainter {
    private Point2D toPointMin = null;
    private Point2D fromPointMin = null;

    // private Connection connection = (Connection)diagramElement;

    public ConnectionPainter(Connection connection) {

        // InterClass to = ((Connection)diagramElement).getTo();
        // InterClass from = ((Connection)diagramElement).getFrom();

        InterClass to = connection.getTo();
        InterClass from = connection.getFrom();

        diagramElement = connection;

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
        //g.drawLine((int)fromPointMin.getX(), (int)fromPointMin.getY(), (int)toPointMin.getX(), (int)toPointMin.getY());

        if(diagramElement instanceof Generalization) {
            double triangleA = 20;

            g.draw(drawTriangle(new Point2D.Double((double)toPointMin.getX(), (double)toPointMin.getY()), triangleA));

            g.drawLine((int)fromPointMin.getX(), (int)fromPointMin.getY(), (int)getC(triangleA).getX(), (int)getC(triangleA).getY());
        }
    }

    private Point2D getC(double a) {
        double x1 = fromPointMin.getX(), y1 = fromPointMin.getY(), x2 = toPointMin.getX(), y2 = toPointMin.getY();
        double h = a * Math.sqrt(3) / 2;
        double AB = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        double xC = (x1 + ((AB-h)/h) * x2) / (1 + (AB-h)/h);
        double yC = (y1 + ((AB-h)/h) * y2) / (1 + (AB-h)/h);

        return new Point2D.Double(xC, yC);
    }

    private Shape drawTriangle(Point2D begPoint, double a) {
        Shape shape = new GeneralPath();

        ((GeneralPath)shape).moveTo(begPoint.getX(), begPoint.getY());

        double x1 = fromPointMin.getX(), y1 = fromPointMin.getY(), x2 = toPointMin.getX(), y2 = toPointMin.getY();

        double xC = getC(a).getX();
        double yC = getC(a).getY();

        double k = (y2 - yC) / (x2 - xC);
        double kn = (xC - x2) / (y2 - yC);
        double kl = (3 * k - Math.sqrt(3)) / (Math.sqrt(3) * k + 3);

        double n = yC - kn * xC;
        double nl = y2 - kl * x2;
        double t = kn / kl;

        double newY1 = (n - t * nl) / (1 - t);
        double newX1 = (newY1 - nl) / kl;

        ((GeneralPath)shape).lineTo(newX1, newY1);

        kl = (-3 * k - Math.sqrt(3)) / (Math.sqrt(3) * k - 3);

        nl = y2 - kl * x2;
        t = kn / kl;

        double newY2 = (n - t * nl) / (1 - t);
        double newX2 = (newY2 - nl) / kl;

        ((GeneralPath)shape).lineTo(newX2, newY2);

        ((GeneralPath)shape).lineTo(begPoint.getX(), begPoint.getY());

        ((GeneralPath) shape).closePath();

        return shape;
    }

    @Override
    public boolean elementAt(Point2D point) {
        return false;
    }

    public Point2D getToPointMin() {
        return toPointMin;
    }

    public void setToPointMin(Point2D toPointMin) {
        this.toPointMin = toPointMin;
    }

    public Point2D getFromPointMin() {
        return fromPointMin;
    }

    public void setFromPointMin(Point2D fromPointMin) {
        this.fromPointMin = fromPointMin;
    }
}
