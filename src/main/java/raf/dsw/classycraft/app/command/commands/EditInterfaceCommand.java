package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.Observer.notifications.StateNotification;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Interface;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Method;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.state.substates.EditState;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class EditInterfaceCommand extends AbstractCommand {
    private String oldInterfaceName;
    private Access oldInterfaceAccess;
    private ArrayList<Method> oldInterfaceMethods;
    private Dimension oldInterfaceDimensions;
    private String newInterfaceName;
    private Access newInterfaceAccess;
    private ArrayList<Method> newInterfaceMethods;
    private Dimension newInterfaceDimensions;
    private DiagramView curDiagram;
    private Interface curEditInterface;

    public EditInterfaceCommand(String oldInterfaceName, Access oldInterfaceAccess, ArrayList<Method> oldInterfaceMethods, Dimension oldInterfaceDimensions, String newInterfaceName, Access newInterfaceAccess, ArrayList<Method> newInterfaceMethods, Dimension newInterfaceDimensions, DiagramView curDiagram, Interface curEditInterface) {
        this.oldInterfaceName = oldInterfaceName;
        this.oldInterfaceAccess = oldInterfaceAccess;
        this.oldInterfaceMethods = new ArrayList<Method>();
        this.oldInterfaceMethods.addAll(oldInterfaceMethods);
        this.oldInterfaceDimensions = oldInterfaceDimensions;
        this.newInterfaceName = newInterfaceName;
        this.newInterfaceAccess = newInterfaceAccess;
        this.newInterfaceMethods = new ArrayList<Method>();
        this.newInterfaceMethods.addAll(newInterfaceMethods);
        this.newInterfaceDimensions = newInterfaceDimensions;
        this.curDiagram = curDiagram;
        this.curEditInterface = curEditInterface;
    }

    @Override
    public void doCommand() {
        curEditInterface.setAccess(newInterfaceAccess);
        curEditInterface.setName(newInterfaceName);
        curEditInterface.setMethods(newInterfaceMethods);
        curEditInterface.setSize(newInterfaceDimensions);
    }

    @Override
    public void undoCommand() {
        curEditInterface.setAccess(oldInterfaceAccess);
        curEditInterface.setName(oldInterfaceName);
        curEditInterface.setMethods(oldInterfaceMethods);
        curEditInterface.setSize(oldInterfaceDimensions);
    }
}
