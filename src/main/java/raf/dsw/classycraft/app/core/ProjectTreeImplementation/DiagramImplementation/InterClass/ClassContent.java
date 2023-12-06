package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;

public class ClassContent {
    private Access access;
    private String returnType;
    private String name;

    public ClassContent(Access access, String returnType, String name) {
        this.access = access;
        this.returnType = returnType;
        this.name = name;
    }

    @Override
    public String toString() {
        return access.toString() + " " + returnType + " " + name;
    }

    public String toStringNames() {
        return access.toStringNames() + " " + returnType + " " + name;
    }
}
