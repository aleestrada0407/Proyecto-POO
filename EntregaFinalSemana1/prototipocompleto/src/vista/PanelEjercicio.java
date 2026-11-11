package vista;
import login.BancoEjercicios; // Sevira de coneccion para usar los ejercicios ya que estan en la carpeta login
import login.Ejercicio;
import login.RespuestaEjercicio;
import login.Resultado;
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
 
    private GestorVentanas gestor;        // Se guarda para poder acceder al usuario actual y al DAO de resultados
    private BancoEjercicios banco;        // las siguientes dos variables nos permitira conservar el banco de preguntas y el ejercicio mostrados 
    private Ejercicio ejercicioActual;
    private ArrayList<JTextField> camposReactivos; // Alamacenara los campos de las respuestas que ponga el usuario 
    private ArrayList<JTextField> camposProductos;
 
    private JLabel lblEnunciado;
    private JLabel lblEcuacion;
    private JPanel panelReactivos;
    private JPanel panelProductos;
    private JButton botonSiguiente;
 
    // Indica si ya se guardó en la BD el primer intento del ejercicio actual.
    // Evita que los reintentos del mismo ejercicio se guarden como resultados nuevos.
    private boolean intentoRegistrado;
 
    
 
    public PanelEjercicio(GestorVentanas gestor) {
        
        this.gestor = gestor;
 
        // Se usa el mismo banco del gestor (cargado desde la BD) para que cada ejercicio
        // tenga su id real. Con un banco propio los ids serían 0 y no se podrían guardar resultados.
        banco = gestor.getBanco();
 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 110, 25, 30));
 
        JLabel titulo = new JLabel("Ejercicio");
        add(Box.createVerticalStrut(12));
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(titulo);
        add(new JLabel("Realice el siguiente inciso:"));
        add(Box.createVerticalStrut(12));
 
 
        ejercicioActual = banco.obtenerSiguiente();
        lblEnunciado = new JLabel(ejercicioActual.getEnunciado());
        add(lblEnunciado);
        lblEcuacion = new JLabel(ejercicioActual.armarEcuacion());
        add(lblEcuacion);
 
        camposReactivos = new ArrayList<>();
        camposProductos = new ArrayList<>();
 
        add(Box.createVerticalStrut(15));
 
        panelReactivos = new JPanel();
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
 
        panelProductos = new JPanel();
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
 
        // Botón para avanzar al siguiente ejercicio
        botonSiguiente = new JButton("Siguiente ejercicio");
        botonSiguiente.setEnabled(false);
        botonSiguiente.addActionListener(e -> mostrarSiguienteEjercicio());
        add(botonSiguiente);
 
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
 
    private void mostrarSiguienteEjercicio() {
 
        // Comprobamos si todavía quedan ejercicios
        if (!banco.haySiguiente()) {
            JOptionPane.showMessageDialog(this,
                    "¡Has llegado al final de los ejercicios!");
            botonSiguiente.setEnabled(false);
            return;
        }
 
        // Obtenemos el siguiente ejercicio
        ejercicioActual = banco.obtenerSiguiente();
 
        // Ejercicio nuevo: todavía no se ha guardado ningún intento.
        intentoRegistrado = false;
 
        // Actualizamos los textos
        lblEnunciado.setText(ejercicioActual.getEnunciado());
        lblEcuacion.setText(ejercicioActual.armarEcuacion());
 
        // Eliminamos los campos del ejercicio anterior
        panelReactivos.removeAll();
        panelProductos.removeAll();
 
        // Limpiamos las listas de campos
        camposReactivos.clear();
        camposProductos.clear();
 
        // Creamos los campos para los nuevos reactivos
        for (String formula : ejercicioActual.getFormulasReactivos()) {
 
            JTextField campo = new JTextField(3);
            campo.setMaximumSize(new Dimension(55, 30));
 
            panelReactivos.add(campo);
            panelReactivos.add(new JLabel(formula));
 
            camposReactivos.add(campo);
        }
 
        // Creamos los campos para los nuevos productos
        for (String formula : ejercicioActual.getFormulasProductos()) {
 
            JTextField campo = new JTextField(3);
            campo.setMaximumSize(new Dimension(55, 30));
 
            panelProductos.add(campo);
            panelProductos.add(new JLabel(formula));
 
            camposProductos.add(campo);
        }
 
        // Actualizamos la presentación gráfica
        panelReactivos.revalidate();
        panelReactivos.repaint();
 
        panelProductos.revalidate();
        panelProductos.repaint();
 
        revalidate();
        repaint();
 
        // Deshabilitamos el botón hasta comprobar otra respuesta
        botonSiguiente.setEnabled(false);
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
 
        // Solo se guarda el primer intento de cada ejercicio.
        // Esto va después de validar los números, así que una entrada inválida no se guarda.
        if (!intentoRegistrado) {
            Resultado resultado = new Resultado(
                    ejercicioActual,
                    gestor.getNombreUsuarioActual(),
                    new RespuestaEjercicio(coefReactivos, coefProductos),
                    correcta);
            gestor.getResultadoBalanceodao().insertar(resultado);
            intentoRegistrado = true;
        }
 
        if (correcta) {
            JOptionPane.showMessageDialog(this,
                    "¡Correcto! La ecuación está balanceada.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "La ecuación no está balanceada. Intenta nuevamente.");
        }
 
// Permitimos avanzar después de comprobar una respuesta válida
botonSiguiente.setEnabled(true);
 
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
 