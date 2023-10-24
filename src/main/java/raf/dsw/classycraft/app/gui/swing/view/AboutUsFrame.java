package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame() {
        setTitle("About us");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/3, screenHeight/3);

        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        Image imageMilojevic = new ImageIcon("src/main/resources/images/aboutUsMilojevic.png").getImage();
        Image imageDavidovic = new ImageIcon("src/main/resources/images/aboutUsDavidovic.png").getImage();

        imageMilojevic = imageMilojevic.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        imageDavidovic = imageDavidovic.getScaledInstance(150, 200, Image.SCALE_SMOOTH);

        JLabel labelMilojevic = new JLabel(new ImageIcon(imageMilojevic));
        JLabel labelDavidovic = new JLabel(new ImageIcon(imageDavidovic));

        JLabel captionMilojevic = new JLabel("Viktor Milojević");
        JLabel captionDavidovic = new JLabel("Viktor Davidović");

        JPanel panelMilojevic = new JPanel(new BorderLayout());
        JPanel panelDavidovic = new JPanel(new BorderLayout());

        Font biggerFont = new Font(captionDavidovic.getFont().getName(), Font.PLAIN, 20);
        captionDavidovic.setFont(biggerFont);
        captionMilojevic.setFont(biggerFont);

        panelDavidovic.add(labelDavidovic, BorderLayout.CENTER);
        panelDavidovic.add(captionDavidovic, BorderLayout.SOUTH);

        panelMilojevic.add(labelMilojevic, BorderLayout.CENTER);
        panelMilojevic.add(captionMilojevic, BorderLayout.SOUTH);

        add(panelMilojevic);
        add(panelDavidovic);

        setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
