package mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MathyGen {

	public final static String RUTA_MATRIZ_GIGANTE_1="./data/matricesGigantes/matrizGigante1.txt";
	public final static String RUTA_MATRIZ_GIGANTE_2="./data/matricesGigantes/matrizGigante2.txt";

	public final static int ANCHOPLANO =625;
	public final static int LARGOPLANO =625;

	private Punto primerPunto;
	private Funcion raizFuncion;
	private ArrayList<Region> listaRegiones;
	private SistemaLineal sistemaLineal;
	public MathyGen(){
//		try {
//			crearMatrizGigante1();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			crearMatrizGigante2();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		double[][]m1=new double[1][1];
		sistemaLineal= new SistemaLineal(m1, null);
	}
	
	public SistemaLineal darSistemaLineal(){
		return sistemaLineal;
	}
	public void iniciarSistemaLineal(double[][] m1,double[][] m2){
		sistemaLineal= new SistemaLineal(m1, m2);
	}
	public double calcularDeterminanteMatriz1(){
		return sistemaLineal.calcularDeterminante(sistemaLineal.darMatrizCoeficientes1());
	}
	public String calcularSolucionesMatriz1() throws MatrizNoInvertibleException{
		String mensaje="";
		double[] soluciones=new double[sistemaLineal.darMatrizCoeficientes1()[0].length];
		sistemaLineal.determinarSolucionesSistema();
		soluciones=sistemaLineal.darSolucionSistema();
		for(int i =0;i<soluciones.length;i++){
			mensaje+= "X"+(i+1)+"= "+soluciones[i]+"\n";
		}
		return mensaje;
	}
	
	public void metodoPrueba(){
		double multiplicacion=0;
		double[][] matrizCoeficientes1= sistemaLineal.darMatrizCoeficientes1();
		double[][] matrizCoeficientes2= sistemaLineal.darMatrizCoeficientes2();
		for(int i =0; i<sistemaLineal.darMatrizCoeficientes1().length;i++){
			for(int k =0;k<sistemaLineal.darMatrizCoeficientes1().length;k++){
				multiplicacion=0;
				for(int j=0;j<sistemaLineal.darMatrizCoeficientes1().length;j++){
					multiplicacion+=matrizCoeficientes1[i][j]*matrizCoeficientes2[j][k];
				}
				
				sistemaLineal.modificarValorMatrizProducto(i,k, multiplicacion);
			}
		}
	}

	public Funcion agregarFuncion(String form, int t){
		Funcion fun=null;
		switch (t) {
		case 3:
			fun=new Polinomio(form);
			break;
		}
		agregarFuncionAlArbol(fun,raizFuncion);
		return fun;
	}
	
	public void agregarFuncionAlArbol(Funcion f,Funcion actual){
		if(actual==null){
			actual=f;
		}else{
			if(f.compareTo(actual)==-1){
				agregarFuncionAlArbol(f,actual.getFunIz());
			}else if(f.compareTo(actual)==1){
				agregarFuncionAlArbol(f,actual.getFunDe());
			}
		}
	}
	
	public void cargarMatricesGigantes() throws IOException{
		sistemaLineal=new SistemaLineal(cargarMatrizGigante1(), cargarMatrizGigante2());
	}
	public double[][] cargarMatrizGigante1() throws IOException{
		File cargar = new File(RUTA_MATRIZ_GIGANTE_1);
		double[][] matriz=null;
		if(cargar.exists()){
			BufferedReader lector = new BufferedReader(new FileReader(cargar));
			String[] tamano= lector.readLine().split(" ");
			matriz= new double[Integer.parseInt(tamano[0])][Integer.parseInt(tamano[1])];
			String lectura= lector.readLine();
			int fila=0;
			while(lectura != null && !lectura.equals("")){
				String[] numeros=lectura.split(" ");
				for(int i =0;i<matriz[0].length;i++){
					matriz[fila][i]=Integer.parseInt(numeros[i]);
				}
				fila++;
				lectura= lector.readLine();
			}
			lector.close();
		}
		return matriz;
	}
	public double[][] cargarMatrizGigante2() throws IOException{
		File cargar = new File(RUTA_MATRIZ_GIGANTE_2);
		double[][] matriz=null;
		if(cargar.exists()){
			BufferedReader lector = new BufferedReader(new FileReader(cargar));
			String[] tamano= lector.readLine().split(" ");
			matriz= new double[Integer.parseInt(tamano[0])][Integer.parseInt(tamano[1])];
			String lectura= lector.readLine();
			int fila=0;
			while(lectura != null && !lectura.equals("")){
				String[] numeros=lectura.split(" ");
				for(int i =0;i<matriz[0].length;i++){
					matriz[fila][i]=Integer.parseInt(numeros[i]);
				}
				fila++;
				lectura= lector.readLine();
			}
			sistemaLineal.modificarMatrizCoeficientes2(matriz);
			lector.close();
		}
		return matriz;
	}
	//TODO
	public void crearMatrizGigante1() throws FileNotFoundException{
		File aEscribir= new File(RUTA_MATRIZ_GIGANTE_1);
		PrintWriter log= new PrintWriter(aEscribir);
		log.println("1000 1000");
		int cont =0;
		for(int i =0;i<1000;i++){
			String escribir="";
			for(int j=0;j<1000;j++){
				escribir+=(int)(100*Math.random())+"";
				if(j<999){
					escribir+=" ";
					System.out.println(cont++);
				}
			}
			log.println(escribir);
		}
		log.close();
		System.out.println("terminado 1");
	}
	//TODO
	public void crearMatrizGigante2() throws FileNotFoundException{
		File aEscribir= new File(RUTA_MATRIZ_GIGANTE_2);
		PrintWriter log= new PrintWriter(aEscribir);
		log.println("1000 1000");
		int cont=0;
		for(int i =0;i<1000;i++){
			String escribir="";
			for(int j=0;j<1000;j++){
				escribir+=(int)(100*Math.random())+"";
				if(j<999){
					escribir+=" ";
				}
				System.out.println(cont++);
			}
			log.println(escribir);
		}
		log.close();
		System.out.println("terminado 2");
	}
}