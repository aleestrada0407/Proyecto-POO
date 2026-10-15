        import java.awt.Component;
        import java.awt.Font;
        import javax.swing.JComponent;
        import java.awt.Dimension;

        import javax.swing.BorderFactory;
        import javax.swing.Box;
        import javax.swing.BoxLayout;
        import javax.swing.ImageIcon;
        import javax.swing.JButton;
        import java.awt.Graphics;
        import java.awt.Color;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;

        import java.net.URL;

//Pantalla de login que muestra información y ofrece inicio de sesión y registro
public class PanelEstudiante extends JPanel {
    private static final int IMAGEN_ANCHO = 480;
    private static final int IMAGEN_ALTO = 420;
    private static final int IMAGEN_MARGEN_DERECHO = 50;
    private static final int IMAGEN_Y = 60;
    private ImageIcon logo;

    public PanelEstudiante(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("Hola, NOMBRE DE ESTUDIANTE."); 
        //se debe de obtener el nombre del estudiante para mostrar aquí
        
        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Selecciona qué deseas hacer:"));
add(Box.createVerticalStrut(12));
        //se agregarán las imagenes después cuando se profundice en el diseño
        URL imagen = getClass().getResource("/imagenes/imagen1.png");
if (imagen != null) {
    logo = new ImageIcon(imagen);
}

        JButton ejercicio = new JButton("Realizar un ejercicio");
        ejercicio.setMaximumSize(new Dimension(200, 35));
        ejercicio.addActionListener(e -> gestor.mostrarEjercicio());
        add(ejercicio);

        JButton desempeno = new JButton("Ver mi desempeño");
        desempeno.setMaximumSize(new Dimension(200, 35));

        desempeno.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
            "Resumen para NOMBRE DE USUARIO:\nNombre: NOMBRE\nDesempeño: NUMERO\nEjercicios completados: NUMERO");
        });//se debe agregar la infomracion faltante
        add(desempeno);

        JButton volver = new JButton("Cerrar sesión");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarInicio());
        add(volver);



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
        }
    }
}