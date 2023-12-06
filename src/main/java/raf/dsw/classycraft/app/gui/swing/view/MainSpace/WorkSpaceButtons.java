package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class WorkSpaceButtons extends JToolBar {

    public WorkSpaceButtons() {
        super(VERTICAL);
        setFloatable(false);

        ActionManager actionManager = new ActionManager();

        add(actionManager.getSelectionAction());
        add(actionManager.getMoveAction());
        add(actionManager.getAddInterClassAction());
        add(actionManager.getAddConnectionAction());
        add(actionManager.getAddElementAction());
        add(actionManager.getDeleteAction());
        add(actionManager.getCopyInterClassAction());

        setAlignmentX(RIGHT_ALIGNMENT);
        setMaximumSize(new Dimension(32, 1000));
    }
}
