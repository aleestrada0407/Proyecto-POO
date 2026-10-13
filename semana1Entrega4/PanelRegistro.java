import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// construye el formulario de inicio (aún no registra nada)
public class PanelRegistro extends JPanel {
    private final JTextField campoNombre = new JTextField(20);
    private final JTextField campoSeccion = new JTextField(20);
    private final JTextField campoUsuario = new JTextField(20);
    private final JComboBox<String> campoTipo = new JComboBox<>(
            new String[] { "Estudiante", "Profesor" });
    private final JPasswordField campoClave = new JPasswordField(20);
    private final JPasswordField campoConfirmacion = new JPasswordField(20);
    private final GestorVentanas gestor;

    public PanelRegistro(GestorVentanas gestor) {
        this.gestor = gestor;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        add(new JLabel("Crear cuenta"));
        add(new JLabel("Nombre:"));
        campoNombre.setMaximumSize(new Dimension(300, 30));
        add(campoNombre);
        add(new JLabel("Sección:"));
        campoSeccion.setMaximumSize(new Dimension(300, 30));
        add(campoSeccion);
        add(new JLabel("Usuario:"));
        campoUsuario.setMaximumSize(new Dimension(300, 30));
        add(campoUsuario);
        add(new JLabel("Tipo de usuario:"));
        campoTipo.setMaximumSize(new Dimension(300, 30));
        add(campoTipo);
        add(new JLabel("Contraseña:"));
        campoClave.setMaximumSize(new Dimension(300, 30));
        add(campoClave);
        add(new JLabel("Confirmar contraseña:"));
        campoConfirmacion.setMaximumSize(new Dimension(300, 30));
        add(campoConfirmacion);

        JButton crear = new JButton("Enviar registro");
        crear.addActionListener(e -> procesarRegistro());
        add(crear);

        JButton entrar = new JButton("Ya tengo cuenta");
        entrar.addActionListener(e -> gestor.mostrarLogin());
        add(entrar);

        JButton volver = new JButton("Volver al inicio");
        volver.addActionListener(e -> gestor.mostrarInicio());
        add(volver);
    }

    private void procesarRegistro() {
        String nombre = campoNombre.getText().trim();
        String seccion = campoSeccion.getText().trim();
        String usuario = campoUsuario.getText().trim();
        String tipo = (String) campoTipo.getSelectedItem();
        char[] clave = campoClave.getPassword();
        char[] confirmacion = campoConfirmacion.getPassword();
        try {
            if (nombre.isEmpty() || seccion.isEmpty() || usuario.isEmpty() || clave.length == 0) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos.");
                return;
            }
            if (!"Estudiante".equals(tipo) && !"Profesor".equals(tipo)) {
                JOptionPane.showMessageDialog(this, "Selecciona un tipo de usuario válido.");
                return;
            }

            // Aquí se regresa al menú de inicio pero no se registra nada, se debe conectar a la base de datos para guardar
            JOptionPane.showMessageDialog(this,
                    "Formulario de registro completo.");
                    gestor.mostrarInicio();
        } finally {//este finally limpia todas las casillas independientemente de qué escriba el usuario
            Arrays.fill(clave, '\0');
            Arrays.fill(confirmacion, '\0');
            campoClave.setText("");
            campoConfirmacion.setText("");
        }
    }
}
