package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

public class ID3 {
	private String clasePos;
	private String claseNeg;
	private HashMap<String, ArrayList<String>> valoresAtributos;
	private Nodo arbol = new Nodo();

	public ID3(String clasePos, String claseNeg, HashMap<String, ArrayList<String>> valoresAtributos) {
		this.clasePos = clasePos;
		this.claseNeg = claseNeg;
		this.valoresAtributos = valoresAtributos;
	}

	public void hacerID3(ArrayList<String> lAtributos, ArrayList<Ejemplo> lEjemplos, int iteracion, Nodo arbol) {

		if (lEjemplos.isEmpty()) {
			arbol.setRaiz("No hay ejemplos");
			arbol.setHoja(true);
		} else {
			Datos datos = new Datos(lEjemplos, lAtributos, clasePos);
			if (lAtributos.isEmpty()) {
				arbol.setRaiz("ERROR");
				arbol.setHoja(true);
			} else if (datos.positivos()) {
				arbol.setRaiz(clasePos);
				arbol.setHoja(true);
			} else if (datos.negativos()) {
				arbol.setRaiz(claseNeg);
				arbol.setHoja(true);
			}
			String mejor = "";
			double merit = Double.MAX_VALUE;
			System.out.println("Iteracion " + iteracion);
			System.out.println("------------------------------");
			pintarRama(arbol, arbol.getRamaPadre());
			System.out.println("");
			for (String a : lAtributos) {
				double am = datos.merito(a);
				if (am < merit) {
					merit = am;
					mejor = a;
				}
				System.out.println(a + " = " + am);
			}
			System.out.println("------------------------------");
			arbol.setRaiz(mejor);
			for (String a : valoresAtributos.get(mejor)) {
				arbol.getRamas().put(a, new Nodo(arbol, a));
				hacerID3(datos.getAtributosRestantes(mejor), datos.getEjemplosRestantes(mejor, a), iteracion + 1,
						arbol.getRamas().get(a));
			}
		}
	}

	public void pintarRama(Nodo arbol, String ramaHijo) {
		while (arbol.getPadre() != null) {
			pintarRama(arbol.getPadre(), arbol.getRamaPadre());
		}
		if (arbol.getPadre() == null) {
			System.out.print("Rama -> " + arbol.getRaiz() + " = " + ramaHijo);
		} else {
			System.out.print(", " + arbol.getRaiz() + " = " + ramaHijo);
		}

	}template<typename T>
	void BinTree<T>::levelorder() const {
		std::queue<NodePointer> pending;
		if (root_node != nullptr) {
			pending.push(root_node);
		}
		while (!pending.empty()) {
			NodePointer current = pending.front();
			pending.pop();
			std::cout << current->elem << " ";
			if (current->left != nullptr) {
				pending.push(current->left);
			}
			if (current->right != nullptr) {
				pending.push(current->right);
			}
		}
	}

	public void pintarArbol(Nodo arbol) {
		ArrayList<Integer> numRamas = new ArrayList<Integer>();
		Queue<Pair2> cola = new LinkedList<Pair2>();
		int altura = 0;
		int recorridos = 0;

		for (int i = 0; i < 1000; i++) {
			numRamas.add(0);
		}
		if (arbol.getRaiz() != "") {
			cola.add(new Pair2(arbol,0));
		}
		System.out.print("|| ");
		while (!cola.isEmpty()) {
			Pair2 removido = cola.remove();
			int alturAct = removido.getSecond();
			Nodo actual = removido.getFirst();
			System.out.print(actual.getRaiz() + " ");
			recorridos+=1;
			if (recorridos >= numRamas.get(altura)) {
				System.out.print("|| ");
				System.out.println();
				System.out.print("|| ");
				altura += 1;
				recorridos = 0;
			}
//			 else {
//				System.out.print(actual.getRaiz() + " ");
//			}
			if (!actual.getRamas().isEmpty()) {
				numRamas.set(alturAct + 1, numRamas.get(alturAct + 1) + actual.getRamas().size());
				for (Nodo e : actual.getRamas().values()) {
					cola.add(new Pair2(e,alturAct+1));
				}
			}
		}
	}

}
