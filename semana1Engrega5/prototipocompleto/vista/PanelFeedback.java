package vista;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

        import java.awt.Graphics;
        import java.awt.Color;

        import java.net.URL;
public class PanelFeedback extends JPanel {
    public PanelFeedback(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("Resumen para PRIMER NOMBRE:"); 
        //se debe de obtener el nombre del estudiante para mostrar aquí
        
        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Nombre completo: ASDAFASA"));
        add(new JLabel("Nombre de usuario: ADSADAFAS"));
        add(new JLabel("Sección: ADSASDAS"));
        add(new JLabel("Desempeño:"));
add(Box.createVerticalStrut(12));

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



        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
}   