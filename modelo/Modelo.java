package modelo;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

import modelo.paciente.Paciente;

public interface Modelo {
	public abstract Map<Integer, Paciente> getMapaPacientes();

	public abstract boolean esVacio();

	/**
	 * Añade el paciente a la estructura de datos
	 * 
	 * @param Paciente
	 *            que hay que añadir
	 * @return si ha sido añadido V sino F
	 */

	public abstract boolean addPaciente(Paciente unPaciente);

	public abstract boolean removePaciente(Paciente unPaciente);

	public abstract boolean editPaciente(Paciente unPaciente);

	public abstract Paciente searchPaciente(int SIP);

	public abstract boolean addIngreso(int SIP, String Ingreso, String tipo);

	public abstract boolean altaIngreso(int SIP);

	public abstract TreeSet<Paciente> getConjuntoIngresados();

	public abstract Collection<Paciente> todosPacientes();

}