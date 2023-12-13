package raf.dsw.classycraft.app.controller.tree;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ChangeAuthorConfirmAction extends AbstractClassyAction {

    public ChangeAuthorConfirmAction() {
        putValue(NAME, "Confirm author name");
        putValue(SHORT_DESCRIPTION, "Confirm the new name of the author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        String newAuthor = MainFrame.getInstance().getCaFrame().getCaTextField().getText();

        MainFrame.getInstance().getCaFrame().setVisible(false);
        MainFrame.getInstance().getCaFrame().getCaTextField().setText("");

        if(newAuthor.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("AUTHOR_NAME_CANNOT_BE_EMPTY", MessageType.ERROR);
            return;
        }

        ((Project)selected.getClassyNode()).setAuthor(newAuthor);
    }
}
