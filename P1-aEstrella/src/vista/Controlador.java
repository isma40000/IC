package vista;

import java.util.List;

import estrella.Nodo;
import estrella.Ntipo;
import estrella.Tablero;

public class Controlador {

	private static Controlador instance = null;
	private Ntipo tipoboton;
	private boolean peligroso;
	private Tablero tablero;

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
		}

		return instance;
	}

	public Controlador() {
		tipoboton = Ntipo.NORMAL;
		peligroso = false;
	}


	public Tablero getTablero() {
		if (tablero == null) {
			tablero = new Tablero(4, 4);
		}
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public void setTipoBoton(Ntipo tipo) {
		this.tipoboton=tipo;
	}
	
	public Ntipo getTipoBoton() {
		return this.tipoboton;
	}
	
	public void setPeligroso(boolean peligroso){
		this.peligroso=peligroso;
	}
	
	public boolean getPeligroso(){
		return this.peligroso;
	}
	public void refresh() {
		VistaPrincipal.getInstance().refresh();
	}

	public void pintarCamino(List<Nodo> solucion) {
		tablero.setSolucion(solucion);
		VistaPrincipal.getInstance().pintarCamino(solucion);
	}
}
