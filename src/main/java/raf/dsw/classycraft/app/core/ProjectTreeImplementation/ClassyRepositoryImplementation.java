package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyRepository;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;

public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer root;

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

    public void setRoot(ProjectExplorer root) {
        this.root = root;
    }
}
