package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.command.commands.AddInterfaceCommand;
import raf.dsw.classycraft.app.command.commands.EditInterfaceCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyAbstractFactory;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.ClassyManufacturer;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.AbstractDiagramElementFactory.InterClassType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;
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

public class InterfaceEditConfirmAction extends AbstractClassyAction {
    public InterfaceEditConfirmAction() {
        putValue(NAME, "Confirm interface values");
        putValue(SHORT_DESCRIPTION, "Confirm the interface values");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newICName = MainFrame.getInstance().getEditInterfaceFrame().getEicTextField().getText();
        Access newICAccess = (Access)MainFrame.getInstance().getEditInterfaceFrame().getEicComboBox().getSelectedItem();
        String newICContent = MainFrame.getInstance().getEditInterfaceFrame().getEicTextArea().getText();

        MainFrame.getInstance().getEditInterfaceFrame().setVisible(false);
        MainFrame.getInstance().getEditInterfaceFrame().getEicTextField().setText("");

        if(newICName.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("INTERFACE_NAME_CANNOT_BE_EMPTY", MessageType.ERROR);
            return;
        }

        String longestRow = newICAccess + " interface " + newICName;
        int rowCount = 0;
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
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Format methods as: public int method()", MessageType.NOTIFICATION);
                return;
            }

            if(arr[arr.length - 1].charAt(arr[arr.length - 1].length() - 1) == ')') {
                String arr2[] = arr[2].split("\\(");
                newClassContent = new Method(Access.valueOf(arr[0].toUpperCase()), arr[1], arr2[0]);
                methods.add((Method)newClassContent);
            }
            else {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("INTERFACE_VALUES_MUST_BE_METHODS", MessageType.ERROR);
                return;
            }
        }
        scanner.close();

        Dimension interClassDimension = new Dimension(100 + longestRow.length()*5, 100 + rowCount*15);

        if(MainFrame.getInstance().getPackageView().getCurrentState() instanceof EditState) {
            Interface curEditInterface = ((Interface)MainFrame.getInstance().getPackageView().getCurEditElement());
            EditInterfaceCommand editInterfaceCommand = new EditInterfaceCommand(curEditInterface.getName(), curEditInterface.getAccess(), curEditInterface.getMethods(), curEditInterface.getSize(), newICName, newICAccess, methods, interClassDimension, MainFrame.getInstance().getCurDiagramView(), curEditInterface);
            ApplicationFramework.getInstance().getCommandManager().addCommand(editInterfaceCommand);

            ((EditState) MainFrame.getInstance().getPackageView().getCurrentState()).notifySubscribers(new StateNotification(MainFrame.getInstance().getCurDiagramView()));
        }
        else {
            AddInterfaceCommand addInterfaceCommand = new AddInterfaceCommand(newICName, newICAccess, methods, interClassDimension, new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()), MainFrame.getInstance().getCurDiagramView());
            ApplicationFramework.getInstance().getCommandManager().addCommand(addInterfaceCommand);
        }
    }
}
