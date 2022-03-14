package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import estrella.A_Estrella;
import estrella.Nodo;
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
		textNumFilas.setForeground(new Color(255, 255, 255));
		textNumFilas.setCaretColor(new Color(255, 255, 255));
		textNumFilas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNumFilas.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textNumFilas.setBackground(new Color(255, 255, 255));
		textNumFilas.setColumns(10);
		
		
		textNumColumnas = new JTextField("10");
		textNumColumnas.setSelectedTextColor(null);
		textNumColumnas.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		textNumColumnas.setForeground(new Color(255, 255, 255));
		textNumColumnas.setCaretColor(new Color(255, 255, 255));
		textNumColumnas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNumColumnas.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textNumColumnas.setBackground(new Color(255, 255, 255));
		textNumColumnas.setColumns(10);
		
		JButton btnGenerarTab = new JButton("Generar");
		btnGenerarTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int filas = Integer.valueOf(textNumFilas.getText());
					int columnas = Integer.valueOf(textNumColumnas.getText());
					if(filas < 0 || columnas < 0)
						throw new NumberFormatException();
					Tablero tablero = new Tablero(filas,columnas);
					Controlador.getInstance().setTablero(tablero);
					Controlador.getInstance().refresh();
				} catch(NumberFormatException nF){
					JOptionPane.showMessageDialog(null, "Los campos filas y columnas deben contener números mayores o iguales que 0.");
				}
			}
		});
		btnGenerarTab.setBackground(new Color(200, 200, 200));
		btnGenerarTab.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));

		
		JLabel lblElementos = new JLabel("Coloca en el tablero  : \r\n");
		lblElementos.setForeground(Color.BLACK);
		lblElementos.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JLabel lblMeta = new JLabel("Meta");
		lblMeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeta.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JLabel lblObstaculo = new JLabel("Prohibido");
		lblObstaculo.setHorizontalAlignment(SwingConstants.CENTER);
		lblObstaculo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JLabel lblWayPoint = new JLabel("WayPoint");
		lblWayPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblWayPoint.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JLabel lblPeligroso = new JLabel("Peligroso");
		lblPeligroso.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeligroso.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JLabel lblNormal = new JLabel("Eliminar");
		lblNormal.setForeground(new Color(240, 255, 255));
		lblNormal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNormal.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JButton btnComenzar = new JButton("Comenzar");
		btnComenzar.setBackground(new Color(255, 255, 255));
		btnComenzar.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		btnComenzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getTablero().getInicio() == null) {
					JOptionPane.showMessageDialog(null, "No hay nodo inicio", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(Controlador.getInstance().getTablero().getMeta() == null){
					JOptionPane.showMessageDialog(null, "No hay nodo meta", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Controlador.getInstance().refresh();
					A_Estrella a = new A_Estrella(Controlador.getInstance().getTablero());
					List<Nodo> solucion;
					if(Controlador.getInstance().getTablero().getWayPoints().size() > 0) {
						solucion = a.estrella_waypoints();
					}
					else {
						solucion = a.estrella();
					}
					if(solucion == null){
						JOptionPane.showMessageDialog(null, "No se puede llegar al nodo Meta", "No hay camino", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
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
		JSeparator separator = new JSeparator();
		
		JButton btnColocarProhibido = new JButton("Prohibido");
		btnColocarProhibido.setVerticalAlignment(SwingConstants.TOP);
		btnColocarProhibido.setBackground(Color.RED);
		btnColocarProhibido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnProhibido(true);
			}
			
		});
		JButton btnColocarWayPoint = new JButton("WayPoint");
		btnColocarWayPoint.setVerticalAlignment(SwingConstants.TOP);
		btnColocarWayPoint.setBackground(Color.YELLOW);
		btnColocarWayPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnWayPoint(true);
			}
			
		});
		JButton btnColocarPeligroso = new JButton("Peligroso");
		btnColocarPeligroso.setVerticalAlignment(SwingConstants.TOP);
		btnColocarPeligroso.setBackground(Color.ORANGE);
		btnColocarPeligroso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnPeligroso(true);
			}
		});
		
		JButton btnColocarNormal = new JButton("");
		btnColocarNormal.setVerticalAlignment(SwingConstants.TOP);
		btnColocarNormal.setBackground(Color.WHITE);
		btnColocarNormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnLibre(true);
			}
			
		});
	
		JButton btnColocarInicio = new JButton("Inicio");
		btnColocarInicio.setVerticalAlignment(SwingConstants.TOP);
		btnColocarInicio.setBackground(Color.BLUE);
		btnColocarInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Controlador.getInstance().getTablero().getInicio() == null){
					Controlador.getInstance().setBtnInicio(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Si quieres cambiar el inicio elimínalo y colócalo de nuevo");

				}
			}
			
		});
		JButton btnColocarMeta = new JButton("Meta");
		btnColocarMeta.setVerticalAlignment(SwingConstants.TOP);
		btnColocarMeta.setBackground(Color.BLUE.darker());
		btnColocarMeta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Controlador.getInstance().getTablero().getMeta() == null){
					Controlador.getInstance().setBtnMeta(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Si quieres cambiar la meta elimínala y colócala de nuevo");
					}
				}			
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(136)
							.addComponent(lblTablero))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblColumnas, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblfilas, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textNumColumnas, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(textNumFilas, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnGenerarTab))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(72)
								.addComponent(lblElementos, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnComenzar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(53)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblPeligroso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnColocarPeligroso, 0, 0, Short.MAX_VALUE)
											.addComponent(btnColocarProhibido, 0, 0, Short.MAX_VALUE)
											.addComponent(lblObstaculo, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
											.addComponent(lblInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnColocarInicio, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnColocarMeta, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblMeta, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
											.addComponent(lblWayPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNormal, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
											.addComponent(btnColocarWayPoint, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnColocarNormal, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
										.addGap(65))
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTablero)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblfilas, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addComponent(textNumFilas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblColumnas, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textNumColumnas, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(btnGenerarTab)))
					.addGap(19)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblElementos, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnColocarInicio, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnColocarMeta, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInicio)
						.addComponent(lblMeta, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnColocarProhibido, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblObstaculo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnColocarWayPoint, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblWayPoint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColocarPeligroso, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnColocarNormal, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeligroso)
						.addComponent(lblNormal, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnComenzar)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		setLayout(groupLayout);
	}
}
