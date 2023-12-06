package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.DiagramButtonPanel.*;
import raf.dsw.classycraft.app.controller.DiagramButtonPanel.Connections.*;
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
    private MoveAction moveAction;
    private EditAction editAction;

    private ClassEditConfirmAction classEditConfirmAction;
    private InterfaceEditConfirmAction interfaceEditConfirmAction;
    private EnumEditConfirmAction enumEditConfirmAction;
    private CopyInterClassAction copyInterClassAction;
    private ClassChosenAction classChosenAction;
    private InterfaceChosenAction interfaceChosenAction;
    private EnumChosenAction enumChosenAction;
    private AggregrationChosenAction aggregrationChosenAction;
    private CompositionChosenAction compositionChosenAction;
    private DependencyChosenAction dependencyChosenAction;
    private GeneralizationChosenAction generalizationChosenAction;
    private AggregationConfirmAction aggregationConfirmAction;
    private CompositionConfirmAction compositionConfirmAction;
    private DependencyConfirmAction dependencyConfirmAction;
    private GeneralizationConfirmAction generalizationConfirmAction;

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
        moveAction = new MoveAction();
        editAction = new EditAction();

        classEditConfirmAction = new ClassEditConfirmAction();
        interfaceEditConfirmAction = new InterfaceEditConfirmAction();
        enumEditConfirmAction = new EnumEditConfirmAction();

        classChosenAction = new ClassChosenAction();
        interfaceChosenAction = new InterfaceChosenAction();
        enumChosenAction = new EnumChosenAction();

        aggregrationChosenAction = new AggregrationChosenAction();
        compositionChosenAction = new CompositionChosenAction();
        dependencyChosenAction = new DependencyChosenAction();
        generalizationChosenAction = new GeneralizationChosenAction();

        aggregationConfirmAction = new AggregationConfirmAction();
        compositionConfirmAction = new CompositionConfirmAction();
        dependencyConfirmAction = new DependencyConfirmAction();
        generalizationConfirmAction = new GeneralizationConfirmAction();
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

    public MoveAction getMoveAction() {
        return moveAction;
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

    public AggregrationChosenAction getAggregrationChosenAction() {
        return aggregrationChosenAction;
    }

    public CompositionChosenAction getCompositionChosenAction() {
        return compositionChosenAction;
    }

    public DependencyChosenAction getDependencyChosenAction() {
        return dependencyChosenAction;
    }

    public GeneralizationChosenAction getGeneralizationChosenAction() {
        return generalizationChosenAction;
    }

    public AggregationConfirmAction getAggregationConfirmAction() {
        return aggregationConfirmAction;
    }

    public CompositionConfirmAction getCompositionConfirmAction() {
        return compositionConfirmAction;
    }

    public DependencyConfirmAction getDependencyConfirmAction() {
        return dependencyConfirmAction;
    }

    public GeneralizationConfirmAction getGeneralizationConfirmAction() {
        return generalizationConfirmAction;
    }

    public EditAction getEditAction() {
        return editAction;
    }
}
