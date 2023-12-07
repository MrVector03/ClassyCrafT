package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ChooseConnectionFrame extends JFrame {
    public ChooseConnectionFrame() {
        setTitle("Choose connection type to add");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/4, 120);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));

        JButton aggregationButton = new JButton();
        aggregationButton.setAction(MainFrame.getInstance().getActionManager().getAggregrationChosenAction());

        JButton compositionButton = new JButton();
        compositionButton.setAction(MainFrame.getInstance().getActionManager().getCompositionChosenAction());

        JButton dependencyButton = new JButton();
        dependencyButton.setAction(MainFrame.getInstance().getActionManager().getDependencyChosenAction());

        JButton generalizationButton = new JButton();
        generalizationButton.setAction(MainFrame.getInstance().getActionManager().getGeneralizationChosenAction());

        add(aggregationButton);
        add(compositionButton);
        add(dependencyButton);
        add(generalizationButton);

        setLocationRelativeTo(null);
    }
}
