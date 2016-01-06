package modelo.estructurasED;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import modelo.Modelo;
import modelo.paciente.Ingreso;
import modelo.paciente.Paciente;

public class GestionPaciente implements Serializable, Modelo {

	private ConcurrentHashMap<Integer, Paciente> mapaPacientes;
	private TreeSet<Paciente> conjuntoIngresados;

	public GestionPaciente() {
		mapaPacientes = new ConcurrentHashMap<Integer, Paciente>();
		conjuntoIngresados = new TreeSet<Paciente>();
	}

	@Override
	public Map<Integer, Paciente> getMapaPacientes() {
		return mapaPacientes;
	}

	@Override
	public boolean addPaciente(Paciente unPaciente) {

		int clave = unPaciente.getSIP();
		Paciente p = mapaPacientes.putIfAbsent(clave, unPaciente);
		return p == null;

	}

	@Override
	public boolean removePaciente(Paciente unPaciente) {

		int clave = unPaciente.getSIP();

		if (!mapaPacientes.containsKey(clave))
			return false;
		mapaPacientes.remove(clave);
		conjuntoIngresados.remove(unPaciente);
		return true;
	}

	@Override
	public boolean editPaciente(Paciente unPaciente) {
		int clave = unPaciente.getSIP();
		Paciente viejoPaciente = mapaPacientes.get(clave);
		if (!mapaPacientes.containsKey(clave))
			return false;
		return mapaPacientes.replace(clave, viejoPaciente, unPaciente);

	}

	@Override
	public Paciente searchPaciente(int SIP) {

		return mapaPacientes.get(SIP);
	}

	@Override
	public boolean addIngreso(int SIP, String Ingreso, String tipo) {
		Ingreso e = new Ingreso(Ingreso, tipo);
		Paciente p = mapaPacientes.get(SIP);
		if (p != null) {
			p.getIngresos().add(e);
			conjuntoIngresados.add(p);
			return true;
		}
		return false;

	}

	@Override
	public boolean altaIngreso(int SIP) {
		Paciente p = mapaPacientes.get(SIP);
		if (p != null)
			return conjuntoIngresados.remove(p);

		return false;
	}

	public TreeSet<Paciente> getConjuntoIngresados() {
		return conjuntoIngresados;
	}

	@Override
	public boolean esVacio() {
		return mapaPacientes.isEmpty();
	}

	@Override
	public Collection<Paciente> todosPacientes() {
		return mapaPacientes.values();
	}

}
