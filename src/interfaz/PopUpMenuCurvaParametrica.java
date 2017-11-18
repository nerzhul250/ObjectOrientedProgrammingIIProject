package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuCurvaParametrica extends JPopupMenu implements ActionListener {

	/**
	 * Es el item de agregar curvas
	 */
	private JMenuItem itAgregar;
	/**
	 * interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * crea un nuevo popUpMenu para las curvas parametricas
	 * @param p interfaz principal
	 */
	public PopUpMenuCurvaParametrica(InterfazMathy p){
		principal =p;
		itAgregar= new JMenuItem("Agregar nueva curva paramétrica");
		itAgregar.addActionListener(this);
		itAgregar.setActionCommand(PopUpMenuFuncionGen.AGREGAR);
		add(itAgregar);
	}
	/**
	 * Método que escuha los items
	 */
	public void actionPerformed(ActionEvent arg0) {
		principal.abrirDialogoAgregarCurvaParametrica();
	}

}
