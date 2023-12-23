package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.json.Json;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SaveProjectAsAction extends AbstractClassyAction {
    public SaveProjectAsAction() {
        // setEnabled(false);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/saveProjectAs.png"));
        putValue(NAME, "Save Project as");
        putValue(SHORT_DESCRIPTION, "Save selected project as...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if (selected == null) return;

        File projectFile;

        projectFile = MainFrame.getInstance().displayFileChooser("save");
        if (projectFile == null) return;

        Project project = (Project) selected.getClassyNode();
        project.setLocalPath(projectFile.getPath());
        Json json = new Json();
        try {
            json.parseProjectToJson(projectFile, project);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
