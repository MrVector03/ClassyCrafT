package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ConnectionPainter extends DiagramElementPainter {
    private Point2D toPointMin = null;
    private Point2D fromPointMin = null;

    //private Connection connection;
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

        //g.drawLine((int)fromPointMin.getX(), (int)fromPointMin.getY(), (int)toPointMin.getX(), (int)toPointMin.getY());
        g.setColor(super.diagramElement.getColor());
        g.setStroke(super.diagramElement.getStroke());

        if(diagramElement instanceof Generalization) {
            double triangleA = 20;

            g.drawLine((int)fromPointMin.getX(), (int)fromPointMin.getY(), (int)getC(triangleA, fromPointMin, toPointMin).getX(), (int)getC(triangleA, fromPointMin, toPointMin).getY());

            g.draw(drawTriangle(new Point2D.Double((double)toPointMin.getX(), (double)toPointMin.getY()), triangleA));
        }

        if(diagramElement instanceof Dependency) {
            double triangleA = 20;

            Graphics2D g2d = (Graphics2D) g.create();
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);

            g2d.drawLine((int)fromPointMin.getX(), (int)fromPointMin.getY(), (int)toPointMin.getX(), (int)toPointMin.getY());

            g2d.dispose();

            g.draw(drawArrow(fromPointMin, toPointMin, triangleA));
        }

        if(diagramElement instanceof Aggregation) {
            double triangleA = 20;

            g.drawLine((int)toPointMin.getX(), (int)toPointMin.getY(), (int)getC(triangleA, toPointMin,  getC(triangleA, toPointMin, fromPointMin)).getX(), (int)getC(triangleA, toPointMin, getC(triangleA, toPointMin, fromPointMin)).getY());

            g.draw(drawArrow(fromPointMin ,toPointMin, triangleA));
            g.draw(drawRhombus(toPointMin, fromPointMin, triangleA));
        }

        if(diagramElement instanceof Composition) {
            double triangleA = 20;

            g.drawLine((int)toPointMin.getX(), (int)toPointMin.getY(), (int)getC(triangleA, toPointMin,  getC(triangleA, toPointMin, fromPointMin)).getX(), (int)getC(triangleA, toPointMin, getC(triangleA, toPointMin, fromPointMin)).getY());

            g.draw(drawArrow(fromPointMin, toPointMin, triangleA));
            g.fill(drawRhombus(toPointMin ,fromPointMin, triangleA));
        }
    }

    //get the point on the "from" - "to" line that is h distanced from "to"
    private Point2D getC(double a, Point2D fromPoint, Point2D toPoint) {
        double x1 = fromPoint.getX(), y1 = fromPoint.getY(), x2 = toPoint.getX(), y2 = toPoint.getY();
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

        double xC = getC(a, fromPointMin, toPointMin).getX();
        double yC = getC(a, fromPointMin, toPointMin).getY();

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

    private Shape drawArrow(Point2D fromPoint,Point2D toPoint, double a) {
        Shape shape = new GeneralPath();

        ((GeneralPath)shape).moveTo(toPoint.getX(), toPoint.getY());

        double x1 = fromPoint.getX(), y1 = fromPoint.getY(), x2 = toPoint.getX(), y2 = toPoint.getY();

        double xC = getC(a, fromPoint, toPoint).getX();
        double yC = getC(a, fromPoint, toPoint).getY();

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

        ((GeneralPath) shape).moveTo(toPoint.getX(), toPoint.getY());

        ((GeneralPath)shape).lineTo(newX2, newY2);

        ((GeneralPath) shape).closePath();

        return shape;
    }

    private Shape drawRhombus(Point2D fromPoint,Point2D toPoint, double a) {
        Shape shape = new GeneralPath();

        ((GeneralPath)shape).moveTo(toPoint.getX(), toPoint.getY());

        double x1 = fromPoint.getX(), y1 = fromPoint.getY(), x2 = toPoint.getX(), y2 = toPoint.getY();

        double xC = getC(a, fromPoint, toPoint).getX();
        double yC = getC(a, fromPoint, toPoint).getY();

        double k = (y2 - yC) / (x2 - xC);
        double kn = (xC - x2) / (y2 - yC);
        double kl = (3 * k - Math.sqrt(3)) / (Math.sqrt(3) * k + 3);

        double n = yC - kn * xC;
        double nl = y2 - kl * x2;
        double t = kn / kl;

        double newY1 = (n - t * nl) / (1 - t);
        double newX1 = (newY1 - nl) / kl;

        kl = (-3 * k - Math.sqrt(3)) / (Math.sqrt(3) * k - 3);

        nl = y2 - kl * x2;
        t = kn / kl;

        double newY2 = (n - t * nl) / (1 - t);
        double newX2 = (newY2 - nl) / kl;

        ((GeneralPath)shape).lineTo(newX1, newY1);

        ((GeneralPath) shape).lineTo(getC(a, fromPoint, getC(a, fromPoint, toPoint)).getX(), getC(a, fromPoint, getC(a, fromPoint, toPoint)).getY());

        ((GeneralPath) shape).lineTo(newX2, newY2);

        ((GeneralPath) shape).lineTo(toPoint.getX(), toPoint.getY());

        ((GeneralPath) shape).closePath();

        return shape;
    }

    @Override
    public boolean elementAt(Point2D point) {
        Line2D line = new Line2D.Double(fromPointMin, toPointMin);

        return line.contains(point);
    }

    public boolean elementAtShape(Rectangle2D rectangle) {
        Line2D line = new Line2D.Double(fromPointMin, toPointMin);

        return line.intersects(rectangle);
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

    public Connection getConnection() {
        return (Connection) diagramElement;
    }

}
