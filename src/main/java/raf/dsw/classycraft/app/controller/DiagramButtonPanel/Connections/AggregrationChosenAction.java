package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AggregrationChosenAction extends AbstractClassyAction {
    public AggregrationChosenAction() {
        putValue(NAME, "Aggregation");
        putValue(SHORT_DESCRIPTION, "Add an aggregation connection to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getEditAggregationFrame().getNameTextField().setText("");
        MainFrame.getInstance().getEditAggregationFrame().getVarNameTextField().setText("");
        MainFrame.getInstance().getEditAggregationFrame().getCardFromTextField().setText("");
        MainFrame.getInstance().getEditAggregationFrame().getCardToTextField().setText("");

        MainFrame.getInstance().getChooseConnectionFrame().setVisible(false);
        MainFrame.getInstance().getEditAggregationFrame().setVisible(true);
    }
}
