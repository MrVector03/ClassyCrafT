package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyAbstractPainterFactory;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractPainterFactory.ClassyPainterManufacturer;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.products.InterClassPainter;
import raf.dsw.classycraft.app.state.substates.AddInterClassState;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

public class EnumEditConfirmAction extends AbstractClassyAction {
    public EnumEditConfirmAction() {
        putValue(NAME, "Confirm enum values");
        putValue(SHORT_DESCRIPTION, "Confirm the enum values");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newICName = MainFrame.getInstance().getEditEnumFrame().getEicTextField().getText();
        Access newICAccess = (Access)MainFrame.getInstance().getEditEnumFrame().getEicComboBox().getSelectedItem();
        String newICContent = MainFrame.getInstance().getEditEnumFrame().getEicTextArea().getText();

        MainFrame.getInstance().getEditEnumFrame().setVisible(false);
        MainFrame.getInstance().getEditEnumFrame().getEicTextField().setText("");

        if(newICName.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("ENUM_NAME_CANNOT_BE_EMPTY", MessageType.ERROR);
            return;
        }

        String longestRow = newICAccess + " enum " + newICName;
        int rowCount = 0;
        ArrayList<String> values = new ArrayList<String>();

        Scanner scanner = new Scanner(newICContent);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            rowCount++;

            if(line.length() > longestRow.length())
                longestRow = line;

            values.add(line);

            ClassContent newClassContent;
        }
        scanner.close();

        Dimension interClassDimension = new Dimension(100 + longestRow.length()*5, 100 + rowCount*15);

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Enum curEditEnum = ((Enum)MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditEnum.setAccess(newICAccess);
            curEditEnum.setName(newICName);
            curEditEnum.setValues(values);
            curEditEnum.setSize(interClassDimension);

            ((EditState) MainFrame.getInstance().getPackageView().getCurrentState()).notifySubscribers(new StateNotification(MainFrame.getInstance().getCurDiagramView()));

        }
        else {
            ClassyAbstractFactory manufacturer = new ClassyManufacturer();
            ClassyAbstractPainterFactory painterManufacturer = new ClassyPainterManufacturer();
            Enum newEnum = (Enum) manufacturer.createInterClass(InterClassType.ENUM, newICName, newICAccess,
                    new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()),
                    interClassDimension, null, false, values, null);

            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(painterManufacturer.createPainter(newEnum));
        }
        System.out.println("finished painting interface");
    }
}
