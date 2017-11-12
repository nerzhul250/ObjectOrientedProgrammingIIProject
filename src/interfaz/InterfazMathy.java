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

import hilos.HiloMultiplicacion;
import mundo.Circunferencia;
import mundo.FormulaParaParametrizarIncompleta;
import mundo.Dibujable;
import mundo.Funcion;
import mundo.FuncionYaExisteException;
import mundo.MathyGen;
import mundo.MatrizNoInvertibleException;
import mundo.NoEsNumeroException;
import mundo.Punto;
import mundo.Region;

public class InterfazMathy extends JFrame{
	private PanelPrincipalPlano ppp;
	private PanelSistemaLineal psl;
	
	private MathyGen mundo;
	
	private VentanaMatriz venMatrizPro;
	private VentanaMatrizB ventanaMaB;
	public InterfazMathy(){
		setTitle("MathyGen");
		
		mundo=new MathyGen();
		ppp=new PanelPrincipalPlano(this,mundo);
		psl=new PanelSistemaLineal(this);

		
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
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error Fatal en la carga de archivos");
		}
		
		JTabbedPane jtp=new JTabbedPane();
		jtp.add(ppp,"Plano");
		jtp.add(psl,"Sistema lineal");
		
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
	public void dispose(){
		try {
			mundo.guardarHistorialSistemaLineal();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
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
		im.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	
	public void mostrarMatrizProducto(double[][] matriz){
		venMatrizPro= new VentanaMatriz(matriz);
		venMatrizPro.setVisible(true);
	}
	public void agregarFuncion(String form,Color color, int grosor, int tipo) {
		try {
			ppp.agregarFuncion(mundo.agregarFuncion(form,color,grosor,tipo));
		} catch (FuncionYaExisteException e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
	public void crearPunto(double x, double y) {
		ppp.agregarPunto(mundo.agregarPunto(x,y));
	}
	public void agregarRegion(ArrayList<Punto> frontera, Color color) {
		ppp.agregarRegion(mundo.agregarRegion(frontera,color));
	}
	public void desplegarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
	public void agregarObjetoDibujable(Dibujable f) {
		mundo.agregarObjetoDibujable(f);
		ppp.refrescarPlano();
	}
	public void borrarObjetoDibujable(Dibujable f) {
		mundo.eliminarObjetoDibujable(f);
		ppp.refrescarPlano();
	}
	public ArrayList<Dibujable> darObjetosDibujables() {
		return mundo.darObjetosDibujables();
	}
	public void organizarRegiones() {
		mundo.organizarRegiones();
		ppp.refrescarListaRegiones(mundo.getListaRegiones());
	}
	public void eliminarObjetoDibujable(Dibujable d) {
		mundo.eliminarObjetoDibujable(d);
		if(d instanceof Punto){
			mundo.eliminarPunto((Punto)d);
			ppp.refrescarListaPuntos(mundo.getPrimerPunto());
		}else if(d instanceof Funcion){
			Funcion dad=((Funcion)d).getPadre();
			if(dad==null){
				mundo.setRaizFuncion(null);
			}else if(dad.getFunDe()==d){
				dad.setFunDe(null);
			}else{
				dad.setFunIz(null);
			}
			if(((Funcion)d).getFunDe()!=null){
				mundo.recorridoDeAgregacion(((Funcion)d).getFunDe(),dad);
			}
			if(((Funcion)d).getFunIz()!=null){
				mundo.recorridoDeAgregacion(((Funcion)d).getFunIz(),dad);
			}	
			ppp.refrescarListaFunciones(mundo.getRaizFuncion());
		}else if(d instanceof Region){
			mundo.eliminarRegion((Region)d);
			ppp.refrescarListaRegiones(mundo.getListaRegiones());
		}
		ppp.refrescarPlano();
	}
}
