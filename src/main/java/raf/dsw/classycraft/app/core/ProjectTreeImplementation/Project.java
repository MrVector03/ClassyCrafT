package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;

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
            System.out.println("GRESKA NE MOZE DA SE DODA NISTA SEM PAKETA U PROJEKAT"); //promeniti u messagegenerator kada ga napravimo
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
