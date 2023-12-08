package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.TabView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ZoomToFitAction extends AbstractClassyAction {
    public ZoomToFitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoomToFit.png"));
        putValue(NAME, "Zoom to fit");
        putValue(SHORT_DESCRIPTION, "Zoom to fit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView curDV = ((TabView)MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        Dimension2D viewSize = ((TabView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getLeftComponent().getSize();

        if(curDV.getDiagramElementPainters().isEmpty())
            return;
        InterClass firstIC = ((InterClassPainter)curDV.getDiagramElementPainters().get(0)).getInterClass();
        double maxX = firstIC.getSize().getWidth() + firstIC.getPosition().getX() , maxY =  firstIC.getSize().getHeight() + firstIC.getPosition().getY(), minX = firstIC.getPosition().getX(), minY = firstIC.getPosition().getY();

        for(DiagramElementPainter dep : curDV.getDiagramElementPainters()) {
            if(dep instanceof InterClassPainter) {
                InterClass depIC = ((InterClassPainter) dep).getInterClass();

                if(depIC.getPosition().getX() + depIC.getSize().getWidth() > maxX)
                    maxX = depIC.getPosition().getX() + depIC.getSize().getWidth();

                if(depIC.getPosition().getY() + depIC.getSize().getHeight() > maxY)
                    maxY = depIC.getPosition().getY() + depIC.getSize().getHeight();

                if(depIC.getPosition().getX() < minX)
                    minX = depIC.getPosition().getX();

                if(depIC.getPosition().getY() < minY)
                    minY = depIC.getPosition().getY();
            }
        }

        double newZoom = viewSize.getWidth()/(maxX-minX);
        if(newZoom > viewSize.getHeight()/(maxY-minY))
            newZoom = viewSize.getHeight()/(maxY-minY);

        curDV.setZoom(newZoom);

        ((JScrollPane)((TabView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getLeftComponent()).getHorizontalScrollBar().setValue((int)(minX*curDV.getZoom()));
        ((JScrollPane)((TabView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getLeftComponent()).getVerticalScrollBar().setValue((int)(minY*curDV.getZoom()));
    }
}
