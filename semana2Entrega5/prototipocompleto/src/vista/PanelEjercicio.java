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

// Pantalla del ejercicio de balanceo (por ahora solo el esqueleto visual).
public class PanelEjercicio extends JPanel {
    private ImageIcon fondoEjercicio;

    public PanelEjercicio(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        JLabel titulo = new JLabel("Ejercicio");
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Realice el siguiente inciso:"));
        add(Box.createVerticalStrut(12));

        // Fondo de la pantalla (se dibuja en paintComponent)
        URL rutaFondo = getClass().getResource("/vista/imagenes/fondo1.png");
        if (rutaFondo != null) {
            fondoEjercicio = new ImageIcon(rutaFondo);
        }

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
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
        if (fondoEjercicio != null) {
            g.drawImage(fondoEjercicio.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
