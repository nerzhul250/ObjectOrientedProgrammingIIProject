package mundo;

import java.awt.Color;

public abstract class CurvaParametrica implements Parametrizable, Dibujable {
	/**
	 * Nombre de la curva
	 */
	private String nombre;
	/**
	 * Color de la curva
	 */
	private Color color;
	/**
	 * Centro de la curva en x
	 */
	private Double centroX;
	/**
	 * Centro de la curva en Y
	 */
	private Double centroY;
	/**
	 * Devuelve el nombre de la curva
	 * @return
	 */
	public String darNombre() {
		return nombre;
	}
	/**
	 * Modifica el nombre de la curva
	 * @param nombre
	 */
	public void modificarNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Devuelve el color de la curva
	 * @return
	 */
	public Color darColor() {
		return color;
	}
	/**
	 * Modifica el color de la curva
	 * @param color
	 */
	public void modificarColor(Color color) {
		this.color = color;
	}
	/**
	 * Devuelve el centro en x
	 * @return
	 */
	public Double darCentroX() {
		return centroX;
	}
	/**
	 * Modifica el centro en x
	 * @param centroX
	 */
	public void modificarCentroX(Double centroX) {
		this.centroX = centroX;
	}
	/**
	 * Devuelve el centro en Y
	 * @return
	 */
	public Double darCentroY() {
		return centroY;
	}
	/**
	 * Modifica el centro en x
	 * @param centroY
	 */
	public void modificarCentroY(Double centroY) {
		this.centroY = centroY;
	}
	
}
