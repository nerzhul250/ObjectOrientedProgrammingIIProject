package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuFuncionGen extends JPopupMenu implements ActionListener{
	public final static String AGREGAR="AGREGAR";
	private JMenuItem itAgregar;
	
	private InterfazMathy principal;
    public PopUpMenuFuncionGen(InterfazMathy in){
    	principal=in;
        itAgregar = new JMenuItem("Agregar nueva funcion");
        itAgregar.addActionListener(this);
        itAgregar.setActionCommand(AGREGAR);
        add(itAgregar);
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGAR)){
			principal.abrirDialogoAgregarFuncion();
		}
	}
}