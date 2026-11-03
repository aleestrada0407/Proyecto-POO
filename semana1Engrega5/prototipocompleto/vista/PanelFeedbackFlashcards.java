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
public class PanelFeedbackFlashcards extends JPanel {
    public PanelFeedbackFlashcards(GestorVentanas gestor) {
        JButton volver = new JButton("Volver");
                volver.setMaximumSize(new Dimension(200, 35));
                volver.addActionListener(e -> gestor.mostrarEstudiante());
                add(volver);
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
}