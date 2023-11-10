package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories.FactoryUtils;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements ClassyTree {
    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;
    private int chosenNodeIndex; //0 is package, 1 is diagram

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

        ClassyNode child = FactoryUtils.getNodeFactory((ClassyNodeComposite) parent.getClassyNode(), chosenNodeIndex).createChild((ClassyNodeComposite) parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));

        child.setParent(parent.getClassyNode());
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);

        if (child instanceof Diagram)
            ((Diagram) child).addToScreen();

    }

    public void removeNode(ClassyTreeItem node)
    {
        if(node.getClassyNode().getParent() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NODE_CANNOT_BE_DELETED", MessageType.ERROR);
            return;
        }

        if (node.getClassyNode() instanceof Diagram) {
            ((Package) node.getClassyNode().getParent()).removeDiagramFromScreen((Diagram) node.getClassyNode());
        } else if (node.getClassyNode() instanceof Package) {
            ((Package) node.getClassyNode()).checkRemovalFromScreen();
        } else if (node.getClassyNode() instanceof Project) {
            ((Project) node.getClassyNode()).remove();
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
}
