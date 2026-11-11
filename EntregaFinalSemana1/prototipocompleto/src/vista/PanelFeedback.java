package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.Usuario;

// Resumen del estudiante: muestra sus datos y da acceso al desempeño por tipo de ejercicio.
public class PanelFeedback extends JPanel {
    // Etiquetas que se rellenan en mostrarDatos() con la información del usuario
    private final JLabel titulo = new JLabel("Resumen:");
    private final JLabel etiquetaNombre = new JLabel();
    private final JLabel etiquetaSeccion = new JLabel();
    private final JLabel etiquetaNombreUsuario = new JLabel();

    public PanelFeedback(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(Box.createVerticalStrut(12));
        add(etiquetaNombre);
        add(etiquetaSeccion);
        add(etiquetaNombreUsuario);
        add(Box.createVerticalStrut(12));

        JButton flashcards = new JButton("Desempeño en Flashcards");
        flashcards.setMaximumSize(new Dimension(200, 35));
        flashcards.addActionListener(e -> gestor.mostrarFeedbackFlashcards());
        add(flashcards);

        JButton balanceo = new JButton("Desempeño en Balanceo");
        balanceo.setMaximumSize(new Dimension(200, 35));
        balanceo.addActionListener(e -> gestor.mostrarFeedbackBalanceo());
        add(balanceo);

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
        add(volver);

        // Alinea todo a la izquierda. Antes esto estaba dentro de mostrarDatos(),
        // por lo que se repetía en cada visita y los elementos salían desalineados la primera vez.
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Rellena el resumen con los datos del usuario (lo llama GestorVentanas.mostrarFeedback)
    public void mostrarDatos(Usuario usuario) {
        titulo.setText("Resumen para " + usuario.getNombre() + ":");
        etiquetaNombre.setText("Nombre: " + usuario.getNombre());
        etiquetaNombreUsuario.setText("Nombre de usuario: " + usuario.getNombreUsuario());
        etiquetaSeccion.setText("Sección: " + usuario.getSeccion());
    }
}
