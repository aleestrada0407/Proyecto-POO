import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Administra la ventana principal y decide qué panel está visible.
public class GestorVentanas extends JFrame {
    private static final String INICIO = "inicio";
    private static final String LOGIN = "login";
    private static final String REGISTRO = "registro";
    private static final String ESTUDIANTE = "estudiante";
    private static final String EJERCICIO = "ejercicio";
    private static final String PROFESOR = "profesor";
    private static final String BUSCARALUMNO = "buscaralumno";

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);

    public GestorVentanas() {
        super("Quimix"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 540);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // se crea una sola instancia de cada pantalla.
        contenedor.add(new PanelInicio(this), INICIO);
        contenedor.add(new PanelLogin(this), LOGIN);
        contenedor.add(new PanelRegistro(this), REGISTRO);
        contenedor.add(new PanelEstudiante(this), ESTUDIANTE);
        contenedor.add(new PanelEjercicio(this), EJERCICIO);
        contenedor.add(new PanelProfesor(this), PROFESOR);
        contenedor.add(new PanelBuscarAlumno(this), BUSCARALUMNO);



        URL rutaFondo = getClass().getResource("/imagenes/fondo.png");
        ImageIcon imagenFondo = rutaFondo == null ? null : new ImageIcon(rutaFondo);
        JPanel fondo = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                
            }
        };
        contenedor.setOpaque(false);
        for (Component componente : contenedor.getComponents()) {
            ((JPanel) componente).setOpaque(false);
        }
        fondo.add(contenedor, BorderLayout.CENTER);
        setContentPane(fondo);

        mostrarInicio();
    }

    public void mostrarInicio() {
        tarjetas.show(contenedor, INICIO);
    }

    public void mostrarLogin() {
        tarjetas.show(contenedor, LOGIN);
    }

    public void mostrarRegistro() {
        tarjetas.show(contenedor, REGISTRO);
    }

    public void mostrarEstudiante(){
        tarjetas.show(contenedor, ESTUDIANTE);
    }

    public void mostrarEjercicio(){
        tarjetas.show(contenedor, EJERCICIO);
    }

    public void mostrarProfesor(){
        tarjetas.show(contenedor, PROFESOR);
    }

    public void mostrarBuscarAlumno(){
        tarjetas.show(contenedor, BUSCARALUMNO);
    }

}
