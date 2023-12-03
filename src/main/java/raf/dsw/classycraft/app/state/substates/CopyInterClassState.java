package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CopyInterClassState implements State {
    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        List<DiagramElementPainter> toCopy = new ArrayList<>();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position))
                toCopy.add(dep);
        }
        for (DiagramElementPainter dep : toCopy)
            copyPaint(dep, diagramView);
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {

    }

    private void copyPaint(DiagramElementPainter elementPainter, DiagramView diagramView) {
        InterClass tmpInterClass = ((InterClassPainter) elementPainter).getInterClass();
        Point newPosition = new Point();

        newPosition.setLocation(tmpInterClass.getPosition().getX() + tmpInterClass.getSize().getWidth() / 2,
                tmpInterClass.getPosition().getY() + tmpInterClass.getSize().getHeight() / 2);

        InterClassPainter copyElement = new InterClassPainter(new InterClass(
                tmpInterClass.getName(),
                tmpInterClass.getAccess(),
                newPosition,
                tmpInterClass.getSize()
        ) {
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
        });
        diagramView.addDiagramElementPainter(copyElement);
    }
}
