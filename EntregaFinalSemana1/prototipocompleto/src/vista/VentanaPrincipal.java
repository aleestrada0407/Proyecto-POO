package vista;

import login.BaseDatos;

import javax.swing.SwingUtilities;

public class VentanaPrincipal {
    public static void main(String[] args) {
        BaseDatos.inicializar();
        SwingUtilities.invokeLater(() -> {
            GestorVentanas gestor = new GestorVentanas();
            gestor.setVisible(true);
        });
    }
}
