package actionListeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorImplementacionModelo;
import de.wannawork.jcalendar.JCalendarComboBox;
import modelo.paciente.Paciente;
import vista.VistaImplementacion;

public class VentanaEditarPaciente extends JFrame {
	// TODO boton para borrar paciente
	final String[] vectorGeneros = { "Hombre", "Mujer" };
	final String[] vectorEstados = { "Soltero", "Casado", "Divorciado", "Viudo" };
	final String[] vectorTipoIngreso = { "Paliativo", "Oncológico" };
	private JPanel superPanel;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private boolean abierta;
	private JCalendarComboBox fechaN;
	private JComboBox<String> desplegableGenero;
	private JComboBox<String> desplegableEstado;
	private JComboBox<String> desplegableTipoIngreso;
	private JTextField cajaNombre;
	private JTextField cajaApellidos;
	private JTextField cajaSip;
	private JTextField cajaPoblacion;
	private JTextField cajaProvincia;
	private JTextField cajaCP;
	private JTextField cajaDoctor;
	private JButton editPaciente;
	private JButton removePaciente;
	private ControladorImplementacionModelo controladorModelo;
	private Paciente p;

	/**
	 * Constructor de la clase, construye un nuevo panel y se asegura de que
	 * solo hay una instancia de este
	 */
	public VentanaEditarPaciente() {
		superPanel = new JPanel();
		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		abierta = false;
		fechaN = new JCalendarComboBox();
		desplegableEstado = new JComboBox<>();
		desplegableGenero = new JComboBox<>();
		desplegableTipoIngreso = new JComboBox<>();
		cajaNombre = new JTextField(8);
		cajaApellidos = new JTextField(8);
		cajaSip = new JTextField(8);
		cajaPoblacion = new JTextField(10);
		cajaProvincia = new JTextField(10);
		cajaCP = new JTextField(7);
		cajaDoctor = new JTextField(8);
		editPaciente = new JButton("Editar paciente");
		removePaciente = new JButton("Eliminar Paciente");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				abierta = false;
			}
		});
	}

	/**
	 * Metodo para crear la ventana UNICA
	 */
	public void creaGUI() {

		if (!abierta) {
			fechaN.setPreferredSize(new Dimension(150, 50));
			superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));
			rellenarDesplegables(desplegableEstado, vectorEstados);
			rellenarDesplegables(desplegableGenero, vectorGeneros);
			rellenarDesplegables(desplegableTipoIngreso, vectorTipoIngreso);
			editPaciente.setIcon(new ImageIcon(getClass().getResource("/media/32/edit.png")));
			removePaciente.setIcon(new ImageIcon(getClass().getResource("/media/32/remove.png")));
			addElementos();
			setCampos();
			editPaciente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = cajaNombre.getText();
					String apellidos = cajaApellidos.getText();
					int sip = p.getSIP();
					Calendar fechaNacimiento = fechaN.getCalendar();
					String sexo = (String) desplegableGenero.getSelectedItem();
					String estado = (String) desplegableEstado.getSelectedItem();
					String poblacion = cajaPoblacion.getText();
					String provincia = cajaProvincia.getText();
					int cP = Integer.parseInt(cajaCP.getText());
					String doctor = cajaDoctor.getText();

					Paciente p = new Paciente(nombre, apellidos, sip, fechaNacimiento, sexo, estado, poblacion,
							provincia, cP, doctor);
					controladorModelo.editarPaciente(p);
					JOptionPane.showMessageDialog(null, "Editado Correctamente");
					setVisible(false);
				}
			});
			removePaciente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int numeroSip = Integer.parseInt(cajaSip.getText());
					Paciente paciente = controladorModelo.recuperarPaciente(numeroSip);
					if (VistaImplementacion.mostrarConfirmacionBorrado(numeroSip)) {
						controladorModelo.borrarPaciente(paciente);
						VistaImplementacion.mostrarBorradoExitoso();
						setVisible(false);
					}

				}
			});
			this.setTitle("Editar Paciente");
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			abierta = true;
		}
	}

	/**
	 * Comprueba si la ventana está abierta
	 * 
	 * @return Verdadero en caso de que sí y Falso en caso de que no
	 */
	public boolean estaAbierta() {
		return abierta;
	}

	/**
	 * Metodo para añadir los elementos al panel, y este a la ventana
	 * 
	 */
	private void addElementos() {
		panel.add(new JLabel("Nombre: "));
		panel.add(cajaNombre);
		panel.add(new JLabel("Apellidos: "));
		panel.add(cajaApellidos);
		panel.add(new JLabel("SIP: "));
		// Bloquear SIP
		cajaSip.setText(Integer.toString(p.getSIP()));
		cajaSip.setEnabled(false);
		cajaSip.setOpaque(true);
		panel.add(cajaSip);
		panel.add(new JLabel("Fecha de Nacimiento: "));
		panel.add(fechaN);
		panel2.add(new JLabel("Sexo"));
		panel2.add(desplegableGenero);
		panel2.add(new JLabel("Estado:"));
		panel2.add(desplegableEstado);
		panel2.add(new JLabel("Población: "));
		panel2.add(cajaPoblacion);
		panel2.add(new JLabel("Província:"));
		panel2.add(cajaProvincia);
		panel2.add(new JLabel("Código Postal: "));
		panel2.add(cajaCP);
		panel2.add(new JLabel("Doctor: "));
		panel2.add(cajaDoctor);
		panel3.add(editPaciente);
		panel3.add(removePaciente);
		superPanel.add(panel);
		superPanel.add(panel2);
		superPanel.add(panel3);
		this.getContentPane().add(superPanel);

		// FOCUS cajaNombre
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cajaNombre.requestFocus();
			}
		});

	}

	/**
	 * Permite rellenar los {@link JComboBox}
	 * 
	 * @param desplegable
	 *            donde quieres añadir cosas
	 * @param elementos
	 *            a añadir en Vector[]
	 */
	private void rellenarDesplegables(JComboBox<String> desplegable, String[] elementos) {
		for (String string : elementos)
			desplegable.addItem(string);

	}

	private void setCampos() {
		cajaNombre.setText(p.getNombre());
		cajaApellidos.setText(p.getApellidos());
		fechaN.setCalendar(p.getFechaNacimiento());
		desplegableGenero.setSelectedItem(p.getSexo());
		desplegableEstado.setSelectedItem(p.getEstado());
		cajaPoblacion.setText(p.getPoblacion());
		cajaProvincia.setText(p.getProvincia());
		cajaCP.setText(Integer.toString(p.getCP()));
		cajaDoctor.setText(p.getDoctor());

	}

	public void setControladorModelo(ControladorImplementacionModelo controladorModelo) {
		this.controladorModelo = controladorModelo;
	}

	public void setP(Paciente p) {
		this.p = p;
	}
}
