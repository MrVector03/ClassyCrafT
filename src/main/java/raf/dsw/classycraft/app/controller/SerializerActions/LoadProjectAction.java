package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.json.Json;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadProjectAction extends AbstractClassyAction {
    public LoadProjectAction() {
        // setEnabled(false);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/loadProject.png"));
        putValue(NAME, "Load Project");
        putValue(SHORT_DESCRIPTION, "Load selected project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        File projectFile = MainFrame.getInstance().displayFileChooser("open");

        if (projectFile == null) return;

        Json json = new Json();

        Project newProject;

        try {
            newProject = json.parseToProject(projectFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        setupConnectionAnchors(newProject);

        System.out.println("parsed");
        ClassyTreeItem treeRoot = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).getRootNode();
        MainFrame.getInstance().getClassyTree().loadProject(treeRoot, newProject);

        setupListeners(newProject);

        MainFrame.getInstance().getProjectTreeView().expandRow(0);

    }

    private void setupListeners(ClassyNode node) {
        if (node instanceof Project) {
            for (ClassyNode pkg : ((Project) node).getChildren()) {
                pkg.addSubscriber(MainFrame.getInstance().getPackageView());
                setupListeners(pkg);
            }
        } else if (node instanceof Package) {
            for (ClassyNode pkg : ((Package) node).getChildren()) {
                if (pkg instanceof Package) {
                    pkg.addSubscriber(MainFrame.getInstance().getPackageView());
                    setupListeners(pkg);
                }
            }
        }
    }

    private void setupConnectionAnchors(ClassyNode node) {
        if (node instanceof Project) {
            for (ClassyNode pkg : ((Project) node).getChildren()) {
                setupConnectionAnchors(pkg);
            }
        } else if (node instanceof Package) {
            for (ClassyNode el : ((Package) node).getChildren()) {
                if (el instanceof Package)
                    setupConnectionAnchors(el);
                else {
                    Diagram diagram = (Diagram) el;
                    List<ClassyNode> diagramChildren = diagram.getChildren();
                    for (ClassyNode diagramElement : diagramChildren) {
                        if (diagramElement instanceof Connection) {
                            Connection connectionCpy = (Connection) diagramElement;
                            findProperAnchor(connectionCpy, diagramChildren, "from");
                            findProperAnchor(connectionCpy, diagramChildren, "to");
                        }
                    }
                }
            }
        }
    }

    private void findProperAnchor(Connection connection, List<ClassyNode> diagramChildren, String type) {
        InterClass anchor;
        if (type.equals("from"))
            anchor = connection.getFrom();
        else
            anchor = connection.getTo();
        for (ClassyNode cn : diagramChildren) {
            if (cn instanceof InterClass) {
                InterClass cpy = (InterClass) cn;
                if (cpy.getName().equals(anchor.getName()) && cpy.getPosition().equals(anchor.getPosition()) && cpy.getSize().equals(anchor.getSize())) {
                    if (type.equals("from")) connection.setFrom(cpy);
                    else connection.setTo(cpy);
                    return;
                }
            }
        }
    }
}
