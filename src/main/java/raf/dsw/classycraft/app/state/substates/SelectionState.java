package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.TemporarySelectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SelectionState implements State {
    private boolean dragMode = false;
    private List<DiagramElementPainter> dragElements = new ArrayList<>();

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position) && !dragElements.contains(dep))
                dragElements.add(dep);
        }
        System.out.println("Selected elements: " + dragElements.size());
        dragMode = !dragElements.isEmpty();
        if (dragMode) return;

        startingPoint = position;
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        if (dragMode) {
            List<DiagramElementPainter> newDragElements = new ArrayList<>();
            diagramView.popTemporaryInterClassPainters(dragElements);
            for (DiagramElementPainter dep : dragElements) {
                InterClass tmp = ((InterClassPainter) dep).getInterClass();

                // Point2D newPoint = new Point2D.Double(3 * tmp.getPosition().getX() - startingPosition.getX(),
                //         3 * startingPosition.getY() - tmp.getPosition().getY());

                InterClass newTmpInterClass = new InterClass(tmp.getName(), tmp.getAccess(),
                        startingPosition, tmp.getSize()) {
                    @Override
                    public Access getAccess() {
                        return super.getAccess();
                    }

                    @Override
                    public Point2D getPosition() {
                        return super.getPosition();
                    }

                    @Override
                    public Dimension getSize() {
                        return super.getSize();
                    }
                };
                InterClassPainter newPainter = new InterClassPainter(newTmpInterClass);
                newDragElements.add(newPainter);
                diagramView.addDiagramElementPainter(newPainter);
            }
            dragElements = newDragElements;
        } else {
            endingPoint = startingPosition;
            diagramView.popTemporarySelectionPainter();
            TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
            diagramView.addDiagramElementPainter(selectionPainter);
        }
    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        if (dragMode) {
            diagramView.popTemporaryInterClassPainters(dragElements);
            for (DiagramElementPainter dep : dragElements) {
                InterClass tmp = ((InterClassPainter) dep).getInterClass();

                // Point2D newPoint = new Point2D.Double(3 * tmp.getPosition().getX() - endingPosition.getX(),
                //         3 * endingPosition.getY() - tmp.getPosition().getY());

                InterClass newTmpInterClass = new InterClass(tmp.getName(), tmp.getAccess(),
                        endingPosition, tmp.getSize()) {
                    @Override
                    public Access getAccess() {
                        return super.getAccess();
                    }

                    @Override
                    public Point2D getPosition() {
                        return super.getPosition();
                    }

                    @Override
                    public Dimension getSize() {
                        return super.getSize();
                    }
                };
                InterClassPainter newPainter = new InterClassPainter(newTmpInterClass);
                diagramView.addDiagramElementPainter(newPainter);
            }
            dragElements.clear();
            dragMode = false;
        } else {
            endingPoint = endingPosition;
            diagramView.popTemporarySelectionPainter();
            TemporarySelectionPainter selectionPainter = new TemporarySelectionPainter(startingPoint, endingPoint);
            diagramView.addDiagramElementPainter(selectionPainter);
        }
        startingPoint = null;
        endingPoint = null;
    }
}
