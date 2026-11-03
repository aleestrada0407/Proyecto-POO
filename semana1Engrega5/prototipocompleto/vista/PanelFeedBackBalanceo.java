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
public class PanelFeedBackBalanceo extends JPanel {
       // Etiqueta donde después se mostrará el porcentaje del estudiante.
    private final JLabel porcentaje =
            new JLabel("Desempeño: pendiente");

    // Etiqueta donde aparecerá el mensaje según el desempeño.
    private final JLabel mensaje =
            new JLabel("Aquí aparecerá tu retroalimentación.");

// El constructor prepara los elementos del panel.
    // Recibe el gestor para poder cambiar a otras pantallas.
    public PanelFeedBackBalanceo(GestorVentanas gestor) {

        // Coloca los elementos uno debajo de otro.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Agrega márgenes: arriba, izquierda, abajo y derecha.
        setBorder(BorderFactory.createEmptyBorder(35, 60, 25, 30));

        // Crea el título y establece su letra en negrita, tamaño 28.
        JLabel titulo = new JLabel("Feedback de balanceo");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));

        // Agrega el título al panel.
        add(titulo);

        // Deja 15 píxeles de espacio debajo del título.
        add(Box.createVerticalStrut(15));

        // Agrega la etiqueta del porcentaje y un espacio debajo.
        add(porcentaje);
        add(Box.createVerticalStrut(10));

        // Agrega la etiqueta del mensaje y un espacio debajo.
        add(mensaje);
        add(Box.createVerticalStrut(20));

        // Crea el botón para regresar.
        JButton volver = new JButton("Volver");

        // Al pulsarlo, el gestor muestra el menú del estudiante.
        volver.addActionListener(e -> gestor.mostrarEstudiante());

        // Agrega el botón al panel.
        add(volver);
}
}

