import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.net.URL;


public class PanelInicio extends JPanel {
    private static final int IMAGEN_ANCHO = 480;
    private static final int IMAGEN_ALTO = 420;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 60;
    private ImageIcon logo;

    public PanelInicio(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("<html>Bienvenido a <font color='#3c24c5'>QUIMIX</font>.</html>");
        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Selecciona qué deseas hacer:"));
        add(Box.createVerticalStrut(12));
        //se agregarán las imagenes después cuando se profundice en el diseño
        URL imagen = getClass().getResource("/imagenes/logo.png");
        if (imagen != null) {
            logo = new ImageIcon(imagen);
        } else {
            File archivo = new File("src/imagenes/logo.png");
            if (archivo.isFile()) {
                logo = new ImageIcon(archivo.getAbsolutePath());
            }
        }

        JButton entrar = new JButton("Iniciar sesión");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> gestor.mostrarLogin());
        add(entrar);

        JButton registrar = new JButton("Crear cuenta");
        registrar.setMaximumSize(new Dimension(200, 35));
        registrar.addActionListener(e -> gestor.mostrarRegistro());
        add(registrar);

        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = getWidth() - IMAGEN_ANCHO - IMAGEN_MARGEN_DERECHO;
        if (logo != null && logo.getIconWidth() > 0) {
            g.drawImage(logo.getImage(), x, IMAGEN_Y, IMAGEN_ANCHO, IMAGEN_ALTO, this);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.drawString("No se encontró imagenes/logo.png", x, IMAGEN_Y + 20);
        }
    }
}
