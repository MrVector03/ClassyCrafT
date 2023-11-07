package raf.dsw.classycraft.app.controller.tree;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddNodeAction extends AbstractClassyAction {

    public AddNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "Add node");
        putValue(SHORT_DESCRIPTION, "Add node to project tree");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(selected.getClassyNode() instanceof Package)
            MainFrame.getInstance().getPordFrame().setVisible(true);
        else
            MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
