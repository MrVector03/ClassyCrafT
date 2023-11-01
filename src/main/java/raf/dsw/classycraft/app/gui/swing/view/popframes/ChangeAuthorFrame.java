package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ChangeAuthorFrame extends JFrame {
    JTextField caTextField;
    public ChangeAuthorFrame() {
        setTitle("Change project author");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/6, screenHeight/12);

        setLayout(new BorderLayout(10, 10));

        JLabel caLabel = new JLabel("New author name:");
        caLabel.setHorizontalAlignment(SwingConstants.CENTER);

        caTextField = new JTextField();

        JButton caButton = new JButton();
        caButton.setAction(MainFrame.getInstance().getActionManager().getChangeAuthorConfirmAction());

        add(caLabel, BorderLayout.NORTH);
        add(caTextField, BorderLayout.CENTER);
        add(caButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getCaTextField() {
        return caTextField;
    }
}
