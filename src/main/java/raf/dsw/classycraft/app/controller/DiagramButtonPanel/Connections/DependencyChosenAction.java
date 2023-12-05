package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DependencyChosenAction extends AbstractClassyAction {
    public DependencyChosenAction() {
        putValue(NAME, "Dependency");
        putValue(SHORT_DESCRIPTION, "Add a dependency connection to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getChooseConnectionFrame().setVisible(false);
        MainFrame.getInstance().getEditDependencyFrame().setVisible(true);
    }
}
