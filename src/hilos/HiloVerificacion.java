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
		
	}
}