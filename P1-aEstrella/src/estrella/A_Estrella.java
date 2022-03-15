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

			if (board.corrCoord(x, y)) {// comprueba si la coord esta dentro del tablero
				Nodo nVecino = board.getNodo(x, y);
				if (!abierta.contains(nVecino) && !cerrada.contains(nVecino) && nVecino.getTipo() != Ntipo.PROHIBIDO) {
					nVecino.setG(nodo.getG() + board.getDistanceBetNodes(nodo, nVecino));
					nVecino.setF(nVecino.getG() + nVecino.getH());
					nVecino.setNodoAnt(nodo);
					board.setNodo(x, y, nVecino);
					if (nVecino.getH() == 0)
						board.setMeta(nVecino);
					abierta.add(board.getNodo(x, y));
				}
			}
		}
	}

	public List<Nodo> estrella() {
		abierta.clear();
		cerrada.clear();
		Nodo inicio = board.getInicio();

		abierta.add(inicio);
		// cerrada.add(inicio);
		while (!cerrada.contains(board.getMeta()) && !abierta.isEmpty()) {
			Nodo nodoExp = abierta.remove();
			if (nodoExp.getX() == 9 && nodoExp.getY() == 9) {
				System.out.println("Meta en cerrada");
			}
			cerrada.add(nodoExp);
			if (nodoExp != board.getMeta()) {
				expandir(nodoExp);
			}
		}
		List<Nodo> sol = new ArrayList<Nodo>();
		if (cerrada.contains(board.getMeta())) {
			// reconstrucción de solución
			Nodo aux = board.getMeta();
			sol.add(board.getMeta());
			while (aux != inicio) {
				aux = aux.getNodoAnt();
				sol.add(0, aux);
			}
		}
		return sol;
	}

	public List<Nodo> estrella_waypoints() {
		ArrayList<Nodo> wayPoints = new ArrayList<Nodo>(board.getWayPoints());
		wayPoints.add(board.getMeta());
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		List<Nodo> aux = new ArrayList<Nodo>();

		Nodo meta = nextWayPoint(wayPoints, board.getInicio());
		board.setMeta(meta);
		wayPoints.remove(meta);
		aux = estrella();

		if (aux == null)
			return null;

		solucion.addAll(solucion.size(), aux);

		Nodo inicio;
		boolean end = false;
		while (wayPoints.size() > 0 && end == false) {
			inicio = meta;
			meta = nextWayPoint(wayPoints, inicio);
			wayPoints.remove(meta);

			board.setInicio(inicio);
			board.setMeta(meta);
			aux = estrella();
			if (aux != null) {
				aux.remove(0);// borra el nodo inicio porque ya se metio en la solucion como meta, asi
								// evitamos nodos duplicados
				solucion.addAll(solucion.size(), aux);
			} else
				return null;
		}
		return solucion;
	}

	public Nodo nextWayPoint(ArrayList<Nodo> wayPoints, Nodo inicio) {
		if (wayPoints.size() > 0) {
			double minDistance = board.getDistanceBetNodes(wayPoints.get(0), inicio);
			int minIndex = 0;
			int i = 0;
			while (i < wayPoints.size()) {
				if (minDistance > board.getDistanceBetNodes(wayPoints.get(i), inicio)
						&& wayPoints.get(i).getTipo() != Ntipo.META) {
					minDistance = board.getDistanceBetNodes(wayPoints.get(i), inicio);
					minIndex = i;
				}
				i++;
			}
			return wayPoints.get(minIndex);
		}
		return null;
	}

	public Queue<Nodo> getAbierta() {
		return abierta;
	}

	public void setAbierta(Queue<Nodo> abierta) {
		this.abierta = abierta;
	}

	public List<Nodo> getCerrada() {
		return cerrada;
	}

	public void setCerrada(List<Nodo> cerrada) {
		this.cerrada = cerrada;
	}

	public Tablero getBoard() {
		return board;
	}

	public void setBoard(Tablero board) {
		this.board = board;
	}

}
