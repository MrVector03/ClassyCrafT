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
        diagramView.removeAllSelectionPainters();

        TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof InterClassPainter) {
                    Point2D point = ((InterClassPainter) dep).getInterClass().getPosition();
                    Dimension size = ((InterClassPainter) dep).getInterClass().getSize();
                    if (dep.elementAt(startingPosition) || selectionPainter.elementAt(point)
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
                if (dep.elementAt(endingPosition) || selectionPainter.elementAt(point)
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
}
