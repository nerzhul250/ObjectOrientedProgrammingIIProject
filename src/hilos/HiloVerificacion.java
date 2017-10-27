package hilos;

import interfaz.InterfazMathy;
import mundo.SistemaLineal;

public class HiloVerificacion implements Runnable {

	private SistemaLineal sistemaLineal;
	private InterfazMathy principal;
	public HiloVerificacion(InterfazMathy im, SistemaLineal sl){
		sistemaLineal=sl;
		principal=im;
	}
	public void run() {
		long tiempo=System.currentTimeMillis();
		while(sistemaLineal.darHilosEnEjecucion()!=0){
			System.out.println(sistemaLineal.darHilosEnEjecucion());
		}
		tiempo= System.currentTimeMillis()-tiempo;
		System.out.println(tiempo);
		principal.mostrarMatrizProducto(sistemaLineal.darMatrizProducto());
	}
}