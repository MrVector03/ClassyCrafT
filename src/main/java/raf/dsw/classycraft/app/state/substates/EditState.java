package raf.dsw.classycraft.app.state.substates;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Aggregation;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Composition;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Dependency;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.Connections.Generalization;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.*;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditClassFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditAggregationFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditCompositionFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditDependencyFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditGeneralizationFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditEnumFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditInterfaceFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class EditState implements State {
    @Override
    public void classyMouseClicked(Point2D position, DiagramView diagramView) {
        List<DiagramElementPainter> toEdit = new ArrayList<>();
        int a = 5;
        Rectangle2D rectangle = new Rectangle2D.Double(position.getX()-5, position.getY()-5, a*2, a*2);

        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
            if (dep instanceof InterClassPainter && dep.elementAt(position))
                toEdit.add(dep);
            else if (dep instanceof ConnectionPainter && ((ConnectionPainter) dep).elementAtShape(rectangle))
                toEdit.add(dep);
        }

        if(toEdit.size() == 0)
            return;

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

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Enum) {
            Enum enumToEdit = (Enum)((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditEnumFrame eef =  MainFrame.getInstance().getEditEnumFrame();

            eef.getEicComboBox().setSelectedItem(enumToEdit.getAccess().toString());
            eef.getEicTextField().setText(enumToEdit.getName());

            String valueText = "";
            for(String value : enumToEdit.getValues())
                valueText += value + "\n";

            eef.getEicTextArea().setText(valueText);

            MainFrame.getInstance().getPackageView().setCurEditElement(enumToEdit);

            eef.setVisible(true);
        }

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Interface) {
            Interface interfaceToEdit = (Interface) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditInterfaceFrame eif =  MainFrame.getInstance().getEditInterfaceFrame();

            eif.getEicComboBox().setSelectedItem(interfaceToEdit.getAccess().toString());
            eif.getEicTextField().setText(interfaceToEdit.getName());

            String ccText = "";
            for(Method cc : interfaceToEdit.getMethods())
                ccText += cc.toStringNames() + "\n";

            eif.getEicTextArea().setText(ccText);

            MainFrame.getInstance().getPackageView().setCurEditElement(interfaceToEdit);

            eif.setVisible(true);
        }

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Generalization) {
            Generalization generalizationToEdit = (Generalization) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditGeneralizationFrame egf =  MainFrame.getInstance().getEditGeneralizationFrame();

            egf.getNameTextField().setText(generalizationToEdit.getName());

            MainFrame.getInstance().getPackageView().setCurEditElement(generalizationToEdit);

            egf.setVisible(true);
        }

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Aggregation) {
            Aggregation aggregationToEdit = (Aggregation) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditAggregationFrame eaf =  MainFrame.getInstance().getEditAggregationFrame();

            eaf.getNameTextField().setText(aggregationToEdit.getName());
            eaf.getVarNameTextField().setText(aggregationToEdit.getVarName());
            eaf.getCardFromTextField().setText("" + aggregationToEdit.getCardFrom());
            eaf.getCardToTextField().setText("" + aggregationToEdit.getCardTo());

            MainFrame.getInstance().getPackageView().setCurEditElement(aggregationToEdit);

            eaf.setVisible(true);
        }

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Composition) {
            Composition compositionToEdit = (Composition) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditCompositionFrame ecf =  MainFrame.getInstance().getEditCompositionFrame();

            ecf.getNameTextField().setText(compositionToEdit.getName());
            ecf.getVarNameTextField().setText(compositionToEdit.getVarName());
            ecf.getCardFromTextField().setText("" + compositionToEdit.getCardFrom());
            ecf.getCardToTextField().setText("" + compositionToEdit.getCardTo());

            MainFrame.getInstance().getPackageView().setCurEditElement(compositionToEdit);

            ecf.setVisible(true);
        }

        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Dependency) {
            Dependency dependencyToEdit = (Dependency) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
            EditDependencyFrame edf =  MainFrame.getInstance().getEditDependencyFrame();

            edf.getNameTextField().setText(dependencyToEdit.getName());
            edf.getTypeTextfield().setText(dependencyToEdit.getType());

            MainFrame.getInstance().getPackageView().setCurEditElement(dependencyToEdit);

            edf.setVisible(true);
        }
    }

    @Override
    public void classyMousePressed(Point2D position, DiagramView diagramView) {
//        List<DiagramElementPainter> toEdit = new ArrayList<>();
//        int a = 2;
//        Rectangle2D rectangle = new Rectangle2D.Double(position.getX()-a, position.getY()-a, 10, 10);
//
//        for (DiagramElementPainter dep : diagramView.getDiagramElementPainters()) {
//            if (dep instanceof InterClassPainter && dep.elementAt(position))
//                toEdit.add(dep);
//            else if (dep instanceof ConnectionPainter && ((ConnectionPainter) dep).elementAtShape(rectangle))
//                toEdit.add(dep);
//        }
//
//        if(toEdit.size() == 0)
//            return;
//
//        if(toEdit.size() > 1) {
//            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CAN_EDIT_ONLY_ONE_ELEMENT", MessageType.ERROR);
//            return;
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Class) {
//            Class classToEdit = (Class)((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditClassFrame ecf =  MainFrame.getInstance().getEditClassFrame();
//
//            ecf.getAbstractCheckBox().setSelected(classToEdit.isAbstract());
//            ecf.getEicComboBox().setSelectedItem(classToEdit.getAccess().toString());
//            ecf.getEicTextField().setText(classToEdit.getName());
//
//            String ccText = "";
//            for(ClassContent cc : classToEdit.getClassContents())
//                ccText += cc.toStringNames() + "\n";
//
//            ecf.getEicTextArea().setText(ccText);
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(classToEdit);
//
//            ecf.setVisible(true);
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Enum) {
//            Enum enumToEdit = (Enum)((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditEnumFrame eef =  MainFrame.getInstance().getEditEnumFrame();
//
//            eef.getEicComboBox().setSelectedItem(enumToEdit.getAccess().toString());
//            eef.getEicTextField().setText(enumToEdit.getName());
//
//            String valueText = "";
//            for(String value : enumToEdit.getValues())
//                valueText += value + "\n";
//
//            eef.getEicTextArea().setText(valueText);
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(enumToEdit);
//
//            eef.setVisible(true);
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Interface) {
//            Interface interfaceToEdit = (Interface) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditInterfaceFrame eif =  MainFrame.getInstance().getEditInterfaceFrame();
//
//            eif.getEicComboBox().setSelectedItem(interfaceToEdit.getAccess().toString());
//            eif.getEicTextField().setText(interfaceToEdit.getName());
//
//            String ccText = "";
//            for(Method cc : interfaceToEdit.getMethods())
//                ccText += cc.toStringNames() + "\n";
//
//            eif.getEicTextArea().setText(ccText);
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(interfaceToEdit);
//
//            eif.setVisible(true);
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Generalization) {
//            Generalization generalizationToEdit = (Generalization) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditGeneralizationFrame egf =  MainFrame.getInstance().getEditGeneralizationFrame();
//
//            egf.getNameTextField().setText(generalizationToEdit.getName());
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(generalizationToEdit);
//
//            egf.setVisible(true);
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Aggregation) {
//            Aggregation aggregationToEdit = (Aggregation) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditAggregationFrame eaf =  MainFrame.getInstance().getEditAggregationFrame();
//
//            eaf.getNameTextField().setText(aggregationToEdit.getName());
//            eaf.getVarNameTextField().setText(aggregationToEdit.getVarName());
//            eaf.getCardFromTextField().setText("" + aggregationToEdit.getCardFrom());
//            eaf.getCardToTextField().setText("" + aggregationToEdit.getCardTo());
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(aggregationToEdit);
//
//            eaf.setVisible(true);
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Composition) {
//            Composition compositionToEdit = (Composition) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditCompositionFrame ecf =  MainFrame.getInstance().getEditCompositionFrame();
//
//            ecf.getNameTextField().setText(compositionToEdit.getName());
//            ecf.getVarNameTextField().setText(compositionToEdit.getVarName());
//            ecf.getCardFromTextField().setText("" + compositionToEdit.getCardFrom());
//            ecf.getCardToTextField().setText("" + compositionToEdit.getCardTo());
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(compositionToEdit);
//
//            ecf.setVisible(true);
//        }
//
//        if(((DiagramElementPainter)toEdit.get(0)).getDiagramElement() instanceof Dependency) {
//            Dependency dependencyToEdit = (Dependency) ((DiagramElementPainter)toEdit.get(0)).getDiagramElement();
//            EditDependencyFrame edf =  MainFrame.getInstance().getEditDependencyFrame();
//
//            edf.getNameTextField().setText(dependencyToEdit.getName());
//            edf.getTypeTextfield().setText(dependencyToEdit.getType());
//
//            MainFrame.getInstance().getPackageView().setCurEditElement(dependencyToEdit);
//
//            edf.setVisible(true);
//        }
    }

    @Override
    public void classyMouseDragged(Point2D startingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseReleased(Point2D endingPosition, DiagramView diagramView) {

    }

    @Override
    public void classyMouseWheelMoved(Point2D position, DiagramView diagramView, MouseWheelEvent e) {

    }
}
