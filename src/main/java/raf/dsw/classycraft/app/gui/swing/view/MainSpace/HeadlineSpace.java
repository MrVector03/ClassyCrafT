package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

import javax.swing.*;
import java.awt.*;

public class HeadlineSpace extends JPanel {
    private JLabel projectLabel = new JLabel(" ");
    private JLabel authorLabel = new JLabel(" ");

    public HeadlineSpace() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        projectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        authorLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(projectLabel);
        add(authorLabel);
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
