package vista;

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

import login.Usuario;

// Menú principal del profesor: consultar alumnos o cerrar sesión.
public class PanelProfesor extends JPanel {
    // Tamaño y posición de la imagen del profesor (lado derecho)
    private static final int IMAGEN_ANCHO = 360;
    private static final int IMAGEN_ALTO = 320;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 90;

    // El texto real se asigna en mostrarDatos() cuando se conoce al usuario
    private final JLabel titulo = new JLabel(" ");
    private ImageIcon logo;           // imagen del profesor
    private ImageIcon fondoEjercicio; // fondo que cubre todo el panel

    public PanelProfesor(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Seleccione qué desea hacer:"));
        add(Box.createVerticalStrut(12));

        // Carga la imagen del profesor y el fondo de este panel
        URL imagen = getClass().getResource("/vista/imagenes/profesor.png");
        if (imagen != null) {
            logo = new ImageIcon(imagen);
        }
        URL rutaFondo = getClass().getResource("/vista/imagenes/fondo1.png");
        if (rutaFondo != null) {
            fondoEjercicio = new ImageIcon(rutaFondo);
        }

        JButton entrar = new JButton("Consultar alumno");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> gestor.mostrarBuscarAlumno());
        add(entrar);

        JButton registrar = new JButton("Cerrar sesión");
        registrar.setMaximumSize(new Dimension(200, 35));
        registrar.addActionListener(e -> gestor.mostrarInicio());
        add(registrar);

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Muestra el nombre del profesor en el saludo (lo llama GestorVentanas.mostrarProfesor)
    public void mostrarDatos(Usuario usuario) {
        titulo.setText("Buenas tardes " + usuario.getNombre() + ".");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Primero el fondo, estirado a todo el panel
        if (fondoEjercicio != null) {
            g.drawImage(fondoEjercicio.getImage(), 0, 0, getWidth(), getHeight(), this);
        }

        // 2. Encima, la imagen del profesor pegada a la derecha
        if (logo != null && logo.getIconWidth() > 0) {
            int x = getWidth() - IMAGEN_ANCHO - IMAGEN_MARGEN_DERECHO;
            g.drawImage(logo.getImage(), x, IMAGEN_Y, IMAGEN_ANCHO, IMAGEN_ALTO, this);
        }
    }
}
