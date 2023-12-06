package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditClassFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class EditState implements State {
    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
        List<DiagramElementPainter> toEdit = new ArrayList<>();
        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep.elementAt(position))
                toEdit.add(dep);
        }

        if(toEdit.size() > 1) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CAN_EDIT_ONLY_ONE_ELEMENT", MessageType.ERROR);
            return;
        }

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Class) {
            Class classToEdit = (Class)((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditClassFrame ecf =  MainFrame.getInstance().getEditClassFrame();

            ecf.getAbstractCheckBox().setSelected(classToEdit.isAbstract());
            ecf.getEicComboBox().setSelectedItem(classToEdit.getAccess().toString());
            ecf.getEicTextField().setText(classToEdit.getName());

            String ccText = "";
            for(ClassContent cc : classToEdit.getClassContents())
                ccText += cc.toStringNames() + "\n";

            ecf.getEicTextArea().setText(ccText);

            MainFrame.getInstance().getPackageView().setCurEditElement(classToEdit);

            ecf.setVisible(true);
        }

    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {

    }
}
