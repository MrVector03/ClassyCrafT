package raf.dsw.classycraft.app.controller.tree;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DiagramChosenAction extends AbstractClassyAction {
    public DiagramChosenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/diagram.png"));
        putValue(NAME, "Add diagram");
        putValue(SHORT_DESCRIPTION, "Add a diagram to the current package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        MainFrame.getInstance().getPordFrame().setVisible(false);

        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).setChosenNodeIndex(1);
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
