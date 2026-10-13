import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Administra la ventana principal y decide qué panel está visible.
public class GestorVentanas extends JFrame {
    private static final String INICIO = "inicio";


    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);

    public GestorVentanas() {
        super("titulo de la ventana"); //modificar nombre luego. crea la ventana princpal para añadir los paneles
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 540);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // se crea una sola instancia de cada pantalla.
        contenedor.add(new PanelInicio(this), INICIO);


        add(contenedor, BorderLayout.CENTER);

        mostrarInicio();
    }

    public void mostrarInicio() {
        tarjetas.show(contenedor, INICIO);
    }

   
}
