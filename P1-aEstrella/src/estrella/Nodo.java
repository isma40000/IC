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

//	/** más adelante*/
//	private double peligro;


	public Nodo(int x, int y, double h) {
		this.x = x;
		this.y = y;
		nodoAnt = null;
		tipo = Ntipo.NORMAL;
		g = Double.MAX_VALUE;
		this.h = h;
		f = Double.MAX_VALUE;
	}

//	public double getdMeta() {
//		return dMeta;
//	}
//	public void setdMeta(double dMeta) {
//		this.dMeta = dMeta;
//	}

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

	public double getg() {
		return g;
	}

	public double getf() {
		return f;
	}

	public Ntipo getTipo() {
		return tipo;
	}

	public void setTipo(Ntipo tipo) {
		this.tipo = tipo;
	}

	public void setg(double g) {
		this.g = g;
	}

	public void setf(double f) {
		this.f = f;
	}

	public double geth() {
		return h;
	}

	public void seth(double h) {
		this.h = h;
	}

	public Nodo getNodoAnt() {
		return nodoAnt;
	}

	public void setNodoAnt(Nodo nodoAnt) {
		this.nodoAnt = nodoAnt;
	}

	@Override
	public int compareTo(Nodo o) {
		// TODO Auto-generated method stub
		if (f < o.getf()) {
			return 1;
		} else {
			return -1;
		}
	}

}
