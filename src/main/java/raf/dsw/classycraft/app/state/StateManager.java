package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.state.substates.*;

public class StateManager {
    private State currentState;

    private final AddInterClassState addInterClassState = new AddInterClassState();
    private final AddConnectionState addConnectionState = new AddConnectionState();
    private final AddElementState addElementState = new AddElementState();
    private final DeleteState deleteState = new DeleteState();
    private final SelectionState selectionState = new SelectionState();

    public void setAddInterClassState() {
        this.currentState = this.addInterClassState;
        System.out.println("set to addInterState");
    }

    public void setAddConnectionState() {
        this.currentState = this.addConnectionState;
        System.out.println("set to addConnectionState");
    }

    public void setAddElementState() {
        this.currentState = this.addElementState;
        System.out.println("set to addElementState");
    }

    public void setDeleteState() {
        this.currentState = this.deleteState;
        System.out.println("set to deleteState");
    }

    public void setSelectionState() {
        this.currentState = this.selectionState;
        System.out.println("set to selectionState");
    }

    public State getCurrentState() {
        return currentState;
    }
}
