import java.awt.Dimension;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Pantalla de login que muestra información y ofrece inicio de sesión y registro
public class PanelInicio extends JPanel {
    public PanelInicio(GestorVentanas gestor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(60, 30, 25, 30));
        add(new JLabel("Bienvenido"));
        add(new JLabel("Selecciona qué deseas hacer:"));

        //se agregarán las imagenes después cuando se profundice en el diseño
        URL imagen = getClass().getResource("/imagenes/logo.png");
        if (imagen != null) {
            add(new JLabel(new ImageIcon(imagen)));
        }

        JButton entrar = new JButton("Iniciar sesión");
        entrar.setMaximumSize(new Dimension(220, 35));
        entrar.addActionListener(e -> gestor.mostrarLogin());
        add(entrar);


    }
}
