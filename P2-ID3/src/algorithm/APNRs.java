package algorithm;

public class APNRs {
	private double a;
	private double p;
	private double n;
	private double r;

	public APNRs() {
		this.a = 0;
		this.p = 0;
		this.n = 0;
		this.r = 0;
	}

	public APNRs(double a, double p, double n, double r) {
		this.a = a;
		this.p = p;
		this.n = n;
		this.r = r;
	}

	public double getTotal() {
		return a;
	}

	public void setTotal(double total) {
		this.a = total;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

}
