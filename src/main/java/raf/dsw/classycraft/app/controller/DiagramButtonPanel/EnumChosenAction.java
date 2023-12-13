package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class EnumChosenAction extends AbstractClassyAction {
    public EnumChosenAction() {
        putValue(NAME, "Enum");
        putValue(SHORT_DESCRIPTION, "Add an enum to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getEditEnumFrame().getEicComboBox().setSelectedIndex(0);
        MainFrame.getInstance().getEditEnumFrame().getEicTextArea().setText("");
        MainFrame.getInstance().getEditEnumFrame().getEicTextField().setText("");

        MainFrame.getInstance().getChooseInterClassFrame().setVisible(false);
        MainFrame.getInstance().getEditEnumFrame().setVisible(true);
    }
}
