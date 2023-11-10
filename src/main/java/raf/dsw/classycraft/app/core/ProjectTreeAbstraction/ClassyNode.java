package raf.dsw.classycraft.app.core.ProjectTreeAbstraction;

public abstract class ClassyNode {
    private String name;
    private ClassyNode parent;

    public ClassyNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // public abstract void setName(String name) {};

    public void setName(String name) {
        this.name = name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }
}
