package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClassyTreeImplementation implements ClassyTree, IPublisher {
    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;
    private int chosenNodeIndex; //0 is package, 1 is diagram

    private final List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(ApplicationFramework.getInstance().getClassyRepositoryImplementation().getRoot());
        defaultTreeModel = new DefaultTreeModel(root);
        classyTreeView = new ClassyTreeView(defaultTreeModel);

        return classyTreeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;

        ClassyNode child = ApplicationFramework.getInstance().getClassyRepositoryImplementation().getChildFactory().makeChild(((ClassyNodeComposite)parent.getClassyNode()), chosenNodeIndex);
        parent.add(new ClassyTreeItem(child));

        child.setParent(parent.getClassyNode());
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

        notifySubscribers("ADDED_D");

        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }

    public void removeNode(ClassyTreeItem node)
    {
        if(node.getClassyNode().getParent() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message("NODE_CANNOT_BE_DELETED", MessageType.ERROR, LocalDateTime.now()));
            return;
        }

        ((ClassyNodeComposite)node.getClassyNode().getParent()).deleteChild(node.getClassyNode());

        node.removeFromParent();
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    public void setChosenNodeIndex(int chosenNodeIndex) {
        this.chosenNodeIndex = chosenNodeIndex;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : subscribers)
            subscriber.update(notification);
    }
}
