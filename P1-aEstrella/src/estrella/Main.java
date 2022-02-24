package estrella;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int dX,dY;
		int iX,iY;
		int mX,mY;
		System.out.println("----Algoritmo A*----");
		System.out.println("----Ricardo Carazo Pérez----");
		System.out.println("----Ismael Giménez Chillada----");
		System.out.println("--------------------");
		System.out.println("La primera celda será la (0,0)");
		System.out.println("Introduce las dimensiones del tablero");
		dX = sc.nextInt();
		dY = sc.nextInt();
		System.out.println("Introduce coordenadas de la celda de inicio");
		iX = sc.nextInt();
		iY = sc.nextInt();
		System.out.println("Introduce coordenadas de la meta");
		mX = sc.nextInt();
		mY = sc.nextInt();
		
	}

}
