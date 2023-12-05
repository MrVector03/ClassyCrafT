package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

public class Dependency extends Connection {
    private String type;

    public Dependency(String name, InterClass from, InterClass to, String type) {
        super(name, from, to);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
