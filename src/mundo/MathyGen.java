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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MathyGen {
	
	public final static String RUTA_REGION_GUARDADA="./data/EstadoGraficadora/listaRegion.mat";
	public final static String RUTA_FUNCION_GUARDADA="./data/EstadoGraficadora/funcionRaiz.mat";
	public final static String RUTA_PUNTOS_GUARDADA="./data/EstadoGraficadora/puntos.txt";
	public final static String RUTA_MATRIZ_GIGANTE_1="./data/matricesGigantes/MATRIZ11.txt";
	public final static String RUTA_MATRIZ_GIGANTE_2="./data/matricesGigantes/MATRIZ2.txt";

	public final static String RUTA_HISTORIAL_SISTEMAS_LINEALES="./data/historialSistema/historialSistema.txt";


	
	/**
	 * Ancho del planoXY
	 */
	public final static int ANCHOPLANO =625;
	/**
	 * Largo del planoXY
	 */
	public final static int LARGOPLANO =625;

	public static final int TRIGONOMETRICO = 1;
	public final static int TIPOPOLINOMIO=3;
	public final static int CIRCUNFERENCIA=2;
	public final static int ELIPSE=4;

	public long tiempo;
	/**
	 * El primer punto de la lista enlazada
	 */
	private Punto primerPunto;
	/**
	 * La raiz del arbol de funciones
	 */
	private Funcion raizFuncion;
	/**
	 * La lista de las regiones
	 */
	private ArrayList<Region> listaRegiones;
	private SistemaLineal sistemaLineal;
	/**
	 * Las lista de objetos dibujables
	 */
	private ArrayList<Dibujable>objetosDibujables;
	private SistemaLineal historialSistema;
	private ArrayList<CurvaParametrica> curvasParametricas;

	




	/**
	 * agrega un sistema lineal al historial
	 * @param s sistema a agregr
	 * @param nombre nombre del sistema lineal. Los nombres deben ser diferentes
	 * @return boolean fue agregado
	 * @throws NombreFaltanteSistemaLinealException en caso de que no se ingrese un nombre
	 */
	public boolean agregarSistemaLinealAlHistorial(SistemaLineal s,String nombre) throws NombreFaltanteSistemaLinealException{
		boolean agregado= false;
		if(s== null){
			throw new NullPointerException("No has creado ninguna matriz");
		}else if(s.darMatrizCoeficientes1()== null){
			throw new NullPointerException("No puedes guardar un sistema lineal cuya matriz"
					+ "uno esté vacia");
		}else if(nombre== null || nombre.equals("")){
			throw new NombreFaltanteSistemaLinealException("Debes colocarle un nombre a tu sistema lineal");
		}else{
			if(historialSistema==null){
				historialSistema= s;
				agregado=true;
				s.modificarNombre(nombre);
			}else{
				s.modificarNombre(nombre);
				SistemaLineal actual= historialSistema;
				while(actual.darSiguiente()!= null&&!actual.darNombre().equals(nombre)){
					actual= actual.darSiguiente();
				}
				if(actual.darNombre().equals(nombre)){
					agregado=false;
				}else{
					actual.modificarSiguiente(s);
					s.modificarAnterior(actual);
					agregado =true;
				}
			}
		}
		return agregado;
	}
	/**
	 * Devuelve todas las curvas parametricas actuales
	 * @return
	 */
	public ArrayList<CurvaParametrica> darCurvasParametricas(){
		return curvasParametricas;
	}
	/**
	 * añade una curva parametrica a las curvas actules
	 * @param a
	 */
	public void añadirCurvaParametrica(CurvaParametrica a){
		curvasParametricas.add(a);
	}
	/**
	 * busca la región según el área ingresada
	 * @param area área de la región
	 * @return devuelve la región que encuentra primero con esa área
	 */
	public Region buscarRegion(String area){
		organizarRegiones();
		boolean encontrado=false;
		int inicio= 0;
		Region buscada= null;
		try{
			double areaNumero=Double.parseDouble(conversionNumero(area));
			int fin= listaRegiones.size()-1;
			while(!encontrado&&inicio<=fin){
				int medio=(inicio+fin)/2;
				if(Double.parseDouble(conversionNumero(listaRegiones.get(medio).area()))== areaNumero){
					encontrado= true;
					buscada= listaRegiones.get(medio);
				}else if(Double.parseDouble(conversionNumero(listaRegiones.get(medio).area()))<areaNumero){
					inicio=medio+1;
				}else{
					fin=medio-1;
				}
			}
			
		}catch(NumberFormatException e){
			throw new NumberFormatException();
		}
		return buscada;
	}
	/**
	 * Método auxiliar que permite convertir un numero especifico para poder 
	 * convertirlo luego a double. Ejemplo: "7,09" se convierte en "7.09".ss
	 * @param n numero a convertir
	 * @return numero convertido
	 */
	public String conversionNumero(String n){
		String men="";
		for(int i =0;i<n.length();i++){
			if(n.charAt(i) ==','){
				men+=".";
			}else{
				men+=n.charAt(i)+"";
			}
		}
		return men;
	}
	/**
	 * Elimina una curva parametrica de las curvas actuales
	 * @param a curva parametrica a eliminar
	 */
	public void eliminarCurvaParametrica(CurvaParametrica a){
		for(int i =0;i<curvasParametricas.size();i++){
			if(curvasParametricas.get(i)==a){
				curvasParametricas.remove(i);
				break;
			}
		}
	}
	/**
	 * Elimina un sistema lineal del historial según el nombres
	 * @param nombre nombre del sistema
	 * @throws Exception En caso de que no se encuentre el sistema lineal a eliminar
	 */
	public void eliminarSistemaLinealDelHistorial(String nombre) throws Exception{
		SistemaLineal actual= historialSistema;
		if(actual!= null&&historialSistema.darNombre().equals(nombre)){
			if(historialSistema.darSiguiente()!= null){
				historialSistema.darSiguiente().modificarAnterior(null);
			}
			historialSistema= historialSistema.darSiguiente();
		}else if(actual!= null){
			while(actual!= null&&!actual.darNombre().equals(nombre)){
				actual=actual.darSiguiente();
			}
			if(actual!= null){
				if(actual.darSiguiente()!= null){
					actual.darSiguiente().modificarAnterior(actual.darAnterior());
				}
				actual.darAnterior().modificarSiguiente(actual.darSiguiente());
			}else{
				throw new Exception("No se encontró el sistema lineal");
			}
		}else{
			throw new Exception("No se encontró el sistema lineal");
		}
	}
	/**
	 * Guarda en un archivo serializable el historial de sistemas lineales
	 * @throws IOException
	 */
	public void guardarHistorialSistemaLineal() throws IOException{
		File ar=new File(RUTA_HISTORIAL_SISTEMAS_LINEALES);
		try {
			ObjectOutputStream salida= new ObjectOutputStream(new FileOutputStream(ar));
			salida.writeObject(historialSistema);
			salida.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se encontró el directorio para guardar el historial");
		} catch (IOException e) {
			throw new IOException("Error al guardar el historial de sistemas lineales");
		}
	}
	/**
	 * Carga desde un archivo serailizable el historial De Sistemas Lineales
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void cargarHistorialSistemaLineal() throws IOException, ClassNotFoundException{
		File ar = new File(RUTA_HISTORIAL_SISTEMAS_LINEALES);
		if(ar.exists()){
			try {
				ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ar));
				historialSistema= (SistemaLineal)entrada.readObject();
				entrada.close();
			} catch (FileNotFoundException e) {
				throw new FileNotFoundException("Error al tratar de encontrar el archivo para el historial"
						+ "de sistemas lineales");
			} catch (IOException e) {
				throw new IOException("Error al leer el archivo de historial de sistemas");
			} catch (ClassNotFoundException e) {
				throw new ClassNotFoundException("Error al asignar el historial "
						+ "contacte por favor con algún técnico");
			}
		}
	}
	/**
	 * Busca un sistemaLineal según su nombre
	 * @param nombre nombre del sistema lineal
	 * @return sistema lineal. Si no lo encuentre retorna null
	 */
	public SistemaLineal buscarSistemaLineal(String nombre){
		SistemaLineal actual= historialSistema;
		if(actual!= null){
			if(!actual.darNombre().equals(nombre)){
				while(actual!= null&& !actual.darNombre().equals(nombre)){
					actual= actual.darSiguiente();
				}
			}
		}
		return actual;
	}
	

	/**
	 * Ordena las regiones actuales descendientemente
	 */
	public void ordenarRegionesDescendientemente(){
		for(int i =1;i<listaRegiones.size();i++){
			for(int j=i;j>0&&listaRegiones.get(j-1).compareTo(listaRegiones.get(j))<0;j--){
				Region temp= listaRegiones.get(j);
				listaRegiones.set(j, listaRegiones.get(j-1));
				listaRegiones.set(j-1, temp);
			}
		}
}

	
	public MathyGen(){
		objetosDibujables=new ArrayList<Dibujable>();
		curvasParametricas= new ArrayList<CurvaParametrica>();
		double[][]m1=new double[1][1];
		sistemaLineal= new SistemaLineal(m1, null);
	}
	
	//TODO buscar binariamente las regiones
	/**
	 * Devuleve el sistema lineal actual
	 * @return
	 */
	public SistemaLineal darSistemaLineal(){
		return sistemaLineal;
	}

	/**
	 * Inicializa el sistema lineal actual
	 * @param m1 matriz 1
	 * @param m2 matriz 2
	 */
	public void iniciarSistemaLineal(double[][] m1,double[][] m2){
		sistemaLineal= new SistemaLineal(m1, m2);
	}
	/**
	 * Calcula el determinante de la matriz1
	 * @return deterimante
	 */
	public double calcularDeterminanteMatriz1(){
		return sistemaLineal.calcularDeterminante(sistemaLineal.darMatrizCoeficientes1());
	}
	/**
	 * Utiliza el método calcularSolucionMatriz de la clase sistema lineal
	 * para utilizar su resultado y mostrarlo en la interfaz
	 * @return String mensaje con las soluciones dle sistema
	 * @throws MatrizNoInvertibleException
	 */
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
	/**
	 * Es un método de prueba cuya funcionalidad es únicamente multiplicar las amtrices gigantes
	 * con un solo hilo para luego compararlo con la multiplicación de matrices gigantes con
	 * 2 hilos
	 */
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

	
	/**
	 * Agrega una curva parametrica
	 * @param form formula de la curva parametrica. debe cumplir con los requisitos de las formulas
	 * @param color color de la curva
	 * @param tipo tipo de la curva
	 * @return CurvaParametrica que se agrego
	 * @throws FormulaParaParametrizarIncompleta
	 */
	public CurvaParametrica agregarCurvaParametrica(String form, Color color, int tipo) throws FormulaParaParametrizarIncompleta{
		CurvaParametrica cur=null;
		switch(tipo){
		case CIRCUNFERENCIA:
			cur= new Circunferencia(form);
			break;
		case ELIPSE:
			cur= new Elipse(form);
		}
		cur.modificarColor(color);
		curvasParametricas.add(cur);
		return cur;
	}
	


	public Funcion agregarFuncion1(String form, Color color, int grosor, int tipo) throws FuncionYaExisteException{
		Funcion fun=null;
		switch (tipo) {
		case TIPOPOLINOMIO:
			fun=new Polinomio(form);
			break;
		}
		fun.setColor(color);
		fun.setGrosor(grosor);
		fun.setForma(form);
		if(estaEnElArbol1(fun,raizFuncion)) throw new FuncionYaExisteException(fun.getForma());
		agregarFuncionAlArbol1(fun,raizFuncion);
		return fun;
	}
	public boolean estaEnElArbol1(Funcion f,Funcion actual){
		if(raizFuncion==null){
			return false;
		}else if(actual!=null && actual.compareTo(f)==0){
			return true;
		}else if(actual!=null){
			return estaEnElArbol1(f,actual.getFunDe()) || estaEnElArbol1(f,actual.getFunIz());
		}
		return false;
	}
	public void agregarFuncionAlArbol1(Funcion f,Funcion actual) throws FuncionYaExisteException{
		if(raizFuncion==null){
			raizFuncion=f;
		}else{
			if(f.compareTo(actual)==-1){
				if(actual.getFunIz()==null){
					actual.setFunIz(f);
				}else{
					agregarFuncionAlArbol1(f,actual.getFunIz());
				}
			}else if(f.compareTo(actual)==1){
				if(actual.getFunDe()==null){
					actual.setFunDe(f);
				}else{
					agregarFuncionAlArbol1(f,actual.getFunDe());
				}
			}
		}
	}

	/**
	 * Carga las matrices gigantes
	 * @throws IOException
	 */

	public void cargarMatricesGigantes() throws IOException{
		sistemaLineal=new SistemaLineal(cargarMatrizGigante1(), cargarMatrizGigante2());
	}
	/**
	 * Carga la matriz gigante 1
	 * @return matriz cargada
	 * @throws IOException
	 */
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
	/**
	 * Carga la matriz gigante 2 desde un archivo de texto
	 * @return
	 * @throws IOException
	 */
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
	/**
	 * Crea una matriz gigante de 1000x1000 en un archivo de texto
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Crea una matriz gigante 2 en un archivo de texto
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Este metodo da el primer punto
	 * @return
	 */
	public Punto getPrimerPunto() {
		return primerPunto;
	}
	/**
	 * Este metodo actualiza el primer punto
	 * @param primerPunto
	 */
	public void setPrimerPunto(Punto primerPunto) {
		this.primerPunto = primerPunto;
	}
	/**
	 * Este metodo da la funcion raiz
	 * @return
	 */
	public Funcion getRaizFuncion() {
		return raizFuncion;
	}
	/**
	 * Este metodo da la lista de regiones
	 * @return
	 */
	public ArrayList<Region> getListaRegiones() {
		return listaRegiones;
	}
	/**
	 * Este metodo da la lista de objetos dibujables
	 * @return
	 */
	public ArrayList<Dibujable> darObjetosDibujables() {
		return objetosDibujables;
	}
	/**
	 * Este metodo se encarga de agregar una nueva funcion al mundo
	 * @param form la forma de la funcion
	 * @param color color de la funcion
	 * @param grosor grosor de la funcion
	 * @param tipo tipo de funcion
	 * @return la funcion agregada
	 * @throws FuncionYaExisteException
	 */
	public Funcion agregarFuncion(String form, Color color, int grosor, int tipo) throws FuncionYaExisteException{
		Funcion fun=null;
		switch (tipo) {
		case TRIGONOMETRICO:
			fun=new Trigonometrico(form);
			break;
		case TIPOPOLINOMIO:
			fun=new Polinomio(form);
			break;
		}
		fun.setColor(color);
		fun.setGrosor(grosor);
		fun.setForma(form);
		if(estaEnElArbol(fun,raizFuncion)!=null) throw new FuncionYaExisteException(fun.getForma());
		agregarFuncionAlArbol(fun,raizFuncion);
		return fun;
	}
	/**
	 * Este metodo se encarga de retorna la funcion pasada por parametro si esta 
	 * existe en el arbol
	 * @param f la funcion a verificar si esta en el arbol
	 * @param actual funcion actual del metodo recursivo
	 * @return
	 */
	public Funcion estaEnElArbol(Funcion f,Funcion actual){
		if(raizFuncion==null){
			return null;
		}else if(actual!=null && actual.compareTo(f)==0){
			return actual;
		}else if(actual!=null){
			Funcion fa=estaEnElArbol(f,actual.getFunDe());
			Funcion fe=estaEnElArbol(f,actual.getFunIz());
			if(fa==null && fe==null){
				return null;
			}else{
				if(fa!=null){
					return fa;
				}else{
					return fe;
				}
			}
		}
		return null;
	}
	/**
	 * Este metodo se encarga de agregar una nueva funcion al arbol de funciones
	 * @param f la funcion a agregar
	 * @param actual la funcion actual del metodo recursivo
	 * @throws FuncionYaExisteException en caso de que ya existe la susodicha funcion
	 */
	public void agregarFuncionAlArbol(Funcion f,Funcion actual) throws FuncionYaExisteException{
		if(raizFuncion==null){
			raizFuncion=f;
		}else{
			if(f.compareTo(actual)==-1){
				if(actual.getFunIz()==null){
					actual.setFunIz(f);
					f.setPadre(actual);
				}else{
					agregarFuncionAlArbol(f,actual.getFunIz());
				}
			}else if(f.compareTo(actual)==1){
				if(actual.getFunDe()==null){
					actual.setFunDe(f);
					f.setPadre(actual);
				}else{
					agregarFuncionAlArbol(f,actual.getFunDe());
				}
			}
		}
	}
	/**
	 * Este metodo agrega un nuevo punto a la lista enlazada de puntos
	 * @param x coordenada x del punto
	 * @param y coordenada y del punto
	 * @return el punto agregado
	 */
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
	/**
	 * 	Este metodo se encarga de agregar una nueva region a la lista de regiones
	 * @param frontera los puntos que definen a la region
	 * @param color el color de la region
	 * @return la region agregada
	 */
	public Region agregarRegion(ArrayList<Punto> frontera, Color color) {
		Region r=new Region(frontera, color);
		listaRegiones.add(r);
		return r;
	}
	/**
	 * Agrega un objeto dibujable a la lista objetosDibujables
	 * @param d
	 */
	public void agregarObjetoDibujable(Dibujable d){
		if(!objetosDibujables.contains(d)){
			objetosDibujables.add(d);
		}
	}
	/**
	 * Este metodo se encarga de eliminar un objeto dibujable, de la lista objetosDibujable
	 * @param d el objeto a eliminar
	 */
	public void eliminarObjetoDibujable(Dibujable d){
		if(objetosDibujables.contains(d)){
			objetosDibujables.remove(d);
		}
	}
	/**
	 * Este metodo se encarga de cargar el ultimo estado guardado de lod dibujable
	 * @return arreglo de boolean para decisiones posteriores
	 * @throws Exception en caso de que falle algo con los streams
	 */
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
	/**
	 * Este metodo se encarga de guardar el estado actual de lo dibujable
	 * @throws IOException
	 */
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
	/**
	 * Este metodo se encarga de organizar regiones por selection sort
	 */
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
	/**
	 * Este metodo se encarga de eliminar un punto
	 * @param d El punto a eliminar
	 */
	public void eliminarPunto(Punto d) {
		Punto before=null;
		Punto current=getPrimerPunto();
		if(current==d){
			this.setPrimerPunto(current.getSgtPunto());
		}else{
			before=current;
			current=current.getSgtPunto();
			while(current!=null){
				if(current==d){
					before.setSgtPunto(current.getSgtPunto());
					break;
				}
				before=current;
				current=current.getSgtPunto();
			}
		}
	}
	/**
	 * Este metodo se encarga de eliminar una region
	 * @param d La region a eliminar
	 */
	public void eliminarRegion(Region d) {
		getListaRegiones().remove(d);
	}

	public SistemaLineal darHistorialSistemaLineal(){
		return historialSistema;
	}
	public void modificarHistorialSistemaLineal(SistemaLineal m){
		historialSistema=m;
	}
	public ArrayList<Region> darRegiones(){
		return listaRegiones;
	}
	/**
	 * Este metodo se encarga de eliminar una funcion
	 * @param d La funcion a eliminar
	 */
	public void eliminarFuncion(Funcion d) {
		Funcion padre=d.getPadre();
		if(d.getFunDe()==null && d.getFunIz()==null){
			if(padre!=null){
				if(padre.getFunDe()==d){
					padre.setFunDe(null);
				}else{
					padre.setFunIz(null);
				}
			}else{
				raizFuncion=null;
			}
		}else if(d.getFunDe()!=null){
			Funcion menor=d.getFunDe();
			while(menor.getFunIz()!=null){
				menor=menor.getFunIz();
			}
			Funcion padre2=menor.getPadre();
			if(padre2.getFunDe()==menor){
				padre2.setFunDe(menor.getFunDe());
			}else{
				padre2.setFunIz(menor.getFunDe());
			}
			if(padre!=null){
				if(padre.getFunDe()==d){
					padre.setFunDe(menor);
				}else{
					padre.setFunIz(menor);
				}
			}else{
				raizFuncion=menor;
			}
			menor.setFunDe(d.getFunDe());
			menor.setFunIz(d.getFunIz());
		}else{
			if(padre!=null){
				if(padre.getFunDe()==d){
					padre.setFunDe(d.getFunIz());
				}else{
					padre.setFunIz(d.getFunIz());
				}
			}else{
				raizFuncion=raizFuncion.getFunIz();
			}
		}
	}
}