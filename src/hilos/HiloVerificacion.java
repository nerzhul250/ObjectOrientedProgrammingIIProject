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
		principal.desplegarMensaje("El tiempo de multiplicación de matrices es de:\n"+(tiempo/1000)+" segundos"+"\n"+"Espera a que se despliegue el resultado");
		principal.mostrarMatrizProducto(sistemaLineal.darMatrizProducto());
	}
}