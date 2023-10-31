package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements ClassyTree {
    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;

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

        ClassyNode child = makeChild((ClassyNodeComposite) parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));

        child.setParent(parent.getClassyNode());
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    private ClassyNode makeChild(ClassyNodeComposite parent)
    {
        String newProjectName = "New Project";
        String newPackageName = "New Package";
        String newDiagramName = "New Diagram";


        if(parent.getChildren().size() > 0) {
            newProjectName += " (" + parent.getChildren().size() + ")";
            newPackageName += " (" + parent.getChildren().size() + ")";
            newDiagramName += " (" + parent.getChildren().size() + ")";
        }

        if(parent instanceof ProjectExplorer)
            return new Project(newProjectName, "default", "/");
        if(parent instanceof Project)
            return new Package(newPackageName);
        if(parent instanceof Package)
            return new Diagram(newDiagramName);

        return null;
    }
}
