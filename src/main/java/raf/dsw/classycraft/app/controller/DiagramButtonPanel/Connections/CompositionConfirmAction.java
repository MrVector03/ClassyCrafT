package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.command.commands.connections.AddAggregationCommand;
import raf.dsw.classycraft.app.command.commands.connections.AddCompositionCommand;
import raf.dsw.classycraft.app.command.commands.connections.EditCompositionCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.state.substates.AddConnectionState;
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

        char newCardFrom = ((String)MainFrame.getInstance().getEditCompositionFrame().getCardCmb().getSelectedItem()).charAt(0);
        char newCardTo = ((String)MainFrame.getInstance().getEditCompositionFrame().getCardCmb().getSelectedItem()).charAt(4);

        InterClass newFrom = MainFrame.getInstance().getCurFrom();
        InterClass newTo = MainFrame.getInstance().getCurTo();

        MainFrame.getInstance().getEditCompositionFrame().setVisible(false);

        if (MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Composition curEditComposition = ((Composition) MainFrame.getInstance().getPackageView().getCurEditElement());
            EditCompositionCommand editCompositionCommand = new EditCompositionCommand(curEditComposition.getName(), curEditComposition.getVarName(), curEditComposition.getCardFrom(), curEditComposition.getCardTo(), newConName, newVarName, newCardFrom, newCardTo, curEditComposition, MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(editCompositionCommand);

            ((EditState) MainFrame.getInstance().getPackageView().getCurrentState()).notifySubscribers(new StateNotification(MainFrame.getInstance().getCurDiagramView()));

        } else {
            AddCompositionCommand addCompositionCommand = new AddCompositionCommand(newConName, newVarName, newCardFrom, newCardTo, newFrom, newTo, MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(addCompositionCommand);
        }
    }
}
