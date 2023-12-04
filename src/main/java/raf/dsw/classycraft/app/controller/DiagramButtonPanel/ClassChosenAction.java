package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ClassChosenAction extends AbstractClassyAction {
    public ClassChosenAction() {
        putValue(NAME, "Class");
        putValue(SHORT_DESCRIPTION, "Add a class to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getChooseInterClassFrame().setVisible(false);
        MainFrame.getInstance().getEditClassFrame().setVisible(true);
    }
}
