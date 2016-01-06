package modelo;

import java.util.Collection;
import java.util.TreeSet;

import modelo.paciente.Paciente;

public interface Modelo {

	/**
	 * Comprueba si está vacio el MAPA
	 * 
	 * @return Verdadero si está vacio o Falso sino lo está
	 */

	public abstract boolean esMapaVacio();

	/**
	 * Comprueba si la lista de ingresados está vacia
	 * 
	 * @return Verdadero si está vacio o Falso sino lo está
	 */
	public abstract boolean esConjuntoVacio();

	/**
	 * Permite devolver todos los pacientes sin devolver el objeto del conjunto
	 * 
	 * @return Conjunto actual de pacientes ingresados
	 */
	public abstract TreeSet<Paciente> devolverPacientes();

	/**
	 * Añade el paciente a la estructura de datos Mapa
	 * 
	 * @param Paciente
	 *            que hay que añadir
	 * @return si ha sido añadido V sino F
	 */

	public abstract boolean addPaciente(Paciente unPaciente);

	/**
	 * Borra el paciente de la estructura de datos Mapa
	 * 
	 * @param Paciente
	 *            que hay que borrar
	 * @return si ha sido borrado V sino F
	 */

	public abstract boolean removePaciente(Paciente unPaciente);

	/**
	 * Elimina un paciente del conjunto de ingresados
	 * 
	 * @param Paciente
	 *            a eliminar
	 */
	public abstract void eliminarElemConjunto(Paciente p);

	/**
	 * Vacia todo el conjunto de ingresados
	 * 
	 * @param coleccion
	 *            con los pacientes a eliminar
	 */
	public abstract void eliminarTodoConjunto(Collection<Paciente> coleccion);

	/**
	 * Edita el paciente de la estructura de datos Mapa
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

	/**
	 * Numero de pacientes actuales en la base de datos (Mapa)
	 * 
	 * @return Devuelve el numero, o 0 si no hay
	 */
	public abstract int numeroPacientes();

	/**
	 * Numero de pacientes actuales ingresados (TreeSet)
	 * 
	 * @return Devuelve el numero, o 0 si no hay
	 */
	public abstract int numeroIngresados();

}