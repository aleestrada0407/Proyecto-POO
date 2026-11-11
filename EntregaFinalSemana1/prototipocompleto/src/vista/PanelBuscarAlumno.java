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
import javax.swing.JPanel;

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
    // Servirá para buscar su rendimiento en la base de datos (se implementa en el siguiente commit).
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
        // Pendiente: la búsqueda del rendimiento se agrega en el siguiente commit.
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

    // Pinta el fondo estirado a todo el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
