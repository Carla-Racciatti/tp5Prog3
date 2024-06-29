import javax.swing.SwingUtilities;
import GUI.GUIRedSocial;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIRedSocial().setVisible(true));
    }
}