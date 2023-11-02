package raf.dsw.classycraft.app.core.ProjectTreeAbstraction;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;

public interface ClassyRepository {
    public ProjectExplorer getRoot();
    public ClassyNodeChildFactory getChildFactory();
}
