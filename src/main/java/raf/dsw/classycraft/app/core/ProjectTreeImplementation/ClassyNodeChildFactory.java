package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;

public class ClassyNodeChildFactory {
    public ClassyNode makeChild(ClassyNodeComposite parent, int chosenNodeIndex)
    {
        String newProjectName = "New Project";
        String newPackageName = "New Package";
        String newDiagramName = "New Diagram";


        if(parent.getChildren().size() > 0) {
            newProjectName += " (" + parent.getChildren().size() + ")";
            newPackageName += " (" + parent.getChildren().size() + ")";
            newDiagramName += " (" + parent.getChildren().size() + ")";
        }

        if(parent instanceof ProjectExplorer)
            return new Project(newProjectName, "default", "/");
        if(parent instanceof Project)
            return new Package(newPackageName);
        if(parent instanceof Package)
            if(chosenNodeIndex == 0)
                return new Package(newPackageName);
            else {
                return new Diagram(newDiagramName);
            }

        return null;
    }
}
