package algorithm;

import java.util.List;
import atributos.*;

public class ID3 {

	private List<Atributos> lAtributos;
	private List<Ejemplo> lEjemplos;
	
	public ID3() {
		
	}
	public Object hacerID3(List<Atributos> lAtributos,List<Ejemplo> lEjemplos) {
		if(lEjemplos.isEmpty()) {
			return null;
		}
		else {
			if(comprobarPositivos(lEjemplos)) return "Si";
			if(comprobarNegativos(lEjemplos)) return "No";
			if(lAtributos.isEmpty()) return "ERROR"; //Lanzar excepción??
			
			
		}
		return null;
	}
	public boolean comprobarPositivos(List<Ejemplo> lEjemplos){
		int i= 0;
		while(i < lEjemplos.size()) {
			if(!lEjemplos.get(i).isJugar()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean comprobarNegativos(List<Ejemplo> lEjemplos){
		int i= 0;
		while(i < lEjemplos.size()) {
			if(lEjemplos.get(i).isJugar()) {
				return false;
			}
		}
		return true;
	}
	
	public Atributos merito(List<Atributos> lAtributos,List<Ejemplo> lEjemplos) {
		
		return Atributos.Humedad;
	}
}
