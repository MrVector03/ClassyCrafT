package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.TemporarySelectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SelectionState implements State {

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        diagramView.getSelectedElements().clear();
        startingPoint = position;
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position))
                diagramView.selectElement(dep);
        }
        diagramView.markSelectedElements();
    }

    @SuppressWarnings("ALL")
    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {


        endingPoint = startingPosition;

        //if (startingPoint.getX() > endingPoint.getX() && startingPoint.getY() > endingPoint.getY()) {
        //    Point2D tmp = startingPoint;
        //    startingPoint = endingPoint;
        //    endingPoint = tmp;
        //} else if (startingPoint.getX() > endingPoint.getX()) {
        //    Point2D newStart = new Point2D.Double(endingPoint.getX(), startingPoint.getY());
        //    Point2D newEnd = new Point2D.Double(startingPoint.getX(), endingPoint.getY());
        //    startingPoint = newStart;
        //    endingPoint = newEnd;
        //} else if (startingPoint.getY() > endingPoint.getY()) {
        //    Point2D newStart = new Point2D.Double(startingPoint.getX(), endingPoint.getY());
        //    Point2D newEnd = new Point2D.Double(endingPoint.getX(), startingPoint.getY());
        //    startingPoint = newStart;
        //    endingPoint = newEnd;
        //}

        diagramView.removeAllSelectionPainters();

        TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof InterClassPainter) {
                    Point2D point = ((InterClassPainter) dep).getInterClass().getPosition();
                    Dimension size = ((InterClassPainter) dep).getInterClass().getSize();
                    Point2D endPoint = new Point2D.Double(point.getX() + size.getWidth(), point.getY() + size.getHeight());
                    if (dep.elementAt(startingPosition)
                            || testIntersectionLines(startingPoint, endingPoint, point, endPoint, (InterClassPainter) dep)
                            || selectionPainter.elementAt(point)
                            || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY()))
                            || selectionPainter.elementAt(new Point2D.Double(point.getX(), point.getY() + size.height))
                            || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY() + size.height)))
                        diagramView.selectElement(dep);
                    else {
                        diagramView.unselectElement(dep);
                    }
            } else if (dep instanceof ConnectionPainter && ((selectionPainter.elementAt(((ConnectionPainter) dep).getFromPointMin()))
                    || selectionPainter.elementAt(((ConnectionPainter) dep).getToPointMin())))
                diagramView.selectElement(dep);
            else
                diagramView.unselectElement(dep);

        }
        diagramView.markSelectedElements();
        diagramView.addDiagramElementPainter(selectionPainter);
    }

    @SuppressWarnings("ALL")
    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        endingPoint = endingPosition;
        diagramView.removeAllSelectionPainters();
        TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof InterClassPainter) {
                Point2D point = ((InterClassPainter) dep).getInterClass().getPosition();
                Dimension size = ((InterClassPainter) dep).getInterClass().getSize();
                Point2D endPoint = new Point2D.Double(point.getX() + size.getWidth(), point.getY() + size.getHeight());
                if (dep.elementAt(endingPosition)
                        || testIntersectionLines(startingPoint, endingPoint, point, endPoint, (InterClassPainter) dep)
                        || selectionPainter.elementAt(point)
                        || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY()))
                        || selectionPainter.elementAt(new Point2D.Double(point.getX(), point.getY() + size.height))
                        || selectionPainter.elementAt(new Point2D.Double(point.getX() + size.width, point.getY() + size.height)))
                    diagramView.selectElement(dep);
                else {
                    diagramView.unselectElement(dep);
                }
            } else if (dep instanceof ConnectionPainter && ((selectionPainter.elementAt(((ConnectionPainter) dep).getFromPointMin()))
                    || selectionPainter.elementAt(((ConnectionPainter) dep).getToPointMin())))
                diagramView.selectElement(dep);
            else
                diagramView.unselectElement(dep);

        }
        diagramView.markSelectedElements();
        diagramView.addDiagramElementPainter(selectionPainter);

        startingPoint = null;
        endingPoint = null;
        diagramView.removeAllSelectionPainters();
    }

    public boolean testIntersectionLines(Point2D selStart, Point2D selEnd, Point2D elStart, Point2D elEnd, InterClassPainter icp) {

        if (selStart.getX() > selEnd.getX() && selStart.getY() > selEnd.getY()) {
            Point2D tmp = selStart;
            selStart = selEnd;
            selEnd = tmp;
            System.out.println("top left");
        } else if (selStart.getX() > selEnd.getX()) {
            System.out.println("bottom left");
            Point2D newStart = new Point2D.Double(selEnd.getX(), selStart.getY());
            Point2D newEnd = new Point2D.Double(selStart.getX(), selEnd.getY());
            selStart = newStart;
            selEnd = newEnd;
        } else if (selStart.getY() > selEnd.getY()) {
            System.out.println("top right");
            Point2D newStart = new Point2D.Double(selStart.getX(), selEnd.getY());
            Point2D newEnd = new Point2D.Double(selEnd.getX(), selStart.getY());
            selStart = newStart;
            selEnd = newEnd;
        } else {
            System.out.println("default");
        }

        if ((selStart.getX() >= elStart.getX() && selStart.getX() <= elEnd.getX()) &&
                (selEnd.getX() >= elStart.getX() && selEnd.getX() <= elEnd.getX()) &&
                (elStart.getY() >= selStart.getY() && elStart.getY() <= selEnd.getY()) &&
                (elEnd.getY() >= selStart.getY() && elEnd.getY() <= selEnd.getY())) {
            double m1 = (selEnd.getY() - selStart.getY()) / (selEnd.getX() - selStart.getX());
            double b1 = selStart.getY() - m1 * selStart.getX();

            double m2 = (elEnd.getY() - elStart.getY()) / (elEnd.getX() - elStart.getX());
            double b2 = elStart.getY() - m2 * elStart.getX();

            if (m1 == m2) return false;

            double testX = (b2 - b1) / (m1 - m2);
            double testY = m1 * testX + b1;

            Point2D testPoint = new Point2D.Double(testX, testY);

            return icp.elementAt(testPoint);
        }
        return false;
    }
}
