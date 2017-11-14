package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.CurvaParametrica;

public class PopUpMenuCuvaParametricaGeneral extends JPopupMenu implements ActionListener {

	private InterfazMathy principal;
	private CurvaParametrica curv;
	private JMenuItem itEliminar;
	private JMenuItem itDibujar;
	private JMenuItem itBorrar;
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
