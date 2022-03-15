package vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import estrella.Nodo;
import estrella.Tablero;

public class PanelTablero extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton[][] casillas;
	private int dX;
	private int dY;

	public PanelTablero() {
		initGui();
	}

	public void initGui() {
		inicializar();
		ajustar();
	}

	public void inicializar() {
		this.removeAll();
		Tablero tablero = Controlador.getInstance().getTablero();
		dX = tablero.getdX();
		dY = tablero.getdY();
		this.setBackground(Color.WHITE);
		setLayout(new GridLayout(dX, dY));
		casillas = new JButton[dX][dY];

		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				casillas[i][j] = new JButton();
				colorearBoton(i, j);
				casillas[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				casillas[i][j].addActionListener(new ActionListenerImp(i, j));
				this.add(casillas[i][j]);
			}
		}
		this.revalidate();
		this.repaint();
	}

	private void colorearBoton(int i, int j) {
		switch (Controlador.getInstance().getTablero().getNodo(i, j).getTipo()) {
		case NORMAL:
			if (Controlador.getInstance().getTablero().getNodo(i, j).isPeligroso()) {
				casillas[i][j].setBackground((Color.ORANGE));
			} else
				casillas[i][j].setBackground((Color.WHITE));
			break;
		case META:
			casillas[i][j].setBackground(Color.BLUE.darker());
			break;
		case INICIO:
			casillas[i][j].setBackground(Color.BLUE);
			break;
		case PROHIBIDO:
			casillas[i][j].setBackground(Color.RED);
			break;
		case WAYPOINT:
			casillas[i][j].setBackground(Color.YELLOW);
			break;
		case SOLUCION:
			casillas[i][j].setBackground(Color.GREEN);
			break;
		default:
			casillas[i][j].setBackground(Color.BLACK);
			break;
		}
	}

	public int getdX() {
		return this.dX;
	}

	public int getdY() {
		return this.dY;
	}

	public void setdX(int m) {
		this.dX = m;
	}

	public void setdY(int n) {
		this.dY = n;
	}

	public void ajustar() {
		int ancho = this.getWidth();
		int alto = this.getHeight();

		int anchoBtn = ancho / dY;
		int altoBtn = alto / dX;

		for (int i = 0; i < dX; i++) {
			for (int j = 0; j < dY; j++) {
				casillas[i][j].setBounds((j) * anchoBtn, (i) * altoBtn, anchoBtn, altoBtn);
			}
		}

	}

	public void refresh() {
		this.initGui();
	}

	private class ActionListenerImp implements ActionListener {
		private int i;
		private int j;

		public ActionListenerImp(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (Controlador.getInstance().getTipoBoton() != null) {
				Nodo n = new Nodo(i, j, Double.MAX_VALUE);
				n.setTipo(Controlador.getInstance().getTipoBoton());
				Controlador.getInstance().getTablero().deleteCell(i, j);

				switch (Controlador.getInstance().getTipoBoton()) {
				case INICIO:
					Controlador.getInstance().getTablero().setInicio(n);
					break;
				case META:
					Controlador.getInstance().getTablero().setMeta(n);
					break;
				case NORMAL:
					if (Controlador.getInstance().getPeligroso()) {
						Controlador.getInstance().getTablero().addPeligroso(n);
					}
					break;
				case WAYPOINT:
					Controlador.getInstance().getTablero().addWayPoint(n);
					break;
				case PROHIBIDO:
					Controlador.getInstance().getTablero().setProhibido(n);
					break;
				case SOLUCION:
					break;
				}
				Controlador.getInstance().setTipoBoton(null);
				Controlador.getInstance().setPeligroso(false);
				colorearBoton(i, j);
			}
		}

	}

}
