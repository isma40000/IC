package Negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import algorithm.Ejemplo;
import algorithm.Pair;

public class Main {
	private static ArrayList<Ejemplo> ejemplos;
	private ArrayList<String> atributos;
	private static HashMap<String, ArrayList<String>> valoresAtributos;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		
	}

}
