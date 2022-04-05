package algorithm;

import java.util.HashMap;

public class Nodo {

	private String raiz = "";
	private boolean hoja;
	private HashMap<String, Nodo> ramas = new HashMap<String, Nodo>();
	private Nodo padre;
	private String ramaPadre = "";

	public Nodo(String raiz, boolean hoja, HashMap<String, Nodo> ramas, Nodo padre, String ramaPadre) {
		this.raiz = raiz;
		this.hoja = hoja;
		this.ramas = ramas;
		this.padre = padre;
		this.ramaPadre = ramaPadre;
	}

	public Nodo() {
		this.padre = null;
		this.ramaPadre = "";
	}

	public Nodo(Nodo padre, String ramaPadre) {
		this.padre = padre;
		this.ramaPadre = ramaPadre;
	}

	public String getRaiz() {
		return raiz;
	}

	public void setRaiz(String raiz) {
		this.raiz = raiz;
	}

	public boolean isHoja() {
		return hoja;
	}

	public void setHoja(boolean hoja) {
		this.hoja = hoja;
	}

	public HashMap<String, Nodo> getRamas() {
		return ramas;
	}

	public void setRamas(HashMap<String, Nodo> ramas) {
		this.ramas = ramas;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public String getRamaPadre() {
		return ramaPadre;
	}

	public void setRamaPadre(String ramaPadre) {
		this.ramaPadre = ramaPadre;
	}

}
