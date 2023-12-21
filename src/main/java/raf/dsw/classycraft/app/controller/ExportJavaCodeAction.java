package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Factories.PackageFactory;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportJavaCodeAction extends AbstractClassyAction {
    public ExportJavaCodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/exportcode.png"));
        putValue(NAME, "Export java code");
        putValue(SHORT_DESCRIPTION, "Export the current project as Java code.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(!(selected.getClassyNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CAN_ONLY_EXPORT_PROJECT", MessageType.ERROR);
            return;
        }

        File fileToSave = MainFrame.getInstance().displayFileChooser();

        exportProject(fileToSave, (Project)selected.getClassyNode());
    }

    private void exportProject(File fileToSave, Project proj) {
        File path = new File(fileToSave + "/" + proj.getName());
        System.out.println(path);
        path.mkdirs();

        for(ClassyNode pck : proj.getChildren())
            if(pck instanceof Package)
                recursiveExportPackages(path, (Package)pck);
    }

    private void recursiveExportPackages(File fileToSave, Package pck) {
        File path = new File(fileToSave + "/" + pck.getName());
        path.mkdirs();

        for(ClassyNode cn : pck.getChildren())
            if(cn instanceof Package)
                recursiveExportPackages(path, (Package)cn);
            else if(cn instanceof Diagram)
                exportDiagram(path, (Diagram)cn);
    }

    private void exportDiagram(File fileToSave ,Diagram dia) {
        File newDiagramFile = new File(fileToSave + "/" + dia.getName() + ".java");

        dia.convertToCode(newDiagramFile);
    }
}
