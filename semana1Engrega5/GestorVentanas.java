package vista;
import login.BancoEjercicios;
import login.Usuariodao;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GestorVentanas extends JFrame {
    private static final String INICIO = "inicio";
    private static final String LOGIN = "login";
    private static final String REGISTRO = "registro";
    private static final String ESTUDIANTE = "estudiante";
    private static final String EJERCICIO = "ejercicio";
    private static final String NOMENCLATURA = "nomenclatura";
    private static final String PROFESOR = "profesor";
    private static final String BUSCARALUMNO = "buscaralumno";

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);

    private final Usuariodao usuariodao = new Usuariodao();

    private final BancoEjercicios banco = new BancoEjercicios();


    private PanelNomenclatura panelNomenclatura;

    public GestorVentanas() {
        super("titulo de la ventana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 540);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelNomenclatura = new PanelNomenclatura(this);

        contenedor.add(new PanelInicio(this), INICIO);
        contenedor.add(new PanelLogin(this), LOGIN);
        contenedor.add(new PanelRegistro(this), REGISTRO);
        contenedor.add(new PanelEstudiante(this), ESTUDIANTE);
        contenedor.add(new PanelEjercicio(this), EJERCICIO);
        contenedor.add(panelNomenclatura, NOMENCLATURA);
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

}