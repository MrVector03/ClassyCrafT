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
    private final boolean rev;
    private final boolean release;
    private final ArrayList<Point2D> lastValidPoints;

    public MoveNotification(DiagramView diagramView, ArrayList<DiagramElementPainter> changedPainters, Point2D change, boolean rev, boolean release, ArrayList<Point2D> lastValidPoints) {
        this.diagramView = diagramView;
        this.changedPainters = changedPainters;
        this.change = change;
        this.rev = rev;
        this.release = release;
        this.lastValidPoints = lastValidPoints;
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

    public boolean isRev() {
        return rev;
    }

    public boolean isRelease() {
        return release;
    }

    public ArrayList<Point2D> getLastValidPoints() {
        return lastValidPoints;
    }
}
