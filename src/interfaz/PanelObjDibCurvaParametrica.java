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

import mundo.CurvaParametrica;

public class PanelObjDibCurvaParametrica extends JPanel implements MouseListener {
	private JList<CurvaParametrica> lista;
	private DefaultListModel<CurvaParametrica> listModel;
	private InterfazMathy principal;
	
	public PanelObjDibCurvaParametrica(InterfazMathy p){
		principal = p;
		
		listModel= new DefaultListModel<CurvaParametrica>();
		lista = new JList<CurvaParametrica>(listModel);
		lista.addMouseListener(this);
		
		JScrollPane scroll= new JScrollPane(lista);
		scroll.setBackground(Color.WHITE);
		scroll.setPreferredSize(new Dimension(260,200));
		add(scroll);
	}
	
	
	public void agregarCurva(CurvaParametrica c){
		listModel.addElement(c);
	}
	public void eliminarTodosLosElementos(){
		listModel.removeAllElements();
	}
	public void refrescarLista(ArrayList<CurvaParametrica>l){
		for(int i=0;i<l.size();i++ ){
			agregarCurva(l.get(i));
		}
	}
	
	
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
