package raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;

import java.awt.event.ActionEvent;

public class GeneralizationChosenAction extends AbstractClassyAction {
    public GeneralizationChosenAction() {
        putValue(NAME, "Generalization");
        putValue(SHORT_DESCRIPTION, "Add a generalization connection to the diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getEditGeneralizationFrame().getNameTextField().setText("");

        MainFrame.getInstance().getChooseConnectionFrame().setVisible(false);
        MainFrame.getInstance().getEditGeneralizationFrame().setVisible(true);
    }
}
