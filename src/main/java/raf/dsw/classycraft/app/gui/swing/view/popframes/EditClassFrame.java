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
        abstractCheckBox.setAlignmentX((float)0.5);

        JLabel eicAccesLabel = new JLabel("New class access:");
        eicAccesLabel.setAlignmentX((float)0.5);

        Access[] accessOptions = {Access.DEFAULT, Access.PUBLIC, Access.PROTECTED, Access.PRIVATE};
        eicComboBox = new JComboBox(accessOptions);
        eicComboBox.setAlignmentX((float)0.5);

        JLabel eicLabel = new JLabel("New class name:");
        eicLabel.setAlignmentX((float)0.5);

        eicTextField = new JTextField();
        eicTextField.setAlignmentX((float)0.5);

        JLabel eicCContentLabel = new JLabel("New class content:");
        eicCContentLabel.setAlignmentX((float)0.5);

        eicTextArea = new JTextArea();
        eicTextArea.setAlignmentX((float)0.5);

        JButton caButton = new JButton();
        caButton.setAction(MainFrame.getInstance().getActionManager().getClassEditConfirmAction());
        caButton.setAlignmentX((float)0.5);

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
