package mundo;

import java.math.BigDecimal;

public class SistemaLineal {

	private double[][] matrizCoeficientes1;
	private double[][] matrizB;
	private double[][] matrizProducto;
	private double[][] matrizCoeficientes2;
	
	private int filaEjecutada;
	private int columnaEjecutada;
	private int hilosEnEjecucion;
	private double[] solucionSistema;
	
	public SistemaLineal(double[][] matrizCoeficientes1,double[][] matrizCoeficientes2){
		if(matrizCoeficientes2!= null)
		matrizProducto=new double[matrizCoeficientes1.length][matrizCoeficientes2[0].length];
		this.matrizCoeficientes1=matrizCoeficientes1;
		this.matrizCoeficientes2=matrizCoeficientes2;
		solucionSistema=new double[matrizCoeficientes1[0].length];
	}
	public int darFilaEjecutada() {
		filaEjecutada=columnaEjecutada/matrizB.length;
		return filaEjecutada;
	}
	
	public int[] darFilaYColumnaEjecutada(){
		int[] devolver={darColumnaEjecutada(),darFilaEjecutada()};
		columnaEjecutada++;
		return devolver;
	}
	
	public void iniciarSolucionSistema(int tamano){
		solucionSistema=new double[tamano];
	}
	public void asignarValorSolucionSistema(int pos, double valor){
		solucionSistema[pos]=valor;
	}
	public double[] darSolucionSistema(){
		return solucionSistema;
	}
	public void determinarSolucionesSistema(){
		double determinanteOriginal = calcularDeterminante(matrizCoeficientes1);
		double[][] matrizCrammer=new double[matrizCoeficientes1.length][matrizCoeficientes1.length];
		iniciarSolucionSistema(matrizCoeficientes1.length);
		for(int i =0; i<matrizCoeficientes1.length;i++){
			for (int j = 0; j < matrizCrammer.length; j++) {
				matrizCrammer[j]=matrizCoeficientes1[j].clone();
			}
			for(int j=0;j<matrizCoeficientes1.length;j++){
				matrizCrammer[j][i]=matrizB[j][0];
			}
			double solucion= calcularDeterminante(matrizCrammer)/determinanteOriginal;
			solucionSistema[i]=solucion;
		}
	}
	
	public void modificarFilaEjecutada(int hilos) {
		this.filaEjecutada = hilos;
	}
	public void modificarHilosEnEjecucion(int hilos) {
		this.hilosEnEjecucion += hilos;
	}
	public int darHilosEnEjecucion() {
		return hilosEnEjecucion;
	}
	//TODO
	public double calcularDeterminante(double[][] matriz){
		double determinante=0;
		for(int i=0;i<matriz.length;i++){
			if(matriz.length==2){
				determinante+=matriz[0][0]*matriz[1][1]-matriz[0][1]*matriz[1][0];
				i=matriz.length;
			}else{
				double[][] nuevaMatriz=new double[matriz.length-1][matriz.length-1];
				int fila=0;
				int columna=0;
				for(int j=0;j<matriz.length;j++){
					for(int k=0;k<matriz.length;k++){
						if(j>0&&(k<i||k>i)){
							if(columna==nuevaMatriz.length){
								columna=0;
								fila++;
							}
							nuevaMatriz[fila][columna]=matriz[j][k];
							columna++;
						}
					}
				}
				determinante+=Math.pow(-1, i)*matriz[0][i]*calcularDeterminante(nuevaMatriz);
			}
		}
		return determinante;
	}

	public double[][] darMatrizCoeficientes1() {
		return matrizCoeficientes1;
	}

	public void modificarMatrizCoeficientes1(double[][] matrizCoeficientes) {
		this.matrizCoeficientes1 = matrizCoeficientes;
	}
	public int darColumnaEjecutada(){
		return columnaEjecutada%matrizB.length;
		
	}
	
	public void modificarColumnaEjecutada(int valor){
		columnaEjecutada+=valor;
		
	}

	public double[][] darMatrizB() {
		return matrizB;
	}

	public void modificarMatrizB(double[][] matrizB) {
		this.matrizB = matrizB;
	}

	public void modificarValorMatrizProducto(int fila,int columna,double valor){
		matrizProducto[fila][columna]=valor;
	}

	public void iniciarMatrizProducto(int filas,int columnas) {
		matrizProducto=new double[filas][columnas];
	}
	public double[][] darMatrizX(){
		return matrizProducto;
	}

	public double[][] darMatrizCoeficientes2() {
		return matrizCoeficientes2;
	}

	public void modificarMatrizCoeficientes2(double[][] matrizInversa) {
		this.matrizCoeficientes2 = matrizInversa;
	}
	
}
