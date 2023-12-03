package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;

import java.util.ArrayList;

public class Method extends ClassContent {
    private ArrayList<ClassContent> attributes;

    public Method(Access access, String returnType, String name) {
        super(access, returnType, name);
    }

    public Method(Access access, String returnType, String name, ArrayList<ClassContent> attributes) {
        super(access, returnType, name);
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return super.toString() + "()";
    }
}
