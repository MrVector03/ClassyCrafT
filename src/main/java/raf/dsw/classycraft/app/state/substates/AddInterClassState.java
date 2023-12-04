package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.DiagramElement;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AddInterClassState implements State {

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        Point2D checked_position = position;
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            // if (((InterClassPainter) dep).areaElementAt(position, interClassDimension)) {
            //     checked_position = ((InterClassPainter) dep).enhancedElementAt(position, interClassDimension);
            //     System.out.println("finished enhanced");
            // }
            if (dep.elementAt(position))
                return;
        }
        if (checked_position.getX() == 10000) return;
        position = checked_position;

        MainFrame.getInstance().setCurMousePos(position);
        MainFrame.getInstance().setCurDiagramView(diagramView);

        MainFrame.getInstance().getChooseInterClassFrame().setVisible(true);
        //diagramView.getTabbedPane().getClassyPackage().getPackageView().startSelectionState();
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {

    }
}
