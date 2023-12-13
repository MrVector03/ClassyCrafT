package raf.dsw.classycraft.app.gui.swing.view.MainSpace.listeners;

import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class ClassyMouseListener extends MouseAdapter {
    private DiagramView diagramView;

    public ClassyMouseListener(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        diagramView.getTabbedPane().getClassyPackage().getPackageView().getCurrentState()
                .classyMouseClicked(fixPositionForZoom(e.getPoint()), diagramView);
        diagramView.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        diagramView.getTabbedPane().getClassyPackage().getPackageView().getCurrentState()
                .classyMousePressed(fixPositionForZoom(e.getPoint()), diagramView);
        diagramView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        diagramView.getTabbedPane().getClassyPackage().getPackageView().getCurrentState()
                .classyMouseReleased(fixPositionForZoom(e.getPoint()), diagramView);
        diagramView.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private Point2D fixPositionForZoom(Point2D pos) {
        double myXLocationWithoutZoom = pos.getX()*(1/ diagramView.getZoom());
        double myYLocationWithoutZoom = pos.getY()*(1/ diagramView.getZoom());
        Point2D newPosition = new Point2D.Double(myXLocationWithoutZoom, myYLocationWithoutZoom);

        return newPosition;
    }
}
