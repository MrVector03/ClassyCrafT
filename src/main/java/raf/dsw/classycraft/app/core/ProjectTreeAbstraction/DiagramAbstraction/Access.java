package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

public enum Access {
    PUBLIC, PRIVATE, PROTECTED, DEFAULT;

    @Override
    public String toString() {
        if(ordinal() == 0)
            return "+";
        if(ordinal() == 1)
            return "-";
        if(ordinal() == 2)
            return "#";
        if(ordinal() == 3)
            return "~";
        return super.toString();
    }
}
