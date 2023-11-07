package raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

public class DiagramFactory extends ClassyNodeChildFactory {
    public DiagramFactory() {
    }

    @Override
    public ClassyNode createChild(ClassyNodeComposite parent) {
        if (parent.getChildren().size() > 0) {
            int i = 0;
            while(true) {
                i++;
                boolean found = false;

                for(ClassyNode cn : parent.getChildren())
                    if (cn.getName().equals("New Diagram (" + i + ")")) {
                        found = true;
                        break;
                    }

                if(!found)
                    return new Diagram("New Diagram (" + i + ")");
            }
        }

        return new Diagram("New Diagram");
    }
}
