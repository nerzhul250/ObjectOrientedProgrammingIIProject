package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mundo.Dibujable;
import mundo.Funcion;
import mundo.Punto;
import mundo.Region;

public class PanelObjetoDibujable extends JPanel{
	private PanelObjDibFuncion podf;
	private PanelObjDibRegion podr;
	private PanelObjDibPunto podp;
	private PanelObjDibCurvaParametrica podc;
	private InterfazMathy principal;
	public PanelObjetoDibujable(InterfazMathy in){
		principal=in;
		setLayout(new BorderLayout());
		JPanel aux= new JPanel();
		aux.setLayout(new GridLayout(4,1));
		podf=new PanelObjDibFuncion(principal);
		podr=new PanelObjDibRegion(principal);
		podp=new PanelObjDibPunto(principal);
		podc= new PanelObjDibCurvaParametrica(principal);
		
		aux.add(podf);
		aux.add(podc);
		aux.add(podr);
		aux.add(podp);
		JScrollPane scroll= new JScrollPane(aux);
		scroll.setPreferredSize(new Dimension(270,600));
		add(scroll, BorderLayout.CENTER);
	}
	public void agregarFuncion(Funcion fun) {
		podf.agregarFuncion(fun);
	}
	public void agregarRegion(Region r) {
		podr.agregarRegion(r);
	}
	public void agregarPunto(Punto p) {
		podp.agregarPunto(p);
	}
	public void refrescarListaFunciones(Funcion raizFuncion) {
		podf.removerTodosLosElementos();
		podf.refrescarLista(raizFuncion);
	}
	public void refrescarListaRegiones(ArrayList<Region> listaRegiones) {
		podr.removerTodosLosElementos();
		podr.refrescarLista(listaRegiones);
	}
	public void refrescarListaPuntos(Punto primerPunto) {
		podp.removerTodosLosElementos();
		podp.refrescarLista(primerPunto);
	}
}
