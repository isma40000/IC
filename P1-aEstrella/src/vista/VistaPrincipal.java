package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import estrella.Nodo;
import estrella.Tablero;

public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane contentPanel;
	private static VistaPrincipal instance;
	private PanelTablero panelTablero;
	private PanelConfig panelConfig;

	public static VistaPrincipal getInstance() {
		if (instance == null)
			instance = new VistaPrincipal();
		return instance;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = VistaPrincipal.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaPrincipal() {
		super("Algoritmo A estrella");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setSize(1200, 800);
		setContentPane(contentPanel);

		panelTablero = new PanelTablero();

		panelConfig = new PanelConfig();

		contentPanel.setLeftComponent(panelConfig);
		contentPanel.setRightComponent(panelTablero);

		this.setMinimumSize(new Dimension(1200, 800));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
	}

	public void refresh() {
		this.panelTablero.refresh();
	}

	public void pintarCamino(List<Nodo> solucion) {
		int Y;
		int X;
		System.out.println("SOLUCIÓN");
		System.out.println("###################");
		for (int i = 0; i < solucion.size(); i++) {
			X = solucion.get(i).getX();
			Y = solucion.get(i).getY();
			System.out.println("( " + X + ", " + Y + " )");
		}
		System.out.println("###################");
	}
}
