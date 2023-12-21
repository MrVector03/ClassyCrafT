package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveProjectAsAction extends AbstractClassyAction {
    public SaveProjectAsAction() {
        // setEnabled(false);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/saveProjectAs.png"));
        putValue(NAME, "Save Project as");
        putValue(SHORT_DESCRIPTION, "Save selected project as...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
