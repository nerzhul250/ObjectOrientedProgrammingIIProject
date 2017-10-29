package mundo;

import java.awt.Color;

public abstract class CurvaParametrica implements Parametrizable, Dibujable {
	
	private String nombre;
	private Color color;
	private Double centroX;
	private Double centroY;
	public String darNombre() {
		return nombre;
	}
	public void modificarNombre(String nombre) {
		this.nombre = nombre;
	}
	public Color darColor() {
		return color;
	}
	public void modificarColor(Color color) {
		this.color = color;
	}
	public Double darCentroX() {
		return centroX;
	}
	public void modificarCentroX(Double centroX) {
		this.centroX = centroX;
	}
	public Double darCentroY() {
		return centroY;
	}
	public void modificarCentroY(Double centroY) {
		this.centroY = centroY;
	}
	
}
