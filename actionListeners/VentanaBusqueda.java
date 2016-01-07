package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorImplementacionModelo;

public class VentanaBusqueda extends JFrame {
	private JLabel texto;
	private JPanel panel;
	private JTextField cajaTextoSip;
	private JButton boton;
	private ControladorImplementacionModelo controladorModelo;
	private escuchadorBotonBusqueda escuchador;

	/**
	 * Constructor de la clase que instancia el texto, el panel, la caja de
	 * texto, y el boton, añadiendo el escuchador (ActionListener)
	 */
	public VentanaBusqueda() {
		texto = new JLabel("Escribe el SIP: ");
		panel = new JPanel();
		cajaTextoSip = new JTextField(9);

		// Añado el action listener a la caja de texto

		boton = new JButton("Buscar", new ImageIcon(getClass().getResource("/media/32/buscar.png")));

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
		VentanaEditarPaciente ventanaEdicion = new VentanaEditarPaciente();
		ventanaEdicion.setControladorModelo(controladorModelo);
		escuchador = new escuchadorBotonBusqueda(ventanaEdicion, cajaTextoSip);
		escuchador.setControladorModelo(controladorModelo);
		cajaTextoSip.addActionListener(escuchador);
		boton.addActionListener(escuchador);
		this.setTitle(titulo);
		this.setLocationRelativeTo(null);
		this.pack();
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

	public void setControladorModelo(ControladorImplementacionModelo controladorModelo) {
		this.controladorModelo = controladorModelo;
	}

	protected String devolverNumeroSip() {
		return cajaTextoSip.getText();
	}

}

class escuchadorBotonBusqueda implements ActionListener {
	private VentanaEditarPaciente ventana;
	private JTextField cajaSip;
	private ControladorImplementacionModelo controladorModelo;

	/**
	 * Constructor necesario para asegurar una unica instancia de la ventana de
	 * edicion paciente
	 * 
	 * @param ventana
	 *            de {@link VentanaEditarPaciente}
	 */
	public escuchadorBotonBusqueda(VentanaEditarPaciente ventana, JTextField cajaSip) {
		this.ventana = ventana;
		this.cajaSip = cajaSip;
	}

	/**
	 * Accion a realizar
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String texto = cajaSip.getText();
		if (texto.equals(""))
			JOptionPane.showMessageDialog(null, "campo vacío");
		else if (!ventana.estaAbierta()) {
			int sip = -1;
			try {
				sip = Integer.parseInt(texto);
				if (comprobarSip(sip)) {
					ventana.setP(controladorModelo.recuperarPaciente(sip));
					ventana.creaGUI();
				} else
					JOptionPane.showMessageDialog(null, "No existe ese sip");

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "No era un numero");
			}

		}

	}

	private boolean comprobarSip(int sip) {
		return controladorModelo.recuperarPaciente(sip) != null;
	}

	public void setControladorModelo(ControladorImplementacionModelo controladorModelo) {
		this.controladorModelo = controladorModelo;
	}

}
