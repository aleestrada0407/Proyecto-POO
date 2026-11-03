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

// Menú principal del estudiante: realizar ejercicios, ver su desempeño o cerrar sesión.
public class PanelEstudiante extends JPanel {
    // Tamaño y posición de la imagen del lado derecho
    private static final int IMAGEN_ANCHO = 480;
    private static final int IMAGEN_ALTO = 420;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 60;

    // El texto real se asigna en mostrarDatos() cuando se conoce al usuario
    private final JLabel titulo = new JLabel(" ");
    private ImageIcon logo;

    public PanelEstudiante(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Selecciona qué deseas hacer:"));
        add(Box.createVerticalStrut(12));

        // Imagen decorativa (se dibuja en paintComponent)
        URL imagen = getClass().getResource("/vista/imagenes/imagen1.png");
        if (imagen != null) {
            logo = new ImageIcon(imagen);
        }

        // Lleva a la pantalla donde se elige el tipo de ejercicio
        // (en el diseño nuevo iba directo a mostrarEjercicio(); aquí se conserva el flujo del prototipo)
        JButton ejercicio = new JButton("Realizar un ejercicio");
        ejercicio.setMaximumSize(new Dimension(200, 35));
        ejercicio.addActionListener(e -> gestor.mostrarElegirEjercicio());
        add(ejercicio);

        // Abre el resumen de desempeño (reemplaza el JOptionPane de ejemplo del prototipo)
        JButton desempeno = new JButton("Ver mi desempeño");
        desempeno.setMaximumSize(new Dimension(200, 35));
        desempeno.addActionListener(e -> gestor.mostrarFeedback());
        add(desempeno);

        JButton volver = new JButton("Cerrar sesión");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarInicio());
        add(volver);

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Muestra el nombre del estudiante en el saludo (lo llama GestorVentanas.mostrarEstudiante)
    public void mostrarDatos(Usuario usuario) {
        titulo.setText("¡Bienvenido, " + usuario.getNombre() + "!");
    }

    // Dibuja la imagen pegada al lado derecho
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = getWidth() - IMAGEN_ANCHO - IMAGEN_MARGEN_DERECHO;
        if (logo != null) {
            g.drawImage(logo.getImage(), x, IMAGEN_Y, IMAGEN_ANCHO, IMAGEN_ALTO, this);
        }
    }
}
