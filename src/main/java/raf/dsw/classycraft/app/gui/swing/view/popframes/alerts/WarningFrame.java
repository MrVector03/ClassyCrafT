package raf.dsw.classycraft.app.gui.swing.view.popframes.alerts;

import raf.dsw.classycraft.app.gui.swing.view.popframes.alerts.AlertFrame;

public class WarningFrame extends AlertFrame {

    private final String message;

    public WarningFrame(String message) {
        this.message = message;
    }

    @Override
    public void showMessage() {
        showMessageDialog(null, this.message, "Warning", WARNING_MESSAGE);
    }
}
