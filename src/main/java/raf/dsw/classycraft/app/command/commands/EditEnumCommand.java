package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.AbstractProduct.DiagramElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class EditEnumCommand extends AbstractCommand {
    private String oldEnumName;
    private Access oldEnumAccess;
    private Dimension oldEnumDimensions;
    private ArrayList<String> oldEnumValues;
    private String newEnumName;
    private Access newEnumAccess;
    private Dimension newEnumDimensions;
    private ArrayList<String> newEnumValues;
    private DiagramView curDiagram;
    private Enum curEditEnum;

    public EditEnumCommand(String oldEnumName, Access oldEnumAccess, Dimension oldEnumDimensions, ArrayList<String> oldEnumValues, String newEnumName, Access newEnumAccess, Dimension newEnumDimensions, ArrayList<String> newEnumValues, DiagramView curDiagram, Enum curEditEnum) {
        this.oldEnumName = oldEnumName;
        this.oldEnumAccess = oldEnumAccess;
        this.oldEnumDimensions = oldEnumDimensions;
        this.oldEnumValues = oldEnumValues;
        this.newEnumName = newEnumName;
        this.newEnumAccess = newEnumAccess;
        this.newEnumDimensions = newEnumDimensions;
        this.newEnumValues = newEnumValues;
        this.curDiagram = curDiagram;
        this.curEditEnum = curEditEnum;
    }

    @Override
    public void doCommand() {
        curEditEnum.setAccess(newEnumAccess);
        curEditEnum.setName(newEnumName);
        curEditEnum.setValues(newEnumValues);
        curEditEnum.setSize(newEnumDimensions);
    }

    @Override
    public void undoCommand() {
        curEditEnum.setAccess(oldEnumAccess);
        curEditEnum.setName(oldEnumName);
        curEditEnum.setValues(oldEnumValues);
        curEditEnum.setSize(oldEnumDimensions);
    }
}
