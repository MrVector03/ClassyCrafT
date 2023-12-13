package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.MessageGenerator.Message;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyTree;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.products.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.HeadlineSpace;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.TabbedPane;
import raf.dsw.classycraft.app.gui.swing.view.popframes.*;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditAggregationFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditCompositionFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditDependencyFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.EditConnectionFrames.EditGeneralizationFrame;
import raf.dsw.classycraft.app.gui.swing.view.popframes.alerts.AlertFactory;
import raf.dsw.classycraft.app.gui.swing.view.popframes.alerts.AlertFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    private ActionManager actionManager;
    private ClassyTreeImplementation classyTree;


    private AboutUsFrame auFrame;
    private ChangeAuthorFrame caFrame;
    private ChoosePackageOrDiagramFrame pordFrame;
    private EditClassFrame editClassFrame;
    private EditInterfaceFrame editInterfaceFrame;
    private EditEnumFrame editEnumFrame;
    private ChooseInterClassFrame chooseInterClassFrame;
    private ChooseConnectionFrame chooseConnectionFrame;
    private EditGeneralizationFrame editGeneralizationFrame;
    private EditAggregationFrame editAggregationFrame;
    private EditCompositionFrame editCompositionFrame;
    private EditDependencyFrame editDependencyFrame;

    private TabbedPane tabbedPane;
    private HeadlineSpace headlineSpace;
    private PackageView packageView;

    //buduca polja za sve komponente view-a na glavnom prozoru

    private AlertFrame alertFrame;

    private DiagramView curDiagramView;
    private Point2D curMousePos;
    private InterClass curFrom;
    private InterClass curTo;


    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();
        classyTree = new ClassyTreeImplementation();

        tabbedPane = new TabbedPane();
        headlineSpace = new HeadlineSpace();
        packageView = new PackageView(headlineSpace, tabbedPane);

        //GUI elements
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2 + 50);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        auFrame = new AboutUsFrame();
        caFrame = new ChangeAuthorFrame();
        pordFrame = new ChoosePackageOrDiagramFrame();
        editClassFrame = new EditClassFrame();
        editInterfaceFrame = new EditInterfaceFrame();
        editEnumFrame = new EditEnumFrame();
        chooseInterClassFrame = new ChooseInterClassFrame();
        chooseConnectionFrame = new ChooseConnectionFrame();
        editGeneralizationFrame = new EditGeneralizationFrame();
        editAggregationFrame = new EditAggregationFrame();
        editCompositionFrame = new EditCompositionFrame();
        editDependencyFrame = new EditDependencyFrame();


        JTree projectTreeView = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepositoryImplementation().getRoot());
        // JPanel workView = new JPanel();

        JScrollPane treeScrollPane = new JScrollPane(projectTreeView);
        treeScrollPane.setMinimumSize(new Dimension(200, 150));
        JSplitPane mainSplitFrame = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, packageView);
        getContentPane().add(mainSplitFrame, BorderLayout.CENTER);
        mainSplitFrame.setDividerLocation(250);
        mainSplitFrame.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    public AboutUsFrame getAuFrame() {
        return auFrame;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public ChangeAuthorFrame getCaFrame() {
        return caFrame;
    }

    public ChoosePackageOrDiagramFrame getPordFrame() {
        return pordFrame;
    }

    public PackageView getPackageView() {
        return packageView;
    }

    public EditClassFrame getEditClassFrame() {
        return editClassFrame;
    }

    public EditInterfaceFrame getEditInterfaceFrame() {
        return editInterfaceFrame;
    }

    public EditEnumFrame getEditEnumFrame() {
        return editEnumFrame;
    }

    @Override
    public void update(Object notification) {
        if (notification instanceof Message) {
            AlertFactory alertFactory = new AlertFactory();
            alertFrame = alertFactory.getAlert((Message) notification);
            alertFrame.showMessage();
        }
    }

    public ClassyTree getClassyTree() {
        return classyTree;
    }

    public DiagramView getCurDiagramView() {
        return curDiagramView;
    }

    public void setCurDiagramView(DiagramView curDiagramView) {
        this.curDiagramView = curDiagramView;
    }

    public Point2D getCurMousePos() {
        return curMousePos;
    }

    public void setCurMousePos(Point2D curMousePos) {
        this.curMousePos = curMousePos;
    }

    public InterClass getCurFrom() {
        return curFrom;
    }

    public void setCurFrom(InterClass curFrom) {
        this.curFrom = curFrom;
    }

    public InterClass getCurTo() {
        return curTo;
    }

    public void setCurTo(InterClass curTo) {
        this.curTo = curTo;
    }

    public ChooseInterClassFrame getChooseInterClassFrame() {
        return chooseInterClassFrame;
    }

    public ChooseConnectionFrame getChooseConnectionFrame() {
        return chooseConnectionFrame;
    }

    public EditGeneralizationFrame getEditGeneralizationFrame() {
        return editGeneralizationFrame;
    }

    public EditAggregationFrame getEditAggregationFrame() {
        return editAggregationFrame;
    }

    public EditCompositionFrame getEditCompositionFrame() {
        return editCompositionFrame;
    }

    public EditDependencyFrame getEditDependencyFrame() {
        return editDependencyFrame;
    }
}
