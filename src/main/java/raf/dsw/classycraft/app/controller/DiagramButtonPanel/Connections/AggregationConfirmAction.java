package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.command.commands.connections.AddAggregationCommand;
import raf.dsw.classycraft.app.command.commands.connections.EditAggregationCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.state.substates.AddConnectionState;
import raf.dsw.classycraft.app.state.substates.AddInterClassState;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class AggregationConfirmAction extends AbstractClassyAction {
    public AggregationConfirmAction() {
        putValue(NAME, "Confirm");
        putValue(SHORT_DESCRIPTION, "Confirm aggregation changes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newConName = MainFrame.getInstance().getEditAggregationFrame().getNameTextField().getText();
        String newVarName = MainFrame.getInstance().getEditAggregationFrame().getVarNameTextField().getText();

        char newCardFrom = ((String)MainFrame.getInstance().getEditAggregationFrame().getCardCmb().getSelectedItem()).charAt(0);
        char newCardTo = ((String)MainFrame.getInstance().getEditAggregationFrame().getCardCmb().getSelectedItem()).charAt(4);

        InterClass newFrom = MainFrame.getInstance().getCurFrom();
        InterClass newTo = MainFrame.getInstance().getCurTo();

        MainFrame.getInstance().getEditAggregationFrame().setVisible(false);


        if (MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Aggregation curEditAggregation = ((Aggregation) MainFrame.getInstance().getPackageView().getCurEditElement());
            EditAggregationCommand editAggregationCommand = new EditAggregationCommand(curEditAggregation.getName(), curEditAggregation.getVarName(), curEditAggregation.getCardFrom(), curEditAggregation.getCardTo(), newConName, newVarName, newCardFrom, newCardTo, curEditAggregation, MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(editAggregationCommand);

            ((EditState) MainFrame.getInstance().getPackageView().getCurrentState()).notifySubscribers(new StateNotification(MainFrame.getInstance().getCurDiagramView()));
        } else {
            AddAggregationCommand addAggregationCommand = new AddAggregationCommand(newConName, newVarName, newCardFrom, newCardTo, newFrom, newTo, MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(addAggregationCommand);
        }
    }
}
