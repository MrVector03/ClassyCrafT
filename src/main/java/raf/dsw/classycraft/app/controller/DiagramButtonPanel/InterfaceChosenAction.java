package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class InterfaceChosenAction extends AbstractClassyAction {
    public InterfaceChosenAction() {
        putValue(NAME, "Interface");
        putValue(SHORT_DESCRIPTION, "Add an interface to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getEditInterfaceFrame().getEicComboBox().setSelectedIndex(0);
        MainFrame.getInstance().getEditInterfaceFrame().getEicTextArea().setText("");
        MainFrame.getInstance().getEditInterfaceFrame().getEicTextField().setText("");

        MainFrame.getInstance().getChooseInterClassFrame().setVisible(false);
        MainFrame.getInstance().getEditInterfaceFrame().setVisible(true);
    }
}
