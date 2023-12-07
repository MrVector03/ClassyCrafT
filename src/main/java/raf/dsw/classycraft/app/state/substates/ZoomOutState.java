package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

public class ZoomOutState implements State {
    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        diagramView.setZoomPoint(position);
        diagramView.zoomOut();
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
        diagramView.setZoomPoint(position);
        if (e.getWheelRotation() == -1)
            diagramView.zoomIn();
        else if (e.getWheelRotation() == 1)
            diagramView.zoomOut();
    }
}
