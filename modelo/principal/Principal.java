package modelo.principal;

import javax.swing.SwingUtilities;

import modelo.estructurasED.GestionPaciente;
import vista.VistaImplementacion;

public class Principal {

	public static void main(String[] args) {
		VistaImplementacion v = new VistaImplementacion();
		GestionPaciente m = new GestionPaciente();

		v.setModelo(m);
		// m.setVista(v);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				v.creaGUI();

			}
		});
	}

}
