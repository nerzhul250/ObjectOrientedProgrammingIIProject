package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuArchivo extends JMenu implements ActionListener{	

	public final static String GUARDAR="GUARDAR";

	private JMenuItem meitGuardar;
	
	private InterfazMathy principal;
	
	public MenuArchivo(InterfazMathy ventana) {
		super("Archivo");
		
		principal = ventana;
		
		meitGuardar  = new JMenuItem("Guardar estado");		
		meitGuardar.addActionListener(this);
		meitGuardar.setActionCommand(GUARDAR);

		add(meitGuardar);
	}
	
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(GUARDAR)){
			principal.guardarArchivo();;
		}
	}
}
