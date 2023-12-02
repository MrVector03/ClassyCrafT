package raf.dsw.classycraft.app.gui.swing.view.MainSpace.listeners;

import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class ClassyMouseListener extends MouseAdapter {
    private DiagramView diagramView;

    public ClassyMouseListener(DiagramView diagramView) {
        this.diagramView = diagramView;


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        diagramView.getTabbedPane().getClassyPackage().getPackageView().getCurrentState()
                .classyMousePressed(e.getPoint(), diagramView);
        diagramView.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        diagramView.getTabbedPane().getClassyPackage().getPackageView().getCurrentState()
                .classyMousePressed(e.getPoint(), diagramView);
        diagramView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        diagramView.getTabbedPane().getClassyPackage().getPackageView().getCurrentState()
                .classyMouseReleased(e.getPoint(), diagramView);
        diagramView.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
