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

import mundo.Region;

public class PanelObjDibRegion extends JPanel implements MouseListener {
	
	private JList<Region> listaRegion;
	private DefaultListModel<Region> listModel;
	private InterfazMathy principal;
	
	public PanelObjDibRegion(InterfazMathy principal){
		this.principal=principal;
		listModel = new DefaultListModel<Region>();
		listaRegion = new JList<Region>(listModel);
		listaRegion.addMouseListener(this);
		
		JScrollPane scrollMostrar = new JScrollPane(listaRegion);
		scrollMostrar.setBackground(Color.WHITE);
		scrollMostrar.setPreferredSize(new Dimension(260,200));
		
		add(scrollMostrar);
	}
	public void agregarRegion(Region f){
		listModel.addElement(f);
	}
	public Region darRegionSeleccionado() {
		Region p=listaRegion.getSelectedValue();
		return p;
	}
	public void removerTodosLosElementos(){
		listModel.removeAllElements();
	}
	public void refrescarLista(ArrayList<Region> regiones){
		for (int i = 0; i < regiones.size(); i++) {
			agregarRegion(regiones.get(i));
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			if(listaRegion.isSelectionEmpty()){
				PopUpMenuRegionGen menu = new PopUpMenuRegionGen(principal);
				menu.show(this,e.getX(),e.getY());
			}else{
				PopUpMenuRegionPar menu = new PopUpMenuRegionPar(principal,darRegionSeleccionado());
				menu.show(this,e.getX(),e.getY());
				listaRegion.clearSelection();
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
