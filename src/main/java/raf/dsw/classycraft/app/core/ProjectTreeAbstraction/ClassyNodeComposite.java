package raf.dsw.classycraft.app.core.ProjectTreeAbstraction;

import java.util.ArrayList;

public abstract class ClassyNodeComposite extends ClassyNode {
    private ArrayList<ClassyNode> children;

    public ClassyNodeComposite(String name) {
        super(name);
    }

    public ArrayList<ClassyNode> getChildren() {
        return children;
    }

    public void addChild(ClassyNode newChild)
    {
        children.add(newChild);
    }

    public void deleteChild(ClassyNode child)
    {
        children.remove(child);
    }
}
