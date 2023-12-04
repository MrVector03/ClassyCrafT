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

public class SelectionState implements State {

    private boolean dragMode = false;
    private List<DiagramElementPainter> dragElements = new ArrayList<>();

    private Point2D startingPoint = null;
    private Point2D endingPoint = null;

    private Point2D holdDiff = null;

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        diagramView.getSelectedElements().clear();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position) && !dragElements.contains(dep)) {
                dragElements.add(dep);
                // holdDiff = new Point2D.Double(((InterClassPainter) dep).getInterClass().getPosition().getX() - position.getX(),
                //         ((InterClassPainter) dep).getInterClass().getPosition().getY() - position.getY());

                // System.out.println("Class: " + ((InterClassPainter) dep).getInterClass().getPosition());
                // System.out.println("Pointer: " + position);
                // System.out.println("HOLD: " + holdDiff);

                diagramView.selectElement(dep);
            }
        }
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
                // ((Class) tmp).rewriteContents(((Class) ((InterClassPainter) dep).getInterClass()).getClassContents());

                // Point2D newPoint = new Point2D.Double(startingPosition.getX() + holdDiff.getX(),
                //        startingPosition.getX() + holdDiff.getY());
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
                diagramView.selectElement(newPainter);
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

                // Point2D newPoint = new Point2D.Double(endingPosition.getX() + holdDiff.getX(),
                //         endingPosition.getX() + holdDiff.getY());

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
                diagramView.selectElement(newPainter);
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

    private void boldSelectedElements(DiagramView diagramView) {

    }
}
