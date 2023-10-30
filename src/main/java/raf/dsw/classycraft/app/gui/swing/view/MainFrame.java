package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.view.popframes.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    private ActionManager actionManager;

    private AboutUsFrame auFrame;

    //buduca polja za sve komponente view-a na glavnom prozoru

    private AlertFrame alertFrame;


    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        auFrame = new AboutUsFrame();
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    public AboutUsFrame getAuFrame() {
        return auFrame;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Object notification) {
        AlertFactory alertFactory = new AlertFactory();
        alertFrame = alertFactory.getAlert((Message) notification);
        alertFrame.showMessage();
    }
}
