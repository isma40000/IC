package algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class Ejemplo {

	HashMap<String, String> atributos;

	public Ejemplo(ArrayList<String> atributo, ArrayList<String> valor) {
		this.atributos = new HashMap<String, String>();
		for (int i = 0; i < valor.size(); i++) {
			this.atributos.put(atributo.get(i), valor.get(i));
		}
	}

	public HashMap<String, String> getEjemplo() {
		return atributos;
	}

	public void setEjemplo(HashMap<String, String> ejemplo) {
		this.atributos = ejemplo;
	}

	public void addAtributo(String atributo, String valor) {
		atributos.put(atributo, valor);
	}

	public String getValorAtributo(String atributo) {
		return atributos.get(atributo);
	}

	public void eliminarAtributo(String atributo) {
		this.atributos.remove(atributo);
	}

}
