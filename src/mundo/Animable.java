package mundo;

import java.awt.Graphics2D;

public interface Animable {

	/**
	 * M�todo encargado de animar el dibujo
	 * @param g2d graphics en donde se va a animar
	 * @param alcance alcance del panel
	 * @param traslY traslaci�n del centro en y
	 * @param traslX translaci�n del centro en x
	 * @param ancho ancho de la curva
	 */
	void animarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho);
}
