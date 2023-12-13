package raf.dsw.classycraft.app.gui.swing.view.MainSpace;

import javax.swing.*;
import java.awt.*;

public class DiagramScroll extends JScrollPane {
    public DiagramScroll(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
    }
}
