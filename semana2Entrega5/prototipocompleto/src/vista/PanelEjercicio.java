package vista;
import login.BancoEjercicios; // Sevira de coneccion para usar los ejercicios ya que estan en la carpeta login
import login.Ejercicio;
import javax.swing.JTextField;
import javax.swing.JOptionPane; // Permitira que el usuario agregue, guarde y compruebe sus respuestas
import java.util.ArrayList;

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Pantalla del ejercicio de balanceo (por ahora solo el esqueleto visual).
public class PanelEjercicio extends JPanel {
    private ImageIcon fondoEjercicio;

    private BancoEjercicios banco;        // las siguientes dos variables nos permitira conservar el banco de preguntas y el ejercicio mostrados 
    private Ejercicio ejercicioActual;
    private ArrayList<JTextField> camposReactivos; // Alamacenara los campos de las respuestas que ponga el usuario 
    private ArrayList<JTextField> camposProductos;

    

    public PanelEjercicio(GestorVentanas gestor) {
        
        banco = new BancoEjercicios(); // Crea el banco que contiene los ejercicios 

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));

        JLabel titulo = new JLabel("Ejercicio");
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Realice el siguiente inciso:"));
        add(Box.createVerticalStrut(12));


        ejercicioActual = banco.obtenerSiguiente();
        JLabel lblEnunciado = new JLabel(ejercicioActual.getEnunciado());
        add(lblEnunciado);                                                      // obtienen las instrucciones y ejercicios 
        JLabel lblEcuacion = new JLabel(ejercicioActual.armarEcuacion());
        add(lblEcuacion);

        camposReactivos = new ArrayList<>();
        camposProductos = new ArrayList<>();

        add(Box.createVerticalStrut(15));

        JPanel panelReactivos = new JPanel();
        panelReactivos.setOpaque(false);

        String[] reactivos = ejercicioActual.getFormulasReactivos();

        for (String formula : reactivos) {
            JTextField campo = new JTextField(3);
            campo.setMaximumSize(new Dimension(55, 30));

            panelReactivos.add(campo);
            panelReactivos.add(new JLabel(formula));

            camposReactivos.add(campo);
        }

        add(panelReactivos);

        JPanel panelProductos = new JPanel();
        panelProductos.setOpaque(false);

        String[] productos = ejercicioActual.getFormulasProductos();

        for (String formula : productos) {
            JTextField campo = new JTextField(3);
            campo.setMaximumSize(new Dimension(55, 30));

            panelProductos.add(campo);
            panelProductos.add(new JLabel(formula));

            camposProductos.add(campo);
        }

        add(new JLabel("Productos:"));
        add(panelProductos);
        JButton comprobar = new JButton("Comprobar respuesta");
        comprobar.addActionListener(e -> comprobarRespuesta());
        add(comprobar);

        // Fondo de la pantalla (se dibuja en paintComponent)
        URL rutaFondo = getClass().getResource("/vista/imagenes/fondo1.png");
        if (rutaFondo != null) {
            fondoEjercicio = new ImageIcon(rutaFondo);
        }

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarEstudiante());
        add(volver);

        // Alinea todo a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    private void comprobarRespuesta() {

    try {
        int[] coefReactivos =
                new int[camposReactivos.size()];

        int[] coefProductos =
                new int[camposProductos.size()];

        for (int i = 0; i < camposReactivos.size(); i++) {
            coefReactivos[i] = Integer.parseInt(
                    camposReactivos.get(i).getText().trim()
            );

            if (coefReactivos[i] <= 0) {
                throw new NumberFormatException();
            }
        }

        for (int i = 0; i < camposProductos.size(); i++) {
            coefProductos[i] = Integer.parseInt(
                    camposProductos.get(i).getText().trim()
            );

            if (coefProductos[i] <= 0) {
                throw new NumberFormatException();
            }
        }

        boolean correcta = ejercicioActual.esBalanceada(
                coefReactivos,
                coefProductos
        );

        if (correcta) {
            JOptionPane.showMessageDialog(this,
                    "¡Correcto! La ecuación está balanceada.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "La ecuación no está balanceada. Intenta nuevamente.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Introduce números enteros positivos en todos los campos.");
    }
}

    // Pinta el fondo estirado a todo el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondoEjercicio != null) {
            g.drawImage(fondoEjercicio.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
