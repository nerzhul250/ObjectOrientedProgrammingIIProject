package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuCurvaParametrica extends JPopupMenu implements ActionListener {

	private JMenuItem itAgregar;
	private InterfazMathy principal;
	public PopUpMenuCurvaParametrica(InterfazMathy p){
		principal =p;
		itAgregar= new JMenuItem("Agregar nueva curva paramétrica");
		itAgregar.addActionListener(this);
		itAgregar.setActionCommand(PopUpMenuFuncionGen.AGREGAR);
		add(itAgregar);
	}
	public void actionPerformed(ActionEvent arg0) {
		principal.abrirDialogoAgregarCurvaParametrica();
	}

}
