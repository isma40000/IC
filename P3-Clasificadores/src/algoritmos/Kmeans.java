package algoritmos;

import java.util.ArrayList;

import Jama.Matrix;
import utils.Util;

public class Kmeans {
	private double tolerancia = 0.01;
	private double b = 2;
	private ArrayList<Matrix> puntos;
	private ArrayList<Matrix> centros;
	private ArrayList<Matrix> centrosAnt;
	private double[][] U;

	public Kmeans(ArrayList<double[]> _puntos, ArrayList<double[]> _c) {
		inicializaDatos(_puntos, _c);
	}

	private void inicializaDatos(ArrayList<double[]> _puntos, ArrayList<double[]> _c) {
		this.puntos = new ArrayList<Matrix>();
		this.centros = new ArrayList<Matrix>();
		this.centrosAnt = new ArrayList<Matrix>();
		for (double[] p : _puntos) {
			this.puntos.add(Util.getMatrix(p));
		}
		for (double[] c : _c) {
			this.centros.add(Util.getMatrix(c));
		}
	}

	public void execute() {
		do {
			this.centrosAnt = this.centros;
			calculaMatrizPertenenciaU();
			actualizaCentros();

		} while (!fin());
	}

	private void actualizaCentros() {
		for (int i = 0; i < this.centros.size(); i++) {
			calculaCentro(i);
		}

	}

	private void calculaCentro(int iCentro) {
		double[] aux = new double[this.centros.get(iCentro).getRowDimension()];
		for (int i = 0; i < this.centros.get(iCentro).getRowDimension(); i++) {
			aux[i] = 0;
		}
		Matrix num = Util.getMatrix(aux);
		double denom = 0;
		for (int j = 0; j < this.U[iCentro].length; j++) {
			double s = Math.pow(this.U[iCentro][j], this.b);
			num = num.plus(this.puntos.get(j).times(s));
			denom += s;
		}

		this.centros.set(iCentro, num.times(1.0 / denom));
	}

	private void calculaMatrizPertenenciaU() {
		this.U = new double[this.centros.size()][this.puntos.size()];
		for (int i = 0; i < this.centros.size(); i++) {
			for (int j = 0; j < this.puntos.size(); j++) {
				calculaProbPertenencia(i, j);
			}

		}
	}

	private void calculaProbPertenencia(int iClase, int jPunto) {
		double num = Math.pow(1 / d(this.centros.get(iClase), this.puntos.get(jPunto)), 1 / (b - 1));
		double denom = 0.0;
		for (Matrix c : this.centros) {
			denom += Math.pow(1 / d(c, this.puntos.get(jPunto)), 1 / (b - 1));
		}
		this.U[iClase][jPunto] = num / denom;
	}
	public double calculaPertenencia(int iClase, Matrix jPunto) {
		double num = Math.pow(1 / d(this.centros.get(iClase), jPunto), 1 / (b - 1));
		double denom = 0.0;
		for (Matrix c : this.centros) {
			denom += Math.pow(1 / d(c, jPunto), 1 / (b - 1));
		}
		return num / denom;
	}

	private double d(Matrix p1, Matrix p2) {
		return Math.pow(distancia(p1.getRowPackedCopy(), p2.getRowPackedCopy()), 2);
	}

	private boolean fin() {
		for (int i = 0; i < this.centros.size(); i++) {
			if (distancia(this.centros.get(i).getRowPackedCopy(),
					this.centrosAnt.get(i).getRowPackedCopy()) >= tolerancia) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<double[]> getCentros() {
		return Util.matrixtocentros(centros);
	}

	public void setCentros(ArrayList<double[]> _centros) {
		this.centros = Util.centrostoMatrix(_centros);
		this.centrosAnt = new ArrayList<Matrix>();
	}

	public double getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}

	private double distancia(double[] punto, double[] centro) {
		double distancia = 0;
		for (int i = 0; i < punto.length; i++) {
			distancia += Math.pow(punto[i] - centro[i], 2);
		}
		distancia = Math.sqrt(distancia);
		return distancia;
	}

	public int clasificarNuevo(double[] punto) {
		Matrix aux = Util.getMatrix(punto);
		int iClaseMejor = 0;
		double dist;
		double distMejor = d(aux, this.centros.get(0));
		for (int i = 1; i < this.centros.size(); i++) {
			dist = d(aux, this.centros.get(i));
			if (dist < distMejor) {
				distMejor = dist;
				iClaseMejor = i;
			}
		}
		return iClaseMejor;
	}

	public ArrayList<double[]> getCentrosAnt() {
		return Util.matrixtocentros(centrosAnt);
	}

	public void setCentrosAnt(ArrayList<double[]> centrosAnt) {
		this.centrosAnt = Util.centrostoMatrix(centrosAnt);
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double[][] getU() {
		return U;
	}

	public void setU(double[][] u) {
		U = u;
	}

}