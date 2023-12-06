package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.TemporarySelectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State {

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        diagramView.popTemporarySelectionPainter();
        startingPoint = position;
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        endingPoint = startingPosition;
        Point2D change = new Point2D.Double(endingPoint.getX() - startingPoint.getX(), endingPoint.getY() - startingPoint.getY());
        diagramView.moveMode(change);
        startingPoint = endingPoint;
    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        endingPoint = endingPosition;
        Point2D change = new Point2D.Double(endingPoint.getX() - startingPoint.getX(), endingPoint.getY() - startingPoint.getY());
        diagramView.moveMode(change);
        startingPoint = null;
        endingPoint = null;
    }
}
