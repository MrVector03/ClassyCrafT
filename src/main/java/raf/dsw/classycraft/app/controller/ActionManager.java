package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.DiagramButtonPanel.*;
import raf.dsw.classycraft.app.controller.stateActions.*;
import raf.dsw.classycraft.app.controller.tree.*;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeAuthorConfirmAction changeAuthorConfirmAction;
    private PackageChosenAction packageChosenAction;
    private DiagramChosenAction diagramChosenAction;

    private AddInterClassAction addInterClassAction;
    private AddConnectionAction addConnectionAction;
    private AddElementAction addElementAction;
    private DeleteAction deleteAction;
    private SelectionAction selectionAction;
    private ClassEditConfirmAction classEditConfirmAction;
    private InterfaceEditConfirmAction interfaceEditConfirmAction;
    private EnumEditConfirmAction enumEditConfirmAction;
    private CopyInterClassAction copyInterClassAction;
    private ClassChosenAction classChosenAction;
    private InterfaceChosenAction interfaceChosenAction;
    private EnumChosenAction enumChosenAction;

    public ActionManager() {
        aboutUsAction = new AboutUsAction();
        exitAction = new ExitAction();

        addNodeAction = new AddNodeAction();
        deleteNodeAction = new DeleteNodeAction();
        changeAuthorAction = new ChangeAuthorAction();
        changeAuthorConfirmAction = new ChangeAuthorConfirmAction();
        packageChosenAction = new PackageChosenAction();
        diagramChosenAction = new DiagramChosenAction();

        addInterClassAction = new AddInterClassAction();
        addConnectionAction = new AddConnectionAction();
        addElementAction = new AddElementAction();
        deleteAction = new DeleteAction();
        selectionAction = new SelectionAction();
        copyInterClassAction = new CopyInterClassAction();

        classEditConfirmAction = new ClassEditConfirmAction();
        interfaceEditConfirmAction = new InterfaceEditConfirmAction();
        enumEditConfirmAction = new EnumEditConfirmAction();

        classChosenAction = new ClassChosenAction();
        interfaceChosenAction = new InterfaceChosenAction();
        enumChosenAction = new EnumChosenAction();
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AddNodeAction getAddNodeAction() {
        return addNodeAction;
    }

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public ChangeAuthorAction getChangeAuthorAction() {
        return changeAuthorAction;
    }

    public ChangeAuthorConfirmAction getChangeAuthorConfirmAction() {
        return changeAuthorConfirmAction;
    }

    public PackageChosenAction getPackageChosenAction() {
        return packageChosenAction;
    }

    public DiagramChosenAction getDiagramChosenAction() {
        return diagramChosenAction;
    }

    public AddInterClassAction getAddInterClassAction() {
        return addInterClassAction;
    }

    public AddConnectionAction getAddConnectionAction() {
        return addConnectionAction;
    }

    public AddElementAction getAddElementAction() {
        return addElementAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public SelectionAction getSelectionAction() {
        return selectionAction;
    }

    public ClassEditConfirmAction getClassEditConfirmAction() {
        return classEditConfirmAction;
    }

    public CopyInterClassAction getCopyInterClassAction() {
        return copyInterClassAction;
    }

    public ClassChosenAction getClassChosenAction() {
        return classChosenAction;
    }

    public InterfaceChosenAction getInterfaceChosenAction() {
        return interfaceChosenAction;
    }

    public InterfaceEditConfirmAction getInterfaceEditConfirmAction() {
        return interfaceEditConfirmAction;
    }

    public EnumChosenAction getEnumChosenAction() {
        return enumChosenAction;
    }

    public EnumEditConfirmAction getEnumEditConfirmAction() {
        return enumEditConfirmAction;
    }
}
