package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DeleteState implements State {
    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        ArrayList<DiagramElementPainter> toDelete = new ArrayList<DiagramElementPainter>();
        int a = 5;
        Rectangle2D rectangle = new Rectangle2D.Double(position.getX()-5, position.getY()-5, a*2, a*2);

        for(DiagramElementPainter diagramElementPainter : diagramView.getDiagramElementPainters()) {
            if (diagramElementPainter.elementAt(position))
                toDelete.add(diagramElementPainter);
            if(diagramElementPainter instanceof ConnectionPainter && ((ConnectionPainter) diagramElementPainter).elementAtShape(rectangle))
                toDelete.add(diagramElementPainter);
        }

        diagramView.getDiagramElementPainters().removeAll(toDelete);

        for(DiagramElementPainter dep : toDelete) {
            diagramView.getDiagram().deleteChild(dep.getDiagramElement());
            ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(dep.getDiagramElement());
        }
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseWheelMoved(Point2D position, DiagramView diagramView, MouseWheelEvent e) {

    }
}
