package algorithm;

import atributos.*;


public class Ejemplo {
	
	private Humedad humedad;
	private Temperatura temperatura;
	private TiempoExterior tExterior;
	private Viento viento;
	private boolean jugar;
	
	public Ejemplo(Humedad humedad,Temperatura temperatura,TiempoExterior tExterior,Viento viento,boolean jugar) {
		this.humedad = humedad;
		this.temperatura = temperatura;
		this.tExterior = tExterior;
		this.viento = viento;
		this.jugar = jugar;
	}

	public Humedad getHumedad() {
		return humedad;
	}

	public void setHumedad(Humedad humedad) {
		this.humedad = humedad;
	}

	public Temperatura getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Temperatura temperatura) {
		this.temperatura = temperatura;
	}

	public TiempoExterior gettExterior() {
		return tExterior;
	}

	public void settExterior(TiempoExterior tExterior) {
		this.tExterior = tExterior;
	}

	public Viento getViento() {
		return viento;
	}

	public void setViento(Viento viento) {
		this.viento = viento;
	}

	public boolean isJugar() {
		return jugar;
	}

	public void setJugar(boolean jugar) {
		this.jugar = jugar;
	}
	
	
	
}
