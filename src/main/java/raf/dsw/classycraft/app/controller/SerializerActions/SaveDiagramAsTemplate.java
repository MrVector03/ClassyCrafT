package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveDiagramAsTemplate extends AbstractClassyAction {
    public SaveDiagramAsTemplate() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/saveDiagramAsTemplate.png"));
        putValue(NAME, "Save Diagram as template");
        putValue(SHORT_DESCRIPTION, "Save selected diagram as template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(!(selected.getClassyNode() instanceof Diagram)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT_CREATE_TEMPLATE_FOR_NON_DIAGRAM", MessageType.ERROR);
            return;
        }

        MainFrame.getInstance().getNewTemplateFrame().setVisible(true);
    }
}
