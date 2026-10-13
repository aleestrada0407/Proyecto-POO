import javax.swing.SwingUtilities;

public class VentanaPrincipal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorVentanas gestor = new GestorVentanas();
            gestor.setVisible(true);
        });
    }
}
