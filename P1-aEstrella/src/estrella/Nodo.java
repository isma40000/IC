package estrella;

public class Nodo implements Comparable<Nodo>{
	/** Coordenadas xy del nodo */
	private int x, y;
	/** Esto o un vector de solucion TEMP */
	private Nodo nodoAnt;
	private Ntipo tipo;
	/** Distancia mínima desde el inicio hasta este nodo */
	private double dInicio;

//	private boolean prohibido;
//	/** más adelante*/
//	private double peligro;
//	/** Distancia mínima desde el nodo hasta la meta*/
//	private double dMeta;

	public Nodo(int x, int y) {
		this.x = x;
		this.y = y;
		nodoAnt = null;
		tipo = Ntipo.NORMAL;
		dInicio = Double.MAX_VALUE;
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

	public double getdInicio() {
		return dInicio;
	}

	public void setdInicio(double dInicio) {
		this.dInicio = dInicio;
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
		if(dInicio < o.getdInicio()) {
			return 1;
		}else {
			return -1;
		}
	}

}
