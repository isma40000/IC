package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;

import Jama.Matrix;
import utils.Util;

public class Bayes {
	private HashMap<String, ArrayList<double[]>> puntos;
	private HashMap<String, double[]> medias;
	private HashMap<String, Matrix> mCovarianzas;

	public Bayes(ArrayList<double[]> _puntos, ArrayList<String> _clasesPuntos) {
		inicializaDatos(_puntos, _clasesPuntos);
		this.medias = new HashMap<String, double[]>();
		this.mCovarianzas = new HashMap<String, Matrix>();
	}

	public void execute() {
		calculaMedias();
		calculaMatricesCovarianza();
	}

	private void inicializaDatos(ArrayList<double[]> _puntos, ArrayList<String> _clasesPuntos) {
		// TODO Auto-generated method stub
		this.puntos = new HashMap<String, ArrayList<double[]>>();

		for (int i = 0; i < _puntos.size(); i++) {
			if (!puntos.containsKey(_clasesPuntos.get(i))) {
				puntos.put(_clasesPuntos.get(i), new ArrayList<double[]>());
			}
			puntos.get(_clasesPuntos.get(i)).add(_puntos.get(i));
		}
	}

	private void calculaMedias() {
		for (String clase : this.puntos.keySet()) {
			double[] aux = new double[this.puntos.get(clase).get(0).length];
			for (double[] p : this.puntos.get(clase)) {
				for (int i = 0; i < p.length; i++) {
					aux[i] += p[i];
				}
			}
			for (int j = 0; j < aux.length; j++) {
				aux[j] = aux[j] / this.puntos.get(clase).size();
			}
			this.medias.put(clase, aux);
		}
	}

	private void calculaMatricesCovarianza() {
		for (String clase : this.medias.keySet()) {
			Matrix mCov = Util.inicializaMatriz(this.puntos.get(clase).get(0).length);
			Matrix media = Util.getMatrix(this.medias.get(clase));
			for (double[] puntoClase : this.puntos.get(clase)) {
				Matrix mPunto = Util.getMatrix(puntoClase);

				mCov = mCov.plus((mPunto.minus(media)).times((mPunto.minus(media).transpose())));
			}

			double n = 1.0 / this.puntos.get(clase).size();
			mCov.timesEquals(n);
			mCovarianzas.put(clase, mCov);
		}
	}

	public String clasificarNuevo(double[] punto) {
		String claseMejor = "";
		double fMejor = 0, f = 0;
		int i = 0;
		for (String clase : this.medias.keySet()) {
			f = calcularVerosimilitud(punto, clase);
			if (i != 0) {
				if (fMejor < f) {
					claseMejor = clase;
					fMejor = f;
				}
			} else {
				fMejor = f;
				claseMejor = clase;
			}
			i++;
		}

		return claseMejor;
	}

	private double calcularVerosimilitud(double[] punto, String clase) {
		Matrix mPunto = Util.getMatrix(punto);
		Matrix media = Util.getMatrix(this.medias.get(clase));
		int d = mPunto.getRowDimension();
		Matrix cInversa = this.mCovarianzas.get(clase).inverse();
		double distMahalanobisAlCuadrado = (((mPunto.minus(media)).transpose()).times(cInversa))
				.times(mPunto.minus(media)).get(0, 0);
		double aux = distMahalanobisAlCuadrado * -0.5;
		double f = Math.exp(aux);
		double sol = f * 1.0 / (Math.pow(2 * Math.PI, d / 2) * Math.pow(this.mCovarianzas.get(clase).det(), 0.5));
		return sol;
	}

	public ArrayList<double[]> getMedias() {
		ArrayList<double[]> sol = new ArrayList<double[]>();
		for (String c : this.medias.keySet()) {
			sol.add(this.medias.get(c));
		}
		return sol;
	}

	public HashMap<String, Matrix> getmCovarianzas() {
		return mCovarianzas;
	}

	public void setmCovarianzas(HashMap<String, Matrix> mCovarianzas) {
		this.mCovarianzas = mCovarianzas;
	}

}
