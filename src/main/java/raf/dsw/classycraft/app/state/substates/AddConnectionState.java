package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.TemporaryConnection;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.TemporaryConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

public class AddConnectionState implements State {
    private InterClass from;
    private InterClass to;

    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position)) {
                from = ((InterClassPainter) dep).getInterClass();
                return;
            }
        }
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {
        diagramView.popTemporaryConnectionPainter();
        TemporaryConnection temporaryConnection = new TemporaryConnection("Placeholder", from, startingPosition) {
            @Override
            public InterClass getFrom() {
                return super.getFrom();
            }

            @Override
            public Point2D getTo() {
                return super.getTo();
            }
        };

        TemporaryConnectionPainter connectionPainter = new TemporaryConnectionPainter(temporaryConnection);
        diagramView.addDiagramElementPainter(connectionPainter);
    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {
        diagramView.popTemporaryConnectionPainter();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(endingPosition)) {
                if (from != ((InterClassPainter) dep).getInterClass())
                    to = ((InterClassPainter) dep).getInterClass();
                break;
            }
        }

        MainFrame.getInstance().setCurDiagramView(diagramView);
        MainFrame.getInstance().setCurFrom(from);
        MainFrame.getInstance().setCurTo(to);

        MainFrame.getInstance().getChooseConnectionFrame().setVisible(true);

        /*Connection connection = new Connection("Placeholder", from, to) {
            @Override
            public InterClass getFrom() {
                return super.getFrom();
            }

            @Override
            public InterClass getTo() {
                return super.getTo();
            }
        };

        ConnectionPainter connectionPainter = new ConnectionPainter(connection);
        diagramView.addDiagramElementPainter(connectionPainter);*/

        from = null;
        to = null;
    }

    @Override
    public void classyMouseWheelMoved(Point2D position, DiagramView diagramView, MouseWheelEvent e) {

    }

}
