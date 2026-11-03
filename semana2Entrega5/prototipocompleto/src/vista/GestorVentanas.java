package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import login.BancoEjercicios;
import login.Usuario;
import login.Usuariodao;

// Administra la ventana principal y decide qué panel está visible (CardLayout).
// También guarda el usuario que inició sesión y pinta el fondo general de la app.
public class GestorVentanas extends JFrame {
    // Nombres con los que se registra cada pantalla en el CardLayout
    private static final String INICIO = "inicio";
    private static final String LOGIN = "login";
    private static final String REGISTRO = "registro";
    private static final String ESTUDIANTE = "estudiante";
    private static final String ELEGIREJERCICIO = "elegirejercicio";
    private static final String EJERCICIO = "ejercicio";
    private static final String NOMENCLATURA = "nomenclatura";
    private static final String PROFESOR = "profesor";
    private static final String BUSCARALUMNO = "buscaralumno";
    private static final String FEEDBACK = "feedback";
    private static final String FEEDBACKFLASHCARDS = "feedbackflashcards";
    private static final String FEEDBACKBALANCEO = "feedbackbalanceo";

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);

    // Acceso a datos. "banco" debe existir antes de crear PanelNomenclatura,
    // porque ese panel lo pide en su constructor (gestor.getBanco()).
    private final Usuariodao usuariodao = new Usuariodao();
    private final BancoEjercicios banco = new BancoEjercicios();

    // Paneles a los que hay que mandarles datos cada vez que se muestran
    private final PanelEstudiante panelEstudiante;
    private final PanelProfesor panelProfesor;
    private final PanelFeedback panelFeedback;
    private final PanelNomenclatura panelNomenclatura;

    // Imagen de fondo común a todas las pantallas
    private ImageIcon imagenFondo;

    // Nombre de usuario de quien inició sesión (se llena desde PanelLogin)
    private String nombreUsuarioActual;

    public GestorVentanas() {
        super("Quimix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 540);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        // Se crean los paneles que necesitan referencia propia
        panelEstudiante = new PanelEstudiante(this);
        panelProfesor = new PanelProfesor(this);
        panelFeedback = new PanelFeedback(this);
        panelNomenclatura = new PanelNomenclatura(this);

        // Se registran todas las pantallas en el contenedor
        contenedor.add(new PanelInicio(this), INICIO);
        contenedor.add(new PanelLogin(this), LOGIN);
        contenedor.add(new PanelRegistro(this), REGISTRO);
        contenedor.add(panelEstudiante, ESTUDIANTE);
        contenedor.add(new PanelElegirEjercicio(this), ELEGIREJERCICIO);
        contenedor.add(new PanelEjercicio(this), EJERCICIO);
        contenedor.add(panelNomenclatura, NOMENCLATURA);
        contenedor.add(panelProfesor, PROFESOR);
        contenedor.add(new PanelBuscarAlumno(this), BUSCARALUMNO);
        contenedor.add(panelFeedback, FEEDBACK);
        contenedor.add(new PanelFeedbackFlashcards(this), FEEDBACKFLASHCARDS);
        contenedor.add(new PanelFeedbackBalanceo(this), FEEDBACKBALANCEO);

        // Carga la imagen de fondo (si no existe, simplemente no se dibuja)
        URL rutaFondo = getClass().getResource("/vista/imagenes/fondo.png");
        if (rutaFondo != null) {
            imagenFondo = new ImageIcon(rutaFondo);
        }

        // Panel que dibuja el fondo estirado a todo el tamaño de la ventana
        JPanel fondo = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Todos los paneles se hacen transparentes para que se vea el fondo detrás
        contenedor.setOpaque(false);
        for (Component componente : contenedor.getComponents()) {
            ((JPanel) componente).setOpaque(false);
        }

        fondo.add(contenedor, BorderLayout.CENTER);
        setContentPane(fondo);

        mostrarInicio();
    }

    // ---- Usuario que tiene la sesión abierta ----

    public void setNombreUsuarioActual(String nombreUsuario) {
        nombreUsuarioActual = nombreUsuario;
    }

    public String getNombreUsuarioActual() {
        return nombreUsuarioActual;
    }

    // Busca en la base de datos al usuario actual; devuelve null si no hay sesión
    private Usuario obtenerUsuarioActual() {
        if (nombreUsuarioActual == null) {
            return null;
        }
        return usuariodao.buscarPorNombreUsuario(nombreUsuarioActual);
    }

    // ---- Métodos para cambiar de pantalla ----

    public void mostrarInicio() {
        tarjetas.show(contenedor, INICIO);
    }

    public void mostrarLogin() {
        tarjetas.show(contenedor, LOGIN);
    }

    public void mostrarRegistro() {
        tarjetas.show(contenedor, REGISTRO);
    }

    public void mostrarEstudiante() {
        // Antes de mostrar el menú se actualiza el saludo con el nombre del usuario
        Usuario usuario = obtenerUsuarioActual();
        if (usuario != null) {
            panelEstudiante.mostrarDatos(usuario);
        }
        tarjetas.show(contenedor, ESTUDIANTE);
    }

    public void mostrarElegirEjercicio() {
        tarjetas.show(contenedor, ELEGIREJERCICIO);
    }

    public void mostrarEjercicio() {
        tarjetas.show(contenedor, EJERCICIO);
    }

    public void mostrarNomenclatura() {
        // iniciar() reinicia el banco de nomenclatura y carga el primer ejercicio
        panelNomenclatura.iniciar();
        tarjetas.show(contenedor, NOMENCLATURA);
    }

    public void mostrarProfesor() {
        // Igual que con el estudiante: se actualiza el saludo antes de mostrar
        Usuario usuario = obtenerUsuarioActual();
        if (usuario != null) {
            panelProfesor.mostrarDatos(usuario);
        }
        tarjetas.show(contenedor, PROFESOR);
    }

    public void mostrarBuscarAlumno() {
        tarjetas.show(contenedor, BUSCARALUMNO);
    }

    public void mostrarFeedback() {
        // Se rellenan nombre, sección y usuario en el resumen
        Usuario usuario = obtenerUsuarioActual();
        if (usuario != null) {
            panelFeedback.mostrarDatos(usuario);
        }
        tarjetas.show(contenedor, FEEDBACK);
    }

    public void mostrarFeedbackFlashcards() {
        tarjetas.show(contenedor, FEEDBACKFLASHCARDS);
    }

    public void mostrarFeedbackBalanceo() {
        tarjetas.show(contenedor, FEEDBACKBALANCEO);
    }

    // ---- Acceso a los datos desde los paneles ----

    public Usuariodao getUsuariodao() {
        return usuariodao;
    }

    public BancoEjercicios getBanco() {
        return banco;
    }
}
