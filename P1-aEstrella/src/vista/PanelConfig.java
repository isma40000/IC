package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import estrella.A_Estrella;
import estrella.Nodo;
import estrella.Ntipo;
import estrella.Tablero;

public class PanelConfig extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textNumFilas;
	private JTextField textNumColumnas;

	public PanelConfig() {
		setBorder(null);
		setOpaque(false);
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setBounds(new Rectangle(2, 2, 2, 2));

		JLabel lblfilas = new JLabel("Filas:");
		lblfilas.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		JLabel lblColumnas = new JLabel("Columnas:");
		lblColumnas.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		JLabel lblTablero = new JLabel("Tablero");
		lblTablero.setForeground(new Color(0, 0, 0));
		lblTablero.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

		textNumFilas = new JTextField("10");
		textNumFilas.setSelectedTextColor(null);
		textNumFilas.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textNumFilas.setCaretColor(new Color(255, 255, 255));
		textNumFilas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNumFilas.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textNumFilas.setBackground(new Color(255, 255, 255));
		textNumFilas.setColumns(10);

		textNumColumnas = new JTextField("10");
		textNumColumnas.setSelectedTextColor(null);
		textNumColumnas.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textNumColumnas.setCaretColor(new Color(255, 255, 255));
		textNumColumnas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNumColumnas.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textNumColumnas.setBackground(new Color(255, 255, 255));
		textNumColumnas.setColumns(10);

		JButton btnCrearTab = new JButton("Crear");
		btnCrearTab.setVerticalTextPosition(SwingConstants.CENTER);
		btnCrearTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int filas = Integer.valueOf(textNumFilas.getText());
					int columnas = Integer.valueOf(textNumColumnas.getText());
					if (filas < 0 || columnas < 0)
						throw new NumberFormatException();
					Tablero tablero = new Tablero(filas, columnas);
					Controlador.getInstance().setTablero(tablero);
					Controlador.getInstance().refresh();
				} catch (NumberFormatException nF) {
					JOptionPane.showMessageDialog(null,
							"Los campos filas y columnas deben contener números mayores o iguales que 0.");
				}
			}
		});
		btnCrearTab.setBackground(new Color(200, 200, 200));
		btnCrearTab.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));

		JButton btnComenzar = new JButton("Comenzar");
		btnComenzar.setBackground(new Color(255, 255, 255));
		btnComenzar.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		btnComenzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getInstance().getTablero().getInicio() == null) {
					JOptionPane.showMessageDialog(null, "No hay nodo inicio", "Error", JOptionPane.INFORMATION_MESSAGE);
				} else if (Controlador.getInstance().getTablero().getMeta() == null) {
					JOptionPane.showMessageDialog(null, "No hay nodo meta", "Error", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Controlador.getInstance().refresh();
					A_Estrella a = new A_Estrella(Controlador.getInstance().getTablero());
					List<Nodo> solucion;
					if (Controlador.getInstance().getTablero().getWayPoints().size() > 0) {
						solucion = a.estrella_waypoints();
					} else {
						solucion = a.estrella();
					}
					if (solucion.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se puede llegar al nodo Meta", "No hay camino",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						Controlador.getInstance().setTablero(a.getBoard());
						Controlador.getInstance().pintarCamino(solucion);
						Controlador.getInstance().refresh();
					}
				}

			}

		});
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().getTablero().resetTablero();
				Controlador.getInstance().refresh();
			}

		});

		JButton btnColocarProhibido = new JButton("Prohibido");
		btnColocarProhibido.setBackground(Color.RED);
		btnColocarProhibido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setTipoBoton(Ntipo.PROHIBIDO);
			}

		});
		JButton btnColocarWayPoint = new JButton("WayPoint");
		btnColocarWayPoint.setBackground(Color.YELLOW);
		btnColocarWayPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setTipoBoton(Ntipo.WAYPOINT);
			}

		});
		JButton btnColocarPeligroso = new JButton("Peligroso");
		btnColocarPeligroso.setBackground(Color.ORANGE);
		btnColocarPeligroso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setTipoBoton(Ntipo.NORMAL);
				Controlador.getInstance().setPeligroso(true);
			}
		});

		JButton btnColocarNormal = new JButton("Normal");
		btnColocarNormal.setBackground(Color.WHITE);
		btnColocarNormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setTipoBoton(Ntipo.NORMAL);
			}

		});

		JButton btnColocarInicio = new JButton("Inicio");
		btnColocarInicio.setBackground(Color.BLUE);
		btnColocarInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Controlador.getInstance().getTablero().getInicio() == null) {
					Controlador.getInstance().setTipoBoton(Ntipo.INICIO);
				} else {
					JOptionPane.showMessageDialog(null, "Si quieres cambiar el inicio elimínalo y colócalo de nuevo");
				}
			}

		});
		JButton btnColocarMeta = new JButton("Meta");
		btnColocarMeta.setBackground(Color.BLUE.darker());
		btnColocarMeta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Controlador.getInstance().getTablero().getMeta() == null) {
					Controlador.getInstance().setTipoBoton(Ntipo.META);
				} else {
					JOptionPane.showMessageDialog(null, "Si quieres cambiar la meta elimínala y colócala de nuevo");
				}
			}
		});
		GridLayout experimentLayout = new GridLayout(7, 2);

		setLayout(experimentLayout);
		this.add(lblTablero);
		this.add(btnCrearTab);
		this.add(lblfilas);
		this.add(textNumFilas);
		this.add(lblColumnas);
		this.add(textNumColumnas);
		this.add(btnColocarInicio);
		this.add(btnColocarMeta);

		this.add(btnColocarProhibido);
		this.add(btnColocarWayPoint);

		this.add(btnColocarPeligroso);
		this.add(btnColocarNormal);

		this.add(btnComenzar);
		this.add(btnReset);
		this.repaint();
	}
}
