package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

public abstract class Connection extends DiagramElement {
    private InterClass from;
    private InterClass to;

    public Connection(String name, InterClass from, InterClass to) {
        super(name);
        this.from = from;
        this.to = to;
    }
}
