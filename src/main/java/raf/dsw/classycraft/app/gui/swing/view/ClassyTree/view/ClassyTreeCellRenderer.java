package raf.dsw.classycraft.app.gui.swing.view.ClassyTree.view;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        URL imageUrl = null;

        if(((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer)
            imageUrl = getClass().getResource("/images/pexplorer.png");
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Project)
            imageUrl = getClass().getResource("/images/project.png");
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Package)
            imageUrl = getClass().getResource("/images/package.png");
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Diagram)
            imageUrl = getClass().getResource("/images/diagram.png");
        else if (((ClassyTreeItem)value).getClassyNode() instanceof InterClass)
            imageUrl = getClass().getResource("/images/interclass.png");
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Connection)
            imageUrl = getClass().getResource("/images/connection.png");

        Icon icon = null;
        if(imageUrl != null)
            icon = new ImageIcon(imageUrl);

        setIcon(icon);

        return this;
    }
}
