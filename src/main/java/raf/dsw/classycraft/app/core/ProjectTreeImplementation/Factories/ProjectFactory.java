package raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

public class ProjectFactory extends ClassyNodeChildFactory {
    String pName;
    public ProjectFactory() {
    }

    @Override
    public ClassyNode createChild(ClassyNodeComposite parent) {
        if (parent.getChildren().size() > 0) {
            int i = 0;
            while(true) {
                i++;
                boolean found = false;

                for(ClassyNode cn : parent.getChildren())
                    if (cn.getName().equals("New Project (" + i + ")")) {
                        found = true;
                        break;
                    }

                if(!found)
                    return new Project("New Project (" + i + ")", "default", "/");
            }
        }

        return new Project("New Project", "default", "/");
    }
}
