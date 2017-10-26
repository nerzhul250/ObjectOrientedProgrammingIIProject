package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.Funcion;

public class PopUpMenuFuncionPar extends JPopupMenu implements ActionListener {
	
	public final static String AGREGAR="AGREGAR";
	private JMenuItem itAgregar;
	
	private InterfazMathy principal;
    public PopUpMenuFuncionPar(InterfazMathy in,Funcion f){
    	principal=in;
        itAgregar = new JMenuItem("Agregar nueva funcion");
        itAgregar.addActionListener(this);
        add(itAgregar);
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGAR)){
			principal.abrirDialogoAgregarFuncion();
		}
	}
}
