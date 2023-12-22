package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.json.Json;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.desktop.AppForegroundListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

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

        File projectFile = null;
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());
        int r = jfc.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            projectFile = new File(jfc.getSelectedFile().getAbsolutePath());
        } else return;

        Json json = new Json();

        Project newProject = null;

        try {
            newProject = json.parseToProject(projectFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ClassyTreeItem treeRoot = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).getRootNode();

        MainFrame.getInstance().getClassyTree().loadProject(treeRoot, newProject);

        MainFrame.getInstance().getProjectTreeView().expandRow(0);

    }
}
