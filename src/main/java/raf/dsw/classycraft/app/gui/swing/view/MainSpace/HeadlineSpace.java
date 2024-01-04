package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class HeadlineSpace extends JPanel {
    private JLabel projectLabel = new JLabel(" ");
    private JLabel authorLabel = new JLabel(" ");

    public HeadlineSpace() {
        setLayout(new FlowLayout(1,20, 0));

        JButton undoButton = new JButton();
        undoButton.setAction(MainFrame.getInstance().getActionManager().getUndoAction());

        JButton redoButton = new JButton();
        redoButton.setAction(MainFrame.getInstance().getActionManager().getRedoAction());

        JButton exportImageButton = new JButton();
        exportImageButton.setAction(MainFrame.getInstance().getActionManager().getExportImageAction());

        add(undoButton);
        add(redoButton);
        add(projectLabel);
        add(authorLabel);
        add(exportImageButton);
        setMaximumSize(new Dimension(5000, 250));
    }

    public void setup(String projectName, String author) {
        this.projectLabel.setText(projectName);
        this.authorLabel.setText(" | Author: " +  author);
        Font biggerFont = new Font(projectLabel.getFont().getName(), Font.PLAIN, 14);
        projectLabel.setFont(biggerFont);
        authorLabel.setFont(biggerFont);
        repaint();
    }

    public void refreshLabels(Package pkg) {
        this.projectLabel.setText(pkg.findProject().getName());
        this.authorLabel.setText(" | Author: " +  ((Project) pkg.findProject()).getAuthor());
    }

    public void setupAuthor(String author) {
        this.authorLabel.setText(" | Author: " +  author);
        repaint();
    }

    public void setupProjectName(String project) {
        this.projectLabel.setText(project);
        repaint();
    }

    public void clear() {
        this.authorLabel.setText(" ");
        this.projectLabel.setText(" ");
        repaint();
    }
}
