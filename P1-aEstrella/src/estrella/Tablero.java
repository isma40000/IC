package estrella;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private Nodo[][] nodos;
	private Nodo meta;
	private Nodo inicio;
	private int dX, dY;
	private ArrayList<Nodo> wayPoints;

	public Tablero(int dX, int dY, int iX, int iY, int mX, int mY) {
		this.dX = dX;
		this.dY = dY;
		nodos = new Nodo[dX][dY];
		this.wayPoints = new ArrayList<Nodo>();
		
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
	
	public Tablero(int dX, int dY) {
		this.dX = dX;
		this.dY = dY;
		nodos = new Nodo[dX][dY];
		this.wayPoints = new ArrayList<Nodo>();
		
		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				nodos[i][j] = new Nodo(i, j,Double.MAX_VALUE);
			}
		}
	}
	public void resetTablero() {
		nodos = new Nodo[dX][dY];
		this.wayPoints = new ArrayList<Nodo>();
		
		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				nodos[i][j] = new Nodo(i, j,Double.MAX_VALUE);
			}
		}
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
	
	public ArrayList<Nodo> getWayPoints() {
		return wayPoints;
	}
	
	public void addWayPoint(Nodo m) {
		this.wayPoints.add(m);
		nodos[m.getX()][m.getY()].setTipo(Ntipo.WAYPOINT);
	}
	
	public void deleteWayPoint(int i, int j) {
		this.wayPoints.remove(nodos[i][j]);
		nodos[i][j].setTipo(Ntipo.NORMAL);
	}
	
	public void setProhibido(Nodo m) {
		if(!m.equals(this.inicio) && !m.equals(this.meta)) {
			nodos[m.getX()][m.getY()].setTipo(Ntipo.PROHIBIDO);
		}
		else {
			System.out.println("No puedes poner la meta ni el inicio como prohibido");
		}
	}
	
	public void borrarProhibida(int i, int j) {
		nodos[i][j].setTipo(Ntipo.NORMAL);
	}
	
	public void addPeligroso(Nodo n) {
		if(!n.equals(this.inicio) && !n.equals(this.meta) && n.getTipo()!=Ntipo.PROHIBIDO && n.getTipo()!=Ntipo.WAYPOINT) {
			nodos[n.getX()][n.getY()].setPeligroso();
		}
		else {
			System.out.println("No puedes poner la meta ni el inicio ni nodos prohibidos ni waypoints como peligrosos");
		}
	}
	
	public void removePeligroso(Nodo n) {
		nodos[n.getX()][n.getY()].notPeligroso();
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
		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				nodos[i][j].setH(getDistanceBetPoints(i, j, meta.getX(), meta.getY()));
			}
		}
		this.meta.setH(0);
	}

	public Nodo getInicio() {
		return inicio;
	}

	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				nodos[i][j].setG(Double.MAX_VALUE);
			}
		}
		this.inicio.setG(0);
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
	
	public void setSolucion(List<Nodo> solucion) {
		for(Nodo a : solucion) {
			Nodo b = nodos[a.getX()][a.getY()];
			if(b.getTipo()==Ntipo.NORMAL) b.setTipo(Ntipo.SOLUCION);
		}
	}

}
