package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelElegirEjercicio extends JPanel {
    public PanelElegirEjercicio(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        JLabel titulo = new JLabel("Ejercicios");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
       
        add(Box.createVerticalStrut(12));
        add(titulo);
        add(new JLabel("Selecciona qué quieres practicar:"));
        add(Box.createVerticalStrut(12));

        JButton balanceo = new JButton("Balanceo de ecuaciones");
        balanceo.setMaximumSize(new Dimension(220, 35));
        balanceo.addActionListener(e -> gestor.mostrarEjercicio());
        add(balanceo);

        JButton nomenclatura = new JButton("Nomenclatura");
        nomenclatura.setMaximumSize(new Dimension(220, 35));
        nomenclatura.addActionListener(e -> gestor.mostrarNomenclatura());
        add(nomenclatura);

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(220, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
        add(volver);

       
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }
}