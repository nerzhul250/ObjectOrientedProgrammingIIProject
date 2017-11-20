package hilos;

import interfaz.InterfazMathy;
import mundo.Animable;
import mundo.Dibujable;
import mundo.MathyGen;

public class HiloAnimar implements Runnable {

	/**
	 * Interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Objeto animable
	 */
	private Animable d;
	/**
	 * es el mundo
	 */
	private MathyGen m;
	/**
	 * Crea un nuevo hilo animable
	 * @param p interfaz principal
	 * @param de objeto animable
	 * @param m clase principal del mundo
	 */
	public HiloAnimar(InterfazMathy p, Animable de, MathyGen m){
		principal=p;
		d=de;
		this.m= m;
	}
	/**
	 * Método run
	 */
	public void run() {
		m.modificarEnAnimacion(true);
		principal.animacion(d);
		m.modificarEnAnimacion(false);
	}

}
