package vista;
import login.BancoEjercicios;
import login.Ejerciciodao;
import login.GestorResultados;
import login.Usuario;
import login.Usuariodao;

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
    private static final String ELEGIREJERCICIO = "elegirejercicio";
    private static final String EJERCICIO = "ejercicio";
    private static final String NOMENCLATURA = "nomenclatura";
    private static final String PROFESOR = "profesor";
    private static final String BUSCARALUMNO = "buscaralumno";

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);

    // Datos compartidos por todas las pantallas.
    private final Usuariodao usuariodao = new Usuariodao();
    private final BancoEjercicios banco = new BancoEjercicios();
    private final GestorResultados gestorResultados = new GestorResultados();
    private Usuario usuarioActual; // quien inició sesión (null si nadie)

    // Se guardan para poder recargarlas cada vez que se muestran.
    private PanelEjercicio panelEjercicio;
    private PanelNomenclatura panelNomenclatura;

    public GestorVentanas() {
        super("titulo de la ventana"); //modificar nombre luego. crea la ventana princpal para añadir los paneles
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 540);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Guarda los ejercicios en la base de datos (si faltan) y los lee de ahí,
        // para que cada uno tenga su id y se puedan guardar los resultados.
        Ejerciciodao ejerciciodao = new Ejerciciodao();
        banco.sembrarBaseDatos(ejerciciodao);
        banco.cargarDesdeBD(ejerciciodao);

        panelEjercicio = new PanelEjercicio(this);
        panelNomenclatura = new PanelNomenclatura(this);

        // se crea una sola instancia de cada pantalla.
        contenedor.add(new PanelInicio(this), INICIO);
        contenedor.add(new PanelLogin(this), LOGIN);
        contenedor.add(new PanelRegistro(this), REGISTRO);
        contenedor.add(new PanelEstudiante(this), ESTUDIANTE);
        contenedor.add(new PanelElegirEjercicio(this), ELEGIREJERCICIO);
        contenedor.add(panelEjercicio, EJERCICIO);
        contenedor.add(panelNomenclatura, NOMENCLATURA);
        contenedor.add(new PanelProfesor(this), PROFESOR);
        contenedor.add(new PanelBuscarAlumno(this), BUSCARALUMNO);



        add(contenedor, BorderLayout.CENTER);

        mostrarInicio();
    }

    public void mostrarInicio() {
        usuarioActual = null; // volver al inicio equivale a cerrar sesión
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

    public void mostrarElegirEjercicio(){
        tarjetas.show(contenedor, ELEGIREJERCICIO);
    }

    public void mostrarEjercicio(){
        panelEjercicio.iniciar(); // carga el primer ejercicio antes de mostrar la pantalla
        tarjetas.show(contenedor, EJERCICIO);
    }

    public void mostrarNomenclatura(){
        panelNomenclatura.iniciar();
        tarjetas.show(contenedor, NOMENCLATURA);
    }

    public void mostrarProfesor(){
        tarjetas.show(contenedor, PROFESOR);
    }

    public void mostrarBuscarAlumno(){
        tarjetas.show(contenedor, BUSCARALUMNO);
    }

    public Usuariodao getUsuariodao() {
        return usuariodao;
    }

    public BancoEjercicios getBanco() {
        return banco;
    }

    public GestorResultados getGestorResultados() {
        return gestorResultados;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

}
