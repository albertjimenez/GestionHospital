package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Set;

import javax.swing.JOptionPane;

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

		ObjectOutputStream objeto = null;
		try {
			try {

				FileOutputStream file = new FileOutputStream(archivo);
				objeto = new ObjectOutputStream(file);
				// TODO Revisar esto
				objeto.writeObject(modelo);

			} finally {
				objeto.close();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichero de datos no existe. Se crea una nueva base.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cargar(File fichero) {
		ObjectInputStream objeto = null;
		try {
			try {
				FileInputStream file = new FileInputStream(fichero);
				objeto = new ObjectInputStream(file);
				modelo = (GestionPaciente) objeto.readObject();

			} finally {
				if (objeto != null)
					objeto.close();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichero de datos inexistente");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
