package vista;

import java.util.Calendar;

import javax.swing.JFrame;

import controlador.ControladorImplementacionModelo;

public interface Vista {
	public void creaGUI();

	public JFrame ventanaPacientes();

	public JFrame ventanaConsulta();

	public JFrame ventanaIngreso();

	public String getNombre();

	public String getApellido();

	public int getSIP();

	public Calendar getFechaNacimiento();

	public String getSexo();

	public String getEstado();

	public String getPoblacion();

	public String getProvincia();

	public int getCP();

	public String getDoctor();

	public void mostrarErrorAntiPaciente();

	public void mostrarErrorPacienteRepe();

	public String getTextoIngreso();

	public void setControlador(ControladorImplementacionModelo c);

	// public void setModelo(GestionPaciente m);
}
