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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Pantalla del profesor para consultar el informe de los alumnos.
public class PanelBuscarAlumno extends JPanel {
    private ImageIcon fondo;
    // Lista desplegable (aún sin opciones; se llenará al conectar con la base de datos)
    private final JComboBox<String> campoTipo = new JComboBox<>();

    public PanelBuscarAlumno(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        JLabel titulo = new JLabel("Informe de alumnos");
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);

        add(new JLabel("Tipo de usuario:"));
        campoTipo.setMaximumSize(new Dimension(300, 30));
        add(campoTipo);
        add(Box.createVerticalStrut(12));

        // Fondo de la pantalla (se dibuja en paintComponent)
        URL imagen = getClass().getResource("/vista/imagenes/fondo1.png");
        if (imagen != null) {
            fondo = new ImageIcon(imagen);
        }

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarProfesor());
        add(volver);

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Pinta el fondo estirado a todo el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
