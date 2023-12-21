package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageGenerator;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExportImageAction extends AbstractClassyAction {
    public ExportImageAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/exportimage.png"));
        putValue(NAME, "Export image");
        putValue(SHORT_DESCRIPTION, "Export the current diagram as an image file.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File fileToSave = MainFrame.getInstance().displayFileChooser();


        int width = 3*(int)Toolkit.getDefaultToolkit().getScreenSize().width, height = 3*(int)Toolkit.getDefaultToolkit().getScreenSize().height;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bi.createGraphics();

        ArrayList<DiagramElementPainter> allPainters = MainFrame.getInstance().getCurDiagramView().getDiagramElementPainters();

        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new Rectangle(0,0,width,height));

        graphics2D.setColor(Color.BLACK);
        for(DiagramElementPainter dep : allPainters)
            dep.paint(graphics2D);

        try {
            ImageIO.write(bi, "png", fileToSave);
        } catch (IOException exc) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(exc.getMessage(), MessageType.ERROR);
        }
    }
}
