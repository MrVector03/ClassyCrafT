package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EditInterClassFrame extends JFrame {
    JTextField eicTextField;
    public EditInterClassFrame() {
        setTitle("Set interclass values");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/6, screenHeight/8);

        setLayout(new BorderLayout(10, 10));

        JLabel eicLabel = new JLabel("New class name:");
        eicLabel.setHorizontalAlignment(SwingConstants.CENTER);

        eicTextField = new JTextField();

        JButton caButton = new JButton();
        caButton.setAction(MainFrame.getInstance().getActionManager().getInterClassEditConfirmAction());
        add(eicLabel, BorderLayout.NORTH);
        add(eicTextField, BorderLayout.CENTER);
        add(caButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getEicTextField() {
        return eicTextField;
    }
}
