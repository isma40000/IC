package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Datos {
	private ArrayList<Ejemplo> ejemplos;
	private ArrayList<String> atributos;
	// HashMap<String, ArrayList<String>> valoresAtributos;
	private HashMap<String, ArrayList<Pair>> valoresAtributos;// atributos y sus valores hasta el momento, incluyendo
																// los APNRs
	private String clase;
	private String clasePos;
	private HashMap<String, ArrayList<String>> valoresRegistrados;// valores de atributos que ya han sido registrados,
																	// para no calcular de nuevo APNRs

//	public Datos(ArrayList<Ejemplo> ejemplos, ArrayList<String> atributos, String clasePos) {
//		this.ejemplos = ejemplos;
//		this.atributos = atributos;
//		this.clase = atributos.get(atributos.size() - 1);
//		this.atributos.remove(this.clase);
//		this.clasePos = clasePos;
//		for(Ejemplo e : ejemplos) {
//			for(Entry<String, String> entry : e.getEjemplo().entrySet()) {
//				if(valoresAtributos.containsKey(entry.getKey())) {
//					valoresAtributos.get(entry.getKey()).add(entry.getValue());
//				}else {
//					valoresAtributos.put(entry.getKey(), new ArrayList<String>());
//					valoresAtributos.get(entry.getKey()).add(entry.getValue());
//				}
//			}
//		}
//	}

	public Datos(ArrayList<Ejemplo> ejemplos, ArrayList<String> atributos, String clasePos) {
		this.ejemplos = ejemplos;
		this.atributos = atributos;
		this.clase = atributos.get(atributos.size() - 1);
		this.atributos.remove(this.clase);
		this.clasePos = clasePos;
		for (Ejemplo e : ejemplos) {
			for (Entry<String, String> entry : e.getEjemplo().entrySet()) {
				if (valoresAtributos.containsKey(entry.getKey())) {
					if (!checkRegistrado(entry.getKey(), entry.getValue())) {
						valoresRegistrados.get(entry.getKey()).add(entry.getValue());
						valoresAtributos.get(entry.getKey())
								.add(new Pair(entry.getValue(), getAPNRs(entry.getKey(), entry.getValue())));
					}
				} else {
					valoresAtributos.put(entry.getKey(), new ArrayList<Pair>());// se añade el atributo al mapa de
																				// atributos-->lista valores
					valoresRegistrados.put(entry.getKey(), new ArrayList<String>()); // se añade el atributo al mapa de
																						// atributos-->lista valores
																						// registrados
					valoresRegistrados.get(entry.getKey()).add(entry.getValue()); // se añade el valor a los valores
																					// registrados del atributo
					valoresAtributos.get(entry.getKey())
							.add(new Pair(entry.getValue(), getAPNRs(entry.getKey(), entry.getValue())));// se añade el
																											// valor a
																											// la lista
																											// de
																											// valores
																											// del
																											// atributo
				}
			}
		}
	}

	public boolean checkRegistrado(String atributo, String valor) {
		return valoresRegistrados.get(atributo).contains(valor);
	}

	public ArrayList<Ejemplo> getEjemplos() {
		return ejemplos;
	}

	public void setEjemplos(ArrayList<Ejemplo> ejemplos) {
		this.ejemplos = ejemplos;
	}

	public ArrayList<String> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<String> atributos) {
		this.atributos = atributos;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public int getN() {
		return this.ejemplos.size();
	}

	public HashMap<String, ArrayList<Pair>> getValoresAtributos() {
		return valoresAtributos;
	}

	public void setValoresAtributos(HashMap<String, ArrayList<Pair>> valoresAtributos) {
		this.valoresAtributos = valoresAtributos;
	}

	public String getClasePos() {
		return clasePos;
	}

	public void setClasePos(String clasePos) {
		this.clasePos = clasePos;
	}

	public HashMap<String, ArrayList<String>> getValoresRegistrados() {
		return valoresRegistrados;
	}

	public void setValoresRegistrados(HashMap<String, ArrayList<String>> valoresRegistrados) {
		this.valoresRegistrados = valoresRegistrados;
	}

	public ArrayList<Ejemplo> getEjemplosRestantes(String atributo, String valor) {
		ArrayList<Ejemplo> restantes = new ArrayList<Ejemplo>();
		for (Ejemplo e : ejemplos) {
			if (e.getValorAtributo(atributo) == valor) {
				restantes.add(e);
			}
		}
		return restantes;
	}
	
	public ArrayList<String> getAtributosRestantes(String atributo) {
		ArrayList<String> restantes = new ArrayList<String>(atributos);
		restantes.remove(atributo);
		return restantes;
	}

	public int getNumEjemplosConResultado(String valorResultado) {
		int cont = 0;
		for (Ejemplo e : this.ejemplos) {
			if (e.getValorAtributo(this.clase).equals(valorResultado)) {
				cont++;
			}
		}
		return cont;
	}

	public int getP(String valorResultado) {
		int cont = 0;
		for (Ejemplo e : this.ejemplos) {
			if (e.getValorAtributo(this.clase).equals(valorResultado)) {
				cont++;
			}
		}
		return cont;
	}

	public APNRs getAPNRs(String atributo, String valor) {
		int contP = 0;
		int cont = 0;
		for (Ejemplo e : this.ejemplos) {
			if (e.getValorAtributo(atributo).equals(valor)) {
				cont++;
				if (e.getValorAtributo(this.clase).equals(this.clasePos)) {
					contP++;
				}
			}
		}
		return new APNRs(cont, contP, cont - contP, contP / this.ejemplos.size());
	}

	public double merito(String atributo) {
		double result = 0;
		APNRs aux;
		for (Pair a : valoresAtributos.get(atributo)) {
			aux = a.getSecond();
			result += aux.getR() * infor(aux.getP() / aux.getA(), aux.getN() / aux.getA());
		}
		return result;
	}

	public double infor(double p, double n) {
		return -p * log(2, p) - n * log(2, n);
	}

	public double log(double base, double number) {
		// log_b(x) = ln(x) / ln(b)
		return Math.log(number) / Math.log(base);
	}

	public boolean positivos() {
		for (Ejemplo e : ejemplos) {
			if (!e.getValorAtributo(clase).equals(clasePos))
				return false;
		}
		return true;
	}

	public boolean negativos() {
		for (Ejemplo e : ejemplos) {
			if (e.getValorAtributo(clase).equals(clasePos))
				return false;
		}
		return true;
	}
}
