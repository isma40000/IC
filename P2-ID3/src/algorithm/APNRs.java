package algorithm;


public class APNRs {
	private int a;
	private int p;
	private int n;
	private double r;
	public APNRs() {
		this.a = 0;
		this.p = 0;
		this.n = 0;
		this.r = 0;
	}
	public APNRs(int a, int p, int n, double r) {
		this.a = a;
		this.p = p;
		this.n = n;
		this.r = r;
	}

//	public APNRs(ArrayList<Ejemplo> ejemplos,String atributo,String valor,String clase,String valorResultado) {
//		int contP = 0;
//		int cont = 0;
//		for (Ejemplo e : ejemplos) {
//			if(e.getValorAtributo(atributo).equals(valor)) {
//				cont++;
//				if (e.getValorAtributo(clase).equals(valorResultado)) {
//					contP++;
//				}
//			}
//		}
//		this.a=cont;
//		this.p=contP;
//		this.n=cont-contP;
//	}

	public int getTotal() {
		return a;
	}

	public void setTotal(int total) {
		this.a = total;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public double getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

}
