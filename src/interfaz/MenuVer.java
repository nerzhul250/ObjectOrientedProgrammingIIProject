package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuVer extends JMenu implements ActionListener{
	private JMenuItem meitAcerca;
	
	public final static String ACERCA="ACERCA";
	private InterfazMathy principal;
	
	public MenuVer(InterfazMathy ventana) {
		super("Ver");
		
		principal = ventana;
		
		meitAcerca  = new JMenuItem("Acerca de MathyGen");
		
		meitAcerca.addActionListener(this);
		
		meitAcerca.setActionCommand(ACERCA);
		
		add(meitAcerca);
	}
	
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(ACERCA)){
			principal.mostrarAcercaDelPrograma();;
		}
	}
}
