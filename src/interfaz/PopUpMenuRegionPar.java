package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.Funcion;
import mundo.Region;

public class PopUpMenuRegionPar extends JPopupMenu implements ActionListener {
	
	public final static String DIBUJAR="DIBUJAR";
	public final static String BORRAR="BORRAR";
	public final static String ELIMINAR="ELIMINAR";

	
	private JMenuItem itDibujar;
	private JMenuItem itBorrar;
	private JMenuItem itEliminar;	

	
	private Region r;
	
	private InterfazMathy principal;
    
	public PopUpMenuRegionPar(InterfazMathy principal,Region region) {
		r=region;
		this.principal=principal;
		
        itDibujar = new JMenuItem("Dibujar Region");
        itBorrar=new JMenuItem("Borrar Region");
        itEliminar =new JMenuItem("Eliminar Region");
        
        
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
			principal.agregarObjetoDibujable(r);
		}else if(e.getActionCommand().equals(BORRAR)){
			principal.borrarObjetoDibujable(r);
		}else if(e.getActionCommand().equals(ELIMINAR)){
			principal.eliminarObjetoDibujable(r);
		}
	}
}
