package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.json.Json;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveDiagramAsTemplateConfirm extends AbstractClassyAction {
    public SaveDiagramAsTemplateConfirm() {
        putValue(NAME, "Confirm template name");
        putValue(SHORT_DESCRIPTION, "Confirm the name of a new template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        String newTemplate = MainFrame.getInstance().getNewTemplateFrame().getTextField().getText();

        MainFrame.getInstance().getNewTemplateFrame().setVisible(false);
        MainFrame.getInstance().getNewTemplateFrame().getTextField().setText("");

        File templateFile = (Paths.get("src\\main\\resources\\templates", newTemplate)).toAbsolutePath().toFile();

        try {
            Files.createFile(templateFile.toPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Diagram selectedDiagram = (Diagram) (selected.getClassyNode());

        Json json = new Json();
        try {
            json.parseDiagramToTemplate(templateFile, selectedDiagram);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
