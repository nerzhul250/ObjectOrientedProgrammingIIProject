package mundo;

import java.util.ArrayList;

public class MathyGen {

	public final static int ANCHOPLANO =600;
	public final static int LARGOPLANO =600;
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
}