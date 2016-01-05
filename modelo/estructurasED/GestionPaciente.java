package modelo.estructurasED;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import modelo.Modelo;
import modelo.paciente.Ingreso;
import modelo.paciente.Paciente;
import vista.Vista;

public class GestionPaciente implements Serializable, Modelo {



	private Map<Integer, Paciente> mapaPacientes;
	private TreeSet<Paciente> conjuntoIngresados;
	private transient Vista v;

	public GestionPaciente() {
		mapaPacientes = new HashMap<Integer, Paciente>();
		conjuntoIngresados = new TreeSet<Paciente>();
	}

	@Override
	public Map<Integer, Paciente> getMapaPacientes() {
		return mapaPacientes;
	}

	@Override
	public boolean addPaciente(Paciente unPaciente) {

		int clave = unPaciente.getSIP();

		if (mapaPacientes.containsKey(clave))
			return false;
		else {
			mapaPacientes.put(clave, unPaciente);

			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.estructurasED.Modelo#removePaciente(modelo.paciente.Paciente)
	 */
	@Override
	public boolean removePaciente(Paciente unPaciente) {

		int clave = unPaciente.getSIP();
		if (!mapaPacientes.containsKey(clave))
			return false;
		mapaPacientes.remove(clave);
		conjuntoIngresados.remove(unPaciente);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelo.estructurasED.Modelo#editPaciente(modelo.paciente.Paciente)
	 */
	@Override
	public boolean editPaciente(Paciente unPaciente) {
		int clave = unPaciente.getSIP();
		Paciente viejoPaciente = mapaPacientes.get(clave);

		return mapaPacientes.replace(clave, viejoPaciente, unPaciente);

	}

	@Override
	public Paciente searchPaciente(int SIP) {

		return mapaPacientes.get(SIP);
	}

	@Override
	public boolean addIngreso(int SIP, String Ingreso, String tipo) {
		Ingreso e = new Ingreso(Ingreso, tipo);
		if (mapaPacientes.containsKey(SIP)) {
			mapaPacientes.get(SIP).getIngresos().add(e);
			conjuntoIngresados.add(mapaPacientes.get(SIP));
			return true;
		}
		return false;

	}

	@Override
	public boolean altaIngreso(int SIP) {
		if (mapaPacientes.containsKey(SIP)) {
			conjuntoIngresados.remove(mapaPacientes.get(SIP));
			return true;
		}
		return false;
	}

	public TreeSet<Paciente> getConjuntoIngresados() {
		return conjuntoIngresados;
	}

	@Override
	public void setVista(Vista v) {

		this.v = v;
	}
}
