package raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

public class PackageFactory extends ClassyNodeChildFactory {
    public PackageFactory() {
    }

    @Override
    public ClassyNode createChild(ClassyNodeComposite parent) {
        if (parent.getChildren().size() > 0) {
            int i = 0;
            while(true) {
                i++;
                boolean found = false;

                for(ClassyNode cn : parent.getChildren())
                    if (cn.getName().equals("New Package (" + i + ")")) {
                        found = true;
                        break;
                    }

                if(!found)
                    return new Package("New Package (" + i + ")");
            }
        }

        return new Package("New Package");
    }
}
