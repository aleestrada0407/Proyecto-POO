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

import java.net.URL;

public class PanelEstudiante extends JPanel {
    public PanelEstudiante(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("Hola"); 
        
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Selecciona qué deseas hacer:"));
        add(Box.createVerticalStrut(12));
       
        URL imagen = getClass().getResource("/imagenes/logo.png");
        if (imagen != null) {
            add(new JLabel(new ImageIcon(imagen)));
        }

        JButton ejercicio = new JButton("Realizar un ejercicio");
        ejercicio.setMaximumSize(new Dimension(200, 35));
        ejercicio.addActionListener(e -> gestor.mostrarElegirEjercicio());
        add(ejercicio);

        JButton desempeno = new JButton("Ver mi desempeño");
        desempeno.setMaximumSize(new Dimension(200, 35));

        desempeno.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
            "Resumen para NOMBRE DE USUARIO:\nNombre: NOMBRE\nDesempeño: NUMERO\nEjercicios completados: NUMERO");
        });
        add(desempeno);

        JButton volver = new JButton("Cerrar sesión");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarInicio());
        add(volver);

        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
}