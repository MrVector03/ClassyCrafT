package raf.dsw.classycraft.app.core.ProjectTreeImplementation;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.IPublisher;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNodeComposite implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();

    public Diagram(String name) {
        super(name);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        notifySubscribers(new SubscriberNotification(Type.RENAME, this, name));
    }

    public void addToScreen() {
        ((Package) getParent()).addDiagramOnScreen(this);
    }

    public List<ISubscriber> getSubscribers() {
        return this.subscribers;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber subscriber : this.subscribers)
            subscriber.update(notification);
    }

    @Override
    public void addChild(ClassyNode newChild) {
        MainFrame.getInstance().getClassyTree().addDiagramChild(this, newChild);
        super.addChild(newChild);
    }

    @Override
    public void deleteChild(ClassyNode child) {
        super.deleteChild(child);
    }

    public void convertToCode(File file) {
        try {
            if(file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);

                for(ClassyNode ic : getChildren()) {
                    if (ic instanceof InterClass) {

                        ArrayList<Connection> curIcCons = new ArrayList<Connection>();
                        for (ClassyNode con : getChildren())
                            if (con instanceof Connection) {
                                if (((Connection) con).getFrom().equals(ic) || ((Connection) con).getTo().equals(ic)) {
                                    //resavanje svih konekcija povezanih sa ovim elementom
                                    curIcCons.add((Connection) con);
                                }
                            }

                        ((InterClass) ic).convertToCode(fileWriter, curIcCons);
                    }

                    fileWriter.write("\n");
                }

                fileWriter.close();
            }
        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(ex.getMessage(), MessageType.ERROR);
        }
    }
}
