package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import mundo.Funcion;

public class PanelObjDibFuncion extends JPanel implements MouseListener {
	
	private JList<Funcion> listaFuncion;
	private DefaultListModel<Funcion> listModel;
	private InterfazMathy principal;
	
	public PanelObjDibFuncion(InterfazMathy in){
		principal=in;
		
		setBorder(new TitledBorder("Funciones"));
		
		listModel = new DefaultListModel<Funcion>();
		listaFuncion = new JList<Funcion>(listModel);
		listaFuncion.addMouseListener(this);
		
		JScrollPane scrollMostrar = new JScrollPane(listaFuncion);
		scrollMostrar.setBackground(Color.WHITE);
		scrollMostrar.setPreferredSize(new Dimension(260,200));
		
		add(scrollMostrar);
	}
	public void agregarFuncion(Funcion f){
		listModel.addElement(f);
	}
	public void refrescarLista(Funcion raiz){
		if(raiz!=null){
			agregarFuncion(raiz);
			if(raiz.getFunDe()!=null){
				refrescarLista(raiz.getFunDe());
			}
			if(raiz.getFunIz()!=null){
				refrescarLista(raiz.getFunIz());
			}
		}
	}
	public Funcion darFuncionSeleccionado() {
		Funcion p=listaFuncion.getSelectedValue();
		return p;
	}
	public void removerTodosLosElementos(){
		listModel.removeAllElements();
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			if(listaFuncion.isSelectionEmpty()){
				PopUpMenuFuncionGen menu = new PopUpMenuFuncionGen(principal);
				menu.show(listaFuncion,e.getX(),e.getY());
			}else{
				PopUpMenuFuncionPar menu = new PopUpMenuFuncionPar(principal,darFuncionSeleccionado());
				menu.show(listaFuncion,e.getX(),e.getY());
				listaFuncion.clearSelection();
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