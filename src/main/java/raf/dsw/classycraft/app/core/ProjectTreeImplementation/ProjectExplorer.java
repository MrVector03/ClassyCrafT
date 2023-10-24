package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer() {
        super("Project Explorer");
        ApplicationFramework.getInstance().getClassyRepositoryImplementation().setRoot(this);
    }
}
