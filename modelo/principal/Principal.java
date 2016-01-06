package modelo.principal;

import javax.swing.SwingUtilities;

import controlador.ControladorImplementacionModelo;
import modelo.estructurasED.GestionPaciente;
import vista.VistaImplementacion;

public class Principal {

	public static void main(String[] args) {
		GestionPaciente m = new GestionPaciente();
		VistaImplementacion v = new VistaImplementacion();
		ControladorImplementacionModelo c = new ControladorImplementacionModelo();
		v.setModelo(m);
		v.setControlador(c);
		c.setModelo(m);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				v.creaGUI();

			}
		});
	}

}
