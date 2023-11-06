package raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;

public class PackageFactory extends ClassyNodeChildFactory {
    public PackageFactory() {
    }

    @Override
    public ClassyNode createChild(ClassyNodeComposite parent) {
        String newPackageName = "New Package";


        if (parent.getChildren().size() > 0) {
            newPackageName += " (" + parent.getChildren().size() + ")";
        }

        return new Package(newPackageName);
    }
}
