package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.StateManager;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MoveInterClassCommand extends AbstractCommand {
    private Point2D change;
    private DiagramView curDiagramView;
    private ArrayList<DiagramElementPainter> changedPainters = new ArrayList<DiagramElementPainter>();
    private ArrayList<DiagramElementPainter> selectedPainters = new ArrayList<DiagramElementPainter>();

    public MoveInterClassCommand(Point2D change, DiagramView curDiagramView, ArrayList<DiagramElementPainter> changedPainters, ArrayList<DiagramElementPainter> selectedPainters) {
        this.change = new Point2D.Double(change.getX(), change.getY());
        this.curDiagramView = curDiagramView;

        this.changedPainters.addAll(changedPainters);
        this.selectedPainters.addAll(selectedPainters);
    }

    @Override
    public void doCommand() {
        for (DiagramElementPainter dep : changedPainters) {
            if(dep instanceof InterClassPainter) {
                if(selectedPainters.contains(dep))
                    ((InterClassPainter) dep).getInterClass().changePosition(new Point2D.Double(change.getX(), change.getY()));
            }
        }
    }

    @Override
    public void undoCommand() {
        for (DiagramElementPainter dep : changedPainters) {
            if(dep instanceof InterClassPainter) {
                if(selectedPainters.contains(dep))
                    ((InterClassPainter) dep).getInterClass().changePosition(new Point2D.Double(-change.getX(), -change.getY()));
            }
        }
    }
}
