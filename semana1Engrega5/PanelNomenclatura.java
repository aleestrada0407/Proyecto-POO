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
import javax.swing.JTextField;

import login.BancoEjercicios;


public class PanelNomenclatura extends JPanel {
    private final GestorVentanas gestor;
    private final BancoEjercicios banco;

    private final JLabel progreso = new JLabel(" ");
    private final JLabel instruccion = new JLabel(" ");
    private final JLabel compuesto = new JLabel(" ");
    private final JLabel ayuda = new JLabel(" ");
    private final JLabel retroalimentacion = new JLabel(" ");
    private final JTextField campoRespuesta = new JTextField(20);
    private final JButton comprobar = new JButton("Comprobar");
    private final JButton siguiente = new JButton("Siguiente ejercicio");

    public PanelNomenclatura(GestorVentanas gestor) {
        this.gestor = gestor;
        this.banco = gestor.getBanco();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        JLabel titulo = new JLabel("Nomenclatura");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        
        add(Box.createVerticalStrut(12));
        add(titulo);
        add(progreso);
        add(Box.createVerticalStrut(12));

        add(instruccion);
        compuesto.setFont(new Font("SansSerif", Font.BOLD, 26));
        add(compuesto);
        add(Box.createVerticalStrut(8));

        campoRespuesta.setMaximumSize(new Dimension(300, 30));
        add(campoRespuesta);
        ayuda.setFont(new Font("SansSerif", Font.ITALIC, 12));
        add(ayuda);
        add(Box.createVerticalStrut(8));

        retroalimentacion.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(retroalimentacion);
        add(Box.createVerticalStrut(12));

        comprobar.setMaximumSize(new Dimension(200, 35));
        add(comprobar);

        siguiente.setMaximumSize(new Dimension(200, 35));
        add(siguiente);

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
        add(volver);

        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }
}