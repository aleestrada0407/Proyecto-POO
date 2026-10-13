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
    private final JTextField campoUsuario = new JTextField(20);
    private final JPasswordField campoClave = new JPasswordField(20);

    public PanelLogin(GestorVentanas gestor) {
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
        JButton entrar = new JButton("Entrar");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> procesarLogin());
        add(entrar);

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
        String usuario = campoUsuario.getText().trim();
        char[] clave = campoClave.getPassword();
        try {
            if (usuario.isEmpty() || clave.length == 0) {
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incompletos.");
                return;
            }

            // aquí se debe conectar con la base de datos para hacer el inicio se sesión y comprobar los datos, por ahora siempre avanza a la pestaña de ejercicios
            JOptionPane.showMessageDialog(this,
                    "Inicio de sesión exitoso.");
        } finally {
            //estas lineas limpian los espacios para contraseña
            Arrays.fill(clave, '\0');
            campoClave.setText("");
        }
    }
}
