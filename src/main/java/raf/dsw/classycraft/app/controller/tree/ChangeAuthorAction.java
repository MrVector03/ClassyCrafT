package raf.dsw.classycraft.app.controller.tree;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class ChangeAuthorAction extends AbstractClassyAction {
    public ChangeAuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/changeAuthor.png"));
        putValue(NAME, "Change project author");
        putValue(SHORT_DESCRIPTION, "Change the author of a project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(!(selected.getClassyNode() instanceof Project))
        {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT_EDIT_AUTHOR_ON_NON_PROJECT", MessageType.ERROR);
            return;
        }

        MainFrame.getInstance().getCaFrame().setVisible(true);
    }
}
