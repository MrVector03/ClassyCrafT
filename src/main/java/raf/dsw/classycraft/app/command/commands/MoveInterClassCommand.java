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

    public MoveInterClassCommand(Point2D endingPos,Point2D change, DiagramView curDiagramView) {
    }

    @Override
    public void doCommand() {
    }

    @Override
    public void undoCommand() {
    }
}
