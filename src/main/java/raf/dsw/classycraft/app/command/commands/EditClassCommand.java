package raf.dsw.classycraft.app.command.commands;

import raf.dsw.classycraft.app.command.abstraction.AbstractCommand;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class EditClassCommand extends AbstractCommand {
    private String oldClassName;
    private Access oldClassAccess;
    private ArrayList<ClassContent> oldClassAtributes;
    private Dimension oldClassDimensions;
    private boolean oldClassIsAbs;

    private String newClassName;
    private Access newClassAccess;
    private ArrayList<ClassContent> newClassAtributes;
    private Dimension newClassDimensions;
    private boolean newClassIsAbs;
    private DiagramView curDiagram;
    private Class curEditClass;

    public EditClassCommand(String oldClassName, Access oldClassAccess, ArrayList<ClassContent> oldClassAtributes, Dimension oldClassDimensions, boolean oldClassIsAbs, String newClassName, Access newClassAccess, ArrayList<ClassContent> newClassAtributes, Dimension newClassDimensions, boolean newClassIsAbs, DiagramView curDiagram, Class curEditClass) {
        this.oldClassName = oldClassName;
        this.oldClassAccess = oldClassAccess;
        this.oldClassAtributes = oldClassAtributes;
        this.oldClassDimensions = oldClassDimensions;
        this.oldClassIsAbs = oldClassIsAbs;
        this.newClassName = newClassName;
        this.newClassAccess = newClassAccess;
        this.newClassAtributes = newClassAtributes;
        this.newClassDimensions = newClassDimensions;
        this.newClassIsAbs = newClassIsAbs;
        this.curDiagram = curDiagram;
        this.curEditClass = curEditClass;
    }

    @Override
    public void doCommand() {
        curEditClass.setAbstract(newClassIsAbs);
        curEditClass.setAccess(newClassAccess);
        curEditClass.setName(newClassName);
        curEditClass.setClassContents(newClassAtributes);
        curEditClass.setSize(newClassDimensions);
    }

    @Override
    public void undoCommand() {
        curEditClass.setAbstract(oldClassIsAbs);
        curEditClass.setAccess(oldClassAccess);
        curEditClass.setName(oldClassName);
        curEditClass.setClassContents(oldClassAtributes);
        curEditClass.setSize(oldClassDimensions);
    }
}
