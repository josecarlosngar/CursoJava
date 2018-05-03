package prueba;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.Generated;

public class GestorPersona {
	private static GestorPersona gestorPersona;
	private Collection<Persona> personas;

	// atributo plantilla, metodo media edad plantilla
	private GestorPersona() {
		this.personas = new HashSet<Persona>();
	}

	public static GestorPersona getInstance() {
		if (gestorPersona == null) {
			gestorPersona = new GestorPersona();
		}
		return gestorPersona;
	}

	public Collection<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Collection<Persona> personas) {
		this.personas = personas;
	}

	public double getMediaEdad() {
		double res = 0.0;
		for (Persona p : this.personas) {
			res += p.getEdad();
		}
		res = res / this.personas.size();
		return res;
	}

	public void addEmpleado(Persona persona) throws DNIDuplicadoException {

		try {

			for (Persona p : this.personas) {

				if (persona.getDni().equals(p.getDni())) {
					throw new DNIDuplicadoException();
				}
			}
		} catch (DNInoValidoException e) {
			System.out.print(e.getMessage());
		}

		this.personas.add(persona);
	}

	// crear imprime()

	public static void imprime(Predicate<Persona> checker) {
		for (Persona p : getInstance().getPersonas()) {
			if (checker.test(p)) {
				System.out.print(p + " ");
			}
		}
	}
	// metodo procesa persona que acepte una lista y admita un predicate y un
	// consumer... coger >65 y jubilar

	public static void procesaPersona(Predicate<Persona> tester, Consumer<Persona> block) {
		for (Persona p : getInstance().getPersonas()) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}
	
	
	
}