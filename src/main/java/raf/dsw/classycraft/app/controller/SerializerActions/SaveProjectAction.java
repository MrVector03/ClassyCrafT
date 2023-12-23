package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
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
import java.util.Objects;

public class SaveProjectAction extends AbstractClassyAction {
    public SaveProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/saveProject.png"));
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save selected project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if (selected == null) return;

        if(!(selected.getClassyNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT_SAVE_SELECTED_ITEM", MessageType.ERROR);
            return;
        }

        File projectFile;

        if (!Objects.equals(((Project) selected.getClassyNode()).getLocalPath(), "/"))
            projectFile = new File(((Project) selected.getClassyNode()).getLocalPath());
        else {
            projectFile = MainFrame.getInstance().displayFileChooser("save");
            if (projectFile == null) return;
        }

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
