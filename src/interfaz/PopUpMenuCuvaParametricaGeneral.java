package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.CurvaParametrica;

public class PopUpMenuCuvaParametricaGeneral extends JPopupMenu implements ActionListener {

	/**
	 * Interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Curva paramétrica seleccionada
	 */
	private CurvaParametrica curv;
	/**
	 * item eliminar
	 */
	private JMenuItem itEliminar;
	/**
	 * item que da la orden de dibujar la curva
	 */
	private JMenuItem itDibujar;
	/**
	 * item que da la orden de borrar la curva seleccionada
	 */
	private JMenuItem itBorrar;
	/**
	 * Crea un nuevo popUpMenu para curva parametrica
	 * @param p interfaz principal
	 * @param c curva paramétrica seleccionada
	 */
	public PopUpMenuCuvaParametricaGeneral(InterfazMathy p, CurvaParametrica c){
		curv=c;
		principal=p;
		itEliminar= new JMenuItem("Eliminar curva");
		itBorrar= new JMenuItem("Borrar función del plano");
		itDibujar= new JMenuItem("Dibujar curva");
		
		itEliminar.addActionListener(this);
		itEliminar.setActionCommand(PopUpMenuFuncionPar.ELIMINAR);
		
		itBorrar.addActionListener(this);
		itBorrar.setActionCommand(PopUpMenuFuncionPar.BORRAR);
		
		itDibujar.addActionListener(this);
		itDibujar.setActionCommand(PopUpMenuFuncionPar.DIBUJAR);
		
		add(itDibujar);
		add(itBorrar);
		add(itEliminar);
	}
	/**
	 * Escucha los items
	 */
	public void actionPerformed(ActionEvent e) {
		String comando= e.getActionCommand();
		if(comando.equals(PopUpMenuFuncionPar.ELIMINAR)){
			principal.eliminarObjetoDibujable(curv);
		}else if(comando.equals(PopUpMenuFuncionPar.DIBUJAR)){
			principal.agregarObjetoDibujable(curv);
		}else if(comando.equals(PopUpMenuFuncionPar.BORRAR)){
			principal.borrarObjetoDibujable(curv);
		}
	}

}
