package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo extends JPanel {
	ImageIcon imagen;
	
	public Fondo(String nombre) {
		super();
		initialize();
		imagen = new ImageIcon(getClass().getResource(nombre));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());
	}
	
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(imagen.getImage(),0,0,d.width,d.height,null);
		this.setOpaque(false);
		super.paintComponent(g);
	}
	
	private void initialize() {
		this.setSize(1200, 800);
	}
}
