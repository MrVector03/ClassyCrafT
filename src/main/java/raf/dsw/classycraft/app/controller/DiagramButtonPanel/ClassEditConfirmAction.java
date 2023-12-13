package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassEditConfirmAction extends AbstractClassyAction {
    public ClassEditConfirmAction() {
        putValue(NAME, "Confirm class values");
        putValue(SHORT_DESCRIPTION, "Confirm the class values");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newICName = MainFrame.getInstance().getEditClassFrame().getEicTextField().getText();
        Access newICAccess = (Access)MainFrame.getInstance().getEditClassFrame().getEicComboBox().getSelectedItem();
        String newICContent = MainFrame.getInstance().getEditClassFrame().getEicTextArea().getText();
        boolean isAbs = MainFrame.getInstance().getEditClassFrame().getAbstractCheckBox().isSelected();

        MainFrame.getInstance().getEditClassFrame().setVisible(false);
        MainFrame.getInstance().getEditClassFrame().getEicTextField().setText("");

        if(newICName.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CLASS_NAME_CANNOT_BE_EMPTY", MessageType.ERROR);
            return;
        }

        String longestRow = newICAccess + " class (A)" + newICName;
        int rowCount = 0;
        ArrayList<ClassContent> attributes = new ArrayList<ClassContent>();
        ArrayList<Method> methods = new ArrayList<Method>();

        Scanner scanner = new Scanner(newICContent);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            rowCount++;

            if(line.length() > longestRow.length())
                longestRow = line;

            String arr[] = line.split(" ");

            ClassContent newClassContent;

            if(Access.fromString(arr[0]) == null || arr.length < 3) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Format attributes as: public int attribute\nFormat methods as: public int method()", MessageType.NOTIFICATION);
                return;
            }

            if(arr[arr.length - 1].charAt(arr[arr.length - 1].length() - 1) == ')') {
                String arr2[] = arr[2].split("\\(");
                newClassContent = new Method(Access.valueOf(arr[0].toUpperCase()), arr[1], arr2[0]);
                methods.add((Method)newClassContent);
            }
            else {
                newClassContent = new ClassContent(Access.valueOf(arr[0].toUpperCase()), arr[1], arr[2]);
                attributes.add(newClassContent);
            }
        }
        scanner.close();

        attributes.addAll(methods);

        Dimension interClassDimension = new Dimension(100 + longestRow.length()*5, 100 + rowCount*15);

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Class curEditClass = ((Class)MainFrame.getInstance().getPackageView().getCurEditElement());

            curEditClass.setAbstract(isAbs);
            curEditClass.setAccess(newICAccess);
            curEditClass.setName(newICName);
            curEditClass.setClassContents(attributes);
            curEditClass.setSize(interClassDimension);
        }
        else {
            MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new InterClassPainter(new Class(newICName, newICAccess,
                    new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()), interClassDimension,
                    attributes, isAbs)));
        }
        System.out.println("finished painting class");
    }
}
