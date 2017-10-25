package hilos;

import java.util.Random;

import mundo.SistemaLineal;

public class HiloMultiplicacion implements Runnable {

	private SistemaLineal sistema;
	int fila;
	int columna;
	
	public HiloMultiplicacion(SistemaLineal s,int fila, int columna){
		sistema = s;
		this.fila=fila;
		this.columna=columna;
	}


	public void run() {

		double[][] matrizInversa=sistema.darMatrizCoeficientes2();
		double[][] matrizB=sistema.darMatrizB();
		sistema.modificarHilosEnEjecucion(1);
		double multiplicacion=0;
		for(int i =0;i<matrizInversa[0].length;i++){
			multiplicacion+=matrizInversa[fila][i]*matrizB[i][columna];
		}
		sistema.modificarHilosEnEjecucion(-1);
		sistema.modificarValorMatrizProducto(fila,columna, multiplicacion);
		
	}
	
	
}
