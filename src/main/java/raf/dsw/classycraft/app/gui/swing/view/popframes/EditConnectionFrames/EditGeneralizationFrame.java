package raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditGeneralizationFrame extends JFrame {
    private JTextField nameTextField;
    public EditGeneralizationFrame() {
        JPanel jpanel = new JPanel();
        jpanel.setBorder(new EmptyBorder(10,20,10,20));
        setContentPane(jpanel);

        setTitle("Set generalization values");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/8, screenHeight/8);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Connection name:");
        nameLabel.setAlignmentX((float)0.5);

        nameTextField = new JTextField();
        nameTextField.setAlignmentX((float)0.5);

        JButton confirmButton = new JButton();
        confirmButton.setAction(MainFrame.getInstance().getActionManager().getGeneralizationConfirmAction());
        confirmButton.setAlignmentX((float)0.5);

        add(nameLabel);
        add(nameTextField);
        add(confirmButton);

        setLocationRelativeTo(null);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }
}
