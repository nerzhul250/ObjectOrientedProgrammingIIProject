package interfaz;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import hilos.HiloMultiplicacion;
import mundo.Funcion;
import mundo.MathyGen;
import mundo.MatrizNoInvertibleException;
import mundo.NoEsNumeroException;

public class InterfazMathy extends JFrame{
	private PanelPrincipalPlano ppp;
	private PanelSistemaLineal psl;
	
	private MathyGen mundo;
	
	private VentanaMatriz venMatrizPro;
	private VentanaMatrizB ventanaMaB;
	public InterfazMathy(){
		setTitle("MathyGen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ppp=new PanelPrincipalPlano(this,mundo);
		psl=new PanelSistemaLineal(this);
		mundo=new MathyGen();
		
		JTabbedPane jtp=new JTabbedPane();
		jtp.add(ppp,"Plano");
		jtp.add(psl,"Sistema lineal");
		
		JMenuBar miMenuBar = new JMenuBar();
		miMenuBar.add(new MenuVer(this));
		setJMenuBar(miMenuBar);		
		
		add(jtp);
		pack();
	}
	public int darTamanoMatrizB(){
		return mundo.darSistemaLineal().darMatrizCoeficientes1()[0].length;
	}
	public void iniciarProductoEntreMatrices(){
		try {
			double[][] m1=psl.darMatriz1();
			double[][] m2=psl.darMatriz2();
			mundo.iniciarSistemaLineal(m1, m2);
			int poolSize = 4;
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
		      int poolSize = 4;
		      ExecutorService service = Executors.newFixedThreadPool(poolSize);
		      List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
		
		      Future f1 = service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(), 250, 0));
		      futures.add(f1);
		      Future f2=service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(), 500,250));
		      futures.add(f2);
		      Future f3=service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(),750,500));
		      futures.add(f3);
		      Future f4=service.submit(new HiloMultiplicacion(mundo.darSistemaLineal(),1000,750));
		      futures.add(f4);
		    
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
		ppp.agregarFuncion(mundo.agregarFuncion(form,color,grosor,tipo));
	}
	public void desplegarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
	public void agregarObjetoDibujable(Funcion f) {
		ppp.agregarObjetoDibujable(f);
	}
	public void borrarObjetoDibujable(Funcion f) {
		ppp.borrarObjetoDibujable(f);
	}
	
}
