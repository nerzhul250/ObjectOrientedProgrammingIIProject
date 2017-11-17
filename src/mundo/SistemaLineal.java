package mundo;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @author steve
 *
 */
public class SistemaLineal implements Serializable{
	/**
	 * La matriz que representa la matriz 1 de coeficientes del sistema
	 */
	private double[][] matrizCoeficientes1;
	/**
	 * Es la matriz b del sistema matricial 1
	 */
	private double[] matrizB;
	/**
	 * Es la matriz que resulta del producto entre las 2 matrices del sistema
	 */
	private double[][] matrizProducto;
	/**
	 * Matriz 2 de coeficientes
	 */
	private double[][] matrizCoeficientes2;
	/**
	 * Es el nombre que se pondrá al sistema al ser guardado en el historial
	 */
	private String nombreSistema;
	/**
	 * Es el anterior sistema lineal en la lista doblemente enlazada
	 */
	private SistemaLineal anterior;
	/**
	 * Es el siguiente sistema lineal en la lista doblemente enlazada
	 */
	private SistemaLineal siguiente;
	/**
	 * Es la solución de la primer matriz de coeficientes en caso de que exista
	 */
	private double[] solucionSistema;
	/**
	 * Construye un nuevo sistema lineal
	 * @param matrizCoeficientes1 Es la matriz de coeficientes 1. !=null
	 * @param matrizCoeficientes2 es la matriz de coeficientes 2.
	 */
	public SistemaLineal(double[][] matrizCoeficientes1,double[][] matrizCoeficientes2){
		if(matrizCoeficientes2!= null)
		matrizProducto=new double[matrizCoeficientes1.length][matrizCoeficientes2[0].length];
		this.matrizCoeficientes1=matrizCoeficientes1;
		this.matrizCoeficientes2=matrizCoeficientes2;
		solucionSistema=new double[matrizCoeficientes1[0].length];
		//TODO
	}
	/**
	 * Metodo que inicializa el vector solución del sistema
	 * @param tamano tamaño del vector
	 */
	public void iniciarSolucionSistema(int tamano){
		solucionSistema=new double[tamano];
	}
	/**
	 * Método encargado de asignar un valor al vector solución del sistema
	 * @param pos posición de la solución particular de un sistema lineal
	 * @param valor valor que representa un componente de la solución
	 */
	public void asignarValorSolucionSistema(int pos, double valor){
		solucionSistema[pos]=valor;
	}
	/**
	 * Devuelve la solución del sistema
	 * @return
	 */
	public double[] darSolucionSistema(){
		return solucionSistema;
	}
	/**
	 * Determina la solución de un sistema matricial 
	 * @throws MatrizNoInvertibleException en caso de que la matriz no sea invertible
	 */
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
	
	/**
	 * Calcula el determinante de la matriz 1
	 * @param matriz matriz a la que se le va a calcular el determinante
	 * @return determinante de la matriz 1
	 */
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
	/**
	 * Método auxiliar que simula un ciclo en donde se crea una nueva matriz
	 * para realizar el cálculo de matrices por medio de cofactores
	 * @param matriz
	 * @param nuevaMatriz
	 * @param indiceFila
	 * @param indiceColumna
	 * @param filaActual
	 * @param columActual
	 * @param filaMatriz
	 * @param columMatriz
	 * @param cont
	 */
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

	/**
	 * Devuelve la matriz de coeficientes 1
	 * @return
	 */
	public double[][] darMatrizCoeficientes1() {
		return matrizCoeficientes1;
	}

	/**
	 * asigna una nueva matriz a la matriz 1 de coeficientes
	 * @param matrizCoeficientes
	 */
	public void modificarMatrizCoeficientes1(double[][] matrizCoeficientes) {
		this.matrizCoeficientes1 = matrizCoeficientes;
	}

	/**
	 * devuelve el vector b asociado al una amtriz
	 * @return
	 */
	public double[] darMatrizB() {
		return matrizB;
	}
	/**
	 * asigna un nuevo vector b asociado a una matriz
	 * @param matrizB
	 */
	public void modificarMatrizB(double[] matrizB) {
		this.matrizB = matrizB;
	}
	/**
	 * Modifica un valor en la matriz resultante del producto de dos matrices
	 * @param fila fila en donde se insertara el valor
	 * @param columna columna en donde se insertara el valor
	 * @param valor valor a insertar
	 */
	public void modificarValorMatrizProducto(int fila,int columna,double valor){
		matrizProducto[fila][columna]=valor;
	}
	/**
	 * inicia la matriz producto del producto de las 2 matrices
	 * @param filas nro de filas
	 * @param columnas nro de columnas
	 */
	public void iniciarMatrizProducto(int filas,int columnas) {
		matrizProducto=new double[filas][columnas];
	}
	/**
	 * Da la matriz producto
	 * @return
	 */
	public double[][] darMatrizProducto(){
		return matrizProducto;
	}
	/**
	 * devuelve la matriz de coefcieitnes2
	 * @return
	 */
	public double[][] darMatrizCoeficientes2() {
		return matrizCoeficientes2;
	}
	/**
	 * Asigna una nueva matriz a la matriz 2 de coeficientess
	 * @param matrizInversa
	 */
	public void modificarMatrizCoeficientes2(double[][] matrizInversa) {
		this.matrizCoeficientes2 = matrizInversa;
	}
	/**
	 * Devuelve el sistema anterior a este
	 * @return
	 */
	public SistemaLineal darAnterior() {
		return anterior;
	}
	/**
	 * asigna un nuevo sistema anterior
	 * @param anterior
	 */
	public void modificarAnterior(SistemaLineal anterior) {
		this.anterior = anterior;
	}
	/**
	 * Devuelve el siguiente sistema lineal en la lista
	 * @return
	 */
	public SistemaLineal darSiguiente() {
		return siguiente;
	}
	/**
	 * asigna un nuevo sistema siguiente
	 * @param siguiente
	 */
	public void modificarSiguiente(SistemaLineal siguiente) {
		this.siguiente = siguiente;
	}
	/**
	 * Modifica el nombre del sistema lineal
	 * @param n
	 */
	public void modificarNombre(String n){
		nombreSistema= n;
	}
	/**
	 * Devuelve el nombre del sistema lineal
	 * @return
	 */
	public String darNombre(){
		return nombreSistema;
	}
	
}
