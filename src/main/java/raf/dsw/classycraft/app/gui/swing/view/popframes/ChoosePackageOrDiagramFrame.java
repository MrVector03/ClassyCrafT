package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ChoosePackageOrDiagramFrame extends JFrame{
    public ChoosePackageOrDiagramFrame() {
        setTitle("Change project author");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/6, screenHeight/12);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));

        JButton pckButton = new JButton();
        pckButton.setAction(MainFrame.getInstance().getActionManager().getPackageChosenAction());
        JButton diaButton = new JButton();
        diaButton.setAction(MainFrame.getInstance().getActionManager().getDiagramChosenAction());

        add(pckButton);
        add(diaButton);

        setLocationRelativeTo(null);
    }
}
