package prueba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PruebaPersona {

	public static void main(String[] args) {
		String nombre = "Nombre";
		String apellidos = "Apellidos";
		int edad = 16;
		Integer dninumber = 12345789;
		int fechainicio = 2012;
		String organismoPublico = "junta";
		 Integer numeroTrienio = 3;
		List<Persona> personas = new ArrayList<Persona>();
		for (int i = 1; i <= 10000; i++) {
			TipoContrato tipoContrato;
			switch (i % 3) {
			case 0:
				tipoContrato = TipoContrato.INDEFINIDO;
				break;
			case 1:
				tipoContrato = TipoContrato.OBRA_Y_SERVICIO;
				break;
			default:
				tipoContrato = TipoContrato.TEMPORAL;
				break;
			}
			EmpleadoPublico persona = new EmpleadoPublico(nombre + i, apellidos + i, (edad+i)%100 , (dninumber + i) + "N",
					fechainicio - i, tipoContrato, organismoPublico, numeroTrienio);
			personas.add(persona);
		}
/*		for (Persona p : personas) {
			try {
				System.out.print("DNI: " + p.getDni() + "  Nombre: " + p.getNombre() + "  Apellido: " + p.getApellidos()
						+ "  Tipo contrato: " + p.getTipoContrato() + "\n");
			} catch (DNInoValidoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.print("----------------------------------------------------------------------\n");
		Collections.sort(personas);
		for (Persona p : personas) {
			try {
				System.out.print("DNI: " + p.getDni() + "  Nombre: " + p.getNombre() + "  Apellido: " + p.getApellidos()
						+ "  Tipo contrato: " + p.getTipoContrato() + "\n");
			} catch (DNInoValidoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		*/
//		Persona p = new EmpleadoPublico("Pedro", "Díaz", 68, "123456789N", 1980, TipoContrato.INDEFINIDO,
//				"Junta de Andalucía", 3131);
		Cotizable c = new EmpleadoPublico("Pedro", "Díaz", 68, "123456789N", 1980, TipoContrato.INDEFINIDO,
				"Junta de Andalucía", 3131);
		EmpleadoPublico e = new EmpleadoPublico("Pedro", "Díaz", 68, "123456789N", 1980, TipoContrato.INDEFINIDO,
				"Junta de Andalucía", 3131);
		GestorPersona gestor = GestorPersona.getInstance();
		
		
		long inicio = System.currentTimeMillis();
		personas
		.parallelStream()
		.sorted()
		.filter(p -> p.getEdad() > 40)
		.forEach(p -> System.out.print(p +" Edad "+ p.getEdad()+"\n"));
		long fin = System.currentTimeMillis();	
		double tiempo = (double) ((fin - inicio));
		
		long inicio2 = System.currentTimeMillis();
		personas
		.stream()
		.sorted()
		.filter(p -> p.getEdad() > 40)
		.forEach(p -> System.out.print(p +" Edad "+ p.getEdad()+"\n"));
		
		long fin2 = System.currentTimeMillis();
		double tiempo2 = (double) ((fin2 - inicio2));
		
		System.out.print("\n");
	    System.out.println(tiempo  +" milisegundos en parallelstream");
	    System.out.println(tiempo2  +" milisegundos en stream");
	    
	    String cadena = "holamundo";
	    String m = "mundi";
	    Boolean cadenapartida = cadena.contains("m");
	    System.out.println(cadenapartida);
	    
	    StringBuilder sb = new StringBuilder("animals");
	    sb.insert(7,"-");
	    sb.insert(0,"-");
	    System.out.println(sb);
	    
	    
	}

}
