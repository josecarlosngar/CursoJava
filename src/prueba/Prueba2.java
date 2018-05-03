package prueba;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Prueba2 {

	private static String HOME = "timelog.log";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// ap1
		Path p = Paths.get(HOME);
		System.out.println(Files.exists(p));
		// ap2
		System.out.println(Files.isReadable(p));
		System.out.println(Files.isWritable(p));
		System.out.println(Files.isExecutable(p)+"\n");
		// ap3 y ap4
		
		try {
			System.out.println(Files.readAllLines(p).size());
			System.out.println(Files.lines(p).filter(l -> (l.contains("(4") || l.contains("(5"))).count()+"\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Día con más visitas
		Map<Object, List<String>> lista = Files.lines(p)
				.collect(Collectors.groupingBy(l -> l.toString().substring(0, 10)));

		Object dia = lista.keySet().stream()
				.max((d1, d2) -> Integer.compare(lista.get(d1).size(), lista.get(d2).size())).get();

		System.out.println(dia);
		System.out.println(lista.get(dia).size()+"\n");
	
		// Página más visitada
		Map<Object, List<String>> listaPagina = Files.lines(p).collect(Collectors.groupingBy(l -> l.split("\"")[1]));
		Object paginaMasVisitada = listaPagina.keySet().stream()
				.max((d1, d2) -> Integer.compare(listaPagina.get(d1).size(), listaPagina.get(d2).size())).get();

		System.out.println(paginaMasVisitada);
		System.out.println(listaPagina.get(paginaMasVisitada).size());

	}
}
