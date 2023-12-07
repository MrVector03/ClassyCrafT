package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class WorkSpaceButtons extends JToolBar {

    public WorkSpaceButtons() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getSelectionAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());
        add(MainFrame.getInstance().getActionManager().getAddInterClassAction());
        add(MainFrame.getInstance().getActionManager().getAddConnectionAction());
        add(MainFrame.getInstance().getActionManager().getAddElementAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getCopyInterClassAction());
        add(MainFrame.getInstance().getActionManager().getEditAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());

        setAlignmentX(RIGHT_ALIGNMENT);
        setMaximumSize(new Dimension(32, 1000));
    }
}
