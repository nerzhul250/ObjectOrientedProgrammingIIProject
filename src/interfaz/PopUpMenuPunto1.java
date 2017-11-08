package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.MathyGen;
import mundo.Punto;

public class PopUpMenuPunto1 extends JPopupMenu implements ActionListener{

	public final static String DIBUJAR="DIBUJAR";
	public final static String BORRAR="BORRAR";
	public final static String ELIMINAR="ELIMINAR";
	
	private JMenuItem itDibujar;
	private JMenuItem itBorrar;
	private JMenuItem itEliminar;	
	
	private InterfazMathy principal;
	
	private Punto punto;
	public PopUpMenuPunto1(InterfazMathy principal,Punto punto) {
		this.principal=principal;
		this.punto=punto;
		
        itDibujar = new JMenuItem("Dibujar punto");
        itBorrar =new JMenuItem("Borrar punto");
        itEliminar =new JMenuItem("Eliminar punto");
        
        
        itDibujar.addActionListener(this);
        itDibujar.setActionCommand(DIBUJAR);
        
        itBorrar.addActionListener(this);
        itBorrar.setActionCommand(BORRAR);
        
        itEliminar.addActionListener(this);
        itEliminar.setActionCommand(ELIMINAR);
        
        add(itDibujar);
        add(itBorrar);
        add(itEliminar);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(DIBUJAR)){
			principal.agregarObjetoDibujable(punto);
		}else if(e.getActionCommand().equals(BORRAR)){
			principal.borrarObjetoDibujable(punto);
		}else if(e.getActionCommand().equals(ELIMINAR)){
			principal.eliminarObjetoDibujable(punto);
		}
	}
}
