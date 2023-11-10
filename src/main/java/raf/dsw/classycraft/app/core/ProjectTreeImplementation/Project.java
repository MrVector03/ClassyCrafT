package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;

import java.time.LocalDateTime;

public class Project extends ClassyNodeComposite {
    public String author;
    public String localPath;


    public Project(String name, String author, String localPath) {
        super(name);
        this.author = author;
        this.localPath = localPath;
    }

    @Override
    public void addChild(ClassyNode newChild) {
        if(newChild instanceof Package)
            super.addChild(newChild);
        else
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NODE_CANNOT_BE_ADDED", MessageType.ERROR);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        for (ClassyNode cn : this.getChildren())
            ((Package) cn).changeOnProject(author, this, Type.CHANGE_AUTHOR);
    }

    public void remove() {
        for (ClassyNode cn : this.getChildren()) {
            ((Package) cn).checkRemovalFromScreen(this);
        }
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        for (ClassyNode cn : this.getChildren())
            ((Package) cn).changeOnProject(name, this, Type.RENAME);
    }
}
