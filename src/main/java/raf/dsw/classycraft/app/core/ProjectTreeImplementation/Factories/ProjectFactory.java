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
        String newProjectName = "New Project";


        if (parent.getChildren().size() > 0) {
            newProjectName += " (" + parent.getChildren().size() + ")";
        }

        return new Project(newProjectName, "default", "/");
    }
}
