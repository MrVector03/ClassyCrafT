package raf.dsw.classycraft.app.controller.tree;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteNodeAction extends AbstractClassyAction implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();

    public DeleteNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/deleteNode.png"));
        putValue(NAME, "Delete node");
        putValue(SHORT_DESCRIPTION, "Delete node from project tree");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).removeNode(selected);
        if (selected.getClassyNode() instanceof Package || selected.getClassyNode() instanceof Project)
            notifySubscribers("TOTAL_CLEAR");
        else if (selected.getClassyNode() instanceof Diagram)
            notifySubscribers("DELETED_D");
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : subscribers)
            subscriber.update(notification);
    }
}
