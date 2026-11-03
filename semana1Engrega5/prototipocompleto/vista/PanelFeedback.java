package vista;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import login.Usuario;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

        import java.awt.Graphics;
        import java.awt.Color;

        import java.net.URL;
public class PanelFeedback extends JPanel {
    private final JLabel titulo = new JLabel("Resumen:");
    private final JLabel etiquetaNombre = new JLabel();
private final JLabel etiquetaSeccion = new JLabel();
private final JLabel etiquetaNombreUsuario = new JLabel();
    public PanelFeedback(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        //se debe de obtener el nombre del estudiante para mostrar aquí
        
        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
add(Box.createVerticalStrut(12));
        add(etiquetaNombre);
        add(etiquetaSeccion);
        add(etiquetaNombreUsuario);

        JButton flashcards = new JButton("Desempeño en Flashcards");
        flashcards.setMaximumSize(new Dimension(200, 35));
        flashcards.addActionListener(e -> gestor.mostrarFeedbackFlashcards());
        add(flashcards);

        JButton balanceo = new JButton("Desempeño en Balanceo");
        balanceo.setMaximumSize(new Dimension(200, 35));
        balanceo.addActionListener(e -> gestor.mostrarFeedbackBalanceo());
        add(balanceo);

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
        add(volver);
    }
        public void mostrarDatos(Usuario usuario) {
    titulo.setText("Resumen para " + usuario.getNombre() + ":");
    etiquetaNombre.setText("Nombre: " + usuario.getNombre());
    etiquetaNombreUsuario.setText("Nombre de usuario: " +usuario.getNombreUsuario());
    etiquetaSeccion.setText("Sección: " + usuario.getSeccion());



        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
}   