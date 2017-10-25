package hilos;

import mundo.SistemaLineal;

public class HiloMultiplicacion implements Runnable {

	private SistemaLineal sistemaLineal;
	private double[] ar1;
	private double[] ar2;
	private int fila;
	public HiloMultiplicacion(SistemaLineal sl,double[] ar1, double[] ar2,int fila){
		sistemaLineal=sl;
		this.ar1=ar1;
		this.ar2=ar2;
		this.fila=fila;
	}
	public void run() {
		sistemaLineal.aumNumHilos();
		double sum=0;
		for (int i = 0; i < ar1.length; i++) {
			sum+=ar1[i]*ar2[i];
		}
		sistemaLineal.cambiarResX(fila,sum);
		sistemaLineal.redNumHilos();
	}
}