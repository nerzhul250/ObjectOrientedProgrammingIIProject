package mundo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SistemaLineal implements Serializable{

	private double[][] matrizCoeficientes1;
	private double[] matrizB;
	private double[][] matrizProducto;
	private double[][] matrizCoeficientes2;
	private String nombreSistema;
	private SistemaLineal anterior;
	private SistemaLineal siguiente;
	
	public SistemaLineal darAnterior() {
		return anterior;
	}
	public void modificarAnterior(SistemaLineal anterior) {
		this.anterior = anterior;
	}
	public SistemaLineal darSiguiente() {
		return siguiente;
	}
	public void modificarSiguiente(SistemaLineal siguiente) {
		this.siguiente = siguiente;
	}
	public void modificarNombre(String n){
		nombreSistema= n;
	}
	public String darNombre(){
		return nombreSistema;
	}

	private int filaEjecutada;
	private int columnaEjecutada;
	private double[] solucionSistema;
	
	public SistemaLineal(double[][] matrizCoeficientes1,double[][] matrizCoeficientes2){
		if(matrizCoeficientes2!= null)
		matrizProducto=new double[matrizCoeficientes1.length][matrizCoeficientes2[0].length];
		this.matrizCoeficientes1=matrizCoeficientes1;
		this.matrizCoeficientes2=matrizCoeficientes2;
		solucionSistema=new double[matrizCoeficientes1[0].length];
		//TODO
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
	public void determinarSolucionesSistema() throws MatrizNoInvertibleException{
		if(calcularDeterminante(matrizCoeficientes1)!=0){
			
			double determinanteOriginal = calcularDeterminante(matrizCoeficientes1);
			double[][] matrizCrammer=new double[matrizCoeficientes1.length][matrizCoeficientes1.length];
			iniciarSolucionSistema(matrizCoeficientes1.length);
			for(int i =0; i<matrizCoeficientes1.length;i++){
				for (int j = 0; j < matrizCrammer.length; j++) {
					matrizCrammer[j]=matrizCoeficientes1[j].clone();
				}
				for(int j=0;j<matrizCoeficientes1.length;j++){
					matrizCrammer[j][i]=matrizB[j];
				}
				double solucion= calcularDeterminante(matrizCrammer)/determinanteOriginal;
				solucionSistema[i]=solucion;
			}
		}else{
			throw new MatrizNoInvertibleException("La matriz no es invertible, la aplicación solo es capaz de resolver sistemas cuadrados e invertibles");
		}
		
	}
	
	public void modificarFilaEjecutada(int hilos) {
		this.filaEjecutada = hilos;
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
//				int fila=0;
//				int columna=0;
//				for(int j=0;j<matriz.length;j++){
//					for(int k=0;k<matriz.length;k++){
//						if(j>0&&(k<i||k>i)){
//							if(columna==nuevaMatriz.length){
//								columna=0;
//								fila++;
//							}
//							nuevaMatriz[fila][columna]=matriz[j][k];
//							columna++;
//						}
//					}
//				}
				calcularMatrizFaltante(matriz, nuevaMatriz, 0, i, 0, 0, 0, 0, 0);
				
				determinante+=Math.pow(-1, i)*matriz[0][i]*calcularDeterminante(nuevaMatriz);
			}
		}
		return determinante;
	}
	
	public void calcularMatrizFaltante(double[][] matriz,double[][] nuevaMatriz, int indiceFila,int indiceColumna,int filaActual,int columActual,int
			filaMatriz,int columMatriz, int cont){
		if(columActual>=matriz[0].length){
			columActual=0;
			filaActual++;
		}
		if(columMatriz>=nuevaMatriz[0].length){
			columMatriz=0;
			filaMatriz++;
			System.out.println("hecho");
		}
		if(cont>=nuevaMatriz.length*nuevaMatriz[0].length){
		}else if(indiceFila<filaActual&&(columActual<indiceColumna||columActual>indiceColumna)){
			nuevaMatriz[filaMatriz][columMatriz]=matriz[filaActual][columActual];
			columActual++;
			columMatriz++;
			cont++;
			System.out.println(columMatriz);
			calcularMatrizFaltante(matriz, nuevaMatriz, indiceFila, indiceColumna, filaActual, columActual, filaMatriz, columMatriz, cont);
		}else{
			columActual++;
			calcularMatrizFaltante(matriz, nuevaMatriz, indiceFila, indiceColumna, filaActual, columActual, filaMatriz, columMatriz, cont);
		}
		
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

	public double[] darMatrizB() {
		return matrizB;
	}

	public void modificarMatrizB(double[] matrizB) {
		this.matrizB = matrizB;
	}

	public void modificarValorMatrizProducto(int fila,int columna,double valor){
		matrizProducto[fila][columna]=valor;
	}

	public void iniciarMatrizProducto(int filas,int columnas) {
		matrizProducto=new double[filas][columnas];
	}
	public double[][] darMatrizProducto(){
		return matrizProducto;
	}

	public double[][] darMatrizCoeficientes2() {
		return matrizCoeficientes2;
	}

	public void modificarMatrizCoeficientes2(double[][] matrizInversa) {
		this.matrizCoeficientes2 = matrizInversa;
	}
	
}
