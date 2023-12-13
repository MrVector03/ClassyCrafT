package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.event.ActionEvent;

public class DependencyConfirmAction extends AbstractClassyAction {
    public DependencyConfirmAction() {
        putValue(NAME, "Confirm");
        putValue(SHORT_DESCRIPTION, "Confirm dependency changes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newConName = MainFrame.getInstance().getEditDependencyFrame().getNameTextField().getText();
        String newConType = MainFrame.getInstance().getEditDependencyFrame().getTypeTextfield().getText();

        InterClass newFrom = MainFrame.getInstance().getCurFrom();
        InterClass newTo = MainFrame.getInstance().getCurTo();

        MainFrame.getInstance().getEditDependencyFrame().setVisible(false);

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Dependency curEditDependency = ((Dependency)MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditDependency.setName(newConName);
            curEditDependency.setType(newConType);
        }
        else
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new ConnectionPainter(new Dependency(newConName, newFrom, newTo, newConType)));
    }
}
