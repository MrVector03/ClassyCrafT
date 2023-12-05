package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

public class Aggregation extends Connection {
    private String varName;
    private char cardFrom;
    private char cardTo;

    public Aggregation(String name, InterClass from, InterClass to, String varName, char cardFrom, char cardTo) {
        super(name, from, to);
        this.varName = varName;
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
    }
}
