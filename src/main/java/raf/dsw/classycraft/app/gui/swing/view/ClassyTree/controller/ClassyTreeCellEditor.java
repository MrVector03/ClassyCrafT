package raf.dsw.classycraft.app.gui.swing.view.ClassyTree.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.security.auth.Subject;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener, IPublisher {
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
                    assert selectedNode instanceof ClassyTreeItem;
                    notifySubscribers(((ClassyTreeItem) selectedNode).getClassyNode());
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

        System.out.println(clicked.getClassyNode().getParent());
        System.out.println(ApplicationFramework.getInstance().getClassyRepositoryImplementation().getRoot());
        for(ClassyNode cn : ((ClassyNodeComposite)clicked.getClassyNode().getParent()).getChildren())
            if(e.getActionCommand().equals(cn.getName())) {
                ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message("CANNOT_RENAME_NODE", MessageType.WARNING, LocalDateTime.now()));
                return;
            }
        clicked.setName(e.getActionCommand());
        if (clicked.getClassyNode() instanceof Package)
            notifySubscribers(clicked.getClassyNode());
        else if (clicked.getClassyNode() instanceof Diagram)
            notifySubscribers(clicked.getClassyNode().getParent());
        else if (clicked.getClassyNode() instanceof Project)
            notifySubscribers("RENAME_P:" + e.getActionCommand());
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
            for (ISubscriber is : this.subscribers) {
                is.update(notification);
            }
    }
}
