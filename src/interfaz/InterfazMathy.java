package interfaz;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import mundo.MathyGen;

public class InterfazMathy extends JFrame{
	private PanelPrincipalPlano ppp;
	private PanelSistemaLineal psl;
	public InterfazMathy(){
		setTitle("MathyGen");
		
		ppp=new PanelPrincipalPlano();
		psl=new PanelSistemaLineal();
		
		JTabbedPane jtp=new JTabbedPane();
		jtp.add(ppp,"Plano");
		jtp.add(psl,"Sistema lineal");
		
		JMenuBar miMenuBar = new JMenuBar();
		miMenuBar.add(new MenuVer(this));
		setJMenuBar(miMenuBar);		
		
		add(jtp);
		pack();
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
