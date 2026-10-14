package vista;
import login.SistemaLogin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
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
        super("titulo de la ventana"); //modificar nombre luego. crea la ventana princpal para añadir los paneles
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



        add(contenedor, BorderLayout.CENTER);

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

    private final SistemaLogin sistemaLogin =
        new SistemaLogin();

    public SistemaLogin getSistemaLogin() {
    return sistemaLogin;
    }

}
