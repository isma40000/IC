package estrella;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int dX, dY;
		int iX, iY;
		int mX, mY;
		int nProhib;
		int pX,pY;
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
		while(iX < 0 || iX>=dX || iY < 0 || iY>=dY) {
			System.out.println("Introduce un valor entre 0 y "+ (dX-1) +" para la X y un valor entre 0 y "+ (dY-1) +" para la Y.");
			iX = sc.nextInt();
			iY = sc.nextInt();
		}
		System.out.println("Introduce coordenadas de la meta");
		mX = sc.nextInt();
		mY = sc.nextInt();
		while(mX < 0 || mX>=dX || mY < 0 || mY>=dY) {
			System.out.println("Introduce un valor entre 0 y "+ (dX-1) +" para la X y un valor entre 0 y "+ (dY-1) +" para la Y.");
			mX = sc.nextInt();
			mY = sc.nextInt();
		}
		Tablero board = new Tablero(dX,dY,iX,iY,mX,mY);
		//prohibidos
		System.out.println("Introduce el número de casillas prohibidas que vas a querer introducir");
		nProhib = sc.nextInt();
		while(nProhib > dX*dY-2) {
			System.out.println("Introduce un valor menor al número de casillas del tablero menos 2");
			nProhib = sc.nextInt();
		}
		for(int i=0;i<nProhib;i++) {
			System.out.println("Introduce coordenadas de la casilla prohibida (X Y)");
			
		}
		A_Estrella estrella = new A_Estrella(board);

	}

}
