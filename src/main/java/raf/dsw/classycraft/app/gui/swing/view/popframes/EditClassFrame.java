package raf.dsw.classycraft.app.gui.swing.view.popframes;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditClassFrame extends JFrame {
    JTextField eicTextField;
    JComboBox eicComboBox;
    JTextArea eicTextArea;
    JCheckBox abstractCheckBox;
    public EditClassFrame() {
        JPanel jpanel = new JPanel();
        jpanel.setBorder(new EmptyBorder(10,20,10,20));
        setContentPane(jpanel);

        setTitle("Set class values");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/6, screenHeight/4);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        abstractCheckBox = new JCheckBox("Abstract");
        abstractCheckBox.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel eicAccesLabel = new JLabel("New class access:");
        eicAccesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Access[] accessOptions = {Access.DEFAULT, Access.PUBLIC, Access.PROTECTED, Access.PRIVATE};
        eicComboBox = new JComboBox(accessOptions);

        JLabel eicLabel = new JLabel("New class name:");
        eicLabel.setHorizontalAlignment(SwingConstants.CENTER);

        eicTextField = new JTextField();
        eicTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel eicCContentLabel = new JLabel("New class content:");
        eicCContentLabel.setHorizontalAlignment(SwingConstants.CENTER);

        eicTextArea = new JTextArea();

        JButton caButton = new JButton();
        caButton.setAction(MainFrame.getInstance().getActionManager().getClassEditConfirmAction());

        add(abstractCheckBox);
        add(eicAccesLabel);
        add(eicComboBox);
        add(eicLabel);
        add(eicTextField);
        add(eicCContentLabel);
        add(eicTextArea);
        add(caButton);

        setLocationRelativeTo(null);
    }

    public JTextField getEicTextField() {
        return eicTextField;
    }

    public JComboBox getEicComboBox() {
        return eicComboBox;
    }

    public JTextArea getEicTextArea() {
        return eicTextArea;
    }

    public JCheckBox getAbstractCheckBox() {
        return abstractCheckBox;
    }
}
