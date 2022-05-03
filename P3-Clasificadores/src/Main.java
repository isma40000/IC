
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;

import Jama.Matrix;
import algoritmos.Bayes;
import algoritmos.Kmeans;
import algoritmos.Lloyd;
import utils.LeerDatos;
//import algoritmos.Kmeans;
//import algoritmos.Lloyd;
import utils.Util;

public class Main {
	private static String nombreFichero;
	private static ArrayList<double[]> datos;
	private static ArrayList<String> clasesDatos;
	private static ArrayList<String> clasest;
	private static ArrayList<double[]> mediasIni;
	private static String algoritmo;
	private static Kmeans kmeans;
	private static Lloyd lloyd;
	private static Bayes bayes;

	public static void inicializaDatos(ArrayList<double[]> datoss, ArrayList<String> clases) {
		datos = datoss;
		clasesDatos = clases;
		clasest = new ArrayList<String>();
		for (int i = 0; i < clases.size(); i++) {
			if (!clasest.contains(clases.get(i))) {
				clasest.add(new String(clases.get(i)));
			}
		}
		mediasIni = new ArrayList<double[]>();
		mediasIni.add(new double[] { 4.6, 3.0, 4.0, 0.0 });
		mediasIni.add(new double[] { 6.8, 3.4, 4.6, 0.7 });
	}

	public static void inicializaAlgoritmos() {
		if (nombreFichero != null) {
			kmeans = new Kmeans(datos, mediasIni);
			lloyd = new Lloyd(datos, mediasIni);
			bayes = new Bayes(datos, clasesDatos);
		}
	}

	public static void main(String[] a) {
		try {

			JFileChooser jf = new JFileChooser();
			File workingDirectory = new File(System.getProperty("user.dir") + "/src/ficheros");
			jf.setCurrentDirectory(workingDirectory);
			jf.setDialogTitle("Elige el archivo con los casos base");
			jf.setVisible(true);
			jf.showOpenDialog(jf);
			File archivo = jf.getSelectedFile();
			if (archivo != null) {
				nombreFichero = archivo.getAbsolutePath();
				ArrayList<double[]> datos = LeerDatos.readDatos(nombreFichero);
				inicializaDatos(datos, LeerDatos.getClasesDeDatos());
				inicializaAlgoritmos();
			}

//			// Ejercicio 1, Hoja 2
//			ArrayList<double[]> puntos = new ArrayList<double[]>();
//			puntos.add(new double[] { 3, 1 });
//			puntos.add(new double[] { 2, 2 });
//			puntos.add(new double[] { 1, 0 });
//			puntos.add(new double[] { 6, 7 });
//			puntos.add(new double[] { 7, 5 });
//			puntos.add(new double[] { 8, 6 });
//
//			ArrayList<String> clases = new ArrayList<String>();// clase de cada punto
//			clases.add("Clase 1");
//			clases.add("Clase 1");
//			clases.add("Clase 1");
//			clases.add("Clase 2");
//			clases.add("Clase 2");
//			clases.add("Clase 2");
//
//			ArrayList<double[]> centros = new ArrayList<double[]>();
//			centros.add(new double[] { 2, 1 });
//			centros.add(new double[] { 7, 6 });

			bayes = new Bayes(datos, clasesDatos);
			bayes.execute();

			System.out.println("Algoritmo de Bayes");
			System.out.println("-------------------------------------");
			System.out.println("Centros/medias obtenidos:");
			System.out.println(Util.mediastoString(bayes.getMedias()));
			System.out.println("Matrices de Covarianza:");
			HashMap<String, Matrix> mCov = bayes.getmCovarianzas();
			for (String c : mCov.keySet()) {
				System.out.println("Clase: " + c + "\n");
				System.out.println(Util.matrixtoString(mCov.get(c)));
			}
			System.out.println("-------------------------------------");
			jf.setCurrentDirectory(workingDirectory);
			jf.setDialogTitle("Elige un archivo con ejemplos para clasificar - Bayes");
			jf.setVisible(true);
			jf.showOpenDialog(jf);
			archivo = jf.getSelectedFile();
			int i = 0;
			for (double[] punto : LeerDatos.readDatos(archivo.getAbsolutePath())) {
				System.out.println("Clasificado el punto " + i + " como: " + bayes.clasificarNuevo(punto));
				System.out.println("Clase real del punto " + i + ": " + LeerDatos.getClasesDeDatos().get(i));
				i++;
			}
			// Ejercicio 2 Hoja 2
			kmeans.execute();
			ArrayList<double[]> sol2 = kmeans.getCentros();
			System.out.println("########################################## \n");
			System.out.println("Algoritmo k_means");
			System.out.println("..........................................");
			System.out.println("Centros obtenidos:");
			System.out.println(Util.mediastoString(sol2));
			System.out.println("Matriz de grados de pertenencia U:");
			System.out.println(Util.utoString(kmeans.getU()));
			System.out.println("-------------------------------------");
			jf.setCurrentDirectory(workingDirectory);
			jf.setDialogTitle("Elige un archivo con ejemplos para clasificar - Kmeans");
			jf.setVisible(true);
			jf.showOpenDialog(jf);
			archivo = jf.getSelectedFile();
			int i2 = 0;
			for (double[] punto : LeerDatos.readDatos(archivo.getAbsolutePath())) {
				double p1=0,p2;
				int indice=0;
				for(int beta=0;beta<clasest.size();beta++) {
					p2=kmeans.calculaPertenencia(beta, Util.getMatrix(punto));
					if(p2>p1) {
						p1=p2;
						indice=beta;
					}
				}
				System.out.println("Clasificado el punto " + i2 + " como: " + clasest.get(indice));
				System.out.println("Clase real del punto " + i2 + ": " + LeerDatos.getClasesDeDatos().get(i2));
				i2++;
			}
			
			// Ejercicio 3 Hoja 2
			lloyd.execute();
			ArrayList<double[]> sol = lloyd.getCentros();
			System.out.println("########################################## \n");
			System.out.println("Algoritmo de Lloyd - Ejercicio 3, Hoja 2");
			System.out.println("..........................................");
			System.out.println("Centros obtenidos:");
			System.out.println(Util.mediastoString(sol));
			jf.setCurrentDirectory(workingDirectory);
			jf.setDialogTitle("Elige un archivo con ejemplos para clasificar - Lloyd");
			jf.setVisible(true);
			jf.showOpenDialog(jf);
			archivo = jf.getSelectedFile();
			int i3 = 0;
			for (double[] punto : LeerDatos.readDatos(archivo.getAbsolutePath())) {
				double p1=0,p2;
				int indice=0;
				for(int beta=0;beta<clasest.size();beta++) {
					p2=lloyd.bestCenter(punto);
					if(p2>p1) {
						p1=p2;
						indice=beta;
					}
				}
				System.out.println("Clasificado el punto " + i3 + " como: " + clasest.get(indice));
				System.out.println("Clase real del punto " + i3 + ": " + LeerDatos.getClasesDeDatos().get(i3));
				i3++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fichero con formato incorrecto");
			// Controlador.getInstance().clean();
		}
	}
}
