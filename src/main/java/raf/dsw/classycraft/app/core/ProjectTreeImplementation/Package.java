package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;

import java.time.LocalDateTime;

public class Package extends ClassyNodeComposite {
    public Package(String name) {
        super(name);
    }

    @Override
    public void addChild(ClassyNode newChild) {
        if(newChild instanceof Package || newChild instanceof Diagram)
            super.addChild(newChild);
        else
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NODE_CANNOT_BE_ADDED", MessageType.ERROR);
    }

    public ClassyNodeComposite findProject() {
        ClassyNodeComposite project = (ClassyNodeComposite) this.getParent();
        while (!(project instanceof Project)) {
            project = (ClassyNodeComposite) project.getParent();
        }
        return project;
    }
}
