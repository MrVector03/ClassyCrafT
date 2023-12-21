package raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditDependencyFrame extends JFrame {
    private JTextField nameTextField;
    private JTextField typeTextfield;
    public EditDependencyFrame() {
        JPanel jpanel = new JPanel();
        jpanel.setBorder(new EmptyBorder(10,20,10,20));
        setContentPane(jpanel);

        setTitle("Set dependency values");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/8, screenHeight/8);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Connection name:");
        nameLabel.setAlignmentX((float)0.5);

        nameTextField = new JTextField();
        nameTextField.setAlignmentX((float)0.5);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setAlignmentX((float)0.5);

        typeTextfield = new JTextField();
        typeTextfield.setAlignmentX((float)0.5);

        JButton confirmButton = new JButton();
        confirmButton.setAction(MainFrame.getInstance().getActionManager().getDependencyConfirmAction());
        confirmButton.setAlignmentX((float)0.5);

        add(nameLabel);
        add(nameTextField);
        add(typeLabel);
        add(typeTextfield);
        add(confirmButton);

        setLocationRelativeTo(null);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getTypeTextfield() {
        return typeTextfield;
    }
}
