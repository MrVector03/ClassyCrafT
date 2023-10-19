package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.AboutUsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractClassyAction {
    public AboutUsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsFrame aboutUs = new AboutUsFrame();
        aboutUs.setVisible(true);
    }
}