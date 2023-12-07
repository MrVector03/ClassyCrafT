package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Observer.ISubscriber;
import raf.dsw.classycraft.app.core.Observer.notifications.MoveNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.SubscriberNotification;
import raf.dsw.classycraft.app.core.Observer.notifications.Type;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.ClassyNode;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Access;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.Connection;
import raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction.InterClass;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.Diagram;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.Class;
import raf.dsw.classycraft.app.core.ProjectTreeImplementation.DiagramImplementation.InterClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.DiagramPainters.*;
import raf.dsw.classycraft.app.gui.swing.view.MainSpace.listeners.ClassyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class DiagramView extends JPanel implements ISubscriber {
    private final TabbedPane tabbedPane;
    private final Diagram diagram;
    private String name;
    private ArrayList<DiagramElementPainter> diagramElementPainters = new ArrayList<DiagramElementPainter>();
    private final ArrayList<DiagramElementPainter> selectedElements = new ArrayList<>() {

        @Override
        public void clear() {
            revertAllSelectedElements();
            super.clear();
        }
    };

    private double zoom;
    private Point2D zoomPoint = new Point2D.Double(0, 0);

    public DiagramView(Diagram diagram, TabbedPane tabbedPane) {
        this.diagram = diagram;
        this.name = diagram.getName();
        this.tabbedPane = tabbedPane;
        this.zoom = 1;
        diagram.addSubscriber(this);

        tabbedPane.getClassyPackage().getPackageView().addMoveStateSubscriber(this);

        addMouseListener(new ClassyMouseListener(this));

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                tabbedPane.getClassyPackage().getPackageView().getCurrentState()
                        .classyMouseWheelMoved(e.getPoint(), DiagramView.this, e);
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                tabbedPane.getClassyPackage().getPackageView().getCurrentState()
                        .classyMouseDragged(e.getPoint(), DiagramView.this);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    public Diagram getDiagram() {
        return diagram;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DiagramElementPainter> getDiagramElementPainters() {
        return diagramElementPainters;
    }

    public void addDiagramElementPainter(DiagramElementPainter diagramElementPainter) {
        diagramElementPainters.add(diagramElementPainter);
        if(diagramElementPainter.getDiagramElement() != null)
            diagramElementPainter.getDiagramElement().addSubscriber(this);
        repaint();
    }

    public void popTemporaryConnectionPainter() {
        if (diagramElementPainters.get(diagramElementPainters.size() - 1) instanceof TemporaryConnectionPainter)
            diagramElementPainters.remove(diagramElementPainters.size() - 1);
    }

    // SELECTION STATE - SELECTION MODE
    public void popTemporarySelectionPainter() {
        if (!diagramElementPainters.isEmpty() && diagramElementPainters.get(diagramElementPainters.size() - 1) instanceof TemporarySelectionPainter)
            diagramElementPainters.remove(diagramElementPainters.size() - 1);
    }

    public void removeAllSelectionPainters() {
        diagramElementPainters.removeIf(dep -> dep instanceof TemporarySelectionPainter);
        repaint();
    }

    public void testConnections(InterClass interClass) {
        List<ConnectionPainter> toRemove = new ArrayList<>();
        for (DiagramElementPainter dep : diagramElementPainters) {
            if (dep instanceof ConnectionPainter) {
                Connection connection = ((ConnectionPainter) dep).getConnection();
                if (connection.getTo() == interClass || connection.getFrom() == interClass)
                    toRemove.add((ConnectionPainter) dep);
            }
        }
        for (ConnectionPainter cp : toRemove)
            diagramElementPainters.remove(cp);
    }

    public boolean deleteSelected() {
        if (this.selectedElements.isEmpty())
            return true;
        else {
            for (DiagramElementPainter elementPainter : selectedElements) {
                this.diagramElementPainters.remove(elementPainter);
                if (elementPainter instanceof InterClassPainter)
                    testConnections(((InterClassPainter) elementPainter).getInterClass());
            }
            repaint();
            return false;
        }
    }

    public ArrayList<DiagramElementPainter> getSelectedElements() {
        return selectedElements;
    }

    public void selectElement(DiagramElementPainter elementPainter) {
        if (!this.selectedElements.contains(elementPainter) && !(elementPainter instanceof TemporarySelectionPainter)) {
            this.selectedElements.add(elementPainter);
        }
    }

    public void unselectElement(DiagramElementPainter elementPainter) {
        revertSelected(elementPainter);
        this.selectedElements.remove(elementPainter);
    }

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public void update(Object notification) {
        if (notification instanceof SubscriberNotification) {
            SubscriberNotification n = (SubscriberNotification) notification;
            // NOTIFICATION FOR RENAME = [TYPE.RENAME, DIAGRAM, NEW NAME]
            if (n.getType().equals(Type.RENAME)) {
                this.setName(n.getMsg());
                this.tabbedPane.renameDiagram(n.getClassyNode(), n.getMsg());
            }

            if(n.getType().equals(Type.EDIT_DIAGRAM_ELEMENT))
                repaint();
        } else if (notification instanceof MoveNotification) {
            MoveNotification n = (MoveNotification) notification;
            if (n.getDiagramView() == this)
                this.diagramElementPainters = n.getChangedPainters();
            repaint();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        AffineTransform transform = new AffineTransform();
        transform.scale(zoom,zoom);
        g2d.setTransform(transform);
        System.out.println(zoom);

        for(DiagramElementPainter diagramElementPainter : diagramElementPainters) {
            diagramElementPainter.paint(g2d);
        }
    }

    public void revertSelected(DiagramElementPainter dep) {
        if (dep instanceof TemporarySelectionPainter) return;
        if (dep instanceof InterClassPainter) {
            ((InterClassPainter) dep).getInterClass().setColor(Color.BLACK);
            ((InterClassPainter) dep).getInterClass().setStroke(new BasicStroke());
        } else {
            ((ConnectionPainter) dep).getConnection().setColor(Color.BLACK);
            ((ConnectionPainter) dep).getConnection().setStroke(new BasicStroke());
        }
        repaint();
    }


    public void markSelectedElements() {
        List<ConnectionPainter> connections = new ArrayList<>();
        for (DiagramElementPainter dep : this.diagramElementPainters)
            if (dep instanceof ConnectionPainter) connections.add((ConnectionPainter) dep);
        List<InterClass> selected = new ArrayList<>();

        for (DiagramElementPainter dep : diagramElementPainters) {
            if (dep instanceof TemporarySelectionPainter) continue;
            if (selectedElements.contains(dep)) {
                if (dep instanceof InterClassPainter) {
                    selected.add(((InterClassPainter) dep).getInterClass());
                    ((InterClassPainter) dep).getInterClass().setStroke(new BasicStroke(3.0f));
                    ((InterClassPainter) dep).getInterClass().setColor(Color.BLUE);
                } else {
                    selected.add(null);
                    ((ConnectionPainter) dep).getConnection().setColor(Color.BLUE);
                    ((ConnectionPainter) dep).getConnection().setStroke(new BasicStroke(3.0f));
                }
            } else {
                selected.add(null);
            }
            selectInConnections(connections, selected);
            repaint();
        }
    }

    private void selectInConnections(List<ConnectionPainter> connections, List<InterClass> selected) {

        for (ConnectionPainter cp : connections) {

            int id = diagramElementPainters.indexOf(cp);

            InterClass from = cp.getConnection().getFrom();
            InterClass to = cp.getConnection().getTo();
            if (from == to) System.out.println("OLD SAME");


            if (selected.contains(from) && selected.contains(to)) {
                if (selected.contains(from) && diagramElementPainters.get(selected.indexOf(from)) instanceof InterClassPainter) {
                    from = (((InterClassPainter) diagramElementPainters.get(selected.indexOf(from))).getInterClass());
                }
                if (selected.contains(to) && diagramElementPainters.get(selected.indexOf(to)) instanceof InterClassPainter) {
                    to = (((InterClassPainter) diagramElementPainters.get(selected.indexOf(to))).getInterClass());
                    cp.getConnection().setFrom(from);
                    cp.getConnection().setTo(to);
                } else if (selected.contains(from)) {
                    if (selected.contains(from) && diagramElementPainters.get(selected.indexOf(from)) instanceof InterClassPainter) {
                        from = (((InterClassPainter) diagramElementPainters.get(selected.indexOf(from))).getInterClass());
                    }
                    cp.getConnection().setFrom(from);
                } else {
                    if (selected.contains(to) && diagramElementPainters.get(selected.indexOf(to)) instanceof InterClassPainter)
                        to = (((InterClassPainter) diagramElementPainters.get(selected.indexOf(to))).getInterClass());
                    cp.getConnection().setTo(to);
                }
            }
        }
    }


    private void revertAllSelectedElements() {
        for (DiagramElementPainter dep : selectedElements) {
            if (dep instanceof InterClassPainter) {
                ((InterClassPainter) dep).getInterClass().setColor(Color.BLACK);
                ((InterClassPainter) dep).getInterClass().setStroke(new BasicStroke());
            } else {
                ((ConnectionPainter) dep).getConnection().setColor(Color.BLUE);
                ((ConnectionPainter) dep).getConnection().setStroke(new BasicStroke());
            }
            repaint();
        }
    }

    public void zoomIn() {
        if(zoom < 3) {
            zoom += 0.25;
            if(zoom > 3)
                zoom = 3;
        }
    }

    public void zoomOut() {
        if(zoom > 0.3) {
            zoom -= 0.1;

            if(zoom < 0.3)
                zoom = 0.3;
        }
    }


    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public Point2D getZoomPoint() {
        return zoomPoint;
    }

    public void setZoomPoint(Point2D zoomPoint) {
        this.zoomPoint = zoomPoint;
    }
}
