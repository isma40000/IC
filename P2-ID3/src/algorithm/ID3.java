package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ID3 {
	private String clasePos;
	private String claseNeg;
	private String nombreClase;
	private HashMap<String, ArrayList<String>> valoresAtributos;
	private Nodo arbol = new Nodo();
	private SortedMap<String, String> reglas = new TreeMap<String, String>();
	int numreglas = 0;

	public ID3(String clasePos, String claseNeg, HashMap<String, ArrayList<String>> valoresAtributos,
			String nombreClase) {
		this.clasePos = clasePos;
		this.claseNeg = claseNeg;
		this.valoresAtributos = valoresAtributos;
		this.nombreClase = nombreClase;
	}

	public void hacerID3(ArrayList<String> lAtributos, ArrayList<Ejemplo> lEjemplos, int iteracion, Nodo arbol) {

		if (lEjemplos.isEmpty()) {
			arbol.setRaiz("No hay ejemplos");
			arbol.setHoja(true);
		} else {
			Datos datos = new Datos(lEjemplos, lAtributos, clasePos, nombreClase);
			if (lAtributos.isEmpty()) {
				arbol.setRaiz("ERROR");
				arbol.setHoja(true);
			} else if (datos.positivos()) {
				arbol.setRaiz(clasePos);
				arbol.setHoja(true);
			} else if (datos.negativos()) {
				arbol.setRaiz(claseNeg);
				arbol.setHoja(true);
			} else {
				String mejor = "";
				double merit = Double.MAX_VALUE;
				System.out.println("Iteracion " + iteracion);
				for (String a : lAtributos) {
					double am = datos.merito(a);
					if (am < merit) {
						merit = am;
						mejor = a;
					}
					System.out.println(a + " = " + am);
				}
				arbol.setRaiz(mejor);
				if (iteracion > 0)
					pintarRama(arbol, arbol.getRamaPadre(), 0);
				System.out.println("------------------------------");
				for (String a : valoresAtributos.get(mejor)) {
					arbol.getRamas().put(a, new Nodo(arbol, a));
					hacerID3(datos.getAtributosRestantes(mejor), datos.getEjemplosRestantes(mejor, a), iteracion + 1,
							arbol.getRamas().get(a));
				}
			}
		}
	}

	public void pintarRama(Nodo arbol, String ramaPadre, int i) {
		if (arbol.getPadre() != null) {
			pintarRama(arbol.getPadre(), arbol.getRamaPadre(), i + 1);
		}
		if (arbol.getPadre() == null) {
			System.out.print("Rama -> " + arbol.getRaiz() + " = ");
		} else if (i != 0) {
			System.out.print(ramaPadre + " ," + arbol.getRaiz() + " = ");
		} else {
			System.out.print(ramaPadre);
			System.out.println();
		}

	}

	public void conseguirReglas(Nodo arbol, String ramaPadre, int i, String reglaparcial) {
		if (arbol.isHoja()) {
			reglas.put("Regla " + numreglas, reglaparcial += " entonces " + nombreClase + " = " + arbol.getRaiz());
			numreglas++;
		} else if (i == 0) {
			reglaparcial += "Si " + arbol.getRaiz() + " = ";
			for (Entry<String, Nodo> e : arbol.getRamas().entrySet()) {
				Nodo aux = e.getValue();
				String auxS = reglaparcial;
				auxS += e.getKey();
				conseguirReglas(aux, aux.getRamaPadre(), i + 1, auxS);
			}
		} else {
			reglaparcial += " y " + arbol.getRaiz() + " = ";
			for (Entry<String, Nodo> e : arbol.getRamas().entrySet()) {
				Nodo aux = e.getValue();
				String auxS = reglaparcial;
				auxS += e.getKey();
				conseguirReglas(aux, aux.getRamaPadre(), i + 1, auxS);
			}
		}
	}

	public void pintarReglas() {
		for (Entry<String, String> a : reglas.entrySet()) {
			System.out.println(a.getKey() + ": " + a.getValue());
		}
	}

	public void pintarArbol() {
		int numRamas[] = new int[1000];
		Queue<Pair2> cola = new LinkedList<Pair2>();
		Queue<Pair3> ramas = new LinkedList<Pair3>();
		int altura = 0;
		int recorridos = 0;

		for (int i = 0; i < 1000; i++) {
			numRamas[i] = 0;
		}
		if (!arbol.isHoja()) {
			cola.add(new Pair2(arbol, 0));
			System.out.print("|| ");
			while (!cola.isEmpty()) {
				Pair2 removido = cola.remove();
				int alturAct = removido.getSecond();
				Nodo actual = removido.getFirst();
				System.out.print("*" + actual.getRaiz() + " ");
				recorridos += 1;
				if (!actual.getRamas().isEmpty()) {
					numRamas[alturAct + 1] = numRamas[alturAct + 1] + actual.getRamas().size();
					for (Nodo e : actual.getRamas().values()) {
						cola.add(new Pair2(e, alturAct + 1));
						ramas.add(new Pair3(e.getRamaPadre(), alturAct + 1));
					}
				}
				if (recorridos >= numRamas[altura]) {
					System.out.print("|| ");
					System.out.println();
					System.out.print("|| ");
					if (!ramas.isEmpty()) {
						Pair3 a = ramas.element();
						altura += 1;
						while (!ramas.isEmpty() && a.getSecond() == altura) {
							Pair3 b = ramas.remove();
							System.out.print("|" + b.getFirst() + " ");

						}
						System.out.print("|| ");
						System.out.println();
						System.out.print("|| ");
					}
					recorridos = 0;
				}

			}
		} else {
			System.out.println("|| *" + arbol.getRaiz() + " ||");
		}
	}

	public void evaluarCasos(ArrayList<Ejemplo> ejemplosSolucionar, Nodo arbol) {
		int i = 0;
		for (Ejemplo e : ejemplosSolucionar) {
			Nodo aux = arbol;
			while (!aux.isHoja() && !aux.getRaiz().equals("No existe")) {
				String valorAtributo;
				valorAtributo = e.getValorAtributo(aux.getRaiz());
				if (aux.getRamas().containsKey(valorAtributo)) {
					aux = aux.getRamas().get(valorAtributo);
				} else {
					aux.setRaiz("No existe");
				}
			}
			if (aux.getRaiz().equals("No existe")) {
				System.out.println("Ejemplo " + i + " evaluado como: No evaluable");
			} else {
				System.out.println("Ejemplo " + i + " evaluado como: " + aux.getRaiz());
			}
			i++;
		}
	}

	public String getClasePos() {
		return clasePos;
	}

	public void setClasePos(String clasePos) {
		this.clasePos = clasePos;
	}

	public String getClaseNeg() {
		return claseNeg;
	}

	public void setClaseNeg(String claseNeg) {
		this.claseNeg = claseNeg;
	}

	public HashMap<String, ArrayList<String>> getValoresAtributos() {
		return valoresAtributos;
	}

	public void setValoresAtributos(HashMap<String, ArrayList<String>> valoresAtributos) {
		this.valoresAtributos = valoresAtributos;
	}

	public Nodo getArbol() {
		return arbol;
	}

	public void setArbol(Nodo arbol) {
		this.arbol = arbol;
	}

}
