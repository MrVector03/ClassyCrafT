package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class NewTemplateFrame extends JFrame {
    JTextField textField;
    public NewTemplateFrame() {
        setTitle("Create new template");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/6, screenHeight/8);

        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("New template name:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        textField = new JTextField();

        JButton confirmButton = new JButton();
        confirmButton.setAction(MainFrame.getInstance().getActionManager().getSaveDiagramAsTemplateConfirm());

        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getTextField() {
        return textField;
    }
}
