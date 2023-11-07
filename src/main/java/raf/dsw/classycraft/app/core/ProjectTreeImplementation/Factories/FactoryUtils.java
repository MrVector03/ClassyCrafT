package raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;

public class FactoryUtils {
    public static ClassyNodeChildFactory getNodeFactory(ClassyNodeComposite parent, int chosenNodeIndex) {
        String newProjectName = "New Project";
        String newPackageName = "New Package";
        String newDiagramName = "New Diagram";


        if (parent.getChildren().size() > 0) {
            newProjectName += " (" + parent.getChildren().size() + ")";
            newPackageName += " (" + parent.getChildren().size() + ")";
            newDiagramName += " (" + parent.getChildren().size() + ")";
        }

        if (parent instanceof ProjectExplorer)
            return new ProjectFactory();
        if (parent instanceof Project)
            return new PackageFactory();
        if (parent instanceof Package)
            if (chosenNodeIndex == 0)
                return new PackageFactory();
            else
                return new DiagramFactory();


            return null;
    }
}
