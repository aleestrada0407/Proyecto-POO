        package vista;
        import login.Usuariodao;
        import java.awt.Component;
        import java.awt.BorderLayout;
        import java.awt.CardLayout;
        import java.awt.Graphics;
        import java.net.URL;
        import javax.swing.ImageIcon;
        import login.Usuario;       
        import javax.swing.JFrame;
        import javax.swing.JPanel;

        // Administra la ventana principal y decide qué panel está visible.
        public class GestorVentanas extends JFrame {
                private ImageIcon imagenFondo;
            private static final String INICIO = "inicio";
            private static final String LOGIN = "login";
            private static final String REGISTRO = "registro";
            private static final String ESTUDIANTE = "estudiante";
            private static final String EJERCICIO = "ejercicio";
            private static final String PROFESOR = "profesor";
            private static final String BUSCARALUMNO = "buscaralumno";
                private static final String FEEDBACK = "feedback";
            private static final String FEEDBACKFLASHCARDS = "feedbackflashcards";
            private static final String FEEDBACKBALANCEO = "feedbackbalanceo";
            private final PanelFeedback panelFeedback = new PanelFeedback(this);
            private final PanelEstudiante panelEstudiante = new PanelEstudiante(this);
                private final PanelProfesor panelProfesor = new PanelProfesor(this);
            private final CardLayout tarjetas = new CardLayout();
            private final JPanel contenedor = new JPanel(tarjetas);
            
;
            

            public GestorVentanas() {
                super("Quimix"); 
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(960, 540);
                setMinimumSize(getSize());
                setLocationRelativeTo(null);
                setLayout(new BorderLayout());

                contenedor.add(new PanelInicio(this), INICIO);
                contenedor.add(new PanelLogin(this), LOGIN);
                contenedor.add(new PanelRegistro(this), REGISTRO);

                contenedor.add(new PanelEjercicio(this), EJERCICIO);
                contenedor.add(new PanelBuscarAlumno(this), BUSCARALUMNO);
                contenedor.add(panelFeedback, FEEDBACK);
                contenedor.add(panelEstudiante, ESTUDIANTE);
                contenedor.add(panelProfesor, PROFESOR);
                        contenedor.add(new PanelFeedbackFlashcards(this), FEEDBACKFLASHCARDS);
                                contenedor.add(new PanelFeedbackBalanceo(this), FEEDBACKBALANCEO);


                URL rutaFondo = getClass().getResource("/vista/imagenes/fondo.png");
        if (rutaFondo != null) {
            imagenFondo = new ImageIcon(rutaFondo);
        }
                
                JPanel fondo = new JPanel(new BorderLayout()) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (imagenFondo != null) {
                            g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                        }
                        
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

            private String nombreUsuarioActual;

        public void setNombreUsuarioActual(String nombreUsuario) {
            nombreUsuarioActual = nombreUsuario;
        }

            public String getNombreUsuarioActual() {
                return nombreUsuarioActual;
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

            public void mostrarEstudiante() {
        Usuario usuario = usuariodao.buscarPorNombreUsuario(nombreUsuarioActual);
            panelEstudiante.mostrarDatos(usuario);
        tarjetas.show(contenedor, ESTUDIANTE);
}

            public void mostrarEjercicio(){
                tarjetas.show(contenedor, EJERCICIO);
            }

            public void mostrarProfesor(){
                Usuario usuario = usuariodao.buscarPorNombreUsuario(nombreUsuarioActual);
            panelProfesor.mostrarDatos(usuario);
                tarjetas.show(contenedor, PROFESOR);
            }

            public void mostrarBuscarAlumno(){
                tarjetas.show(contenedor, BUSCARALUMNO);
            }

            public void mostrarFeedback() {
            String nombreUsuario = getNombreUsuarioActual();

        Usuario usuario = usuariodao.buscarPorNombreUsuario(nombreUsuario);
            panelFeedback.mostrarDatos(usuario);
            tarjetas.show(contenedor, FEEDBACK);
        }

            public void mostrarFeedbackFlashcards(){
                tarjetas.show(contenedor, FEEDBACKFLASHCARDS);
            }

            public void mostrarFeedbackBalanceo(){
                tarjetas.show(contenedor, FEEDBACKBALANCEO);
            }

            private final Usuariodao usuariodao = new Usuariodao();

            public Usuariodao getUsuariodao() {
                return usuariodao;
            }

        }