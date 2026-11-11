package vista;

// Importa las herramientas para crear y organizar la interfaz.
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Hereda de JPanel para funcionar como una pantalla del programa.
public class PanelFeedbackFlashcards extends JPanel {
    // Etiqueta donde después se mostrará el porcentaje del estudiante.
    private final JLabel porcentaje = new JLabel("Desempeño: pendiente");

    // Etiqueta donde aparecerá el mensaje según el desempeño.
    private final JLabel mensaje = new JLabel("Aquí aparecerá tu retroalimentación.");

    // El constructor prepara los elementos del panel.
    // Recibe el gestor para poder cambiar a otras pantallas.
    public PanelFeedbackFlashcards(GestorVentanas gestor) {
        // Coloca los elementos uno debajo de otro.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Agrega márgenes: arriba, izquierda, abajo y derecha.
        setBorder(BorderFactory.createEmptyBorder(35, 60, 25, 30));

        // Crea el título y establece su letra en negrita, tamaño 28.
        JLabel titulo = new JLabel("Feedback de Flashcards");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(titulo);
        add(Box.createVerticalStrut(12));

        add(porcentaje);
        add(Box.createVerticalStrut(10));

        add(mensaje);
        add(Box.createVerticalStrut(20));

        // Botón para regresar al resumen del estudiante.
        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> gestor.mostrarFeedback());
        add(volver);
    }

    // Recibe el porcentaje y actualiza el contenido del panel.
    public void actualizarDesempeno(double desempeno) {
        // Muestra el porcentaje recibido.
        porcentaje.setText("Desempeño: " + desempeno + "%");

        if (desempeno < 61) {
            // El mensaje decía "balanceo" por copia del otro panel; aquí corresponde a flashcards.
            mensaje.setText("Continúa practicando las flashcards para mejorar tu desempeño.");
        } else {
            mensaje.setText("¡Buen trabajo! Tu desempeño en flashcards es positivo. ¡Sigue así!");
        }
    }
}
