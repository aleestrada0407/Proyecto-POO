package vista;

// Importa las herramientas para crear y organizar la interfaz.
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Permite indicar el formato de números (punto como separador decimal).
import java.util.Locale;

// Hereda de JPanel para funcionar como una pantalla del programa.
public class PanelFeedbackBalanceo extends JPanel {
    // Etiqueta donde después se mostrará el porcentaje del estudiante.
    private final JLabel porcentaje = new JLabel("Desempeño: pendiente");

    // Etiqueta donde aparecerá el mensaje según el desempeño.
    private final JLabel mensaje = new JLabel("Aquí aparecerá tu retroalimentación.");

    // El constructor prepara los elementos del panel.
    // Recibe el gestor para poder cambiar a otras pantallas.
    public PanelFeedbackBalanceo(GestorVentanas gestor) {
        // Coloca los elementos uno debajo de otro.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Agrega márgenes: arriba, izquierda, abajo y derecha.
        setBorder(BorderFactory.createEmptyBorder(35, 60, 25, 30));

        // Crea el título y establece su letra en negrita, tamaño 28.
        JLabel titulo = new JLabel("Feedback de balanceo");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(titulo);

        // Deja 15 píxeles de espacio debajo del título.
        add(Box.createVerticalStrut(15));

        // Agrega la etiqueta del porcentaje y un espacio debajo.
        add(porcentaje);
        add(Box.createVerticalStrut(10));

        // Agrega la etiqueta del mensaje y un espacio debajo.
        add(mensaje);
        add(Box.createVerticalStrut(20));

        // Botón para regresar al resumen del estudiante.
        // La flecha (->) indica qué acción se ejecuta cuando ocurre el evento.
        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> gestor.mostrarFeedback());
        add(volver);
    }

    // Recibe el porcentaje y actualiza el contenido del panel.
    public void actualizarDesempeno(double desempeno) {
        // Redondea el porcentaje a un decimal para que se lea mejor.
        // Antes se mostraba completo, por ejemplo "66.66666666666667%".
        // Se multiplica por 10, se redondea al entero más cercano y se divide entre 10.
        double desempenoRedondeado = Math.round(desempeno * 10) / 10.0;

        // Convierte el número a texto con un decimal.
        // Locale.US asegura que el decimal use punto (66.7) en cualquier computadora.
        String porcentajeTexto = String.format(Locale.US, "%.1f", desempenoRedondeado);

        // Muestra el porcentaje ya redondeado, por ejemplo "Desempeño: 66.7%".
        porcentaje.setText("Desempeño: " + porcentajeTexto + "%");

        // Se compara con el valor redondeado para que el mensaje coincida con lo que se ve.
        // Así, si en pantalla aparece "61.0%", el mensaje también será el positivo.
        if (desempenoRedondeado < 61) {
            // Menor al 61%: mensaje para seguir practicando.
            mensaje.setText("Continúa practicando el balanceo para mejorar tu desempeño.");
        } else {
            // Mayor o igual al 61%: mensaje positivo.
            mensaje.setText("¡Buen trabajo! Tu desempeño en balanceo es positivo. ¡Sigue así!");
        }
    }
        // Se utiliza cuando el estudiante todavía no tiene respuestas registradas.
    public void mostrarSinResultados() {

        // Reemplaza el porcentaje anterior por un aviso de ausencia de resultados.
        porcentaje.setText("Desempeño: sin resultados");

        // Indica qué debe hacer el estudiante para obtener su desempeño.
        mensaje.setText(
                "Realiza ejercicios de balanceo para conocer tu desempeño.");
    }

    // Se utiliza cuando ocurre un error al consultar la base de datos.
    public void mostrarErrorConsulta() {

        // Quita cualquier porcentaje anterior porque no pudo actualizarse.
        porcentaje.setText("Desempeño: no disponible");

        // Informa del problema y explica cómo volver a intentarlo.
        mensaje.setText(
                "No se pudieron consultar tus resultados. Vuelve a abrir esta pantalla.");
    }
}
