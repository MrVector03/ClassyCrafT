package raf.dsw.classycraft.app.gui.swing.view.popframes;

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
