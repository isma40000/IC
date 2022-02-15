package estrella;

public class Nodo {
	
	/** Distancia mínima desde el nodo hasta la meta*/
	private double dMeta;
	/** Coordenadas xy del nodo*/
	private int x,y;
	/** Distancia mínima desde el inicio hasta este nodo*/
	private double dInicio;
	/** Esto o un vector de solucion TEMP*/
	private Nodo nodoAnt;
	private boolean prohibido;
	/** más adelante*/
	private double peligro;
	
	
	public Nodo(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double getdMeta() {
		return dMeta;
	}
	public void setdMeta(double dMeta) {
		this.dMeta = dMeta;
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
	
}
