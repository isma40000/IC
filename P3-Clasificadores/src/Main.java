
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;

import Jama.Matrix;
import algoritmos.Bayes;
import utils.LeerDatos;
//import algoritmos.Kmeans;
//import algoritmos.Lloyd;
import utils.Util;

public class Main {
	private static String nombreFichero;
	private static ArrayList<double[]> datos;
	private static ArrayList<String> clasesDatos;
	private static ArrayList<String> clases;
	private static ArrayList<double[]> mediasIni;
	private static String algoritmo;
//	private static Kmeans kmeans;
//	private static Lloyd lloyd;
	private static Bayes bayes;

	public static void inicializaDatos(ArrayList<double[]> datoss, ArrayList<String> clases) {
		datos = datoss;
		clasesDatos = clases;
		clases = new ArrayList<String>();
		for (int i = 0; i < clases.size(); i++) {
			if (!clases.contains(clases.get(i))) {
				clases.add(new String(clases.get(i)));
			}
		}
		mediasIni = new ArrayList<double[]>();
		mediasIni.add(new double[] { 4.6, 3.0, 4.0, 0.0 });
		mediasIni.add(new double[] { 6.8, 3.4, 4.6, 0.7 });
	}

	public static void inicializaAlgoritmos() {
		if (nombreFichero != null) {
//			kmeans = new Kmeans(this.datos, this.centrosIni);
//			lloyd = new Lloyd(this.datos,this.centrosIni); 
			bayes = new Bayes(datos, clasesDatos);
		}
	}

	public static void main(String[] a) {
		try {

			JFileChooser jf = new JFileChooser();
			File workingDirectory = new File(System.getProperty("user.dir") + "/src/ficheros");
			jf.setCurrentDirectory(workingDirectory);
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
			jf.setVisible(true);
			jf.showOpenDialog(jf);
			archivo = jf.getSelectedFile();
			int i = 0;
			for (double[] punto : LeerDatos.readDatos(archivo.getAbsolutePath())) {
				System.out.println("Clasificado el punto " + i + " como: " + bayes.clasificarNuevo(punto));
				System.out.println("Clase real del punto " + i + ": " + LeerDatos.getClasesDeDatos().get(i));
				i++;
			}
//		//Ejercicio 2 Hoja 2
//		kmeans = new Kmeans(puntos,centros);
//		kmeans.execute();
//		ArrayList<double[]> sol2 = kmeans.getCentros();
//		System.out.println("########################################## \n");
//		System.out.println("Algoritmo k_means borroso - Ejercicio 2, Hoja 2");
//		System.out.println("..........................................");
//		System.out.println("Centros obtenidos:");
//		System.out.println(Auxiliar.centros2String(sol2));
//		System.out.println("Matriz de grados de pertenencia U:");
//		System.out.println(Auxiliar.u2String(k_medias.getU()));
//		
//		//Ejercicio 3 Hoja 2
//		
//		Lloyd l = new Lloyd(puntos,centros);
//		l.execute();
//		ArrayList<double[]> sol = l.getCentros();
//		System.out.println("########################################## \n");
//		System.out.println("Algoritmo de Lloyd - Ejercicio 3, Hoja 2");
//		System.out.println("..........................................");
//		System.out.println("Centros obtenidos:");
//		System.out.println(Auxiliar.centros2String(sol));
		} catch (Exception e) {
			System.out.println("Fichero con formato incorrecto");
			// Controlador.getInstance().clean();
		}
	}
}
