package raf.dsw.classycraft.app.gui.swing.view.ClassyTree.controller;

import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;

public class ClassyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();

        ClassyTreeItem selectedTreeItem = (ClassyTreeItem) path.getLastPathComponent();
    }
}
