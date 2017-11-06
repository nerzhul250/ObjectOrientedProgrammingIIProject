package mundo;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MathyGen {
	
	public final static String RUTA_REGION_GUARDADA="./data/EstadoGraficadora/listaRegion.mat";
	public final static String RUTA_FUNCION_GUARDADA="./data/EstadoGraficadora/funcionRaiz.mat";
	public final static String RUTA_PUNTOS_GUARDADA="./data/EstadoGraficadora/puntos.txt";
	public final static String RUTA_MATRIZ_GIGANTE_1="./data/matricesGigantes/MATRIZ11.txt";
	public final static String RUTA_MATRIZ_GIGANTE_2="./data/matricesGigantes/MATRIZ2.txt";

	public final static int ANCHOPLANO =625;
	public final static int LARGOPLANO =625;
	
	public static final int TRIGONOMETRICO = 1;
	public final static int TIPOPOLINOMIO=3;

	public long tiempo;
	private Punto primerPunto;
	private Funcion raizFuncion;
	private ArrayList<Region> listaRegiones;
	private SistemaLineal sistemaLineal;
	private ArrayList<Dibujable>objetosDibujables;
	private Circunferencia circulo;

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
		objetosDibujables=new ArrayList<Dibujable>();
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
	public double[][] darMatrizProducto() {
		return sistemaLineal.darMatrizProducto();
	}
	public Punto getPrimerPunto() {
		return primerPunto;
	}
	public Funcion getRaizFuncion() {
		return raizFuncion;
	}
	public ArrayList<Region> getListaRegiones() {
		return listaRegiones;
	}
	public Circunferencia darCirculo() {
		return circulo;
	}
	public void modificarCirculo(Circunferencia circulo) {
		this.circulo = circulo;
	}
	public ArrayList<Dibujable> darObjetosDibujables() {
		return objetosDibujables;
	}
	public Funcion agregarFuncion(String form, Color color, int grosor, int tipo) throws FuncionYaExisteException{
		Funcion fun=null;
		switch (tipo) {
		case 1:
			fun=new Trigonometrico(form);
			break;
		case 3:
			fun=new Polinomio(form);
			break;
		}
		fun.setColor(color);
		fun.setGrosor(grosor);
		fun.setForma(form);
		if(estaEnElArbol(fun,raizFuncion)) throw new FuncionYaExisteException(fun.getForma());
		agregarFuncionAlArbol(fun,raizFuncion);
		return fun;
	}
	public boolean estaEnElArbol(Funcion f,Funcion actual){
		if(raizFuncion==null){
			return false;
		}else if(actual!=null && actual.compareTo(f)==0){
			return true;
		}else if(actual!=null){
			return estaEnElArbol(f,actual.getFunDe()) || estaEnElArbol(f,actual.getFunIz());
		}
		return false;
	}
	public void agregarFuncionAlArbol(Funcion f,Funcion actual) throws FuncionYaExisteException{
		if(raizFuncion==null){
			raizFuncion=f;
		}else{
			if(f.compareTo(actual)==-1){
				if(actual.getFunIz()==null){
					actual.setFunIz(f);
				}else{
					agregarFuncionAlArbol(f,actual.getFunIz());
				}
			}else if(f.compareTo(actual)==1){
				if(actual.getFunDe()==null){
					actual.setFunDe(f);
				}else{
					agregarFuncionAlArbol(f,actual.getFunDe());
				}
			}
		}
	}
	public Punto agregarPunto(double x, double y) {
		Punto p=new Punto(x,y);
		if(primerPunto==null){
			primerPunto=p;
		}else{
			p.setSgtPunto(primerPunto);
			primerPunto=p;
		}
		agregarObjetoDibujable(p);
		return p;
	}
	public Region agregarRegion(ArrayList<Punto> frontera, Color color) {
		Region r=new Region(frontera, color);
		listaRegiones.add(r);
		return r;
	}
	public void agregarObjetoDibujable(Dibujable d){
		if(!objetosDibujables.contains(d)){
			objetosDibujables.add(d);
		}
	}
	public void eliminarObjetoDibujable(Dibujable d){
		if(objetosDibujables.contains(d)){
			objetosDibujables.remove(d);
		}
	}
	public boolean[] cargarEstado() throws Exception{
		boolean[] res=new boolean[3];
		File f=new File(MathyGen.RUTA_PUNTOS_GUARDADA);
		if(f.exists()){
			BufferedReader br= new BufferedReader(new FileReader(f));
			String a=br.readLine();
			while(a!=null && !a.isEmpty()){
				String[] b=a.split("\\s+");
				double x=Double.parseDouble(b[0]);
				double y=Double.parseDouble(b[1]);
				agregarPunto(x, y);
				a=br.readLine();
			}
			res[0]=true;
			br.close();
		}else{
			primerPunto=null;
		}
		f=new File(MathyGen.RUTA_FUNCION_GUARDADA);
		if(f.exists()){
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(MathyGen.RUTA_FUNCION_GUARDADA));
			raizFuncion=(Funcion)ois.readObject();
			res[1]=true;
		}else{
			raizFuncion=null;
		}
		f=new File(MathyGen.RUTA_REGION_GUARDADA);
		if(f.exists()){
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(MathyGen.RUTA_REGION_GUARDADA));
			listaRegiones=(ArrayList<Region>)ois.readObject();
			res[2]=true;
		}else{
			listaRegiones=new ArrayList<Region>();
		}
		return res;
	}
	public void guardarEstado() throws IOException{
		if(primerPunto!=null){
			BufferedWriter bw=new BufferedWriter(new FileWriter(MathyGen.RUTA_PUNTOS_GUARDADA));
			Punto current=primerPunto;
			while(current!=null){
				bw.write(current.getX()+" "+current.getY()+"\n");
				current=current.getSgtPunto();
			}
			bw.close();
		}
		ObjectOutputStream oos=null;
		if(raizFuncion!=null){
			oos=new ObjectOutputStream(new FileOutputStream(MathyGen.RUTA_FUNCION_GUARDADA));
			oos.writeObject(raizFuncion);
			oos.close();
		}
		if(listaRegiones.size()!=0){
			oos=new ObjectOutputStream(new FileOutputStream(MathyGen.RUTA_REGION_GUARDADA));
			oos.writeObject(listaRegiones);
			oos.close();
		}
	}

	public void organizarRegiones() {
		for (int i = 0; i < listaRegiones.size()-1; i++) {
			Region r=listaRegiones.get(i);
			int cual=i;
			for (int j = i+1; j < listaRegiones.size(); j++) {
				if(listaRegiones.get(j).compareTo(r)<0){
					r=listaRegiones.get(j);
					cual=j;
				}
			}
			Region ra=listaRegiones.get(i);
			listaRegiones.set(i,r);
			listaRegiones.set(cual,ra);
		}
	}
}