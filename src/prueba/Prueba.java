package prueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prueba {

	public static void main(String[] args) {
		EmpleadoPublico persona1 = new EmpleadoPublico("Pedro", "díaz", 68, "12345679F", 1980, TipoContrato.INDEFINIDO,
				"Junta de Andalucía", 3131);
		EmpleadoPublico persona2 = new EmpleadoPublico("Jose", "rodriguez", 35, "72233444D", 1990,
				TipoContrato.INDEFINIDO, "Junta de Andalucía", 3132);
		EmpleadoPublico persona3 = new EmpleadoPublico("Paco", "perez", 17, "55226679E", 1980, TipoContrato.TEMPORAL,
				"Junta de Andalucía", 3133);
		EmpleadoPublico persona4 = new EmpleadoPublico("Juan", "fernandez", 24, "32414231D", 1970,
				TipoContrato.TEMPORAL, "Junta de Andalucía", 3134);
		EmpleadoPublico empleado1 = new EmpleadoPublico("Pedro", "Díaz", 68, "12345689N", 1980, TipoContrato.INDEFINIDO,
				"Junta de Andalucía", 3131);
		EmpleadoPublico empleado2 = new EmpleadoPublico("Jose", "Rodriguez", 64, "72233444D", 1990,
				TipoContrato.INDEFINIDO, "Seguridad social", 1233);
		EmpleadoPublico empleado3 = new EmpleadoPublico("Paco", "Perez", 54, "55226689E", 1980, TipoContrato.TEMPORAL,
				"Seguridad social", 4211);
		/*
		 * System.out.print(Calendar.getInstance().get(Calendar.YEAR));
		 * System.out.print("\nPersona1 con persona2: " + persona1.compareTo(persona2) +
		 * "\n"); System.out.print("Persona1 con persona3: " +
		 * persona1.compareTo(persona3) + "\n");
		 * System.out.print("¿Persona1 puede jubilarse?: " +
		 * persona1.getPuedeJubilarse() + "\n");
		 * System.out.print("¿Persona2 puede jubilarse?: " +
		 * persona2.getPuedeJubilarse() + "\n");
		 * System.out.print("¿Persona3 puede jubilarse?: " +
		 * persona3.getPuedeJubilarse() + "\n");
		 */
		TreeSet<Persona> personas = Persona.guardarPersonas(persona1, persona2, persona3);
		for (Persona p : personas) {
			try {
				System.out.print("DNI: " + p.getDni() + "  Nombre: " + p.getNombre() + "  Apellido: " + p.getApellidos()
						+ "  Tipo contrato: " + p.getTipoContrato() + "\n");
			} catch (DNInoValidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// System.out.print(Persona.getMayoriaDeEdad() + "\n");
		// System.out.print(TipoContrato.TEMPORAL.getYears(20) + "\n");
		GestorPersona g = GestorPersona.getInstance();
		g.setPersonas(personas);
		// System.out.print(GestorPersona.getInstance().getMediaEdad() + "\n");
		TreeSet<EmpleadoPublico> empleados = new TreeSet<EmpleadoPublico>();
		empleados.add(empleado1);
		empleados.add(empleado2);
		empleados.add(empleado3);
		for (EmpleadoPublico p : empleados) {
			try {
				System.out.print("DNI: " + p.getDni() + "  Nombre: " + p.getNombre() + "  Apellido: " + p.getApellidos()
						+ "  Trienio: " + p.getNumeroTrienio() + "\n");
			} catch (DNInoValidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.print("\n\n");
		// System.out.print(persona1.sonVerdaderos(true, true, false, false, true, true)
		// + "\n");
		// System.out.print(persona1.sonVerdaderos(true, true, true, true, true, true) +
		// "\n");

		// System.out.print(persona1.toString()+"\n");

		try {
			// System.out.print(g.getPersonas()+"\n");
			g.addEmpleado(persona4);
		} catch (DNIDuplicadoException e) {
			e.printStackTrace();
		}
		// System.out.print(g.getPersonas()+"\n");

		GestorPersona.imprime(f -> f.getEdad() > 18);
		System.out.print("\n");

		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

		/*
		 * GestorPersona.getInstance().getPersonas().stream() .filter(p->p.getEdad()>65)
		 * .map(p->p.getNombre()=p.getNombre() +"Jubilado")
		 * .forEach(System.out::println);
		 */

		double edadMedia = g.getPersonas().stream().mapToInt(p -> p.getEdad()).average().getAsDouble();
		List<Persona> personaMayorEdad = g.getPersonas().stream().filter(p -> p.getEdad() > edadMedia)
				.collect(Collectors.toList());
		List<Persona> personaMenorEdad = g.getPersonas().stream().filter(p -> p.getEdad() < edadMedia)
				.collect(Collectors.toList());
		System.out.print(personaMayorEdad + "\n");
		System.out.print(personaMenorEdad + "\n");

		g.getPersonas().stream().mapToInt(p -> {
			System.out.print("map: " + p + "\n");
			return p.getEdad();
		}).filter(p -> {
			System.out.print("filter: " + p + "\n");
			return p >= 18;
		}).forEach(p -> System.out.print("for each: " + p + "\n"));

		Supplier<Stream<Persona>> streamSupplier = () -> g.getPersonas().stream();
		System.out.print("\n");

		Map<String, List<Persona>> personasApellido = g.getPersonas().stream()
				.collect(Collectors.groupingBy(p -> p.getApellidos().substring(0, 1)));
		personasApellido.forEach((f, p) -> System.out.print(p));

		System.out.print("\n\n");

		
		Collector<Persona, StringJoiner, String> personaDNICollector =
				Collector.of(() -> new StringJoiner(","),
				(j, p) -> {
					try {
						j.add(p.getDni());
					} catch (DNInoValidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				},
				(j1, j2) -> j1.merge(j2),
				StringJoiner::toString);

		String dnis = g.getPersonas()
				.stream()
				.collect(personaDNICollector);

		System.out.print(dnis);
		System.out.print("\n\n");
		
		g.getPersonas()
		.stream()
		.filter(p -> p.getPuedeJubilarse() == false)
		.reduce((p1,p2) -> p1.getEdad() > p2.getEdad() ? p1 : p2)
		.ifPresent(System.out::println);
		

	}

}
