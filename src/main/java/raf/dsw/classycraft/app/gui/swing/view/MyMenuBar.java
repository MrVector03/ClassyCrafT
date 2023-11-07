package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.controller.AboutUsAction;
import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        JMenu editMenu = new JMenu("Edit");
        fileMenu.setMnemonic(KeyEvent.VK_E);

        editMenu.add(MainFrame.getInstance().getActionManager().getAddNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());


        add(fileMenu);
        add(editMenu);
    }

}
