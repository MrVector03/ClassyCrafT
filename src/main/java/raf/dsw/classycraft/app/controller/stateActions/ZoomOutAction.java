package raf.dsw.classycraft.app.controller.stateActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomOutAction extends AbstractClassyAction {
    public ZoomOutAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoomout.png"));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startZoomOutState();
    }
}
