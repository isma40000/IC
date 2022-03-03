package estrella;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int dX, dY;		//Dimensiones tablero
		int iX, iY;		//Coordenadas inicio
		int mX, mY;		//Coordenadas meta
		int nProhib;	//Numero de prohibidos
		int pX,pY;		//Coordenadas prohibidos
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
		while(nProhib > (dX * dY) - 2) {
			System.out.println("Introduce un valor menor al número de casillas del tablero menos 2");
			nProhib = sc.nextInt();
		}
		for(int i=0; i< nProhib; i++) {
			System.out.println("Introduce coordenadas de la casilla prohibida (X Y)");
			pX = sc.nextInt();
			pY = sc.nextInt();
			while(pX < 0 || pX >= dX || pY < 0 || pY >= dY) {
				System.out.println("Introduce un valor entre 0 y "+ (dX-1) +" para la X y un valor entre 0 y "+ (dY-1) +" para la Y.");
				pX = sc.nextInt();
				pY = sc.nextInt();
			}
			board.putProhib(pX, pY);
		}
		A_Estrella estrella = new A_Estrella(board);
		List<Nodo> sol = estrella.estrella();
		
		if (sol.isEmpty()) System.out.println("No hay solución");
		else {
			for (int i = 0; i < sol.size(); i++) {
				System.out.println((i + 1) + " (" + sol.get(i).getX() + ", " + sol.get(i).getY() + ") ");
			}
		}
		
		sc.close();

	}

}
