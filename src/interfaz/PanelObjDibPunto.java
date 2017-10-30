package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mundo.Punto;
import mundo.Region;

public class PanelObjDibPunto extends JPanel implements MouseListener {
	
	private JList<Punto> listaPunto;
	private DefaultListModel<Punto> listModel;
	private InterfazMathy principal;
	
	public PanelObjDibPunto(InterfazMathy principal) {
		this.principal=principal;
		listModel = new DefaultListModel<Punto>();
		listaPunto = new JList<Punto>(listModel);
		listaPunto.addMouseListener(this);
		
		JScrollPane scrollMostrar = new JScrollPane(listaPunto);
		scrollMostrar.setBackground(Color.WHITE);
		scrollMostrar.setPreferredSize(new Dimension(260,200));
		
		add(scrollMostrar);
	}

	public void agregarPunto(Punto p) {
		listModel.addElement(p);
	}
	public ArrayList<Punto> darPuntosSeleccionados() {
		return(ArrayList<Punto>) listaPunto.getSelectedValuesList();
	}
	public void removerTodosLosElementos(){
		listModel.removeAllElements();
	}
	/**
	 * <pre>:primerPunto!=null, no hay ningun elemento en listModel
	 * @param primerPunto
	 */
	public void refrescarLista(Punto primerPunto){
		Punto current=primerPunto;
		while(current!=null){
			listModel.addElement(current);
			current=current.getSgtPunto();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			if(!listaPunto.isSelectionEmpty()){
				ArrayList<Punto>puntos=darPuntosSeleccionados();
				if(puntos.size()==2){
					PopUpMenuPunto2 menu = new PopUpMenuPunto2(principal,puntos);
					menu.show(this,e.getX(),e.getY());					
				}
				listaPunto.clearSelection();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
