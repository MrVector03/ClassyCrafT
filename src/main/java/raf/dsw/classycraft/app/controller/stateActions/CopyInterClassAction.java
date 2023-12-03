package raf.dsw.classycraft.app.controller.stateActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CopyInterClassAction extends AbstractClassyAction {
    public CopyInterClassAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/copy.png"));
        putValue(NAME, "Copy InterClass");
        putValue(SHORT_DESCRIPTION, "Copy InterClass");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startCopyInterClassState();
    }
}
