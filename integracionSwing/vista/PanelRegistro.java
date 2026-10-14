package vista;
import login.Usuario;
import login.Estudiante;
import login.Profesor;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
//titulo mejorado para que se vea como un titulo 
        JLabel titulo = new JLabel("Crear cuenta");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
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

        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        JButton crear = new JButton("Enviar registro");
        crear.setMaximumSize(new Dimension(200, 35));
        crear.addActionListener(e -> procesarRegistro());
        add(crear);

        JButton entrar = new JButton("Ya tengo cuenta");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> gestor.mostrarLogin());
        add(entrar);

        JButton volver = new JButton("Volver al inicio");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarInicio());
        add(volver);
        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
}
    }

    
    private void procesarRegistro() {
        String nombre = campoNombre.getText().trim();
        String seccion = campoSeccion.getText().trim();
        String nombreUsuario = campoUsuario.getText().trim();
        String tipo = (String) campoTipo.getSelectedItem();

        char[] clave = campoClave.getPassword();
        char[] confirmacion = campoConfirmacion.getPassword();

        try {
            // 1. Comprobar que los campos no estén vacíos
            if (nombre.isEmpty() || seccion.isEmpty()
                    || nombreUsuario.isEmpty() || clave.length == 0
                    || confirmacion.length == 0) {

                JOptionPane.showMessageDialog(this,
                        "Completa todos los campos.");
                return;
            }

            // 2. Verificar que las contraseñas coincidan
            if (!Arrays.equals(clave, confirmacion)) {
                JOptionPane.showMessageDialog(this,
                        "Las contraseñas no coinciden.");
                return;
            }

            // 3. Crear el usuario según su tipo
            Usuario nuevoUsuario;

            if ("Estudiante".equals(tipo)) {
                nuevoUsuario = new Estudiante(
                        nombre, seccion, nombreUsuario,
                        new String(clave));
            } else if ("Profesor".equals(tipo)) {
                nuevoUsuario = new Profesor(
                        nombre, seccion, nombreUsuario,
                        new String(clave));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Tipo de usuario no válido.");
                return;
            }

            // 4. Registrar el usuario con la lógica existente
            boolean registrado = gestor.getSistemaLogin()
                    .registrarUsuario(nuevoUsuario);

            // 5. Mostrar el resultado
            if (registrado) {
                JOptionPane.showMessageDialog(this,
                        "¡Cuenta registrada correctamente!");

                campoNombre.setText("");
                campoSeccion.setText("");
                campoUsuario.setText("");

                gestor.mostrarLogin();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Ese nombre de usuario ya existe.");
            }

        } finally {
            Arrays.fill(clave, '\0');
            Arrays.fill(confirmacion, '\0');
            campoClave.setText("");
            campoConfirmacion.setText("");
        }
    }

}
