package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.event.ActionEvent;

public class GeneralizationConfirmAction extends AbstractClassyAction {
    public GeneralizationConfirmAction() {
        putValue(NAME, "Confirm");
        putValue(SHORT_DESCRIPTION, "Confirm generalization changes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newConName = MainFrame.getInstance().getEditGeneralizationFrame().getNameTextField().getText();
        InterClass newFrom = MainFrame.getInstance().getCurFrom();
        InterClass newTo = MainFrame.getInstance().getCurTo();

        MainFrame.getInstance().getEditGeneralizationFrame().setVisible(false);

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Generalization curEditGeneralization = ((Generalization)MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditGeneralization.setName(newConName);
        }
        else
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new ConnectionPainter(new Generalization(newConName, newFrom, newTo)));
    }
}
