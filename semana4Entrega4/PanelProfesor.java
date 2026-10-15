import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

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



//Pantalla de login que muestra información y ofrece inicio de sesión y registro

public class PanelProfesor extends JPanel {
    private static final int IMAGEN_ANCHO = 360;
    private static final int IMAGEN_ALTO = 320;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 90;
    private ImageIcon logo;
    private ImageIcon fondoEjercicio;

    public PanelProfesor(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("Bienvenido, NOMBRE DEL PROFESOR");
        //aquí también se debe buscar el nombre
        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Seleccione qué desea hacer:"));
add(Box.createVerticalStrut(12));
        //se agregarán las imagenes después cuando se profundice en el diseño
            URL imagen = getClass().getResource("/imagenes/profesor.png");
        if (imagen != null) {
            logo = new ImageIcon(imagen);
            URL rutaFondo = getClass().getResource("/imagenes/fondo1.png");
        if (rutaFondo != null) {
            fondoEjercicio = new ImageIcon(rutaFondo);
        }
}

        JButton entrar = new JButton("Consultar alumno");
        entrar.setMaximumSize(new Dimension(200, 35));
        entrar.addActionListener(e -> gestor.mostrarBuscarAlumno());
        add(entrar);

        JButton registrar = new JButton("Cerrar sesión");
        registrar.setMaximumSize(new Dimension(200, 35));
        registrar.addActionListener(e -> gestor.mostrarInicio());
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
            g.drawImage(fondoEjercicio.getImage(),
                0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.DARK_GRAY);

        }
    }

}
