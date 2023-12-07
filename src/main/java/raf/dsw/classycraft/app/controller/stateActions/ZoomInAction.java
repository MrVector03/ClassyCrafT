package raf.dsw.classycraft.app.controller.stateActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractClassyAction {
    public ZoomInAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoomin.png"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startZoomInState();
    }
}
