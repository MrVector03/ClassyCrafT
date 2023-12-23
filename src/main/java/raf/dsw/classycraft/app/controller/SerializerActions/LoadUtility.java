package raf.dsw.classycraft.app.controller.SerializerActions;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;

import java.util.List;

public class LoadUtility {
    public static void setupConnectionAnchors(ClassyNode node) {
        if (node instanceof Project) {
            for (ClassyNode pkg : ((Project) node).getChildren())
                setupConnectionAnchors(pkg);
        } else if (node instanceof Package) {
            for (ClassyNode el : ((Package) node).getChildren())
                setupConnectionAnchors(el);
        } else if (node instanceof Diagram) {
            Diagram diagram = (Diagram) node;
            List<ClassyNode> diagramChildren = diagram.getChildren();
            for (ClassyNode diagramElement : diagramChildren) {
                if (diagramElement instanceof Connection) {
                    Connection connectionCpy = (Connection) diagramElement;
                    findProperAnchor(connectionCpy, diagramChildren, "from");
                    findProperAnchor(connectionCpy, diagramChildren, "to");
                }
            }
        }
    }

    private static void findProperAnchor(Connection connection, List<ClassyNode> diagramChildren, String type) {
        InterClass anchor;
        if (type.equals("from"))
            anchor = connection.getFrom();
        else
            anchor = connection.getTo();
        for (ClassyNode cn : diagramChildren) {
            if (cn instanceof InterClass) {
                InterClass cpy = (InterClass) cn;
                if (cpy.getName().equals(anchor.getName()) && cpy.getPosition().equals(anchor.getPosition()) && cpy.getSize().equals(anchor.getSize())) {
                    if (type.equals("from")) connection.setFrom(cpy);
                    else connection.setTo(cpy);
                    return;
                }
            }
        }
    }
}
