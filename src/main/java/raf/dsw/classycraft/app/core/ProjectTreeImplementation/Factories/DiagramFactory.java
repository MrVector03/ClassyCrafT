package raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeChildFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;

public class DiagramFactory extends ClassyNodeChildFactory {
    public DiagramFactory() {
    }

    @Override
    public ClassyNode createChild(ClassyNodeComposite parent) {
        String newDiagramName = "New Diagram";


        if (parent.getChildren().size() > 0) {
              newDiagramName += " (" + parent.getChildren().size() + ")";
        }

        return new Diagram(newDiagramName);
    }
}
