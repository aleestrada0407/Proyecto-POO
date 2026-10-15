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
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;

//Pantalla de login que muestra información y ofrece inicio de sesión y registro
public class PanelEjercicio extends JPanel {
        private ImageIcon fondoEjercicio;
    public PanelEjercicio(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
        JLabel titulo = new JLabel("Ejercicio");
        //espacio entre los botones para dejar un poco de distancia
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Realice el siguiente inciso:"));
add(Box.createVerticalStrut(12));
        
           URL rutaFondo = getClass().getResource("/imagenes/fondo1.png");
        if (rutaFondo != null) {
            fondoEjercicio = new ImageIcon(rutaFondo);
        }


        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
        add(volver);
        
        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
        ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
            g.drawImage(fondoEjercicio.getImage(),
                0, 0, getWidth(), getHeight(), this);
    
            g.setColor(Color.DARK_GRAY);

        }
    }


