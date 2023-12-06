package raf.dsw.classycraft.app.core.ProjectTreeAbstraction.DiagramAbstraction;

import java.awt.*;

public abstract class Connection extends DiagramElement {
    private InterClass from;
    private InterClass to;

    public Connection(String name, InterClass from, InterClass to) {
        super(name);
        super.setColor(Color.BLACK);
        super.setStroke(new BasicStroke());

        this.from = from;
        this.to = to;
    }

    public InterClass getFrom() {
        return from;
    }

    public InterClass getTo() {
        return to;
    }

    public void setFrom(InterClass from) {
        this.from = from;
    }

    public void setTo(InterClass to) {
        this.to = to;
    }
}
