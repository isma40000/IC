package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import estrella.Nodo;
import estrella.Ntipo;

public class ActionListenerImp implements ActionListener {
	private int i;
	private int j;

	public ActionListenerImp(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Nodo n = new Nodo(i, j,Double.MAX_VALUE);

		if (Controlador.getInstance().isBtnInicio()) {
			n.setTipo(Ntipo.INICIO);
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
			Controlador.getInstance().getTablero().setInicio(n);
			Controlador.getInstance().setBtnInicio(false);
		} else if (Controlador.getInstance().isBtnMeta()) {
			n.setTipo(Ntipo.META);
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
			Controlador.getInstance().getTablero().setMeta(n);
			Controlador.getInstance().setBtnMeta(false);
		} else if (Controlador.getInstance().isBtnProhibido()) {
			n.setTipo(Ntipo.PROHIBIDO);
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
			Controlador.getInstance().getTablero().setProhibido(n);
			Controlador.getInstance().setBtnProhibido(false);
		} else if (Controlador.getInstance().isBtnWayPoint()) {
			n.setTipo(Ntipo.WAYPOINT);
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
			Controlador.getInstance().getTablero().añadirWayPoint(n);
			Controlador.getInstance().setBtnWayPoint(false);
		} else if (Controlador.getInstance().isBtnPeligroso()) {
			n.setTipo(Ntipo.PELIGROSO);
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
			Controlador.getInstance().getTablero().añadirPeligroso(n);
			Controlador.getInstance().setBtnPeligroso(false);
		} else if (Controlador.getInstance().isBtnCamino()) {
			n.setTipo(Ntipo.CAMINO);
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j); // si es que había ya marcado esta
																					// casilla
			Controlador.getInstance().getTablero().añadirCamino(n);
			Controlador.getInstance().setBtnCamino(false);
		} else {
			Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
			Controlador.getInstance().setBtnLibre(false);
		}
		colorearBoton(i, j);
	}

}
