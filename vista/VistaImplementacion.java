package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import actionListeners.VentanaBusqueda;
import controlador.ControladorImplementacionModelo;
import de.wannawork.jcalendar.JCalendarComboBox;
import jxl.Workbook;
import jxl.write.Boolean;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Ingreso;
import modelo.paciente.Paciente;

public class VistaImplementacion implements Vista, Serializable {
	// Atributos
	int contador = 1;

	private GestionPaciente modeloGestor;
	private ControladorImplementacionModelo controladorModelo;

	// Textfields
	private JFileChooser file;
	private JTextField cajaNombre;
	private JTextField cajaApellidos;
	private JTextField cajaSip;
	private JCalendarComboBox fechaN;
	private JComboBox<String> desplegableGenero;
	private JComboBox<String> desplegableEstado;
	private JTextField cajaPoblacion;
	private JTextField cajaProvincia;
	private JTextField cajaCP;
	private JTextField cajaDoctor;
	private JTextField textoIngreso;
	private JComboBox<String> desplegableTipoIngreso;
	private JLabel informacion;

	public VistaImplementacion() {

		file = new JFileChooser();
		cajaNombre = new JTextField(8);
		cajaApellidos = new JTextField(8);
		cajaSip = new JTextField(8);
		fechaN = new JCalendarComboBox();
		desplegableGenero = new JComboBox<String>();
		desplegableEstado = new JComboBox<String>();
		cajaPoblacion = new JTextField(10);
		cajaProvincia = new JTextField(10);
		cajaCP = new JTextField(7);
		cajaDoctor = new JTextField(8);
		textoIngreso = new JTextField(10);
		desplegableTipoIngreso = new JComboBox<String>();
		informacion = new JLabel("En la base de datos hay " + "0 pacientes y " + "0 ingresados.");
	}

	@Override
	public void creaGUI() {

		JFrame ventana = new JFrame("Base de Datos Hospitalaria");
		JPanel panel = new JPanel();
		// Boton NUEVO PACIENTE
		JButton nuevoPaciente = new JButton("Añadir Paciente");
		nuevoPaciente.setIcon(new ImageIcon(getClass().getResource("/media/64/newP.png")));

		// BOTON CONSULTAR PACIENTE
		JButton consultarPaciente = new JButton("Consultar Paciente");
		consultarPaciente.setIcon(new ImageIcon(getClass().getResource("/media/64/consultar.png")));

		// BOTON Editar Paciente

		JButton editarPaciente = new JButton("Editar Paciente");
		editarPaciente.setIcon(new ImageIcon(getClass().getResource("/media/64/edit.png")));
		//
		// BOTON NUEVO Ingreso

		JButton nuevoIngreso = new JButton("Añadir Ingreso");
		nuevoIngreso.setIcon(new ImageIcon(getClass().getResource("/media/64/doctor.png")));

		// BOTON MOSTRAR Ingresos

		JButton mostrarListaIngresados = new JButton("Mostrar Ingresados");
		mostrarListaIngresados.setIcon(new ImageIcon(getClass().getResource("/media/64/ingresados.png")));

		// BOTON MOSTRAR TODOS

		JButton mostrarTodos = new JButton("Mostrar pacientes",
				new ImageIcon(getClass().getResource("/media/64/todos.png")));

		// BOTON Guardar

		JButton guardar = new JButton("Guardar");
		guardar.setIcon(new ImageIcon(getClass().getResource("/media/64/save.png")));

		// Boton Cargar

		JButton cargar = new JButton("Cargar");
		cargar.setIcon(new ImageIcon(getClass().getResource("/media/64/cargar.png")));

		// boton Exportar

		JButton exportar = new JButton("Exportar");
		exportar.setIcon(new ImageIcon(getClass().getResource("/media/64/excel.png")));
		JPanel superpanel = new JPanel();
		superpanel.setLayout(new BoxLayout(superpanel, BoxLayout.Y_AXIS));
		JPanel panel2 = new JPanel();
		// ADDS de cosicas
		panel2.add(informacion);
		JPanel panel3 = new JPanel();
		JButton botonLicencia = new JButton("Licencia");
		botonLicencia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("licencia");
				JTextPane licencia = new JTextPane();
				licencia.setText(
						"En la presente licencia de ‘Gestión Hospitalaria’ desarrollada íntegramente por Albert Antoni Jiménez Fuentes,\n se permite el uso a Pablo Serra Bel y superiores,\n siempre que se respete el uso como tal del software a fines personales.\nCualquier modificación, redistribución y/o venta del mismo suponen una anulación de los fines destinados con consecuencias legales.");
				licencia.setEditable(false);
				JPanel jPanel = new JPanel();
				JButton ima = new JButton();
				ima.setIcon(new ImageIcon(getClass().getResource("/media/32/yo.png")));
				jPanel.add(ima);
				jPanel.add(licencia);
				frame.getContentPane().add(jPanel);
				frame.pack();
				frame.setVisible(true);

			}
		});
		JPanel panel4Abajo = new JPanel();
		Container contenedor = ventana.getContentPane();
		panel3.add(botonLicencia);
		panel.add(nuevoPaciente);
		panel.add(consultarPaciente);
		panel.add(nuevoIngreso);
		panel.add(mostrarListaIngresados);
		panel4Abajo.add(mostrarTodos);
		panel4Abajo.add(editarPaciente);
		panel4Abajo.add(guardar);
		panel4Abajo.add(cargar);
		panel4Abajo.add(exportar);
		superpanel.add(panel);
		superpanel.add(panel4Abajo);
		superpanel.add(panel3);
		superpanel.add(panel2);
		contenedor.add(superpanel);
		ventana.setLocationRelativeTo(null);
		// acciones

		// accion añadir
		nuevoPaciente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame vPaciente = new JFrame();
				vPaciente = ventanaPacientes();
				vPaciente.pack();
				vPaciente.setVisible(true);

			}
		});
		// Accion consultar
		consultarPaciente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame vConsulta = ventanaConsulta();
				// vConsulta.pack();
				vConsulta.setVisible(true);

			}
		});

		// Accion editar

		editarPaciente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaBusqueda window = new VentanaBusqueda();
				window.creaVentana("Ventana de Búsqueda");
			}
		});

		// Accion nuevo ingreso

		nuevoIngreso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame vIngreso = ventanaIngreso();
				vIngreso.setVisible(true);

			}
		});

		// Accion lista Ingreso

		mostrarListaIngresados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame vListaIngreso = vListaIngresados();
				vListaIngreso.setVisible(true);

			}
		});

		mostrarTodos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame v = ventanaTodos();
				v.setVisible(true);
			}
		});
		// Accion Guardar
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// file.setAcceptAllFileFilterUsed(false);

				file.setFileFilter(new FileNameExtensionFilter("Archivo Binario", "bin"));
				file.setDialogTitle("Guardar archivo");
				file.setName(file.getName() + ".bin");
				file.setAcceptAllFileFilterUsed(false);

				if (file.showSaveDialog(null) == JFileChooser.CANCEL_OPTION)
					JOptionPane.showMessageDialog(null, "No se ha guardado ningún archivo");
				else {
					File guarda = file.getSelectedFile();
					File g = new File(guarda.getAbsolutePath() + ".bin");

					guardar(g);
					JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
				}
			}
		});
		// Accion cargar
		cargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				file.setDialogTitle("Cargar archivo");

				file.setFileFilter(new FileNameExtensionFilter("Archivo Binario", "bin"));
				file.setMultiSelectionEnabled(false);

				file.setAcceptAllFileFilterUsed(false);
				if (file.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
					JOptionPane.showMessageDialog(null, "No se ha cargado ningún archivo");
				else {
					File carga = file.getSelectedFile();
					cargar(carga);
					JOptionPane.showMessageDialog(null, "Datos cargados con éxito");
				}

			}
		});

		// ACCION BOTON EXPORTAR

		exportar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				file.setDialogTitle("Exportar a Excel");
				// file.setDialogType(JFileChooser.SAVE_DIALOG);
				if (!modeloGestor.getMapaPacientes().isEmpty()) {
					if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						try {
							// String ruta = "/Users/Beruto/Desktop/prueba.xls";
							File archivo = file.getSelectedFile();
							File a = new File(archivo.getAbsolutePath() + ".xls");

							WritableWorkbook libro = Workbook.createWorkbook(a);
							WritableSheet hoja = libro.createSheet("Gestión hospitalaria", 0);

							cabeceras(hoja);
							escribirNombre(hoja);
							escribirApellido(hoja);
							escribirSip(hoja);
							escribirFechaNacimiento(hoja);
							escribirSexo(hoja);
							escribirEstado(hoja);
							escribirPoblacion(hoja);
							escribirProvincia(hoja);
							escribirCP(hoja);
							escribirDoctor(hoja);
							escribirIsIngresado(hoja);
							escribirNumIngresos(hoja);
							// escribirFechaIngreso(hoja);

							libro.write();
							libro.close();

							JOptionPane.showMessageDialog(null, "Fichero exportado correctamente");

						} catch (IOException e1) {

							e1.printStackTrace();
						} catch (WriteException e1) {

							e1.printStackTrace();
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "No hay datos almacenados");
			}
		});

		ventana.pack();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void cabeceras(WritableSheet unaHoja) {
		String[] vectorCabeceras = { "Nombre", "Apellidos", "SIP", "Fecha de Nacimiento", "Sexo", "Estado", "Población",
				"Província", "C.P.", "Doctor", "¿Ingresado ahora?", "Nº ingresos" };
		for (int i = 0; i < vectorCabeceras.length; i++) {
			jxl.write.Label label = new jxl.write.Label(i, 0, vectorCabeceras[i]);
			try {
				unaHoja.addCell(label);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void escribirNombre(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label nombre = new Label(0, f, p.getNombre());

			// ADD CELL
			try {
				unaHoja.addCell(nombre);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirApellido(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label apellido = new Label(1, f, p.getApellidos());

			// ADD CELL
			try {
				unaHoja.addCell(apellido);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirSip(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Number sip = new Number(2, f, p.getSIP());

			// ADD CELL
			try {
				unaHoja.addCell(sip);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirFechaNacimiento(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			DateTime fecha = new DateTime(3, f, p.getFechaNacimiento().getTime());

			// ADD CELL
			try {
				unaHoja.addCell(fecha);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirSexo(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label sexo = new Label(4, f, p.getSexo());

			// ADD CELL
			try {
				unaHoja.addCell(sexo);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirEstado(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label estado = new Label(5, f, p.getEstado());

			// ADD CELL
			try {
				unaHoja.addCell(estado);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirPoblacion(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label poblacion = new Label(6, f, p.getPoblacion());

			// ADD CELL
			try {
				unaHoja.addCell(poblacion);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirProvincia(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label provincia = new Label(7, f, p.getProvincia());

			// ADD CELL
			try {
				unaHoja.addCell(provincia);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirCP(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Number cp = new Number(8, f, p.getCP());

			// ADD CELL
			try {
				unaHoja.addCell(cp);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirDoctor(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Label doc = new Label(9, f, p.getDoctor());

			// ADD CELL
			try {
				unaHoja.addCell(doc);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirIsIngresado(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Boolean ingreso = new Boolean(10, f, p.getIngresos().isEmpty());

			// ADD CELL
			try {
				unaHoja.addCell(ingreso);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	private void escribirNumIngresos(WritableSheet unaHoja) {
		int f = 1;
		for (Paciente p : modeloGestor.getMapaPacientes().values()) {
			// LABELS y FIELDS
			Number numIngresos = new Number(11, f, p.getIngresos().size());

			// ADD CELL
			try {
				unaHoja.addCell(numIngresos);
			} catch (RowsExceededException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			f++;
		}

	}

	@Override
	public JFrame ventanaPacientes() {
		// desplegables();
		JFrame miVentana = new JFrame("Añadir Pacientes");
		JPanel superPanel = new JPanel();
		fechaN.setPreferredSize(new Dimension(150, 50));
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));
		desplegableGenero.addItem("Hombre");
		desplegableGenero.addItem("Mujer");

		desplegableEstado.addItem("Soltero");
		desplegableEstado.addItem("Casado");
		desplegableEstado.addItem("Divorciado");
		desplegableEstado.addItem("Viudo");
		desplegableTipoIngreso.addItem("Paliativo");
		desplegableTipoIngreso.addItem("Oncológico");

		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel.add(new JLabel("Nombre: "));
		panel.add(cajaNombre);
		panel.add(new JLabel("Apellidos: "));
		panel.add(cajaApellidos);
		panel.add(new JLabel("SIP: "));
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
		JButton addPaciente = new JButton("Añadir paciente");
		addPaciente.setIcon(new ImageIcon(getClass().getResource("/media/32/Add.png")));

		// ACCIÓN
		addPaciente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// textoIngreso.setText(getTextoIngreso());
				String nombre = cajaNombre.getText();
				String apellido = cajaApellidos.getText();
				int sip = getSIP();
				Calendar fecha = fechaN.getCalendar();
				String sexo = (String) desplegableGenero.getSelectedItem();
				String estado = (String) desplegableEstado.getSelectedItem();
				String poblacion = cajaPoblacion.getText();
				String provincia = cajaProvincia.getText();
				int cp = getCP();
				String doctor = cajaDoctor.getText();
				Paciente p = new Paciente(nombre, apellido, sip, fecha, sexo, estado, poblacion, provincia, cp, doctor);

				if (nombre.equals("") || apellido.equals("") || poblacion.equals("") || provincia.equals("")
						|| doctor.equals("")) {
					JOptionPane.showMessageDialog(null, "Error campos vacíos");
					miVentana.dispose();

				} else {
					// TODO voy a cambiar esto a ver si no peta
					if (controladorModelo.recuperarPaciente(sip) != null)
						mostrarErrorPacienteRepe();
					else if (p.getSIP() != 0 || p.getCP() != 0) {
						controladorModelo.insertPaciente(p);
						JOptionPane.showMessageDialog(null, "Cliente con SIP " + sip + " agregado correctamente");
						actualizarInformacion();
						// crear paciente y aumentar contador
						contador++;

					}
					// System.out.println(modeloGestor.getMapaPacientes().size());
					cleaner();
					miVentana.dispose();

				}

			}

		});
		panel3.add(addPaciente);
		// panel2.add(textoIngreso);
		superPanel.add(panel);
		superPanel.add(panel2);
		superPanel.add(panel3);
		miVentana.getContentPane().add(superPanel);
		// miVentana.setAlwaysOnTop(true);
		miVentana.pack();
		miVentana.isVisible();

		return miVentana;
	}

	@Override
	public JFrame ventanaIngreso() {
		JFrame miVentana = new JFrame("Añadir ingreso");
		JPanel panel = new JPanel();
		// JPanel panel2 = new JPanel();

		panel.add(new JLabel("SIP: "));
		JTextField cajaSip = new JTextField(8);
		panel.add(cajaSip);
		// TODO cuidado que hay que rellenar los desplegables
		JComboBox<String> desplegable = new JComboBox<String>();
		desplegable.addItem("Paliativo");
		desplegable.addItem("Oncológico");
		panel.add(desplegable);
		JButton botonIngreso = new JButton("Añadir Ingreso");
		botonIngreso.setIcon(new ImageIcon(getClass().getResource("/media/32/ingreso.png")));

		JTextArea areaTexto = new JTextArea(5, 45);

		panel.add(botonIngreso);
		panel.add(areaTexto);
		// panel.add(panel2);

		botonIngreso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Ingreso nuevoIngreso = new Ingreso(areaTexto.getText(), (String) desplegable.getSelectedItem());
				int sip = -1;
				try {
					sip = Integer.parseInt(cajaSip.getText());
					if (modeloGestor.addIngreso(sip, areaTexto.getText(), nuevoIngreso.getTipo())) {
						JOptionPane.showMessageDialog(null, "Ingreso añadido");
						actualizarInformacion();
						miVentana.setVisible(false);
					}

					else
						mostrarErrorAntiPaciente();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "No era un numero");
				}
			}
		});

		miVentana.getContentPane().add(panel);
		miVentana.pack();
		cleaner();
		return miVentana;
	}

	@Override
	public JFrame ventanaConsulta() {
		JFrame miVentana = new JFrame("Consultar paciente");

		// Tostring del cliente y getListaIngresos.toString en 2 bloques
		// distintos
		JPanel panel = new JPanel();
		// JTextPane textoPaciente = new JTextPane();
		// JTextPane textIngreso = new JTextPane();
		panel.add(new JLabel("Inserta el SIP "));
		JTextField cajaBuscarPaciente = new JTextField(8);
		JButton botonBusqueda = new JButton("Buscar", new ImageIcon(getClass().getResource("/media/32/buscar.png")));

		// cajaBuscarPaciente.setPreferredSize(new Dimension(500, 500));
		// ACCION
		botonBusqueda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int valor = 0;
				try {
					valor = Integer.parseInt(cajaBuscarPaciente.getText());
					if (modeloGestor.searchPaciente(valor) == null)
						JOptionPane.showMessageDialog(null, "No existe el paciente solicitado");
					else {
						JFrame miVentana = new JFrame("Añadir Pacientes");
						JPanel superPanel = new JPanel();
						superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));
						JPanel panel = new JPanel();
						JPanel panel2 = new JPanel();
						JPanel panel3 = new JPanel();
						JButton botonMostrarIngreso = new JButton("Mostrar historial de ingresos");
						botonMostrarIngreso.setIcon(new ImageIcon(getClass().getResource("/media/32/historial.png")));
						JTextField cajaNombre = new JTextField(8);
						JTextField cajaApellidos = new JTextField(8);
						JTextField cajaSip = new JTextField(8);
						JCalendarComboBox fechaN = new JCalendarComboBox();
						JComboBox<String> desplegableGenero = new JComboBox<String>();
						JComboBox<String> desplegableEstado = new JComboBox<String>();
						JTextField cajaPoblacion = new JTextField(10);
						JTextField cajaProvincia = new JTextField(10);
						JTextField cajaCP = new JTextField(7);
						JTextField cajaDoctor = new JTextField(8);

						JComboBox<String> desplegableTipoIngreso = new JComboBox<String>();
						desplegableGenero.addItem("Hombre");
						desplegableGenero.addItem("Mujer");

						desplegableEstado.addItem("Soltero");
						desplegableEstado.addItem("Casado");
						desplegableEstado.addItem("Divorciado");
						desplegableEstado.addItem("Viudo");
						desplegableTipoIngreso.addItem("Paliativo");
						desplegableTipoIngreso.addItem("Oncológico");
						panel.add(new JLabel("Nombre: "));
						panel.add(cajaNombre);
						panel.add(new JLabel("Apellidos: "));
						panel.add(cajaApellidos);
						panel.add(new JLabel("SIP: "));
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
						// panel3.add(new JLabel("Tipo de ingreso: "));
						// panel3.add(desplegableTipoIngreso);

						Paciente pa = modeloGestor.searchPaciente(valor);

						// ACCION
						botonMostrarIngreso.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								JFrame miFrame = new JFrame("Mostrando historial de " + cajaNombre.getText());
								// JPanel pan = new JPanel();

								JTextArea area = new JTextArea(30, 100);
								JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								area.setLineWrap(true);
								if (pa.getIngresos().isEmpty())
									area.setText("No hay ingresos");
								else
									area.setText(pa.getIngresos().toString());
								area.setEditable(false);

								miFrame.add(scroll);
								miFrame.pack();
								miFrame.setVisible(true);
							}
						});

						superPanel.add(panel);
						superPanel.add(panel2);
						superPanel.add(panel3);
						superPanel.add(botonMostrarIngreso);

						cajaNombre.setText(pa.getNombre());
						cajaNombre.setEnabled(false);
						cajaNombre.setOpaque(true);
						cajaApellidos.setEnabled(false);
						cajaApellidos.setText(pa.getApellidos());
						cajaApellidos.setOpaque(true);

						cajaSip.setText(Integer.toString(pa.getCP()));
						cajaSip.setEnabled(false);
						cajaSip.setOpaque(true);
						fechaN.setCalendar(pa.getFechaNacimiento());
						// fechaN.setEnabled(false);
						fechaN.setPreferredSize(new Dimension(60, 30));
						fechaN.setOpaque(true);
						desplegableGenero.setSelectedItem(pa.getSexo());
						desplegableGenero.setEnabled(false);
						desplegableGenero.setOpaque(true);
						desplegableEstado.setSelectedItem(pa.getEstado());
						desplegableEstado.setEnabled(false);
						desplegableEstado.setOpaque(true);
						cajaPoblacion.setText(pa.getPoblacion());
						cajaPoblacion.setEnabled(false);
						cajaPoblacion.setOpaque(true);
						cajaProvincia.setText(pa.getProvincia());
						cajaProvincia.setEnabled(false);
						cajaProvincia.setOpaque(true);
						cajaCP.setText(Integer.toString(pa.getCP()));
						cajaCP.setEnabled(false);
						cajaCP.setOpaque(true);
						cajaDoctor.setText(pa.getDoctor());
						cajaDoctor.setEnabled(false);
						cajaDoctor.setOpaque(true);
						desplegableTipoIngreso.setSelectedItem(pa.getTipoIngreso());
						desplegableTipoIngreso.setEnabled(false);
						desplegableTipoIngreso.setOpaque(true);

						miVentana.getContentPane().add(superPanel);
						miVentana.pack();
						miVentana.setVisible(true);

					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "No es un número");
				}

			}
		});

		panel.add(cajaBuscarPaciente);
		panel.add(botonBusqueda);

		// panel.add(mostrarListaIngresados);
		// panel.add(textoPaciente);
		// panel.add(textIngreso);
		// textIngreso.setEditable(false);
		// textoPaciente.setEditable(false);
		miVentana.getContentPane().add(panel);
		miVentana.pack();
		miVentana.isVisible();
		return miVentana;

	}

	@Override
	public String getNombre() {

		return cajaNombre.getText();
	}

	@Override
	public String getApellido() {

		return cajaApellidos.getText();
	}

	@Override
	public int getSIP() throws NumberFormatException {
		int valor = 0;
		try {
			valor = Integer.parseInt(cajaSip.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error no era un número");
			ventanaPacientes();
		}

		return valor;
	}

	@Override
	public Calendar getFechaNacimiento() {

		return fechaN.getCalendar();
	}

	@Override
	public String getSexo() {

		return (String) desplegableGenero.getSelectedItem();
	}

	@Override
	public String getEstado() {

		return (String) desplegableEstado.getSelectedItem();
	}

	@Override
	public String getPoblacion() {

		return cajaPoblacion.getText();
	}

	@Override
	public String getProvincia() {

		return cajaProvincia.getText();
	}

	@Override
	public int getCP() throws NumberFormatException {
		int valor = 0;
		try {
			valor = Integer.parseInt(cajaCP.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error no era un número");
			ventanaPacientes();
		}
		return valor;
	}

	@Override
	public String getDoctor() {

		return cajaDoctor.getText();
	}

	@Override
	public void mostrarErrorAntiPaciente() {
		JOptionPane.showMessageDialog(null, "No existe el paciente");
	}

	@Override
	public void mostrarErrorPacienteRepe() {
		JOptionPane.showMessageDialog(null, "Paciente repetido");
	}

	public JFrame vListaIngresados() {
		JFrame ventana = new JFrame("Lista de Ingresados");
		JSplitPane panelDividido = new JSplitPane();
		JList<Paciente> list = new JList<>();
		list.setVisibleRowCount(10);
		JTextArea areaTexto = new JTextArea(25, 25);
		areaTexto.add(new JScrollPane());
		areaTexto.setEditable(false);

		DefaultListModel<Paciente> listModel = new DefaultListModel<>();
		JScrollPane scrollTexto = new JScrollPane(areaTexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollLista = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// BOTON eliminar o dar de alta
		JButton darAlta = new JButton("Eliminar ingresados", new ImageIcon(getClass().getResource("/media/32/ok.png")));

		if (!modeloGestor.getConjuntoIngresados().isEmpty())
			for (Paciente elemento : modeloGestor.getConjuntoIngresados())
				listModel.addElement(elemento);
		else {
			areaTexto.setText("No hay pacientes ingresados :)");
		}
		list.setModel(listModel);
		list.add(new JScrollPane());
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValuesList().size() > 1)
					areaTexto.setText("Selecciona un unico paciente para mostrar su información");
				else if (list.getSelectedValuesList().size() == 1)
					areaTexto.setText(list.getSelectedValue().toString2());

			}
		});
		// ACCION dar alta
		darAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"¿Desea dar de alta los pacientes seleccionados?") == JOptionPane.YES_OPTION) {

					if (list.getSelectedValuesList().size() > 1) {
						modeloGestor.getConjuntoIngresados().removeAll(list.getSelectedValuesList());
						areaTexto.setText("");
						DefaultListModel<Paciente> modeloNuevo = new DefaultListModel<>();
						if (!modeloGestor.getConjuntoIngresados().isEmpty()) {
							for (Paciente elemento : modeloGestor.getConjuntoIngresados())
								modeloNuevo.addElement(elemento);
							list.setModel(modeloNuevo);
						} else
							listModel.removeAllElements();
						actualizarInformacion();
					} else if (list.getSelectedValuesList().size() == 1) {
						modeloGestor.getConjuntoIngresados().remove(list.getSelectedValue());
						areaTexto.setText("");
						listModel.removeElement(list.getSelectedValue());
					} else {
						JOptionPane.showMessageDialog(null, "Error \nLa lista está vacía");
					}
					actualizarInformacion();
				}
			}
		});

		panelDividido.setLeftComponent(scrollLista);
		panelDividido.setRightComponent(scrollTexto);
		JPanel miPanel = new JPanel();
		miPanel.setLayout(new BoxLayout(miPanel, BoxLayout.Y_AXIS));
		darAlta.setAlignmentY(JButton.CENTER_ALIGNMENT);
		miPanel.add(panelDividido);
		miPanel.add(darAlta);
		ventana.getContentPane().add(miPanel);
		// ventana.setLocationRelativeTo(null);
		ventana.pack();
		return ventana;
	}

	public void actualizarInformacion() {

		informacion.setText("En la base de datos hay " + modeloGestor.getMapaPacientes().size() + " paciente/s y "
				+ modeloGestor.getConjuntoIngresados().size() + " ingresado/s.");

	}

	//
	// @Override
	// public List<Ingreso> getListaIngreso() {

	// return null;
	// }

	// @Override
	// public void setControlador(Controlador c) {
	// this.controlador = (ControladorImplementacion) c;
	// }

	@Override
	public void setModelo(GestionPaciente m) {
		this.modeloGestor = m;
	}

	// public void desplegables() {
	// desplegableGenero.addItem("Hombre");
	// desplegableGenero.addItem("Mujer");
	//
	// desplegableEstado.addItem("Soltero");
	// desplegableEstado.addItem("Casado");
	// desplegableEstado.addItem("Divorciado");
	// desplegableEstado.addItem("Viudo");
	//
	// }

	@Override
	public String getTextoIngreso() {
		return textoIngreso.getText();
	}

	public void guardar(File archivo) {

		ObjectOutputStream objeto = null;
		try {
			try {

				FileOutputStream file = new FileOutputStream(archivo);

				objeto = new ObjectOutputStream(file);
				objeto.writeObject(modeloGestor);

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

	public void cargar(File carga) {

		ObjectInputStream objeto = null;
		try {
			try {
				FileInputStream file = new FileInputStream(carga);
				objeto = new ObjectInputStream(file);
				modeloGestor = (GestionPaciente) objeto.readObject();
				actualizarInformacion();
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

	private JFrame ventanaTodos() {

		JFrame ventana = new JFrame("Mostrar todos los pacientes");
		JPanel miPanel = new JPanel();
		JPanel superPanel = new JPanel();
		JTextField apellido = new JTextField(8);
		JButton buscar = new JButton("Buscar", new ImageIcon(getClass().getResource("/media/32/buscar.png")));
		JSplitPane panelDividido = new JSplitPane();
		JList<Paciente> list = new JList<>();
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTextArea areaTexto = new JTextArea(30, 30);
		areaTexto.add(new JScrollPane());
		areaTexto.setEditable(false);
		// MI Panel

		DefaultListModel<Paciente> listModel = new DefaultListModel<>();
		JScrollPane scrollTexto = new JScrollPane(areaTexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollLista = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() == null)
					areaTexto.setText("Nada seleccionado");
				else {
					areaTexto.setText(list.getSelectedValue().toString2());

				}

			}
		});
		// BOTON buscar
		// TODO hacer listmodel public o variable global
		if (!modeloGestor.getMapaPacientes().isEmpty())
			for (Paciente elemento : modeloGestor.getMapaPacientes().values())
				listModel.addElement(elemento);
		else {
			areaTexto.setText("No hay pacientes en la base de datos :)");
		}

		// ACCIONES
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (apellido.getText() != "") {
					DefaultListModel<Paciente> nuevoModelo = new DefaultListModel<>();
					if (!buscar(apellido.getText()).isEmpty()) {
						for (Paciente p : buscar(apellido.getText()))
							nuevoModelo.addElement(p);
						list.setModel(nuevoModelo);
					} else {
						JOptionPane.showMessageDialog(null, "No existe el apellido " + apellido.getText());
						list.setModel(listModel);
					}

				}

			}
		});
		apellido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list.setModel(listModel);

			}
		});
		list.setModel(listModel);
		list.add(new JScrollPane());
		miPanel.add(new JLabel("Apellidos: "));
		miPanel.add(apellido);
		miPanel.add(buscar);
		// miPanel.setLayout(new BoxLayout(miPanel, BoxLayout.PAGE_AXIS));
		// superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));
		panelDividido.setLeftComponent(scrollLista);
		panelDividido.setRightComponent(scrollTexto);

		superPanel.add(panelDividido);
		superPanel.add(miPanel);
		ventana.getContentPane().add(superPanel);
		// ventana.setSize(550, 650);
		// ventana.setLocationRelativeTo(null);
		ventana.pack();
		return ventana;

	}

	public void cleaner() {
		cajaNombre.setText("");
		cajaApellidos.setText("");
		cajaSip.setText("");
		cajaPoblacion.setText("");
		cajaProvincia.setText("");
		cajaCP.setText("");
		cajaDoctor.setText("");
		textoIngreso.setText("");

	}

	private Set<Paciente> buscar(String pattern) {
		Map<Integer, Paciente> m = modeloGestor.getMapaPacientes();
		Set<Paciente> lista = new HashSet<Paciente>();
		if (!m.isEmpty()) {
			for (Paciente p : m.values())
				if (p.getApellidos().toLowerCase().equals(pattern.toLowerCase())
						|| p.getApellidos().toLowerCase().startsWith(pattern))
					lista.add(p);

		}
		return lista;
	}

	@Override
	public void setControlador(ControladorImplementacionModelo c) {
		this.controladorModelo = c;

	}

}
