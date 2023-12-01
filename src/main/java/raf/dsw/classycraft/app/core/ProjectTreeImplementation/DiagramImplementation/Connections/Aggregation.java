package raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;

public class Aggregation extends Connection {
    public Aggregation(String name, InterClass from, InterClass to) {
        super(name, from, to);
    }
}
