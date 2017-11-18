package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import hilos.HiloGeneradorPrimos;
import hilos.HiloMultiplicacion;
import mundo.Circunferencia;
import mundo.CurvaParametrica;
import mundo.FormulaParaParametrizarIncompleta;
import mundo.Dibujable;
import mundo.Funcion;
import mundo.FuncionYaExisteException;
import mundo.MathyGen;
import mundo.MatrizNoInvertibleException;
import mundo.NoEsNumeroException;
import mundo.NombreFaltanteSistemaLinealException;
import mundo.Punto;
import mundo.Region;
import mundo.SistemaLineal;

public class InterfazMathy extends JFrame{
	/**
	 * Panel principal donde yace todo lo del planoXY
	 */
	private PanelPrincipalPlano ppp;
	private PanelSistemaLineal psl;
	private PanelNumeros pn;
	
	/**
	 * Conexion con el mundo
	 */
	private MathyGen mundo;
	
	private VentanaMatriz venMatrizPro;
	private VentanaMatrizB ventanaMaB;
	/**
	 * Construye la interfaz
	 */
	public InterfazMathy(){
		setTitle("MathyGen");
		
		mundo=new MathyGen();
		ppp=new PanelPrincipalPlano(this,mundo);
		psl=new PanelSistemaLineal(this);
		pn=new PanelNumeros(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try {
			boolean[] dec=mundo.cargarEstado();
			if(dec[0]){
				ppp.refrescarListaPuntos(mundo.getPrimerPunto());
			}
			if(dec[1]){
				ppp.refrescarListaFunciones(mundo.getRaizFuncion());
			}
			if(dec[2]){				
				ppp.refrescarListaRegiones(mundo.getListaRegiones());
			}
			refrescarPanelPrimos();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error Fatal en la carga de archivos");
		}
		
		JTabbedPane jtp=new JTabbedPane();
		jtp.add(ppp,"Plano");
		jtp.add(psl,"Sistema lineal");
		jtp.add(pn,"Numeros");
		
		JMenuBar miMenuBar = new JMenuBar();
		miMenuBar.add(new MenuArchivo(this));
		miMenuBar.add(new MenuVer(this));
		setJMenuBar(miMenuBar);		
		try {
			mundo.cargarHistorialSistemaLineal();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		add(jtp);
		pack();
	}
	/**
	 * Metodo que guarda el estado del plano principal
	 */
	public void guardarArchivo(){
		try {
			mundo.guardarEstado();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error fatal en la guardada de estado");
		}
	}
	public int darTamanoMatrizB(){
		return mundo.darSistemaLineal().darMatrizCoeficientes1()[0].length;
	}

	@Override
	public void dispose() {
		try {
			mundo.guardarHistorialSistemaLineal();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		super.dispose();
	}
	
	public void guardarSistema(String nombre){
		double[][] m1;
		try {
			m1 = psl.darMatriz1();
			double[][] m2=psl.darMatriz2();
			mundo.iniciarSistemaLineal(m1, m2);
			boolean agregado=mundo.agregarSistemaLinealAlHistorial(mundo.darSistemaLineal(), nombre);
			if(agregado)
				JOptionPane.showMessageDialog(this, "Guardado exitosamente");
			else
				JOptionPane.showMessageDialog(this, "El nombre ya existe");
		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
		} catch (NombreFaltanteSistemaLinealException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	public void buscarSistema(String nombre){
		SistemaLineal buscado= mundo.buscarSistemaLineal(nombre);
		if(buscado!= null){
			psl.inicializarMatrices(buscado.darMatrizCoeficientes1().length);
			psl.mostrarMatriz1(buscado.darMatrizCoeficientes1());
			psl.mostrarMatriz2(buscado.darMatrizCoeficientes2());
			pack();
		}else{
			JOptionPane.showMessageDialog(this, "Sistema no encontrado");
		}
	}
	
	public void iniciarProductoEntreMatrices(){
		try {
			double[][] m1=psl.darMatriz1();
			double[][] m2=psl.darMatriz2();
			mundo.iniciarSistemaLineal(m1, m2);
			int poolSize = 2;
		      ExecutorService service = Executors.newFixedThreadPool(poolSize);
		      List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
		
		      Future f1 = service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(), m1.length/2, 0));
		      futures.add(f1);
		      Future f2=service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(), m1.length,m1.length/2));
		      futures.add(f2);
		      
		      // wait for all tasks to complete before continuing
		      for (Future<Runnable> fe : futures)
		      {
		         fe.get();
		      }
		      service.shutdownNow();
		      mostrarMatrizProducto(mundo.darMatrizProducto());
		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void calcularDeterminanteMatriz1(){
		double[][] m1;
		try {
			m1 = psl.darMatriz1();
			mundo.iniciarSistemaLineal(m1, null);
			JOptionPane.showMessageDialog(this,"El determinante de la matriz es: "+ mundo.calcularDeterminanteMatriz1());
		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
		}
	}
	public void ventanaMatrizBVisible(){
		double[][] m1;
		try {
			m1 = psl.darMatriz1();
			mundo.iniciarSistemaLineal(m1, null);
			ventanaMaB= new VentanaMatrizB(this);
			ventanaMaB.setVisible(true);
		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
		}
	}
	public void calcularSolucionesMatriz1(){
		try {
			mundo.darSistemaLineal().modificarMatrizB(ventanaMaB.darMatrizB());
			ventanaMaB.setVisible(false);
			JOptionPane.showMessageDialog(this, mundo.calcularSolucionesMatriz1());
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatrizNoInvertibleException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
		}
	}
	public static void main(String[] args) {
		InterfazMathy im=new InterfazMathy(); 
		im.setVisible(true);
		im.setResizable(false);
	}
	public void cargarMatricesGigantes(){	
		try {
			mundo.cargarMatricesGigantes();
			long tiempo=System.currentTimeMillis();
			mundo.metodoPrueba();
			tiempo=System.currentTimeMillis()-tiempo;
			JOptionPane.showMessageDialog(this, "se demoró: "+(tiempo/1000)+" segundos sin hilos");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los archivos");
		}
	}
	public void iniciarMultiplicacionMatricesGigantes(){
		try{
			long tiempo=System.currentTimeMillis();
			int m1=mundo.darSistemaLineal().darMatrizCoeficientes1().length;
			  //limit the number of actual threads
		      int poolSize = 2;
		      ExecutorService service = Executors.newFixedThreadPool(poolSize);
		      List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
		
		      Future f1 = service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(), m1/2, 0));
		      futures.add(f1);
		      Future f2=service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(),m1,m1/2));
		      futures.add(f2);
		    
		      // wait for all tasks to complete before continuing
		      for (Future<Runnable> fe : futures)
		      {
		         fe.get();
		      }
		      service.shutdownNow();
			  tiempo= System.currentTimeMillis()-tiempo;
			  desplegarMensaje("El tiempo de multiplicación de matrices es de:\n"+(tiempo/1000)+" segundos"+"\n"+"Espera a que se despliegue el resultado");
			  mostrarMatrizProducto(mundo.darMatrizProducto());
		}catch(Exception e){
			
		}
	}
	public void mostrarAcercaDelPrograma() {
		JOptionPane.showMessageDialog(this,"Hecho por STEVENANDSEBAS");
	}
	public void abrirDialogoAgregarFuncion() {
		DialogoAgregarFuncion daf=new DialogoAgregarFuncion(this,"Agregar nueva funcion");
		daf.setModalityType(ModalityType.DOCUMENT_MODAL);
		daf.setVisible(true);
	}
	
	public void abrirDialogoAgregarCurvaParametrica(){
		DialogoAgregarCurvaParametrica daf= new DialogoAgregarCurvaParametrica(this);
		daf.setModalityType(ModalityType.DOCUMENT_MODAL);
		daf.setVisible(true);
	}
	public void abrirDialogoAgregarNumero() {	
		DialogoAgregarNumero dan=new DialogoAgregarNumero(this,"Agregar nuevo numero");
		dan.setModalityType(ModalityType.DOCUMENT_MODAL);
		dan.setVisible(true);
	}
	
	public void mostrarMatrizProducto(double[][] matriz){
		venMatrizPro= new VentanaMatriz(matriz);
		venMatrizPro.setVisible(true);
	}
	/**
	 * Este metodo agrega una nueva funcion al mundo
	 * @param form representacion dada por el usuario
	 * @param color color de la funcion
	 * @param grosor grosor de la funcion
	 * @param tipo tipo de funcion
	 */
	public void agregarFuncion(String form,Color color, int grosor, int tipo) {
		try {
			ppp.agregarFuncion(mundo.agregarFuncion(form,color,grosor,tipo));
		} catch (FuncionYaExisteException e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
	public void agregarCurvaParametrica(String form, Color color, int tipo){
		try {
			ppp.agregarCurvaParametrica(mundo.agregarCurvaParametrica(form, color, tipo));
		} catch (FormulaParaParametrizarIncompleta e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
	/**
	 * Agrega un nuevo punto al mundo
	 * @param x coordenada x del punto
	 * @param y coordenada y del punto
	 */
	public void crearPunto(double x, double y) {
		ppp.agregarPunto(mundo.agregarPunto(x,y));
	}
	/**
	 * Agrega una nueva region al mundo
	 * @param frontera los puntos definiendo a la region
	 * @param color el color de la regin
	 */
	public void agregarRegion(ArrayList<Punto> frontera, Color color) {
		ppp.agregarRegion(mundo.agregarRegion(frontera,color));
	}
	/**
	 * Metodo que agrega un objeto dibujable al mundo
	 * @param f el dibujable
	 */
	public void agregarObjetoDibujable(Dibujable f) {
		mundo.agregarObjetoDibujable(f);
		ppp.refrescarPlano();
	}
	/**
	 * Borra el objeto dibujable del planoxy
	 * @param f
	 */
	public void borrarObjetoDibujable(Dibujable f) {
		mundo.eliminarObjetoDibujable(f);
		ppp.refrescarPlano();
	}
	/**
	 * da los objetos dibujables del mundo
	 * @return
	 */
	public ArrayList<Dibujable> darObjetosDibujables() {
		return mundo.darObjetosDibujables();
	}
	public void organizarRegionesAscendentemente() {
		mundo.organizarRegiones();
		ppp.refrescarListaRegiones(mundo.getListaRegiones());
	}
	public void organizarRegionesDescendientemente(){
		mundo.ordenarRegionesDescendientemente();
		ppp.refrescarListaRegiones(mundo.getListaRegiones());
	}
	
	public void buscarRegion(){
		String area = JOptionPane.showInputDialog("Ingresa el área de la región a buscar");
		try{
			if(area!= null && !area.equals("")){
				Region buscada= mundo.buscarRegion(area);
				if(buscada==null){
					JOptionPane.showMessageDialog(this, "Región no encontrada");
				}else{
					agregarObjetoDibujable(buscada);
				}
			}
			
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Ingresa un área válida");
		}
	}

	/**
	 * Metodo que organiza las regiones del mundo
	 */
	public void organizarRegiones() {
		mundo.organizarRegiones();
		ppp.refrescarListaRegiones(mundo.getListaRegiones());
	}
	/**
	 * Elimina el objeto dibujable del mundo
	 * @param d
	 */

	public void eliminarObjetoDibujable(Dibujable d) {
		if(d instanceof Punto){
			mundo.eliminarObjetoDibujable(d);
			mundo.eliminarPunto((Punto)d);
			ppp.refrescarListaPuntos(mundo.getPrimerPunto());
			
		}else if(d instanceof Funcion){
			mundo.eliminarFuncion((Funcion)d);
			ppp.refrescarListaFunciones(mundo.getRaizFuncion());
		}else if(d instanceof Region){
			mundo.eliminarRegion((Region)d);
			ppp.refrescarListaRegiones(mundo.getListaRegiones());
		}else if(d instanceof CurvaParametrica){
			mundo.eliminarCurvaParametrica((CurvaParametrica)d);
			ppp.refrescarListaCurvasParametricas(mundo.darCurvasParametricas());
		}
		ppp.refrescarPlano();
	}
	public void iniciarBusquedaPrimos(){
		if(mundo.estaBuscandoPrimos()){
			refrescarPanelPrimos();
			mundo.pararBusquedaPrimos();
		}else{
			mundo.iniciarBusquedaPrimos();
			new Thread(new HiloGeneradorPrimos(mundo)).start();			
		}
	}
	public void refrescarPanelPrimos(){
		pn.refrescarPrimos(mundo.getPrimos());
	}
	public void refrescarPanelNumeros(){
		pn.refrescarNumeros(mundo.getNumeros());
	}
	public void desplegarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
	public void agregarNumero(String nam) {
		try{
			if(!mundo.agregarNumero(nam)){
				JOptionPane.showMessageDialog(this,"Genere mas primos");
			}else{
				pn.agregarNumero(mundo.getNumeros().get(mundo.getNumeros().size()-1));			
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this,"No fue un numero lo que ingresó");
		}
	}
}
