package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.json.Json;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class LoadTemplateAction extends AbstractClassyAction {
    public LoadTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/loadTemplate.png"));
        putValue(NAME, "Load template");
        putValue(SHORT_DESCRIPTION, "Load a template for Diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();



        if (selected == null) return;

        if(!(selected.getClassyNode() instanceof Package)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT_PUT_TEMPLATE_INTO_NON_PACKAGE", MessageType.ERROR);
            return;
        }
        selected.getClassyNode().addSubscriber(MainFrame.getInstance().getPackageView());

        File diagramFile = MainFrame.getInstance().displayFileChooser("open template");

        if (diagramFile == null) return;

        Json json = new Json();

        Diagram newTemplate;

        try {
            newTemplate = json.parseToDiagram(diagramFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        LoadUtility.setupConnectionAnchors(newTemplate);

        MainFrame.getInstance().getClassyTree().loadTemplate(selected, newTemplate);
        fixDiagramName((Package) selected.getClassyNode(), newTemplate);

        ((Package) selected.getClassyNode()).displayOnScreen();
    }

    private void fixDiagramName(Package parent, Diagram diagram) {
        if (parent.getChildren().size() > 1) {
            int i = 0;
            while(true) {
                i++;
                boolean found = false;

                for(ClassyNode cn : parent.getChildren())
                    if (cn.getName().equals("New Diagram (" + i + ")")) {
                        found = true;
                        break;
                    }

                if(!found) {
                    diagram.setName("New Diagram (" + i + ")");
                    return;
                }
            }
        }
        diagram.setName("New Diagram");
    }

}
