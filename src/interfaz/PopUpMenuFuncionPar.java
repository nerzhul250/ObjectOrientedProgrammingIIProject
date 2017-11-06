package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.Funcion;

public class PopUpMenuFuncionPar extends JPopupMenu implements ActionListener {
	
	public final static String DIBUJAR="DIBUJAR";
	public final static String BORRAR="BORRAR";
	public final static String ELIMINAR="ELIMINAR";

	
	private JMenuItem itDibujar;
	private JMenuItem itBorrar;
	private JMenuItem itEliminar;	

	
	private Funcion f;
	
	private InterfazMathy principal;
    public PopUpMenuFuncionPar(InterfazMathy in,Funcion f){
    	this.f=f;
    	principal=in;
        itDibujar = new JMenuItem("Dibujar funcion");
        itBorrar=new JMenuItem("Borrar funcion");
        itEliminar =new JMenuItem("Eliminar Funcion");
        
        
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
			principal.agregarObjetoDibujable(f);
		}else if(e.getActionCommand().equals(BORRAR)){
			principal.borrarObjetoDibujable(f);
		}else if(e.getActionCommand().equals(ELIMINAR)){
			principal.eliminarObjetoDibujable(f);
		}
	}
}