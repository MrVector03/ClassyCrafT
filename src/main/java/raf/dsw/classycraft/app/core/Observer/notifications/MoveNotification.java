package raf.dsw.classycraft.app.core.Observer.notifications;

import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MoveNotification {
    private final DiagramView diagramView;
    private final ArrayList<DiagramElementPainter> changedPainters;
    private final Point2D change;

    public MoveNotification(DiagramView diagramView, ArrayList<DiagramElementPainter> changedPainters, Point2D change) {
        this.diagramView = diagramView;
        this.changedPainters = changedPainters;
        this.change = change;
    }

    public DiagramView getDiagramView() {
        return diagramView;
    }

    public Point2D getChange() {
        return change;
    }

    public ArrayList<DiagramElementPainter> getChangedPainters() {
        return changedPainters;
    }
}
