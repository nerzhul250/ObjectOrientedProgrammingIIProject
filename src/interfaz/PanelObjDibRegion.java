package interfaz;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mundo.Region;

public class PanelObjDibRegion extends JPanel{
	private JList<Region> listaFuncion;
	private DefaultListModel<Region> listModel;
	public PanelObjDibRegion(InterfazMathy principal){
		listModel = new DefaultListModel<Region>();
		listaFuncion = new JList<Region>(listModel);
		
		JScrollPane scrollMostrar = new JScrollPane(listaFuncion);
		scrollMostrar.setBackground(Color.WHITE);
		
		add(scrollMostrar);
	}
	public void agregarRegion(Region f){
		listModel.addElement(f);
	}
	public Region darFuncionSeleccionado() {
		Region p=listaFuncion.getSelectedValue();
		return p;
	}
	public void removerTodosLosElementos(){
		listModel.removeAllElements();
	}
	/**
	 * <pre>:regiones!=null, no hay ningun elemento en listModel
	 * @param regiones
	 */
	public void refrescarLista(ArrayList<Region>regiones){
		for (int i = 0; i < regiones.size(); i++) {
			listModel.addElement(regiones.get(i));
		}
	}
}
