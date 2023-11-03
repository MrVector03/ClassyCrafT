package raf.dsw.classycraft.app.core.ProjectTreeAbstraction;

import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;

public interface ClassyRepository extends IPublisher {
    public ProjectExplorer getRoot();
    public ClassyNodeChildFactory getChildFactory();
}
