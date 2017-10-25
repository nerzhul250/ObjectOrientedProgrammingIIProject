package interfaz;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import hilos.HiloMultiplicacion;
import hilos.HiloVerificacion;
import mundo.MathyGen;
import mundo.NoEsNumeroException;

public class InterfazMathy extends JFrame{
	private PanelPrincipalPlano ppp;
	private PanelSistemaLineal psl;
	private MathyGen mundo;
	private HiloMultiplicacion hiloMul;
	private HiloVerificacion hiloVerifi;
	private VentanaMatrizProducto venMatrizPro;
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
	
	public void iniciarProductoEntreMatrices(){
		try {
			double[][] m1=psl.darMatriz1();
			double[][] m2=psl.darMatriz2();
			mundo.iniciarSistemaLineal(m1, m2);
			for(int i =0;i<m1.length;i++){
				for(int j=0;j<m2[0].length;j++){
					hiloMul= new HiloMultiplicacion(mundo.darSistemaLineal(), i, j);
					Thread h= new Thread(hiloMul);
					h.start();
				}
			}
			hiloVerifi= new HiloVerificacion(this,mundo.darSistemaLineal());
			Thread h = new Thread(hiloVerifi);
			h.start();

		} catch (NoEsNumeroException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+e.darIndice());
		}
	}
	
	public void mostrarMatrizProducto(double[][] matriz){
		venMatrizPro= new VentanaMatrizProducto(matriz);
		venMatrizPro.setVisible(true);
		venMatrizPro.pack();
	}
	public static void main(String[] args) {
		InterfazMathy im=new InterfazMathy(); 
		im.setDefaultCloseOperation(EXIT_ON_CLOSE);
		im.setVisible(true);
		im.setResizable(false);
	}
	public void mostrarAcercaDelPrograma() {
		JOptionPane.showMessageDialog(this,"Hecho por STEVENANDSEBAS");
	}
	public void refrescarSolucionSistema(double[] darSolucion) {
		
	}
}
