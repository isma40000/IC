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
	public A_Estrella() {
		abierta = new PriorityQueue<Nodo>();
		cerrada = new ArrayList<Nodo>();
	}
	public List<Nodo> expandir() {
		return null;
	}
	public List<Nodo> estrella(){
		Nodo inicio = board.getInicio();
		Nodo meta = board.getMeta();
		cerrada.add(inicio);
		
		return abierta;
	}
}
