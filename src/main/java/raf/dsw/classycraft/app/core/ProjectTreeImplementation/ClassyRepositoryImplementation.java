package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyRepository;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;

public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer root;
    private ClassyNodeChildFactory childFactory;

    public ClassyRepositoryImplementation() {
        if(root == null)
            root = new ProjectExplorer();

        if(childFactory == null)
            childFactory = new ClassyNodeChildFactory();
    }

    @Override
    public ClassyNodeChildFactory getChildFactory() {
        return childFactory;
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

    public void setRoot(ProjectExplorer root) {
        this.root = root;
    }
}
