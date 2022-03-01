package estrella;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class A_Estrella {
	/** Cola de prioridad variable */
	public Queue<Nodo> abierta;
	public List<Nodo> cerrada;
	private Tablero board;

	public A_Estrella(Tablero board) {
		abierta = new PriorityQueue<Nodo>();
		cerrada = new ArrayList<Nodo>();
		this.board = board;
	}

	public void expandir(Nodo nodo) {
		for (Vecinos a : Vecinos.values()) {
			int x = nodo.getX() + a.getVal1();
			int y = nodo.getY() + a.getVal2();
			
			if (board.corrCoord(x, y)) {//comprueba si la coord esta dentro del tablero
				Nodo nVecino = board.getNodo(x, y);
				if(!abierta.contains(nVecino) && !cerrada.contains(nVecino) && nVecino.getTipo() != Ntipo.PROHIBIDO) {
					nVecino.setg(nodo.getg() + board.getDistanceBetNodes(nodo, nVecino));
					nVecino.setf(nVecino.getg() + nVecino.geth());
					nVecino.setNodoAnt(nodo);
					abierta.add(board.getNodo(x, y));
				}
			}
		}
	}

	public List<Nodo> estrella() {
		Nodo inicio = board.getInicio();
		Nodo meta = board.getMeta();
		abierta.add(inicio);
		cerrada.add(inicio);
		while (!cerrada.contains(meta) && !abierta.isEmpty()) {
			Nodo nodoExp = abierta.remove();
			expandir(nodoExp);
			cerrada.add(nodoExp);
		}
		List<Nodo> sol = new ArrayList<Nodo>();
		if(cerrada.contains(meta)) {
			//reconstrucción de solución
			Nodo aux = meta;
			sol.add(meta);
			while(aux!=inicio) {
				aux=aux.getNodoAnt();
				sol.add(0,aux);
			}
		}
		return sol;
	}
}
