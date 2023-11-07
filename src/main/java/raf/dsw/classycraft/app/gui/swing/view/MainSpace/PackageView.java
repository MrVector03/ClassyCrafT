package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.PackageViewNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;

import javax.swing.*;

public class PackageView extends JPanel implements ISubscriber {
    private final HeadlineSpace headlineSpace;
    private final TabbedPane tabbedPane;

    private Package focusedPackage;

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

    @Override
    public void update(Object notification) {
        PackageViewNotification pvn = (PackageViewNotification) notification;

        if (((PackageViewNotification) notification).getType().equals(Type.OPEN) &&
                pvn.getClassyNode() instanceof Package) {
            focusedPackage = (Package) pvn.getClassyNode();
            totalClear();
            setupView(focusedPackage);

        } else if (pvn.getType().equals(Type.ADD) && pvn.getClassyNode() instanceof Diagram) {
            if (focusedPackage != null) {
                totalClear();
                setupView(focusedPackage);
            }

        } else if (pvn.getType().equals(Type.REMOVE)) {
            if (pvn.getClassyNode() instanceof Diagram) {
                System.out.println("removing diagram");
                tabbedPane.removeDiagram(pvn.getClassyNode());

            } else if (pvn.getClassyNode() instanceof Package) {
                if (pvn.getClassyNode() == tabbedPane.getClassyPackage() || tabbedPane.checkParent(pvn.getClassyNode())) {
                    System.out.println("package in package");
                    totalClear();
                    focusedPackage = null;
                }

            } else if (pvn.getClassyNode() instanceof Project) {
                if (!((Project) pvn.getClassyNode()).getChildren().isEmpty() &&
                        tabbedPane.getClassyProject().equals(pvn.getClassyNode())) {
                    totalClear();
                    focusedPackage = null;
                }
            }
        } else if (pvn.getType().equals(Type.RENAME)) {
                if (focusedPackage != null) {
                    if (pvn.getClassyNode() instanceof Project) {
                        headlineSpace.setupProjectName(pvn.getMsg());
                    } else if (pvn.getClassyNode() instanceof Diagram) {
                        if (focusedPackage != null) {
                            tabbedPane.renameDiagram(pvn.getClassyNode(), pvn.getMsg());
                            repaint();
                        }
                    }
                }

        } else if (pvn.getType().equals(Type.CHANGE_AUTHOR)) {
            if (focusedPackage != null) {
                headlineSpace.setupAuthor(pvn.getMsg());
            }
        }
    }
}
