package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.Estudiante;
import login.Profesor;
import login.Usuario;

import java.net.URL;

// Pantalla de inicio de sesión: valida las credenciales contra la base de datos
// y manda al usuario a su menú (estudiante o profesor).
public class PanelLogin extends JPanel {
    // Tamaño y posición de la imagen decorativa
    private static final int IMAGEN_ANCHO = 480;
    private static final int IMAGEN_ALTO = 420;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 60;
    private ImageIcon logo;

    private final GestorVentanas gestor;
    private final JTextField campoUsuario = new JTextField(20);
    private final JPasswordField campoClave = new JPasswordField(20);

    public PanelLogin(GestorVentanas gestor) {

        
        this.gestor = gestor;

        URL imagen = getClass().getResource("/vista/imagenes/logo.png");
        if (imagen != null) {
            logo = new ImageIcon(imagen);
        }
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

        // Espacio entre los campos y los botones
        add(Box.createVerticalStrut(12));
        JButton entrar = new JButton("Iniciar sesión");
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

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    private void procesarLogin() {
        String nombreUsuario = campoUsuario.getText().trim();
        char[] clave = campoClave.getPassword();

        try {
            // 1. Verificar que los campos no estén vacíos
            if (nombreUsuario.isEmpty() || clave.length == 0) {
                JOptionPane.showMessageDialog(this,
                        "Completa el usuario y la contraseña.");
                return;
            }

            // 2. Buscar al usuario en la base de datos
            Usuario usuario = gestor.getUsuariodao()
                    .buscarPorNombreUsuario(nombreUsuario);

            // 3. Verificar que exista y que la contraseña coincida
            if (usuario == null || !usuario.verificarContrasena(new String(clave))) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                return;
            }

            // 4. Guardar quién inició sesión (se usa para los saludos y el resumen).
            //    Se hace DESPUÉS de validar: antes, si el usuario no existía,
            //    usuario.getNombreUsuario() lanzaba NullPointerException.
            gestor.setNombreUsuarioActual(usuario.getNombreUsuario());

            // 5. Mostrar mensaje de bienvenida
            JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!");

            // 6. Abrir la pantalla correspondiente según el tipo de usuario
            if (usuario instanceof Estudiante) {
                gestor.mostrarEstudiante();
            } else if (usuario instanceof Profesor) {
                gestor.mostrarProfesor();
            }

        } finally {
            // Limpia la contraseña de memoria y del campo después de usarla
            Arrays.fill(clave, '\0');
            campoClave.setText("");
        }
    }

    // Dibuja la imagen decorativa. OJO: "logo" nunca se carga en esta clase,
    // por eso por ahora no se dibuja nada (igual que en tu versión nueva).
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = getWidth() - IMAGEN_ANCHO - IMAGEN_MARGEN_DERECHO;
        if (logo != null) {
            g.drawImage(logo.getImage(), x, IMAGEN_Y, IMAGEN_ANCHO, IMAGEN_ALTO, this);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
    }
}
