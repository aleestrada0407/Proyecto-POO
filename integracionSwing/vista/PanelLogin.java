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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class PanelLogin extends JPanel {
    private final GestorVentanas gestor;
    private final JTextField campoUsuario = new JTextField(20);
    private final JPasswordField campoClave = new JPasswordField(20);

    public PanelLogin(GestorVentanas gestor) {
        this.gestor = gestor;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("Iniciar sesión");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Usuario:"));
        campoUsuario.setMaximumSize(new Dimension(300, 30));
        add(campoUsuario);
        add(new JLabel("Contraseña:"));
        campoClave.setMaximumSize(new Dimension(300, 30));
        add(campoClave);

         //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        JButton entrar = new JButton("Entrar ESTUDIANTE");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> procesarLogin());
        add(entrar);
        //IMPORTANTE eliminar el botón de "profesor" cuando se tenga el sistema de log in. 
        // Por ahora no existe modo de diferenciar entre los dos perfiles y se necesita este botón

        JButton entrarprof = new JButton("profesor");
        entrarprof.setMaximumSize(new Dimension(200, 35));
        entrarprof.addActionListener(e -> procesarLoginProf());
        add(entrarprof);


        JButton registro = new JButton("Crear cuenta");
        registro.setMaximumSize(new Dimension(200, 35));
        registro.addActionListener(e -> gestor.mostrarRegistro());
        add(registro);

        JButton volver = new JButton("Volver al inicio");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarInicio());
        add(volver);
        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
}
    }

    
    
    private void procesarLogin() {

        System.out.println("DEBUG: Se ejecuta el login actualizado");

        String nombreUsuario = campoUsuario.getText().trim();
        char[] clave = campoClave.getPassword();

        try {
            // 1. Verificar que los campos no estén vacíos
            if (nombreUsuario.isEmpty() || clave.length == 0) {
                JOptionPane.showMessageDialog(this,
                        "Completa el usuario y la contraseña.");
                return;
            }

            // 2. Comprobar las credenciales
            Usuario usuario = gestor.getSistemaLogin()
                    .iniciarSesion(nombreUsuario, new String(clave));

            // 3. Verificar si el usuario existe
            if (usuario == null) {
                JOptionPane.showMessageDialog(this,
                        "Usuario o contraseña incorrectos.");
                return;
            }

            // 4. Mostrar mensaje de bienvenida
            JOptionPane.showMessageDialog(this,
                    "¡Inicio de sesión exitoso!");

            // 5. Abrir la pantalla correspondiente
            if (usuario instanceof Estudiante) {
                gestor.mostrarEstudiante();

            } else if (usuario instanceof Profesor) {
                gestor.mostrarProfesor();
            }

        } finally {
            // Limpiar la contraseña después de utilizarla
            Arrays.fill(clave, '\0');
            campoClave.setText("");
        }
    }

private void procesarLoginProf() {
        String usuario = campoUsuario.getText().trim();
        char[] clave = campoClave.getPassword();
        try {
            if (usuario.isEmpty() || clave.length == 0) {
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incompletos.");
                return;
            }

            // aquí se debe conectar con la base de datos para hacer el inicio se sesión y comprobar los datos, por ahora siempre avanza a la pestaña de ejercicios
            JOptionPane.showMessageDialog(this,
                    "Inicio de sesión exitoso.");//Se avanza de clase pero no se 
            gestor.mostrarProfesor();
        } finally {
            //estas lineas limpian los espacios para contraseña
            Arrays.fill(clave, '\0');
            campoClave.setText("");
        }
    }


}

