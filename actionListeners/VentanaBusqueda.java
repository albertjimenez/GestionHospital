package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaBusqueda extends JFrame {
	private JLabel texto;
	private JPanel panel;
	private JTextField cajaTextoSip;
	private JButton boton;

	/**
	 * Constructor de la clase que instancia el texto, el panel, la caja de
	 * texto, y el boton, añadiendo el escuchador (ActionListener)
	 */
	public VentanaBusqueda() {
		escuchadorBotonBusqueda escuchador = new escuchadorBotonBusqueda(new VentanaEditarPaciente());
		texto = new JLabel("Escribe el SIP: ");
		panel = new JPanel();
		cajaTextoSip = new JTextField(9);
		// Añado el action listener a la caja de texto
		cajaTextoSip.addActionListener(escuchador);
		boton = new JButton("Buscar", new ImageIcon(getClass().getResource("/media/32/buscar.png")));
		boton.addActionListener(escuchador);

	}

	/**
	 * Metodo como el creaGUI() que permite arrancar la ventan con su titulo y
	 * su posicion
	 * 
	 * @param titulo
	 *            de la ventana
	 * 
	 */
	public void creaVentana(String titulo) {
		addElementos();
		this.setTitle(titulo);
		this.setLocationRelativeTo(null);
		this.pack();
		// this.setAlwaysOnTop(true);
		this.setVisible(true);

	}

	/**
	 * Permite añadir todos los elementos que estan como atributos, al panel, y
	 * este a la ventana
	 */
	private void addElementos() {
		panel.add(texto);
		panel.add(cajaTextoSip);
		panel.add(boton);
		this.getContentPane().add(panel);
	}
}

class escuchadorBotonBusqueda implements ActionListener {
	private VentanaEditarPaciente ventana;

	/**
	 * Constructor necesario para asegurar una unica instancia de la ventana de
	 * edicion paciente
	 * 
	 * @param ventana
	 *            de {@link VentanaEditarPaciente}
	 */
	public escuchadorBotonBusqueda(VentanaEditarPaciente ventana) {
		this.ventana = ventana;
	}

	/**
	 * Accion a realizar
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!ventana.estaAbierta())
			ventana.creaGUI();

	}

}
