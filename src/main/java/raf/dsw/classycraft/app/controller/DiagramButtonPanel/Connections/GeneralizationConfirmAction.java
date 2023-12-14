package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.state.substates.AddConnectionState;
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

        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Generalization curEditGeneralization = ((Generalization)MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditGeneralization.setName(newConName);

            ((EditState) MainFrame.getInstance().getPackageView().getCurrentState()).notifySubscribers(new StateNotification(MainFrame.getInstance().getCurDiagramView()));
        }
        else {
            ClassyAbstractFactory manufacturer = new ClassyManufacturer();
            Generalization newGeneralization = (Generalization) manufacturer.createConnection(ConnectionType.GENERALIZATION,
                    newConName, newFrom, newTo, null, ' ', ' ', null) ;
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(painterManufacturer.createPainter(newGeneralization));
        }
    }
}
