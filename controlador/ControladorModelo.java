package controlador;

import java.util.Collection;

import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public interface ControladorModelo {
	// public void setVista(Vista v);
	public boolean esVacio();

	public void setModelo(GestionPaciente m);

	public boolean insertPaciente(Paciente unPaciente);

	public boolean borrarPaciente(Paciente unPaciente);

	public boolean editarPaciente(Paciente unPaciente);

	public Paciente recuperarPaciente(int SIP);

	public boolean insertIngreso(int SIP, String ingreso, String tipo);

	public Collection<Paciente> todosPacientes();
}
