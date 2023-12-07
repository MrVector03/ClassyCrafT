package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.controller.stateActions.ZoomOutAction;
import raf.dsw.classycraft.app.state.substates.*;

public class StateManager {
    private State currentState;

    private final AddInterClassState addInterClassState = new AddInterClassState();
    private final AddConnectionState addConnectionState = new AddConnectionState();
    private final AddElementState addElementState = new AddElementState();
    private final DeleteState deleteState = new DeleteState();
    private final SelectionState selectionState = new SelectionState();
    private final CopyInterClassState copyInterClassState = new CopyInterClassState();
    private final MoveState moveState = new MoveState();
    private final EditState editState = new EditState();
    private final ZoomInState zoomInState = new ZoomInState();
    private final ZoomOutState zoomOutState = new ZoomOutState();

    public void setAddInterClassState() {
        this.currentState = this.addInterClassState;
    }

    public void setAddConnectionState() {
        this.currentState = this.addConnectionState;
    }

    public void setAddElementState() {
        this.currentState = this.addElementState;
    }

    public void setDeleteState() {
        this.currentState = this.deleteState;
    }

    public void setSelectionState() {
        this.currentState = this.selectionState;
    }

    public void setCopyInterClassState() {
        this.currentState = this.copyInterClassState;
    }

    public void setMoveState() {
        this.currentState = this.moveState;
    }

    public void setEditState() { this.currentState = this.editState; }
    public void setZoomInState() {
        this.currentState = this.zoomInState;
    }

    public void setZoomOutState() {
        this.currentState = this.zoomOutState;
    }

    public State getCurrentState() {
        return currentState;
    }

}
