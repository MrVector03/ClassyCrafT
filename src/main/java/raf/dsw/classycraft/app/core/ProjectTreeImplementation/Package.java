package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    public Package(String name) {
        super(name);
    }

    @Override
    public void addChild(ClassyNode newChild) {
        if(newChild instanceof Package || newChild instanceof Diagram)
            super.addChild(newChild);
        else
            System.out.println("GRESKA NE MOZE DA SE DODA NISTA SEM PAKETA I DIJAGRAMA U PAKET"); //promeniti u messagegenerator kada ga napravimo
    }
}
