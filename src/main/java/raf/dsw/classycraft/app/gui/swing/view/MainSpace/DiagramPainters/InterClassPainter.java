package raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class InterClassPainter extends DiagramElementPainter {
    protected Shape shape;

    private InterClass interClass;

    public InterClassPainter(InterClass interClass) {
        diagramElement = interClass;
        this.interClass = interClass;
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
        g.drawString(interClass.getAccess().toString() + " " + interClass.getName(), (int)interClass.getPosition().getX()+5, (int)interClass.getPosition().getY()+15);

        int counter = 0;
        for(ClassContent cc : interClass.getClassContents()) {
            g.drawString(cc.toString(), (int)interClass.getPosition().getX()+5, (int)interClass.getPosition().getY()+40+counter*15);
            counter++;
        }
    }

    @Override
    public boolean elementAt(Point2D point) {
        Point2D interClassPos = ((InterClass)diagramElement).getPosition();
        Dimension2D interClassSize = ((InterClass)diagramElement).getSize();

        if(point.getX() >= interClassPos.getX() && point.getY() >= interClassPos.getY() && point.getX() <= interClassPos.getX()+interClassSize.getWidth() && point.getY() <= interClassPos.getY() + interClassSize.getHeight())
            return true;

        return false;
    }

    public boolean areaElementAt(Point2D point, Dimension dimension) {
        Point2D startingPoint = point;
        Point2D rightPoint = point;
        Point2D bottomPoint = point;
        Point2D bottomRightPoint = point;

        rightPoint.setLocation(point.getX() + dimension.getWidth(), point.getY());
        bottomPoint.setLocation(point.getX(), point.getY() - dimension.getHeight());
        bottomRightPoint.setLocation(point.getX() + dimension.getWidth(), point.getY() - dimension.getHeight());

        System.out.println(startingPoint);
        System.out.println(rightPoint);
        System.out.println(bottomPoint);
        System.out.println(bottomRightPoint);
        System.out.println();

        return elementAt(startingPoint) || elementAt(rightPoint) ||
                elementAt(bottomPoint) || elementAt(bottomRightPoint);
    }

    public Point2D enhancedElementAt(Point2D point, Dimension interClassDimension) {
        Point2D finalPoint = point;
        for (int area = 0; area < 9; area++) {
            if (!areaElementAt(alterPoint(point, interClassDimension, area), interClassDimension) && finalPoint == point) {
                finalPoint = alterPoint(point, interClassDimension, area);
                System.out.println("CHANGED ON ITER: " + area);
            }
        }

        if (finalPoint == point) {
            finalPoint.setLocation(10000, 10000);
            return finalPoint;
        }

        return finalPoint;
    }

    public Point2D alterPoint(Point2D point, Dimension dimension, int area) {
        point = switchToArea(point, dimension, area);
        //point.setLocation(point.getX() + dimension.getWidth() + 50,
        //        point.getY() + dimension.getHeight() + 50);
        return point;
    }

    // 0 = right, 1 = left, 2 = bottom right; 3 = bottom left
    // 4 = top; 5 = top left; 6 = top right; 7 = bottom
    public Point2D switchToArea(Point2D point, Dimension dimension, int area) {
        double xChange = point.getX();
        double yChange = point.getY();

        switch (area) {
            case 0:
                xChange = point.getX() + dimension.getWidth() + 50;
                yChange = point.getY();
                break;
            case 1:
                xChange = point.getX() - dimension.getWidth() - 50;
                yChange = point.getY();
                break;
            case 2:
                xChange = point.getX() + dimension.getWidth() + 50;
                yChange = point.getY() - dimension.getHeight() - 50;
                break;
            case 3:
                xChange = point.getX() - dimension.getWidth() - 50;
                yChange = point.getY()- dimension.getHeight() - 50;
                break;
            case 4:
                xChange = point.getX();
                yChange = point.getY() + dimension.getHeight() + 50;
                break;
            case 5:
                xChange = point.getX() - dimension.getWidth() - 50;
                yChange = point.getY() + dimension.getHeight() + 50;
                break;
            case 6:
                xChange = point.getX() + dimension.getWidth() + 50;
                yChange = point.getY() + dimension.getHeight() + 50;
                break;
            case 7:
                xChange = point.getX();
                yChange = point.getY() - dimension.getHeight() - 50;
                break;
        }
        point.setLocation(xChange, yChange);
        return point;
    }

    public InterClass getInterClass() {
        return interClass;
    }
}
