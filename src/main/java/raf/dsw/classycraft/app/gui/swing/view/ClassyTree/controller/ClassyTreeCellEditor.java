package raf.dsw.classycraft.app.gui.swing.view.ClassyTree.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;
    private JTextField edit = null;

    private final List<ISubscriber> subscribers = new ArrayList<>();

    public ClassyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedOn = value;
        edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(event instanceof MouseEvent) {
            if (((MouseEvent) event).getClickCount() == 2) {
                int row = tree.getRowForLocation(((MouseEvent) event).getX(), ((MouseEvent) event).getY());
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getPathForRow(row).getLastPathComponent();
                if (selectedNode != null) {
                    if (((ClassyTreeItem) selectedNode).getClassyNode() instanceof Package)
                        ((Package) ((ClassyTreeItem) selectedNode).getClassyNode()).displayOnScreen();
                }
            }
            if (((MouseEvent) event).getClickCount() == 3)
                return true;
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(clickedOn instanceof ClassyTreeItem))
            return;

        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        if (clicked.getClassyNode() instanceof ProjectExplorer || e.getActionCommand().equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT_RENAME_NODE", MessageType.WARNING);
            return;
        }

        for(ClassyNode cn : ((ClassyNodeComposite)clicked.getClassyNode().getParent()).getChildren())
            if(e.getActionCommand().equals(cn.getName())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT_RENAME_NODE", MessageType.WARNING);
                return;
            }

        this.renameNode(clicked.getClassyNode(), e.getActionCommand());

        // clicked.setName(e.getActionCommand());

    }

    private void renameNode(ClassyNode classyNode, String newName) {
        if (classyNode instanceof Diagram)
            ((Diagram) classyNode).setName(newName);
        else if (classyNode instanceof Package)
            ((Package) classyNode).setName(newName);
        else if (classyNode instanceof Project)
            ((Project) classyNode).setName(newName);
    }
}
