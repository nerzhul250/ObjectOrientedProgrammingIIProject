package hilos;

import java.util.Random;

import mundo.SistemaLineal;

public class HiloMultiplicacion implements Runnable {

	private SistemaLineal sistema;
	int fila;
	int inicioFila;
	
	public HiloMultiplicacion(SistemaLineal s,int filaMax,int inicioF){
		sistema = s;
		inicioFila=inicioF;
		this.fila=filaMax;
	}

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
