package utils;

import java.util.ArrayList;

import Jama.Matrix;

public class Util {
	private static Util instance = null;

	public static Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}

	public static Matrix getMatrix(double[] dif) { // matriz columna
		double[][] mDif = new double[dif.length][1];
		for (int i = 0; i < dif.length; i++) {
			mDif[i][0] = dif[i];
		}
		return Matrix.constructWithCopy(mDif);
	}

	public static Matrix inicializaMatriz(int length) {
		double[][] aux = new double[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				aux[i][j] = 0;
			}

		}
		return Matrix.constructWithCopy(aux);
	}

	public static Matrix inicializaMatriz(int length, int length2) {
		double[][] aux = new double[length][length2];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length2; j++) {
				aux[i][j] = 0;
			}

		}
		return Matrix.constructWithCopy(aux);
	}

	public static ArrayList<Matrix> mediastoMatrix(ArrayList<double[]> medias) {
		ArrayList<Matrix> sol = new ArrayList<Matrix>();
		for (double[] c : medias) {
			sol.add(getMatrix(c));
		}
		return sol;
	}

	public static ArrayList<double[]> matrixtomedias(ArrayList<Matrix> medias) {
		ArrayList<double[]> sol = new ArrayList<double[]>();
		for (Matrix c : medias) {
			sol.add(c.getRowPackedCopy());
		}
		return sol;
	}

	public static String ejemplostoString(ArrayList<double[]> ejemplos) {
		String sol = "";
		int pos = 0;
		for (double[] ej : ejemplos) {
			for (int i = 0; i < ej.length; i++) {
				if (i == 0)
					sol += (pos + 1) + ".) " + ej[i];
				else
					sol += ", " + ej[i];
			}
			sol += "\n";
			pos++;
		}

		return sol;

	}

	public static String datostoString(ArrayList<double[]> datos) {
		String sol = "";
		for (int i = 0; i < datos.size(); i++) {
			for (int j = 0; j < datos.get(i).length; j++) {
				if (j == 0)
					sol += datos.get(i)[j];
				else
					sol += ", " + datos.get(i)[j];
			}
			sol += "\n";
		}

		return sol;
	}

	public static String clasestoString(ArrayList<String> clases) {
		String s = "";
		int i = 0;
		for (String c : clases) {
			s += (i + 1) + ".) " + c + "\n";
			i++;
		}
		return s;
	}

	public static String mediastoString(ArrayList<double[]> medias) {
		String text = "";
		for (int i = 0; i < medias.size(); i++) {
			text += (i + 1) + ". [";
			for (int j = 0; j < medias.get(i).length; j++) {
				text += redondearDecimales(medias.get(i)[j], 2);
				if (j != medias.get(i).length - 1) {
					text += ", ";
				}
			}
			text += "] " + "\n";
		}
		return text;
	}

	public static String matrixtoString(Matrix m) {
		String s = "";
		s += "------Matriz------" + "\n";
		for (int i = 0; i < m.getRowDimension(); i++) {
			for (int j = 0; j < m.getColumnDimension(); j++) {
				s += redondearDecimales(m.get(i, j), 3);
				if (j != m.getColumnDimension() - 1) {
					s += "  ";
				}
			}
			s += "\n";
		}
		s += "------------------" + "\n";
		return s;
	}

	public static double redondearDecimales(double valorInicial, int numeroDecimales) {
		double resultado;
		resultado = valorInicial;
		resultado = resultado * Math.pow(10, numeroDecimales);
		resultado = Math.floor(resultado);
		resultado = resultado / Math.pow(10, numeroDecimales);

		return resultado;
	}

	public static String utoString(double[][] u) {
		// TODO Auto-generated method stub
		String s = "";
		s += "---Matriz---------" + "\n";
		for (int i = 0; i < u.length; i++) {
			for (int j = 0; j < u[i].length; j++) {
				s += redondearDecimales(u[i][j], 3);
				if (j != u[i].length - 1) {
					s += "  ";
				}
			}
			s += "\n";
		}
		s += "------------------" + "\n";
		return s;
	}

	public static String mediasInitoString(ArrayList<double[]> medias) {
		String text = "";
		for (int i = 0; i < medias.size(); i++) {
			text += (i + 1) + ". [";
			for (int j = 0; j < medias.get(i).length; j++) {
				text += medias.get(i)[j];
				if (j != medias.get(i).length - 1) {
					text += ", ";
				}
			}
			text += "] " + "\n";
		}
		return text;
	}

}
