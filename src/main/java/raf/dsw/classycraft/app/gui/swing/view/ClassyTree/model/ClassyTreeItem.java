package raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ClassyTreeItem extends DefaultMutableTreeNode {

    ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode classyNode) {
        this.classyNode = classyNode;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

    public void setName(String name)
    {
        this.classyNode.setName(name);
    }

    public ClassyNode getClassyNode() {
        return classyNode;
    }
}
