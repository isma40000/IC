package estrella;

public class Nodo implements Comparable<Nodo> {
	/** Coordenadas xy del nodo */
	private int x, y;
	/** Esto o un vector de solucion TEMP */
	private Nodo nodoAnt;
	private Ntipo tipo;
	/** Distancia mínima desde el inicio hasta este nodo */
	private double g;
	/** Distancia mínima desde el nodo hasta la meta */
	private double h;
	/** Coste para comparar */
	private double f;
	private boolean peligroso;

	public Nodo(int x, int y, double h) {
		this.x = x;
		this.y = y;
		nodoAnt = null;
		tipo = Ntipo.NORMAL;
		g = Double.MAX_VALUE;
		this.h = h;
		f = Double.MAX_VALUE;
		peligroso = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getG() {
		return g;
	}

	public double getF() {
		if (this.peligroso) {
			return f + 1;
		}
		return f;
	}

	public Ntipo getTipo() {
		return tipo;
	}

	public void setTipo(Ntipo tipo) {
		this.tipo = tipo;
	}

	public void setG(double g) {
		this.g = g;
	}

	public void setF(double f) {
		this.f = f;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public Nodo getNodoAnt() {
		return nodoAnt;
	}

	public void setNodoAnt(Nodo nodoAnt) {
		this.nodoAnt = nodoAnt;
	}

	public void setPeligroso() {
		this.peligroso = true;
	}

	public void notPeligroso() {
		this.peligroso = false;
	}

	public boolean isPeligroso() {
		return this.peligroso;
	}

	@Override
	public int compareTo(Nodo o) {
		// TODO Auto-generated method stub
		if (this.getF() > o.getF()) {
			return 1;
		} else {
			return -1;
		}
	}

}
