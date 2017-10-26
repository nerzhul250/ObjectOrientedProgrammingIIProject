package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mundo.Dibujable;

public class PanelObjetoDibujable extends JPanel{
	private JList<Dibujable> listaObj;
	private DefaultListModel listModel;
	public PanelObjetoDibujable(){
		listModel = new DefaultListModel();
		listaObj = new JList<Dibujable>(listModel);
		
		JScrollPane scrollMostrar = new JScrollPane(this.listaObj);
		scrollMostrar.setBackground(Color.WHITE);
		add(scrollMostrar);
	}
	public void agregarObjetoDibujable(Dibujable b){
		listModel.addElement(b);
	}
	public void refrescarLista(ArrayList<Dibujable>kaka){
		listModel.removeAllElements();
		for (int i = 0; i < kaka.size(); i++) {
			listModel.addElement(kaka.get(i));
		}
	}
	public Dibujable darDibujableSeleccionado() {
		Dibujable p=listaObj.getSelectedValue();
		return p;
	}
}
