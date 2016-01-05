package controlador;

import modelo.Modelo;
import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public class ControladorImplementacion implements Controlador {

	// Atributos que son vista y modelo
	Modelo modelo;

	// Vista vista;

	public ControladorImplementacion() {
		super();
		modelo = new GestionPaciente();

	}

	// @Override
	// public void setVista(Vista v) {
	// this.vista = v;
	// }

	@Override
	public void setModelo(Modelo m) {
		this.modelo = m;

	}

	@Override
	public boolean insertPaciente(Paciente unPaciente) {
		return modelo.addPaciente(unPaciente);
	}

	@Override
	public boolean borrarPaciente(Paciente unPaciente) {
		return modelo.removePaciente(unPaciente);
	}

	@Override
	public boolean editarPaciente(Paciente unPaciente) {
		return modelo.editPaciente(unPaciente);
	}

	@Override
	public Paciente recuperarPaciente(int SIP) {
		return modelo.searchPaciente(SIP);
	}

	@Override
	public boolean insertIngreso(int SIP, String ingreso, String tipo) {
		return modelo.addIngreso(SIP, ingreso, tipo);

	}

}
