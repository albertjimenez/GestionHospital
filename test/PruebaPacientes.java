package test;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;

import modelo.Modelo;
import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public class PruebaPacientes {
	Modelo gestor = new GestionPaciente();

	@Test
	public void test() {

		for (int i = 0; i < 2000; i++) {
			int key = i;
			Paciente value = new Paciente("s", "a", key, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500,
					"d");
			gestor.getMapaPacientes().putIfAbsent(key, value);

		}
		// System.out.println(gestor.getMapaPacientes().values().toString());
		// System.out.println(gestor.getMapaPacientes().toString());
		assertEquals(2000, gestor.getMapaPacientes().size(), 0);

		// fail("Not yet implemented");
	}

	@Test
	public void testEdicion() {
		Paciente p1 = new Paciente("sa", "a", 5000, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500, "d");
		gestor.addPaciente(p1);
		// System.out.println(gestor.getMapaPacientes().toString());

		assertEquals("sa", gestor.getMapaPacientes().get(5000).getNombre());
		Paciente p2 = new Paciente("sab", "a", 5000, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500, "d");
		assertEquals(gestor.editPaciente(p2), true);

		// System.out.println(gestor.getMapaPacientes().toString());
		assertEquals("sab", gestor.getMapaPacientes().get(5000).getNombre());

		Paciente p3 = new Paciente("sab", "a", 5200, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500, "d");
		assertEquals(gestor.editPaciente(p3), false);

	}

	@Test
	public void testBorrar() {
		Paciente p1 = new Paciente("sa", "a", 5000, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500, "d");
		Paciente p2 = new Paciente("sa", "a", 5100, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500, "d");
		gestor.addPaciente(p1);
		// gestor.addPaciente(p2);
		assertEquals(gestor.removePaciente(p1), true);
		assertEquals(gestor.removePaciente(p1), false);
		assertEquals(gestor.removePaciente(p2), false);

	}

	@Test
	public void testBuscar() {
		Paciente p1 = new Paciente("sa", "a", 5000, new GregorianCalendar(1, 1, 1), "h", "h", "h", "h", 12500, "d");

		assertEquals(gestor.addPaciente(p1), true);

		assertEquals(gestor.searchPaciente(p1.getSIP()), p1);
	}

}
