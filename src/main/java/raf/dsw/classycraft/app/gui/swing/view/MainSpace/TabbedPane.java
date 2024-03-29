package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TabbedPane extends JTabbedPane {
    private Project classyProject;
    private Package classyPackage;
    private String author;
    private final WorkSpaceButtons buttons = new WorkSpaceButtons();

    private final List<DiagramView> loadedDiagrams = new ArrayList<>();

    private final List<TabView> loadedTabs = new ArrayList<>();

    public TabbedPane() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void loadPackage(Package toLoad) {
        clear();
        classyProject = (Project) toLoad.findProject();
        classyPackage = toLoad;
        loadDiagrams();
        addTabs();
        revalidate();
    }

    public void loadDiagrams() {
        this.loadedTabs.clear();
        for (ClassyNode cn : this.classyPackage.getChildren()) {
            if (cn instanceof Diagram) {
                if (((Diagram) cn).getSubscribers().isEmpty()) {
                    DiagramView newDiagram = new DiagramView((Diagram) cn, this);
                    System.out.println("loading diagram");
                    if (!((Diagram) cn).getChildren().isEmpty()) {
                        for (ClassyNode diagramElement : ((Diagram) cn).getChildren()) {
                            if (diagramElement instanceof InterClass)
                                newDiagram.getDiagramElementPainters().add(new InterClassPainter((InterClass) diagramElement));
                            else
                                newDiagram.getDiagramElementPainters().add(new ConnectionPainter((Connection) diagramElement));
                        }
                    }
                    TabView newTab = new TabView(newDiagram, new WorkSpaceButtons());
                    this.loadedTabs.add(newTab);
                } else {
                    TabView tab = new TabView((DiagramView) ((Diagram) cn).getSubscribers().get(0), new WorkSpaceButtons());
                    this.loadedTabs.add(tab);
                }
            }
        }
    }

    public void addNewDiagram(Diagram diagram) {
        DiagramView newDiagram = new DiagramView(diagram, this);
        if (!(diagram.getChildren().isEmpty())) {
            for (ClassyNode diagramElement : diagram.getChildren()) {
                if (diagramElement instanceof InterClass)
                    newDiagram.getDiagramElementPainters().add(new InterClassPainter((InterClass) diagramElement));
                else
                    newDiagram.getDiagramElementPainters().add(new ConnectionPainter((Connection) diagramElement));
            }
        }
        TabView newTab = new TabView(newDiagram, new WorkSpaceButtons());
        this.loadedTabs.add(newTab);
        addTab(newTab.getDiagramView().getName(), newTab);
    }

    public void clear() {
        for (TabView tabElement : this.loadedTabs) {
            remove(tabElement);
        }

        this.loadedDiagrams.clear();
        this.loadedTabs.clear();
        this.classyPackage = null;
        this.classyProject = null;
    }

    public boolean checkParent(ClassyNode checkParent) {
        if (classyPackage == null) return false;
        if (classyPackage == checkParent) return true;
        ClassyNode tmp = classyPackage;
        while (!(tmp instanceof ProjectExplorer) && tmp.getParent() != null) {
            if (tmp == checkParent) {
                return true;
            }
            tmp = tmp.getParent();
        }
        return false;
    }

    public void renameDiagram(ClassyNode diagram, String newName) {
        for (int i = 0; i < loadedTabs.size(); i++) {
            if (loadedTabs.get(i).getDiagramView().getDiagram() == diagram) {
                // System.out.println("new name: " + newName);
                setTitleAt(i, newName);
                revalidate();
                return;
            }
        }
    }

    public void removeDiagram(ClassyNode diagram) {
        for (TabView tv : this.loadedTabs) {
            if (tv.getDiagramView().getDiagram() == diagram) {
                this.getClassyPackage().getPackageView().masterRemoveSubscriber(tv.getDiagramView());
                remove(tv);
                revalidate();
                return;
            }
        }
    }

    public void addTabs() {
        for (TabView tabElement : this.loadedTabs)
            addTab(tabElement.getDiagramView().getName(), tabElement);
    }

    public void removeAllSelectors() {
        for (TabView tv : loadedTabs) {
            tv.getDiagramView().removeAllSelectionPainters();
        }
    }

    public void setupTemps() {
        for (TabView tv : this.loadedTabs) {
            tv.getDiagramView().setLastValidPoints(tv.getDiagramView().getDiagramElementPainters());
        }
    }

    public boolean testSelectors() {
        for (TabView tv : loadedTabs) {
            if (tv.getDiagramView().deleteSelected())
                return true;
        }
        return false;
    }

    public void moveDividers() {
        for (TabView tv : loadedTabs) {
            tv.resizeDivider();
        }
    }

    public Project getClassyProject() {
        return classyProject;
    }

    public Package getClassyPackage() {
        return classyPackage;
    }

    public void setClassyProject(Project classyProject) {
        this.classyProject = classyProject;
    }

    public void setClassyPackage(Package classyPackage) {
        this.classyPackage = classyPackage;
    }
}
