package vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

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
}
