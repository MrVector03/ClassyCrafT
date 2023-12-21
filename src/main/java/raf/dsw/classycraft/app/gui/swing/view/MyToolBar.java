package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getAddNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        add(MainFrame.getInstance().getActionManager().getExportJavaCodeAction());
        add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
        add(MainFrame.getInstance().getActionManager().getLoadProjectAction());
    }
}
