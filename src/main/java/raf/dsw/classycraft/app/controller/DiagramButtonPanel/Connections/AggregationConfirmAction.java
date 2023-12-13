package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.event.ActionEvent;

public class AggregationConfirmAction extends AbstractClassyAction {
    public AggregationConfirmAction() {
        putValue(NAME, "Confirm");
        putValue(SHORT_DESCRIPTION, "Confirm aggregation changes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newConName = MainFrame.getInstance().getEditAggregationFrame().getNameTextField().getText();
        String newVarName = MainFrame.getInstance().getEditAggregationFrame().getVarNameTextField().getText();

        char newCardFrom = MainFrame.getInstance().getEditAggregationFrame().getCardFromTextField().getText().charAt(0);
        char newCardTo = MainFrame.getInstance().getEditAggregationFrame().getCardToTextField().getText().charAt(0);

        InterClass newFrom = MainFrame.getInstance().getCurFrom();
        InterClass newTo = MainFrame.getInstance().getCurTo();

        MainFrame.getInstance().getEditAggregationFrame().setVisible(false);

        if (MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Aggregation curEditAggregation = ((Aggregation) MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditAggregation.setName(newConName);
            curEditAggregation.setVarName(newVarName);
            curEditAggregation.setCardFrom(newCardFrom);
            curEditAggregation.setCardTo(newCardTo);
        } else
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new ConnectionPainter(new Aggregation(newConName, newFrom, newTo, newVarName, newCardFrom, newCardTo)));
    }
}
