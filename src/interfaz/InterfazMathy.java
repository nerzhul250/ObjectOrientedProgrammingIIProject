package interfaz;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import hilos.HiloMultiplicacion;
import hilos.HiloVerificacion;
import mundo.MathyGen;
import mundo.MatrizNoInvertibleException;
import mundo.NoEsNumeroException;

public class InterfazMathy extends JFrame{
	private PanelPrincipalPlano ppp;
	private PanelSistemaLineal psl;
	private MathyGen mundo;
	private HiloMultiplicacion hiloMul;
	private HiloVerificacion hiloVerifi;
	private VentanaMatriz venMatrizPro;
	private VentanaMatrizB ventanaMaB;
	public InterfazMathy(){
		setTitle("MathyGen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ppp=new PanelPrincipalPlano();
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
			if(mundo.darSistemaLineal().darHilosEnEjecucion()==(2)){
				hiloMul = new HiloMultiplicacion(mundo.darSistemaLineal(), (m1.length-1)/2, 0);
				Thread m= new Thread(hiloMul);
				m.start();
				hiloMul=new HiloMultiplicacion(mundo.darSistemaLineal(), m1.length,(m1.length-1)/2);
				m= new Thread(hiloMul);
				m.start();
				
				
				hiloVerifi= new HiloVerificacion(this,mundo.darSistemaLineal());
				Thread h = new Thread(hiloVerifi);
				h.start();
			}
		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
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
	
	public void mostrarMatrizProducto(double[][] matriz){
		venMatrizPro= new VentanaMatriz(matriz);
		venMatrizPro.setVisible(true);
		venMatrizPro.pack();
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
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los archivos");
		}
	}
	public void iniciarMultiplicacionMatricesGigantes(){
			int m1=mundo.darSistemaLineal().darMatrizCoeficientes1().length;
			if(mundo.darSistemaLineal().darHilosEnEjecucion()==(2)){
				
				hiloMul = new HiloMultiplicacion(mundo.darSistemaLineal(), (m1-1)/2, 0);
				Thread m= new Thread(hiloMul);
				m.start();
				hiloMul=new HiloMultiplicacion(mundo.darSistemaLineal(), m1,(m1-1)/2);
				m= new Thread(hiloMul);
				m.start();
				hiloVerifi= new HiloVerificacion(this,mundo.darSistemaLineal());
				Thread h = new Thread(hiloVerifi);
				h.start();
			}
		
	}
	public void mostrarAcercaDelPrograma() {
		JOptionPane.showMessageDialog(this,"Hecho por STEVENANDSEBAS");
	}
	public void refrescarSolucionSistema(double[] darSolucion) {
		
	}
}
