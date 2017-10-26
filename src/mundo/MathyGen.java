package mundo;

import java.util.ArrayList;

public class MathyGen {

	public final static int ANCHOPLANO =625;
	public final static int LARGOPLANO =625;
	private Punto primerPunto;
	private Funcion raizFuncion;
	private ArrayList<Region> listaRegiones;
	private SistemaLineal sistemaLineal;
	
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
}