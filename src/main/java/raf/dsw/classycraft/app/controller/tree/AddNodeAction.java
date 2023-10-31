package raf.dsw.classycraft.app.controller.tree;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.controller.ClassyTreeSelectionListener;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view.ClassyTreeView;
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
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
