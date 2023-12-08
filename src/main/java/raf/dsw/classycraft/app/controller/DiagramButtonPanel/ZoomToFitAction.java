package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomToFitAction extends AbstractClassyAction {
    public ZoomToFitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoomToFit.png"));
        putValue(NAME, "Zoom to fit");
        putValue(SHORT_DESCRIPTION, "Zoom to fit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
