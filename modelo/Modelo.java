package modelo;

import java.util.Map;
import java.util.TreeSet;

import modelo.paciente.Paciente;
import vista.Vista;

public interface Modelo {

	public abstract Map<Integer, Paciente> getMapaPacientes();

	public abstract boolean addPaciente(Paciente unPaciente);

	public abstract boolean removePaciente(Paciente unPaciente);

	public abstract boolean editPaciente(Paciente unPaciente);

	public abstract Paciente searchPaciente(int SIP);

	public abstract boolean addIngreso(int SIP, String Ingreso, String tipo);

	public abstract boolean altaIngreso(int SIP);

	public abstract TreeSet<Paciente> getConjuntoIngresados();

	public abstract void setVista(Vista v);

}