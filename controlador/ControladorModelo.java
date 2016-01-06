package controlador;

import java.io.File;
import java.util.Collection;
import java.util.TreeSet;

import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public interface ControladorModelo {

	public boolean esMapaVacio();

	public void setModelo(GestionPaciente m);

	public boolean insertPaciente(Paciente unPaciente);

	public boolean borrarPaciente(Paciente unPaciente);

	public boolean editarPaciente(Paciente unPaciente);

	public Paciente recuperarPaciente(int SIP);

	public boolean insertIngreso(int SIP, String ingreso, String tipo);

	public Collection<Paciente> todosPacientes();

	public boolean esConjuntoVacio();

	public void eliminarTodoConjunto(Collection<Paciente> coleccion);

	public void eliminarElemConjunto(Paciente p);

	public TreeSet<Paciente> devolverPacientes();

	/**
	 * Numero de pacientes actuales en la base de datos (Mapa)
	 * 
	 * @return Devuelve el numero, o 0 si no hay
	 */
	public int numeroPacientes();

	/**
	 * Numero de pacientes actuales ingresados (TreeSet)
	 * 
	 * @return Devuelve el numero, o 0 si no hay
	 */
	public int numeroIngresados();

	public void guardar(File archivo);

	public void cargar(File archivo);
}
