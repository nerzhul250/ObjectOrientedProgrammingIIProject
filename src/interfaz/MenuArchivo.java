package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuArchivo extends JMenu implements ActionListener{	
	/**
	 * Constante de guardar
	 */
	public final static String GUARDAR="GUARDAR";
	/***
	 * Item de guardar
	 */
	private JMenuItem meitGuardar;
	/**
	 * ventana principal
	 */
	private InterfazMathy principal;
	
	/**
	 * Construye el menu de archivo
	 * @param ventana
	 */
	public MenuArchivo(InterfazMathy ventana) {
		super("Archivo");
		
		principal = ventana;
		
		meitGuardar  = new JMenuItem("Guardar estado");		
		meitGuardar.addActionListener(this);
		meitGuardar.setActionCommand(GUARDAR);

		add(meitGuardar);
	}
	/**
	 * ACTIONPERFORMED, guarda en resumen
	 */
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(GUARDAR)){
			principal.guardarArchivo();;
		}
	}
}
