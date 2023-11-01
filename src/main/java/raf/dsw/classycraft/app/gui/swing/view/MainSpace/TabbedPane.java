package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TabbedPane extends JTabbedPane {
    private Project classyProject;
    private Package classyPackage;
    private String author;

    private final List<DiagramView> loadedDiagrams = new ArrayList<>();

    public TabbedPane() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void loadPackage(Package toLoad) {
        classyProject = (Project) toLoad.findProject();
        classyPackage = toLoad;
        clear();
        loadDiagrams();
        addTabs();
        revalidate();
    }

    public void loadDiagrams() {
        for (ClassyNode cn : this.classyPackage.getChildren()) {
            if (cn instanceof Diagram)
                this.loadedDiagrams.add(new DiagramView((Diagram) cn));
        }
    }

    public void clear() {
        for (DiagramView tabElement : loadedDiagrams)
            remove(tabElement);
        this.loadedDiagrams.clear();
    }

    public void addTabs() {
        for (DiagramView tabElement : this.loadedDiagrams)
            addTab(tabElement.getName(), tabElement);
    }

    public Project getClassyProject() {
        return classyProject;
    }

    public Package getClassyPackage() {
        return classyPackage;
    }

}
