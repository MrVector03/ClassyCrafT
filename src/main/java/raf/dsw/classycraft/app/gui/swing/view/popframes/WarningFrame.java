package raf.dsw.classycraft.app.gui.swing.view.popframes;

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
