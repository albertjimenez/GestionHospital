package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.estructurasED.GestionPaciente;
import modelo.paciente.Paciente;

public class PruebaGson {
	Gson exportador = new GsonBuilder().setPrettyPrinting().create();

	@Test
	public void test() {
		long t1, t2;
		t1 = System.nanoTime();
		System.out.println("Comienza la creacion y adicion de 100.000 pacientes");
		GestionPaciente gestor = new GestionPaciente();
		for (int i = 0; i < 100000; i++)
			gestor.addPaciente(new Paciente("Santi", "Bernabeuses", i, Calendar.getInstance(), "H", "estado", "pob",
					"p", 12500, "F"));
		String cadena = exportador.toJson(gestor);
		System.out.println("===========");
		try {
			FileWriter writer = new FileWriter("/Users/Beruto/Desktop/exportado.json");
			writer.write(cadena);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedReader buf = new BufferedReader(new FileReader("/Users/Beruto/Desktop/exportado.json"));
			GestionPaciente returnedPatient = exportador.fromJson(buf, GestionPaciente.class);
			t2 = System.nanoTime();
			double tPar = ((double) (t2 - t1)) / 1.0e9;
			assertEquals(true, returnedPatient.numeroPacientes() == 100000);
			System.out.println("Y el tiempo es: " + tPar);

		} catch (IOException e) {

		}
	}

}
