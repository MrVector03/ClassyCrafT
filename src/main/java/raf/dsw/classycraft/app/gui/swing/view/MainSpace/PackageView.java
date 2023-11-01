package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;

import javax.swing.*;

public class PackageView extends JPanel {
    private final HeadlineSpace headlineSpace;
    private final TabbedPane tabbedPane;

    public PackageView(HeadlineSpace headlineSpace, TabbedPane tabbedPane) {
        this.headlineSpace = headlineSpace;
        this.tabbedPane = tabbedPane;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(headlineSpace);
        add(tabbedPane);
    }

    public void setupView(Package openedPackage) {
        this.tabbedPane.loadPackage(openedPackage);
        this.headlineSpace.setup(tabbedPane.getClassyProject().getName(), tabbedPane.getClassyProject().getAuthor());
        repaint();
    }

    public void totalClear() {
        this.headlineSpace.clear();
        this.tabbedPane.clear();
        this.tabbedPane.revalidate();
    }
}
