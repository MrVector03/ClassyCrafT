package raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditCompositionFrame extends JFrame {
    private JTextField nameTextField;
    private JTextField cardFromTextField;
    private JTextField cardToTextField;
    private JTextField varNameTextField;
    public EditCompositionFrame() {
        JPanel jpanel = new JPanel();
        jpanel.setBorder(new EmptyBorder(10,20,10,20));
        setContentPane(jpanel);

        setTitle("Set generalization values");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/8, screenHeight/6);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Connection name:");
        nameLabel.setAlignmentX((float)0.5);

        JLabel varNameLabel = new JLabel("Variable name:");
        varNameLabel.setAlignmentX((float)0.5);
        varNameTextField = new JTextField();
        varNameTextField.setAlignmentX((float)0.5);

        JLabel cardLabel0 = new JLabel("Cardinality:");
        cardLabel0.setAlignmentX((float)0.5);

        JLabel cardLabel = new JLabel("...");
        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardFromTextField = new JTextField();
        cardFromTextField.setPreferredSize(new Dimension(15,30));
        cardToTextField = new JTextField();
        cardToTextField.setPreferredSize(new Dimension(15,30));
        newPanel.add(cardFromTextField);
        newPanel.add(cardLabel);
        newPanel.add(cardToTextField);

        nameTextField = new JTextField();
        nameTextField.setAlignmentX((float)0.5);

        JButton confirmButton = new JButton();
        confirmButton.setAction(MainFrame.getInstance().getActionManager().getCompositionConfirmAction());
        confirmButton.setAlignmentX((float)0.5);

        add(nameLabel);
        add(nameTextField);
        add(varNameLabel);
        add(varNameTextField);
        add(cardLabel0);
        add(newPanel);
        add(confirmButton);

        setLocationRelativeTo(null);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getCardFromTextField() {
        return cardFromTextField;
    }

    public JTextField getCardToTextField() {
        return cardToTextField;
    }

    public JTextField getVarNameTextField() {
        return varNameTextField;
    }
}
