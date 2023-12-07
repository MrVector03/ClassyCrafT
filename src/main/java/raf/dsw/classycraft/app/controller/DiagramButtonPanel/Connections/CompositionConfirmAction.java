package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.event.ActionEvent;

public class CompositionConfirmAction extends AbstractClassyAction {
    public CompositionConfirmAction() {
        putValue(NAME, "Confirm");
        putValue(SHORT_DESCRIPTION, "Confirm composition changes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newConName = MainFrame.getInstance().getEditCompositionFrame().getNameTextField().getText();
        String newVarName = MainFrame.getInstance().getEditCompositionFrame().getVarNameTextField().getText();

        char newCardFrom = MainFrame.getInstance().getEditCompositionFrame().getCardFromTextField().getText().charAt(0);
        char newCardTo = MainFrame.getInstance().getEditCompositionFrame().getCardToTextField().getText().charAt(0);

        InterClass newFrom = MainFrame.getInstance().getCurFrom();
        InterClass newTo = MainFrame.getInstance().getCurTo();

        MainFrame.getInstance().getEditCompositionFrame().setVisible(false);

        if (MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Composition curEditComposition = ((Composition) MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditComposition.setName(newConName);
            curEditComposition.setVarName(newVarName);
            curEditComposition.setCardFrom(newCardFrom);
            curEditComposition.setCardTo(newCardTo);
        } else
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new ConnectionPainter(new Composition(newConName, newFrom, newTo, newVarName, newCardFrom, newCardTo)));
    }
}
