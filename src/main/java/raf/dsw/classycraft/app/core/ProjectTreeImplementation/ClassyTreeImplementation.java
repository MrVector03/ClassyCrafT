package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories.FactoryUtils;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;

public class ClassyTreeImplementation implements ClassyTree {
    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;
    private int chosenNodeIndex; //0 is package, 1 is diagram
    private ArrayList<ClassyTreeItem> diagrams = new ArrayList<ClassyTreeItem>();
    private ClassyTreeItem treeRoot;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(ApplicationFramework.getInstance().getClassyRepositoryImplementation().getRoot());
        this.treeRoot = root;
        defaultTreeModel = new DefaultTreeModel(root);
        classyTreeView = new ClassyTreeView(defaultTreeModel);

        return classyTreeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;

        ClassyNode child = FactoryUtils.getNodeFactory((ClassyNodeComposite) parent.getClassyNode(), chosenNodeIndex).createChild((ClassyNodeComposite) parent.getClassyNode());

        ClassyTreeItem newClassyTreeItem = new ClassyTreeItem(child);

        parent.add(newClassyTreeItem);

        child.setParent(parent.getClassyNode());
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);

        if (child instanceof Diagram) {
            ((Diagram) child).addToScreen();
            diagrams.add(newClassyTreeItem);
        }

    }

    @Override
    public void loadProject(ClassyTreeItem parent, ClassyNode child) {
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;

        ClassyTreeItem newClassyTreeItem = new ClassyTreeItem(child);

        parent.add(newClassyTreeItem);

        child.setParent(parent.getClassyNode());
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);

        if (child instanceof Diagram) {
            ((Diagram) child).addToScreen();
            diagrams.add(newClassyTreeItem);
        }
    }

    @Override
    public void addDiagramChild(Diagram diagram, ClassyNode newChild) {
        ClassyTreeItem curDiagTreeItem = null;

        for (ClassyTreeItem cti : diagrams)
            if(cti.getClassyNode().equals(diagram))
                curDiagTreeItem = cti;

        curDiagTreeItem.add(new ClassyTreeItem(newChild));

        newChild.setParent(curDiagTreeItem.getClassyNode());

        classyTreeView.expandPath(classyTreeView.getSelectionPath().pathByAddingChild(curDiagTreeItem));
        SwingUtilities.updateComponentTreeUI(classyTreeView);
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
        } else if(node.getClassyNode() instanceof DiagramElement) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("USE_DIAGRAM_TOOLS_TO_DELETE_ELEMENTS", MessageType.WARNING);
            return;
        }


        ((ClassyNodeComposite)node.getClassyNode().getParent()).deleteChild(node.getClassyNode());

        node.removeFromParent();
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }

    public void removeNode(ClassyNode toDelete) {
        ClassyNode diagram = toDelete.getParent();

        for(ClassyTreeItem d : diagrams)
        {
            if(d.getClassyNode().equals(diagram)) {
                for(int i = 0; i < d.getChildCount(); i++)
                    if(((ClassyTreeItem)d.getChildAt(i)).getClassyNode().equals(toDelete)) {
                        d.remove(i);
                        break;
                    }
            }
        }

        SwingUtilities.updateComponentTreeUI(classyTreeView);

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    public void setChosenNodeIndex(int chosenNodeIndex) {
        this.chosenNodeIndex = chosenNodeIndex;
    }

    public ClassyTreeItem getRootNode() {
        return this.treeRoot;
    }

    public ClassyTreeView getClassyTreeView() {
        return classyTreeView;
    }
}
