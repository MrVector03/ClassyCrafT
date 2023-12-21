package raf.dsw.classycraft.app.core.ProjectTreeAbstraction;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view.ClassyTreeView;

import java.awt.*;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);
    ClassyTreeItem getSelectedNode();

    void addDiagramChild(Diagram diagram, ClassyNode newChild);

    void loadProject(ClassyTreeItem root, ClassyNode newProject);

    Component getClassyTreeView();
}
