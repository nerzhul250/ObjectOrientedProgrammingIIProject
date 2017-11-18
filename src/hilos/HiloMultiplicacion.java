package hilos;

import java.util.Random;

import mundo.SistemaLineal;

public class HiloMultiplicacion implements Runnable {
	/**
	 * Sistema lineal actual
	 */
	private SistemaLineal sistema;
	/**
	 * Fila donde el hilo terminará de multiplicar
	 */
	int fila;
	/**
	 * Es la fila en donde el hilo empezará a multiplicar
	 */
	int inicioFila;
	/**
	 * Crea un nuevo hilo
	 * @param s sistema lineal
	 * @param filaMax fila maxima donde se multiplicará
	 * @param inicioF fila inicio donde se empezará a multiplicar
	 */
	public HiloMultiplicacion(SistemaLineal s,int filaMax,int inicioF){
		sistema = s;
		inicioFila=inicioF;
		this.fila=filaMax;
	}
	/**
	 * Método que se ejecuta al ejecutar el hilo
	 */
	public void run() {
		double[][] matrizCoeficientes1=sistema.darMatrizCoeficientes1();
		double[][] matrizCoeficientes2=sistema.darMatrizCoeficientes2();
		double multiplicacion=0;
		for(int i =inicioFila; i<fila;i++){
			for(int k =0;k<matrizCoeficientes1.length;k++){
				multiplicacion=0;
				for(int j=0;j<matrizCoeficientes1.length;j++){
					multiplicacion+=matrizCoeficientes1[i][j]*matrizCoeficientes2[j][k];
				}
				sistema.modificarValorMatrizProducto(i,k, multiplicacion);
			}
		}
	}
	
	
}
