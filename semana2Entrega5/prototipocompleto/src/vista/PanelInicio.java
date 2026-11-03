package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Pantalla de bienvenida: ofrece iniciar sesión o crear cuenta.
public class PanelInicio extends JPanel {
    // Tamaño y posición de la imagen decorativa (lado derecho de la pantalla)
    private static final int IMAGEN_ANCHO = 480;
    private static final int IMAGEN_ALTO = 420;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 60;
    private ImageIcon logo;

    public PanelInicio(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        // El HTML permite pintar solo "QUIMIX" de otro color dentro del mismo título
        JLabel titulo = new JLabel("<html>Bienvenido a <font color='#3c24c5'>QUIMIX</font>.</html>");
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Selecciona qué deseas hacer:"));
        add(Box.createVerticalStrut(12));

        // Carga la imagen; si no se encuentra, la pantalla funciona igual sin ella
        URL imagen = getClass().getResource("/vista/imagenes/logo.png");
        if (imagen != null) {
            logo = new ImageIcon(imagen);
        }

        JButton entrar = new JButton("Iniciar sesión");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> gestor.mostrarLogin());
        add(entrar);

        JButton registrar = new JButton("Crear cuenta");
        registrar.setMaximumSize(new Dimension(200, 35));
        registrar.addActionListener(e -> gestor.mostrarRegistro());
        add(registrar);

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Dibuja la imagen pegada al lado derecho, sin importar el ancho de la ventana
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = getWidth() - IMAGEN_ANCHO - IMAGEN_MARGEN_DERECHO;
        if (logo != null && logo.getIconWidth() > 0) {
            g.drawImage(logo.getImage(), x, IMAGEN_Y, IMAGEN_ANCHO, IMAGEN_ALTO, this);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
    }
}
