package estrella;

public class Tablero {
	private Nodo[][] nodos;
	private Nodo meta;
	private Nodo inicio;
	private int dX, dY;

	public Tablero(int dX, int dY, int iX, int iY, int mX, int mY) {
		this.dX = dX;
		this.dY = dY;
		nodos = new Nodo[dX][dY];
		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				nodos[i][j] = new Nodo(i, j, getDistanceBetPoints(i, j, mX, mY));
			}
		}
		inicio = nodos[iX][iY];
		meta = nodos[mX] [mY];
		inicio.setTipo(Ntipo.INICIO);
		meta.setTipo(Ntipo.META);

	}

	public void putProhib(int x, int y) {
		nodos[x][y].setTipo(Ntipo.PROHIBIDO);
	}
	public boolean corrCoord(int x, int y) {
		return x >= 0 && x < dX && y >= 0 && y < dY;
	}

	public Nodo[][] getNodos() {
		return nodos;
	}

	public Nodo getNodo(int x, int y) {
		return nodos[x][y];
	}

	public double getDistanceBetPoints(int xa, int ya, int xb, int yb) {
		return Math.sqrt(Math.pow((xb - xa),2) + Math.pow((yb - ya),2));
	}

	public double getDistanceBetNodes(Nodo a, Nodo b) {
		return Math.sqrt(Math.pow((b.getX() - a.getX()),2) + Math.pow((b.getY() - a.getY()),2));
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
