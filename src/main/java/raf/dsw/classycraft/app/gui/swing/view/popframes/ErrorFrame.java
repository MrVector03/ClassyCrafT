package raf.dsw.classycraft.app.gui.swing.view.popframes;

public class ErrorFrame extends AlertFrame {
    private final String message;

    public ErrorFrame(String message) {
        this.message = message;
    }

    @Override
    public void showMessage() {
        showMessageDialog(null, this.message, "Error", ERROR_MESSAGE);
    }
}
