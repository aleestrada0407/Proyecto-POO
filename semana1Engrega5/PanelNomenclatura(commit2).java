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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import login.BancoEjercicios;
import login.EjerciciosCompletarNomenclatura;

// Pantalla de ejercicios de nomenclatura: dada la fórmula se escribe el nombre
// (modo 1) o dado el nombre se escribe la fórmula (modo 2).
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

    private EjerciciosCompletarNomenclatura actual;
    private int aciertos;

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
        // Con Enter también se comprueba la respuesta.
        campoRespuesta.addActionListener(e -> {
            if (comprobar.isEnabled()) {
                comprobarRespuesta();
            }
        });
        add(campoRespuesta);
        ayuda.setFont(new Font("SansSerif", Font.ITALIC, 12));
        add(ayuda);
        add(Box.createVerticalStrut(8));

        retroalimentacion.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(retroalimentacion);
        add(Box.createVerticalStrut(12));

        comprobar.setMaximumSize(new Dimension(200, 35));
        comprobar.addActionListener(e -> comprobarRespuesta());
        add(comprobar);

        siguiente.setMaximumSize(new Dimension(200, 35));
        siguiente.addActionListener(e -> mostrarSiguiente());
        add(siguiente);

        JButton volver = new JButton("Volver");
        volver.setMaximumSize(new Dimension(200, 35));
        volver.addActionListener(e -> gestor.mostrarElegirEjercicio());
        add(volver);

        //mueve todo el texto a la izquierda para mejor orden
        for (Component componente : getComponents()) {
            ((JComponent) componente).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    // Se llama cada vez que el estudiante entra a esta pantalla: empieza una sesión nueva.
    public void iniciar() {
        banco.reiniciarNomenclatura();
        aciertos = 0;
        mostrarSiguiente();
    }

    private void mostrarSiguiente() {
        if (!banco.haySiguienteNomenclatura()) {
            JOptionPane.showMessageDialog(this, "¡Terminaste los ejercicios de nomenclatura!\n"
                    + "Aciertos: " + aciertos + " de " + banco.getCantidadTotalNomenclatura());
            gestor.mostrarElegirEjercicio();
            return;
        }

        actual = banco.obtenerSiguienteNomenclatura();

        progreso.setText("Ejercicio " + banco.getIndiceNomenclatura()
                + " de " + banco.getCantidadTotalNomenclatura());

        if (actual.getModo() == 1) {
            instruccion.setText("Escribe el nombre del compuesto:");
            compuesto.setText(PanelEjercicio.htmlSimple(formulaConCarga(actual.getFormulaQuimica())));
            ayuda.setText("Ejemplo: Sulfato");
        } else {
            instruccion.setText("Escribe la fórmula del compuesto:");
            compuesto.setText(PanelEjercicio.htmlSimple(PanelEjercicio.escapar(actual.getNombre())));
            ayuda.setText("Escribe sin subíndices y con la carga al final. Ejemplo: ClO3-");
        }

        campoRespuesta.setText("");
        campoRespuesta.setEnabled(true);
        retroalimentacion.setText(" ");
        comprobar.setEnabled(true);
        campoRespuesta.requestFocusInWindow();

        revalidate();
        repaint();
    }

    private void comprobarRespuesta() {
        if (actual.verificarRespuesta(campoRespuesta.getText())) {
            aciertos++;
            retroalimentacion.setText("¡Correcto!");
            comprobar.setEnabled(false);
            campoRespuesta.setEnabled(false);
        } else if (campoRespuesta.getText().trim().isEmpty()) {
            retroalimentacion.setText("Escribe una respuesta primero.");
        } else {
            retroalimentacion.setText("Incorrecto. Inténtalo de nuevo.");
        }
    }

    // MnO4- -> MnO<sub>4</sub><sup>-</sup>
    private static String formulaConCarga(String formula) {
        String carga = "";
        String cuerpo = formula;

        if (formula.endsWith("-") || formula.endsWith("+")) {
            carga = "<sup>" + formula.substring(formula.length() - 1) + "</sup>";
            cuerpo = formula.substring(0, formula.length() - 1);
        }

        return PanelEjercicio.formulaConSubindices(PanelEjercicio.escapar(cuerpo)) + carga;
    }
}
