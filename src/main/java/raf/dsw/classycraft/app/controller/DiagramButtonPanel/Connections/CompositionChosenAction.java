package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class CompositionChosenAction extends AbstractClassyAction {
    public CompositionChosenAction() {
        putValue(NAME, "Composition");
        putValue(SHORT_DESCRIPTION, "Add a composition connection to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getEditCompositionFrame().getNameTextField().setText("");
        MainFrame.getInstance().getEditCompositionFrame().getVarNameTextField().setText("");
        MainFrame.getInstance().getEditAggregationFrame().getCardCmb().setSelectedIndex(0);

        MainFrame.getInstance().getChooseConnectionFrame().setVisible(false);
        MainFrame.getInstance().getEditCompositionFrame().setVisible(true);
    }
}
