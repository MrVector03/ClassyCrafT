package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;

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

        MainFrame.getInstance().getEditInterfaceFrame().setVisible(false);
        MainFrame.getInstance().getEditInterfaceFrame().getEicTextField().setText("");

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

        Dimension interClassDimension = new Dimension(100 + longestRow.length()*4, 100 + rowCount*7);

        MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new InterClassPainter(new Enum(newICName, newICAccess,
                new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()), interClassDimension,
                values)));

        System.out.println("finished painting interface");
    }
}
