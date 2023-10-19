package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AboutUsAction;
import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        ExitAction ea = new ExitAction();
        fileMenu.add(ea);


        JMenu editMenu = new JMenu("Edit");
        fileMenu.setMnemonic(KeyEvent.VK_E);

        AboutUsAction aua = new AboutUsAction();
        editMenu.add(aua);


        add(fileMenu);
        add(editMenu);
    }

}
