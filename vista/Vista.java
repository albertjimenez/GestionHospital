package vista;

import java.util.Calendar;

import javax.swing.JFrame;

import modelo.estructurasED.GestionPaciente;

public interface Vista {
	public void creaGUI();

	public JFrame ventanaPacientes();

	// public JFrame ventanaBorrar();

	public JFrame ventanaConsulta();

	// public JFrame ventanaEdicion();

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

	// public int getNumeroHistoria();

	// public void mostrarCalendario(); // Nuevo jframe donde se añade el combo
	// box.

	public void mostrarErrorAntiPaciente();

	public void mostrarErrorPacienteRepe();

	// public List<Ingreso> getListaIngreso();
	public String getTextoIngreso();

	// public void setControlador(Controlador c);

	public void setModelo(GestionPaciente m);
}
