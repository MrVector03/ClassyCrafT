package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ChooseInterClassFrame extends JFrame{
    public ChooseInterClassFrame() {
        setTitle("Choose interclass element to add");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/6, screenHeight/12);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));

        JButton classButton = new JButton();
        classButton.setAction(MainFrame.getInstance().getActionManager().getClassChosenAction());

        JButton interfaceButton = new JButton();
        interfaceButton.setAction(MainFrame.getInstance().getActionManager().getInterfaceChosenAction());

        JButton enumButton = new JButton();
        enumButton.setAction(MainFrame.getInstance().getActionManager().getEnumChosenAction());

        add(classButton);
        add(interfaceButton);
        add(enumButton);

        setLocationRelativeTo(null);
    }
}
