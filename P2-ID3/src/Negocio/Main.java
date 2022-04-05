package Negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import algorithm.Ejemplo;
import algorithm.ID3;
import algorithm.LecturaDatos;

public class Main {
	private static ArrayList<Ejemplo> ejemplos;
	private static ArrayList<String> atributos;
	private static HashMap<String, ArrayList<String>> valoresAtributos;
	private static ArrayList<Ejemplo> ejemplosSolucionar;
	private static String archivoCasos = "";
	private static String archivoEjemplos = "";
	private static String archivoAtributos = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre del archivo con los Atributos "
				+ "(Sin poner el .txt , debe estar dentro de la carpeta ficheros):");
		Scanner sc = new Scanner(System.in);
		archivoAtributos = sc.nextLine();
		String fileAtributos = "ficheros/" + archivoAtributos + ".txt";
		System.out.println("Introduce el nombre del archivo con los Ejemplos "
				+ "(Sin poner el .txt , debe estar dentro de la carpeta ficheros):");
		archivoEjemplos = sc.nextLine();
		String fileEjemplos = "ficheros/" + archivoEjemplos + ".txt";
		atributos = LecturaDatos.readAtributos(fileAtributos);
		ejemplos = LecturaDatos.readEjemplos(fileEjemplos, atributos);
		valoresAtributos = new HashMap<String, ArrayList<String>>();

		// Para cada atributo guarda los posibles valores que puede tener
		for (Ejemplo e : ejemplos) {
			for (Entry<String, String> entry : e.getEjemplo().entrySet()) {
				if (valoresAtributos.containsKey(entry.getKey())) {
					if (!valoresAtributos.get(entry.getKey()).contains(entry.getValue())) {
						valoresAtributos.get(entry.getKey()).add(entry.getValue());
					}
				} else {
					valoresAtributos.put(entry.getKey(), new ArrayList<String>());
					valoresAtributos.get(entry.getKey()).add(entry.getValue());
				}
			}
		}

		ID3 id3 = new ID3("+", "-", valoresAtributos, "Clase");
		System.out.println("-------------ID3--------------");
		id3.hacerID3(atributos, ejemplos, 0, id3.getArbol());
		System.out.println("-------ARBOL-------");
		id3.pintarArbol();
		System.out.print("FIN");
		System.out.println(" || ");
		System.out.println("-------------------");
		id3.conseguirReglas(id3.getArbol(), id3.getArbol().getRamaPadre(), 0, "");
		System.out.println("-------REGLAS------");
		id3.pintarReglas();
		System.out.println("-------------------");
		System.out.println("-------CASOS-------");
		System.out.println("Introduce el nombre del archivo con los casos "
				+ "(Sin poner el .txt , debe estar dentro de la carpeta ficheros):");
		archivoCasos = sc.nextLine();
		sc.close();
		if (!archivoCasos.equals("no")) {
			String fileCasos = "ficheros/" + archivoCasos + ".txt";
			ejemplosSolucionar = LecturaDatos.readEjemplos(fileCasos, atributos);
			id3.evaluarCasos(ejemplosSolucionar, id3.getArbol());
		} else {
			System.out.println("Se ha elegido no evaluar casos.");
		}

		System.out.println("-------------------");
	}

}
