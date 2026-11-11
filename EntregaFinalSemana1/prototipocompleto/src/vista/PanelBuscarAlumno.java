package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// Permite reconocer errores al consultar la base de datos.
import java.sql.SQLException;

// Permite indicar el formato de números (punto como separador decimal).
import java.util.Locale;

// Permite guardar la lista de estudiantes que llega de la base de datos.
import java.util.ArrayList;

// Representa a cada usuario (estudiante) que se muestra en la lista desplegable.
import login.Usuario;

// Pantalla del profesor para consultar el informe de los alumnos.
public class PanelBuscarAlumno extends JPanel {
    private ImageIcon fondo;

    // Se guarda el gestor para poder pedirle el acceso a la base de datos (Usuariodao).
    private final GestorVentanas gestor;

    // Lista desplegable con los estudiantes registrados.
    // Antes era de tipo String y estaba vacía; ahora guarda objetos Usuario
    // para tener a la mano el nombre, la sección y el nombre de usuario del elegido.
    private final JComboBox<Usuario> campoEstudiante = new JComboBox<>();

    // Botón que se habilita al elegir un estudiante.
    // Busca su rendimiento en la base de datos y lo muestra en un popup.
    private final JButton buscarRendimiento = new JButton("Buscar rendimiento");

    public PanelBuscarAlumno(GestorVentanas gestor) {
        // Guarda el gestor para usarlo después en cargarEstudiantes().
        this.gestor = gestor;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        JLabel titulo = new JLabel("Informe de alumnos");
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);

        // La etiqueta ahora indica que en la lista solo aparecen estudiantes.
        add(new JLabel("Estudiante:"));
        campoEstudiante.setMaximumSize(new Dimension(300, 30));

        // Cada vez que se elige un estudiante se revisa si el botón debe habilitarse.
        // La flecha (->) indica qué acción se ejecuta cuando ocurre el evento.
        campoEstudiante.addActionListener(e -> actualizarBoton());
        add(campoEstudiante);
        add(Box.createVerticalStrut(12));

        // Fondo de la pantalla (se dibuja en paintComponent)
        URL imagen = getClass().getResource("/vista/imagenes/fondo1.png");
        if (imagen != null) {
            fondo = new ImageIcon(imagen);
        }

        // Botón nuevo, colocado encima de Volver.
        // Empieza deshabilitado porque todavía no se ha elegido ningún estudiante.
        buscarRendimiento.setMaximumSize(new Dimension(200, 35));
        buscarRendimiento.setEnabled(false);
        // Al hacer clic se busca el rendimiento del estudiante elegido y se muestra en un popup.
        buscarRendimiento.addActionListener(e -> mostrarRendimiento());
        add(buscarRendimiento);

        // Deja un pequeño espacio entre "Buscar rendimiento" y "Volver".
        add(Box.createVerticalStrut(8));

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarProfesor());
        add(volver);

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Llena la lista desplegable solo con los usuarios de tipo Estudiante.
    // Lo llama GestorVentanas.mostrarBuscarAlumno() cada vez que se abre la pantalla,
    // así también aparecen los estudiantes que se registraron después de abrir el programa.
    public void cargarEstudiantes() {

        // Quita los estudiantes que se habían cargado la vez anterior.
        campoEstudiante.removeAllItems();

        // Pide a la base de datos únicamente los usuarios de tipo Estudiante.
        ArrayList<Usuario> estudiantes = gestor.getUsuariodao().listarEstudiantes();

        // Agrega cada estudiante a la lista desplegable.
        for (Usuario estudiante : estudiantes) {
            campoEstudiante.addItem(estudiante);
        }

        // Deja la lista sin selección para que el profesor elija a un estudiante.
        campoEstudiante.setSelectedIndex(-1);

        // Con la lista sin selección, el botón queda deshabilitado.
        actualizarBoton();
    }

    // Habilita "Buscar rendimiento" solo cuando hay un estudiante seleccionado.
    private void actualizarBoton() {
        buscarRendimiento.setEnabled(campoEstudiante.getSelectedItem() != null);
    }

    // Busca en la base de datos los resultados del estudiante seleccionado
    // y los muestra en un popup (ventana emergente) con este formato:
    //   Estudiante: NOMBRE
    //   Sección: SECCION
    //   Completados N ejercicios de balanceo, con porcentaje de acierto de P%
    //   Completados N ejercicios de flashcards, con porcentaje de acierto de P%
    private void mostrarRendimiento() {

        // Obtiene el estudiante elegido en la lista desplegable.
        Usuario estudiante = (Usuario) campoEstudiante.getSelectedItem();

        // Si no hay estudiante elegido no se consulta nada.
        if (estudiante == null) {
            return;
        }

        try {
            // Cuenta los ejercicios de balanceo: conteo[0] son aciertos y conteo[1] el total.
            int[] balanceo = gestor.getResultadoBalanceodao()
                    .consultarConteoBalanceo(estudiante.getNombreUsuario());

            // Cuenta las flashcards de nomenclatura de la misma forma.
            int[] flashcards = gestor.getResultadoNomenclaturadao()
                    .consultarConteoNomenclatura(estudiante.getNombreUsuario());

            // Arma el texto del resumen línea por línea ("\n" es un salto de línea).
            String resumen =
                    "Estudiante: " + estudiante.getNombre() + "\n"
                    + "Sección: " + estudiante.getSeccion() + "\n"
                    + "Completados " + balanceo[1] + " ejercicios de balanceo, "
                    + "con porcentaje de acierto de " + calcularPorcentaje(balanceo[0], balanceo[1]) + "\n"
                    + "Completados " + flashcards[1] + " ejercicios de flashcards, "
                    + "con porcentaje de acierto de " + calcularPorcentaje(flashcards[0], flashcards[1]);

            // Muestra el popup con el resumen encima de esta pantalla.
            JOptionPane.showMessageDialog(this, resumen,
                    "Rendimiento del estudiante", JOptionPane.INFORMATION_MESSAGE);

        // Si la base de datos falla se avisa en lugar de mostrar datos incorrectos.
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo consultar el rendimiento del estudiante. Inténtalo de nuevo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Convierte aciertos y total en un porcentaje con un decimal, por ejemplo "66.7%".
    // Si el estudiante no tiene ejercicios completados devuelve "0.0%" para no dividir entre cero.
    private String calcularPorcentaje(int aciertos, int total) {

        // Sin ejercicios no hay porcentaje que calcular.
        if (total == 0) {
            return "0.0%";
        }

        // Calcula el porcentaje usando decimales.
        double porcentaje = aciertos * 100.0 / total;

        // Locale.US asegura que el decimal use punto (66.7) en cualquier computadora.
        return String.format(Locale.US, "%.1f", porcentaje) + "%";
    }

    // Pinta el fondo estirado a todo el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
