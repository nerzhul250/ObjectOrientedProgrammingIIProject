package mundo;

import interfaz.InterfazMathy;
import hilos.HiloMultiplicacion;
import hilos.HiloVerificacion;

public class SistemaLineal {

	private double[][] matrizCoeficientes;
	private double[] matrizB;
	private double[] matrizX;
	private InterfazMathy principal;
	private int hilos;
	public SistemaLineal(double[][] mc, double[] mb,InterfazMathy p) {
		matrizCoeficientes=mc;
		matrizB=mb;
		principal=p;
	}
	public void resolver(){
		double[][] invers=calcularInversa();
		for (int i = 0; i < invers.length; i++) {
			new Thread(new HiloMultiplicacion(this,invers[i],matrizB,i)).start();
		}
		new Thread(new HiloVerificacion(principal,this)).start();
	}
	public double[][] calcularInversa() {
		// TODO - implement SistemaLineal.calcularInversa
		throw new UnsupportedOperationException();
	}

	public double calcularDeterminante() {
		// TODO - implement SistemaLineal.calcularDeterminante
		throw new UnsupportedOperationException();
	}
	public int darNumHilos(){
		return hilos;
	}
	public void redNumHilos(){
		hilos--;
	}
	public void aumNumHilos(){
		hilos++;
	}

	public void cambiarResX(int fila, double sum) {
		matrizX[fila]=sum;
	}
	public double[] darSolucion() {
		return matrizX;
	}
}