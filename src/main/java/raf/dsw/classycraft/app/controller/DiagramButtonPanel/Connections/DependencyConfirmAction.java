package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.command.commands.connections.AddDependencyCommand;
import raf.dsw.classycraft.app.command.commands.connections.EditDependencyCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.state.substates.AddConnectionState;
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

        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Dependency curEditDependency = ((Dependency)MainFrame.getInstance().getPackageView().getCurEditElement());
            EditDependencyCommand editDependencyCommand = new EditDependencyCommand(curEditDependency.getName(), curEditDependency.getType(), newConName,newConType,curEditDependency,MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(editDependencyCommand);

            ((EditState) MainFrame.getInstance().getPackageView().getCurrentState()).notifySubscribers(new StateNotification(MainFrame.getInstance().getCurDiagramView()));
        }
        else {
            AddDependencyCommand addDependencyCommand = new AddDependencyCommand(newConName,newConType,newFrom,newTo,MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(addDependencyCommand);
        }
    }
}
