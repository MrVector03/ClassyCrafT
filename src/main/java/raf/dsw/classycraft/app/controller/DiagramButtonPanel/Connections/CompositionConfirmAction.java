package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ConnectionType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
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

        ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();

        if (MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Composition curEditComposition = ((Composition) MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditComposition.setName(newConName);
            curEditComposition.setVarName(newVarName);
            curEditComposition.setCardFrom(newCardFrom);
            curEditComposition.setCardTo(newCardTo);
        } else {
            ClassyAbstractFactory manufacturer = new ClassyManufacturer();
            Composition newComposition = (Composition) manufacturer.createConnection(ConnectionType.COMPOSITION,
                    newConName, newFrom, newTo, newVarName, newCardFrom, newCardTo, null);
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(painterManufacturer.createPainter(newComposition));
        }
    }
}
