package raf.dsw.classycraft.app.controller.DiagramButtonPanel;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.InterClassPainter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class InterClassEditConfirmAction extends AbstractClassyAction {
    public InterClassEditConfirmAction() {
        putValue(NAME, "Confirm values");
        putValue(SHORT_DESCRIPTION, "Confirm the interclass values");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newICName = MainFrame.getInstance().getEditInterClassFrame().getEicTextField().getText();

        MainFrame.getInstance().getEditInterClassFrame().setVisible(false);
        MainFrame.getInstance().getEditInterClassFrame().getEicTextField().setText("");

        if(newICName.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("INTERCLASS_NAME_CANNOT_BE_EMPTY", MessageType.ERROR);
            return;
        }

        Dimension interClassDimension = new Dimension(100, 100);
        MainFrame.getInstance().getCurDiagramView().addDiagramElementPainter(new InterClassPainter(new Class(newICName, Access.DEFAULT,
                new Point2D.Double(MainFrame.getInstance().getCurMousePos().getX(), MainFrame.getInstance().getCurMousePos().getY()), interClassDimension,
                new ArrayList<ClassContent>(), false)));
        System.out.println("finished painting");
    }
}
