package raf.dsw.classycraft.app.gui.swing.view.popframes.alerts;

import raf.dsw.classycraft.app.gui.swing.view.popframes.alerts.AlertFrame;

public class NotificationFrame extends AlertFrame {
    private final String message;

    public NotificationFrame(String message) {
        this.message = message;
    }

    @Override
    public void showMessage() {
        showMessageDialog(null, this.message, "Notification", INFORMATION_MESSAGE);
    }
}
