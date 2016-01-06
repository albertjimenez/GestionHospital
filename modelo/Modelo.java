package modelo;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

import modelo.paciente.Paciente;

public interface Modelo {
	// TODO eliminar esta mierda en cuanto puedas
	public abstract Map<Integer, Paciente> getMapaPacientes();

	/**
	 * Comprueba si está vacio el mapa
	 * 
	 * @return Verdadero si está vacio o Falso sino lo está
	 */

	public abstract boolean esVacio();

	/**
	 * Añade el paciente a la estructura de datos
	 * 
	 * @param Paciente
	 *            que hay que añadir
	 * @return si ha sido añadido V sino F
	 */

	public abstract boolean addPaciente(Paciente unPaciente);

	/**
	 * Borra el paciente de la estructura de datos
	 * 
	 * @param Paciente
	 *            que hay que borrar
	 * @return si ha sido borrado V sino F
	 */

	public abstract boolean removePaciente(Paciente unPaciente);

	/**
	 * Edita el paciente de la estructura de datos
	 * 
	 * @param Paciente
	 *            que hay que editar
	 * @return si ha sido editado V sino F
	 */

	public abstract boolean editPaciente(Paciente unPaciente);

	/**
	 * Busca el paciente en la estructura de datos
	 * 
	 * @param la
	 *            clave el numero SIP
	 * 
	 * @return Devuelve si el paciente se encuentra en la base o NULL si no está
	 */

	public abstract Paciente searchPaciente(int SIP);

	/**
	 * Añade el ingreso al conjunto SET
	 * 
	 * @param SIP
	 *            o clave
	 * @param Descripción
	 *            del ingreso
	 * 
	 * @param tipo
	 *            del ingreso (desplegable)
	 * 
	 * @return si ha sido añadido el ingreso V sino F
	 */

	public abstract boolean addIngreso(int SIP, String Ingreso, String tipo);

	/**
	 * Elimina el ingreso del conjunto SET
	 * 
	 * @param SIP
	 *            o clave
	 * 
	 * @return si ha sido dado de alta (eliminado ) el ingreso V sino F
	 */

	public abstract boolean altaIngreso(int SIP);

	/**
	 * Permite obtener todos los pacientes existentes en la base
	 *
	 * @return Collection de tipo Paciente con todos los pacientes existentes
	 */
	public abstract Collection<Paciente> todosPacientes();

	// TODO eliminar esta mierda en cuanto puedas
	public abstract TreeSet<Paciente> getConjuntoIngresados();

}