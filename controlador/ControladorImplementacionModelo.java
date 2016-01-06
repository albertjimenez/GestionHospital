package controlador;

import java.util.Collection;

import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public class ControladorImplementacionModelo implements ControladorModelo {

	// Atributos que son vista y modelo
	GestionPaciente modelo;

	@Override
	public void setModelo(GestionPaciente m) {
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

	@Override
	public boolean esVacio() {
		return modelo.esVacio();
	}

	@Override
	public Collection<Paciente> todosPacientes() {
		return modelo.todosPacientes();
	}

}
