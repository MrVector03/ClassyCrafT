package raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view;

import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.controller.ClassyTreeCellEditor;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.controller.ClassyTreeSelectionListener;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree {
    public ClassyTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer classyTreeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        ClassyTreeCellEditor classyTreeCellEditor = new ClassyTreeCellEditor(this, classyTreeCellRenderer);
        classyTreeCellEditor.addSubscriber(MainFrame.getInstance());
        setCellEditor(classyTreeCellEditor);
        setCellRenderer(classyTreeCellRenderer);
        setEditable(true);
    }
}
