package raf.dsw.classycraft.app.controller.tree;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChangeAuthorConfirmAction extends AbstractClassyAction implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();

    public ChangeAuthorConfirmAction() {
        putValue(NAME, "Confirm author name");
        putValue(SHORT_DESCRIPTION, "Confirm the new name of the author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();

        String newAuthor = MainFrame.getInstance().getCaFrame().getCaTextField().getText();

        MainFrame.getInstance().getCaFrame().setVisible(false);
        MainFrame.getInstance().getCaFrame().getCaTextField().setText("");

        if(newAuthor.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message("AUTHOR_NAME_CANNOT_BE_EMPTY", MessageType.ERROR, LocalDateTime.now()));
            return;
        }

        ((Project)selected.getClassyNode()).setAuthor(newAuthor);
        notifySubscribers("RENAME_A:" + newAuthor);
        System.out.println("new author:" + newAuthor);
        System.out.println(((Project) selected.getClassyNode()).getAuthor());
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
