package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public class ControladorImplementacionModelo implements ControladorModelo {

	// Atributos que son vista y modelo
	GestionPaciente modelo;

	// TODO Javadoc por aqui

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
	public boolean esMapaVacio() {
		return modelo.esMapaVacio();
	}

	@Override
	public boolean esConjuntoVacio() {
		return modelo.esConjuntoVacio();
	}

	@Override
	public Collection<Paciente> todosPacientes() {
		return modelo.todosPacientes();
	}

	@Override
	public void eliminarElemConjunto(Paciente p) {
		modelo.eliminarElemConjunto(p);

	}

	@Override
	public void eliminarTodoConjunto(Collection<Paciente> coleccion) {
		modelo.eliminarTodoConjunto(coleccion);

	}

	@Override
	public Set<Paciente> devolverPacientes() {
		return modelo.devolverPacientes();
	}

	@Override
	public int numeroIngresados() {

		return modelo.numeroIngresados();
	}

	@Override
	public int numeroPacientes() {

		return modelo.numeroPacientes();
	}

	@Override
	public void guardar(File archivo) {

		try {

			FileWriter escritor = new FileWriter(archivo);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String cadenaModelo = gson.toJson(modelo);
			escritor.write(cadenaModelo);
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cargar(File fichero) {

		Gson gson = new Gson();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(fichero));
			GestionPaciente nuevoModelo = gson.fromJson(buf, GestionPaciente.class);
			modelo = nuevoModelo;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Paciente> buscarNombre(String pattern) {
		return modelo.buscarNombre(pattern);
	}

}
