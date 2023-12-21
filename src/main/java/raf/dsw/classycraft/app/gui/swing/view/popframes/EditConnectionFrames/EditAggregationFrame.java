package raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditAggregationFrame extends JFrame {
    private JTextField nameTextField;
    private JTextField varNameTextField;
    private JComboBox cardCmb;
    public EditAggregationFrame() {
        JPanel jpanel = new JPanel();
        jpanel.setBorder(new EmptyBorder(10,20,10,20));
        setContentPane(jpanel);

        setTitle("Set aggregation values");

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

        String cardList[] = {"1...*", "0...1" };
        cardCmb = new JComboBox(cardList);
        cardCmb.setAlignmentX((float)0.5);


        nameTextField = new JTextField();
        nameTextField.setAlignmentX((float)0.5);

        JButton confirmButton = new JButton();
        confirmButton.setAction(MainFrame.getInstance().getActionManager().getAggregationConfirmAction());
        confirmButton.setAlignmentX((float)0.5);

        add(nameLabel);
        add(nameTextField);
        add(varNameLabel);
        add(varNameTextField);
        add(cardLabel0);
        add(cardCmb);
        add(confirmButton);

        setLocationRelativeTo(null);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JComboBox getCardCmb() {
        return cardCmb;
    }

    public JTextField getVarNameTextField() {
        return varNameTextField;
    }
}
