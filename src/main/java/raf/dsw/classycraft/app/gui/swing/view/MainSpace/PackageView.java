package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.abstractProduct.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Package;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.State;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PackageView extends JPanel implements ISubscriber {
    private final HeadlineSpace headlineSpace;
    private final TabbedPane tabbedPane;
    private final StateManager stateManager = new StateManager();

    private Package focusedPackage;
    private DiagramElement curEditElement;

    public PackageView(HeadlineSpace headlineSpace, TabbedPane tabbedPane) {
        this.startSelectionState();
        this.headlineSpace = headlineSpace;
        this.tabbedPane = tabbedPane;

        this.tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                startSelectionState();
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(headlineSpace);
        add(tabbedPane);
    }

    public void setupView(Package openedPackage) {
        this.tabbedPane.loadPackage(openedPackage);
        this.focusedPackage = openedPackage;
        this.headlineSpace.setup(tabbedPane.getClassyProject().getName(), tabbedPane.getClassyProject().getAuthor());
        if (!tabbedPane.getClassyPackage().checkSubscriber(this))
            this.tabbedPane.getClassyPackage().addSubscriber(this);
        repaint();
    }

    public void totalClear() {
        this.focusedPackage = null;
        this.headlineSpace.clear();
        this.tabbedPane.clear();
        this.tabbedPane.revalidate();
    }

    // STATE SETUPS
    public void addAddConnectionStateSubscriber(DiagramView diagramView) {
        this.stateManager.getAddConnectionState().addSubscriber(diagramView);
    }

    public void removeAddConnectionStateSubscriber(DiagramView diagramView) {
        this.stateManager.getAddConnectionState().removeSubscriber(diagramView);
    }

    public void addAddInterClassStateSubscriber(DiagramView diagramView) {
        this.stateManager.getAddInterClassState().addSubscriber(diagramView);
    }

    public void removeAddInterClassStateSubscriber(DiagramView diagramView) {
        this.stateManager.getAddInterClassState().removeSubscriber(diagramView);
    }

    public void addCopyInterClassStateSubscriber(DiagramView diagramView) {
        this.stateManager.getCopyInterClassState().addSubscriber(diagramView);
    }

    public void removeCopyInterClassStateSubscriber(DiagramView diagramView) {
        this.stateManager.getCopyInterClassState().removeSubscriber(diagramView);
    }

    public void addDeleteStateSubscriber(DiagramView diagramView) {
        this.stateManager.getDeleteState().addSubscriber(diagramView);
    }

    public void removeDeleteStateSubscriber(DiagramView diagramView) {
        this.stateManager.getDeleteState().removeSubscriber(diagramView);
    }

    public void addEditStateSubscriber(DiagramView diagramView) {
        this.stateManager.getEditState().addSubscriber(diagramView);
    }

    public void removeEditStateSubscriber(DiagramView diagramView) {
        this.stateManager.getEditState().removeSubscriber(diagramView);
    }

    public void addMoveStateSubscriber(DiagramView diagramView) {
        this.stateManager.getMoveState().addSubscriber(diagramView);
    }

    public void removeMoveStateSubscriber(DiagramView diagramView) {
        this.stateManager.getMoveState().removeSubscriber(diagramView);
    }

    public void addSelectionStateSubscriber(DiagramView diagramView) {
        this.stateManager.getSelectionState().addSubscriber(diagramView);
    }

    public void removeSelectionStateSubscriber(DiagramView diagramView) {
        this.stateManager.getSelectionState().removeSubscriber(diagramView);
    }

    public void masterAddSubscriber(DiagramView diagramView) {
        addAddConnectionStateSubscriber(diagramView);
        addAddInterClassStateSubscriber(diagramView);
        addCopyInterClassStateSubscriber(diagramView);
        addDeleteStateSubscriber(diagramView);
        addEditStateSubscriber(diagramView);
        addMoveStateSubscriber(diagramView);
        addSelectionStateSubscriber(diagramView);
    }

    public void masterRemoveSubscriber(DiagramView diagramView) {
        removeAddConnectionStateSubscriber(diagramView);
        removeAddInterClassStateSubscriber(diagramView);
        removeCopyInterClassStateSubscriber(diagramView);
        removeDeleteStateSubscriber(diagramView);
        removeEditStateSubscriber(diagramView);
        removeMoveStateSubscriber(diagramView);
        removeSelectionStateSubscriber(diagramView);
    }

    // STATE MEDIATOR METHODS
    public void startAddInterClassState() {
        tabbedPane.removeAllSelectors();
        stateManager.setAddInterClassState();
    }

    public void startAddConnectionState() {
        tabbedPane.removeAllSelectors();
        stateManager.setAddConnectionState();
    }

    public void startAddElementState() {
        tabbedPane.removeAllSelectors();
        stateManager.setAddElementState();
    }

    public void startDeleteState() {
        if (tabbedPane.testSelectors()) {
            stateManager.setDeleteState();
        }
        else {
            tabbedPane.removeAllSelectors();
            stateManager.setSelectionState();
        }
    }

    public void startMoveState() {
        this.tabbedPane.setupTemps();
        stateManager.setMoveState();
    }

    public void startSelectionState() {
        stateManager.setSelectionState();
    }

    public void startCopyInterClassState() {
        stateManager.setCopyInterClassState();
    }
    public void startEditState() {
        stateManager.setEditState();
    }
    public void startZoomInState() {
        stateManager.setZoomInState();
    }

    public void startZoomOutState() {
        stateManager.setZoomOutState();
    }

    public State getCurrentState() {
        return stateManager.getCurrentState();
    }

    public DiagramElement getCurEditElement() {
        return curEditElement;
    }

    public void setCurEditElement(DiagramElement curEditElement) {
        this.curEditElement = curEditElement;
    }

    @Override
    public void update(Object notification) {
        SubscriberNotification n = (SubscriberNotification) notification;

            // NOTIFICATION FOR RENAME = [TYPE.RENAME, PROJECT, NEW NAME]
        if (n.getType().equals(Type.RENAME)) {
            if (n.getClassyNode() == this.tabbedPane.getClassyPackage().findProject())
                this.headlineSpace.setupProjectName(n.getMsg());

            // NOTIFICATION FOR AUTHOR CHANGE = [TYPE.CHANGE_AUTHOR, PROJECT, NEW AUTHOR]
        } else if (n.getType().equals(Type.CHANGE_AUTHOR)) {
            if (n.getClassyNode() == this.tabbedPane.getClassyPackage().findProject())
                this.headlineSpace.setupAuthor(n.getMsg());

            // NOTIFICATION FOR OPENING PACKAGE IN VIEW = [TYPE.ADD, PACKAGE, NONE]
        } else if (n.getType().equals(Type.OPEN)) {
            this.totalClear();
            this.setupView((Package) n.getClassyNode());

            // NOTIFICATION FOR ADDING DIAGRAM = [TYPE.ADD, NEW DIAGRAM, NONE]
        } else if (n.getType().equals(Type.ADD)) {
            if (n.getClassyNode().getParent() == tabbedPane.getClassyPackage() &&
                    tabbedPane.getClassyPackage().getChildren().contains(n.getClassyNode())) {
                this.tabbedPane.addNewDiagram((Diagram) n.getClassyNode());
                this.revalidate();
            }
            // NOTIFICATION FOR REMOVING NODES = [TYPE.ADD, NODE, NONE]
        } else if (n.getType().equals(Type.REMOVE)) {
            if (n.getClassyNode() instanceof Diagram) {
                this.tabbedPane.removeDiagram(n.getClassyNode());
            } else {
                if (tabbedPane.checkParent(n.getClassyNode()))
                    totalClear();
            }
        }
    }

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public Package getFocusedPackage() {
        return focusedPackage;
    }
}
