package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuArchivo extends JMenu implements ActionListener{	

	public final static String GUARDAR="GUARDAR";
	public final static String BUSCAR_REGION="BRA";
	private JMenuItem meitGuardar;
	private JMenuItem buscarRegion;
	private InterfazMathy principal;
	
	
	public MenuArchivo(InterfazMathy ventana) {
		super("Archivo");
		
		principal = ventana;
		
		meitGuardar  = new JMenuItem("Guardar estado");		
		meitGuardar.addActionListener(this);
		meitGuardar.setActionCommand(GUARDAR);
		
		buscarRegion  = new JMenuItem("Buscar región");		
		buscarRegion.addActionListener(this);
		buscarRegion.setActionCommand(BUSCAR_REGION);

		add(meitGuardar);
		add(buscarRegion);
	}
	
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(GUARDAR)){
			principal.guardarArchivo();;
		}else if(comando.equals(BUSCAR_REGION)){
			principal.buscarRegion();
		}
	}
}
