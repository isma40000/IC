package vista;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Auxiliar {
	private static Auxiliar instance = null;

	public Auxiliar() {
		super();
	}
	public static Auxiliar getInstance() {
		if(instance == null){
			instance = new Auxiliar();
		}
		return instance;
	}
	public static void ajustarImagen(JButton btn, Image img) {
		Dimension size = btn.getSize();
		Insets i = btn.getInsets();
		size.width -= i.left + i.right;
		size.height -= i.top + i.bottom;
		if (size.width > size.height)
			size.width = -1;
		else
			size.height = -1;
		Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
		btn.setIcon(new ImageIcon(scaled));
	}
}
