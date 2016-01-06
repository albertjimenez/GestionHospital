package actionListeners;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaEditarPaciente extends JFrame {
	// TODO acabar esta clase que contiene la vista pacientes
	// TODO tambien hay que poner un atributo del controlador para acceder a la
	// busqueda y edidcion del paciente
	private JPanel panel;
	private JButton boton;
	private boolean abierta;

	/**
	 * Constructor de la clase, construye un nuevo panel y se asegura de que
	 * solo hay una instancia de este
	 */
	public VentanaEditarPaciente() {
		panel = new JPanel();
		boton = new JButton("Echa ahi");
		abierta = false;
	}

	/**
	 * Metodo para crear la ventana UNICA
	 */
	public void creaGUI() {

		if (!abierta) {
			addElementos();
			this.setTitle("Editar Paciente");
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			abierta = true;
		}
	}

	/**
	 * Comprueba si la ventana está abierta
	 * 
	 * @return Verdadero en caso de que sí y Falso en caso de que no
	 */
	public boolean estaAbierta() {
		return abierta;
	}

	/**
	 * Metodo para añadir los elementos al panel, y este a la ventana
	 * 
	 */
	private void addElementos() {
		panel.add(boton);
		this.getContentPane().add(panel);

	}

}
