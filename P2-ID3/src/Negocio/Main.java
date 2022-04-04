package Negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import algorithm.Ejemplo;
import algorithm.ID3;
import algorithm.LecturaDatos;
import algorithm.Nodo;

public class Main {
	private static ArrayList<Ejemplo> ejemplos;
	private static ArrayList<String> atributos;
	private static HashMap<String, ArrayList<String>> valoresAtributos;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileEjemplos = "ficheros/Juego.txt";
		String fileAtributos = "ficheros/AtributosJuego.txt";
		atributos = LecturaDatos.readAtributos(fileAtributos);
		ejemplos  = LecturaDatos.readEjemplos(fileEjemplos, atributos);
		valoresAtributos = new HashMap<String,ArrayList<String>>();
		
		
		//Para cada atributo guarda los posibles valores que puede tener
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
		
		ID3 id3 = new ID3("si", "no", valoresAtributos);
		id3.hacerID3(atributos, ejemplos , 0, new Nodo());
		System.out.println("FIN");
		
	}

}
