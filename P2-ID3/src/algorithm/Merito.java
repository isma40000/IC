package algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atributos.*;

public class Merito {
	public Map dicc;
	public Merito (Atributos atributo, List<Ejemplo> lEjemplos) {
		switch(atributo) {
			case Humedad: 
				dicc = new HashMap<Humedad,CPNs>();
				for(Humedad a : Humedad.values()) {
					dicc.put(a, new CPNs());
				}
				break;
			case Temperatura: 
				for(Temperatura a : Temperatura.values()) {
					
				}
				break;
			case TiempoExterior: 
				for(TiempoExterior a : TiempoExterior.values()) {
					
				}
				break;
			case Viento: 
				for(Viento a : Viento.values()) {
					
				}
				break;
		}
	}
	private class CPNs{
		private int total;
		private int p;
		private int n;
		public CPNs() {}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public int getP() {
			return p;
		}
		public void setP(int p) {
			this.p = p;
		}
		public int getN() {
			return n;
		}
		public void setN(int n) {
			this.n = n;
		}
	}
}