package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;

import mundo.CurvaParametrica;

public class PanelObjDibCurvaParametrica extends JPanel implements MouseListener {
	/**
	 * JList que contiene todas las curvas paramétricas
	 */
	private JList<CurvaParametrica> lista;
	/**
	 * ListModel que contiene las curvas paramétricas
	 */
	private DefaultListModel<CurvaParametrica> listModel;
	/**
	 * Interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Crea un nuevo panel que tiene las curvass paramétricas
	 * @param p intefaz principal
	 */
	public PanelObjDibCurvaParametrica(InterfazMathy p){
		principal = p;
		
		setBorder(new TitledBorder("Parametrizables"));
		
		listModel= new DefaultListModel<CurvaParametrica>();
		lista = new JList<CurvaParametrica>(listModel);
		lista.addMouseListener(this);
		
		JScrollPane scroll= new JScrollPane(lista);
		scroll.setBackground(Color.WHITE);
		scroll.setPreferredSize(new Dimension(260,200));
		add(scroll);
	}
	
	/**
	 * Agrega una nueva curva paramétrica a la lista
	 * @param c curva paramétrica
	 */
	public void agregarCurva(CurvaParametrica c){
		listModel.addElement(c);
	}
	/**
	 * Elimina todos los elementos actuales de la lista
	 */
	public void eliminarTodosLosElementos(){
		listModel.removeAllElements();
	}
	/**
	 * Refresca la lista de las curvas paramétricas
	 * @param l arraylist que contiene todas las curvas parametricas
	 */
	public void refrescarLista(ArrayList<CurvaParametrica>l){
		for(int i=0;i<l.size();i++ ){
			agregarCurva(l.get(i));
		}
	}
	
	/**
	 * Método que escucha cuando se hace click encima del panel
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			if(lista.isSelectionEmpty()){
				PopUpMenuCurvaParametrica menu = new PopUpMenuCurvaParametrica(principal);
				menu.show(lista, e.getX(), e.getY());
			}else{
				PopUpMenuCuvaParametricaGeneral menu= new PopUpMenuCuvaParametricaGeneral(principal, lista.getSelectedValue());
				menu.show(lista, e.getX(), e.getY());
				lista.clearSelection();
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
