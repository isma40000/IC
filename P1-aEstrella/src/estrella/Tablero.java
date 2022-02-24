package estrella;

import java.util.List;

public class Tablero {
	private Nodo[][] nodos;
	private Nodo meta;
	private Nodo inicio;
	private int dX, dY;

	public Tablero(int dX, int dY) {
		this.dX = dX;
		this.dY = dY;
		nodos = new Nodo[dX][dY];
		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				nodos[i][j] = new Nodo(i, j);
			}
		}
	}
	public List<Nodo> vecinos(Nodo n){
		Vecinos a[] = Vecinos.values();
		for(Vecinos c : a) {
			
		}
		return null;
		
	}
	public boolean corrCoord(int x, int y) {
		return x>=0 && x<dX && y>=0 && y<dY;
	}
	public Nodo[][] getNodos() {
		return nodos;
	}

	public void setNodos(Nodo[][] nodos) {
		this.nodos = nodos;
	}

	public Nodo getMeta() {
		return meta;
	}

	public void setMeta(Nodo meta) {
		this.meta = meta;
	}

	public Nodo getInicio() {
		return inicio;
	}

	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

}
