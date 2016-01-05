package controlador;

import modelo.Modelo;
import modelo.paciente.Paciente;

public interface Controlador {
	// public void setVista(Vista v);

	public void setModelo(Modelo m);

	public boolean insertPaciente(Paciente unPaciente);

	public boolean borrarPaciente(Paciente unPaciente);

	public boolean editarPaciente(Paciente unPaciente);

	public Paciente recuperarPaciente(int SIP);

	public boolean insertIngreso(int SIP, String ingreso, String tipo);
}
