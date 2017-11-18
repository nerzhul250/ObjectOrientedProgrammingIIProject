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

import mundo.CurvaParametrica;
import mundo.Dibujable;
import mundo.Funcion;
import mundo.Punto;
import mundo.Region;

public class PanelObjetoDibujable extends JPanel{
	/**
	 * Es el panel de funciones
	 */
	private PanelObjDibFuncion podf;
	/**
	 * Es el panel de regiones
	 */
	private PanelObjDibRegion podr;
	/**
	 * Es el panel de puntos
	 */
	private PanelObjDibPunto podp;
	/**
	 * Es el panel de curvas paramétricas
	 */
	private PanelObjDibCurvaParametrica podc;
	/**
	 * Es la interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Construye un panel donde contiene todos los paneles de objetos dibujables
	 * @param in Intefaz principal
	 */
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
	/**
	 * Agrega una nueva función al panel de funciones
	 * @param fun funcion a agregar
	 */
	public void agregarFuncion(Funcion fun) {
		podf.agregarFuncion(fun);
	}
	/**
	 * Agrega una curva paramétrica al panel de curvas
	 * @param cur curva a agregar
	 */
	public void agregarCurvaParametrica(CurvaParametrica cur){
		podc.agregarCurva(cur);
	}
	/**
	 * Agrega una región al panel de regiones
	 * @param r región a agregar
	 */
	public void agregarRegion(Region r) {
		podr.agregarRegion(r);
	}
	/**
	 * Agrega un punto al panel de puntos
	 * @param p puntos a agregar
	 */
	public void agregarPunto(Punto p) {
		podp.agregarPunto(p);
	}
	/**
	 * Refresca la lista de curvas paramétricas en el panel de curvas
	 * @param c lista de curvas paramétricas
	 */
	public void refrescarListaCurvasParametricas(ArrayList<CurvaParametrica> c){
		podc.eliminarTodosLosElementos();
		podc.refrescarLista(c);
	}
	/**
	 * Refresca la lista de funciones
	 * @param raizFuncion es el arbol de funciones
	 */
	public void refrescarListaFunciones(Funcion raizFuncion) {
		podf.removerTodosLosElementos();
		podf.refrescarLista(raizFuncion);
	}
	/**
	 * Refresca la lista de regiones
	 * @param listaRegiones lista de regiones
	 */
	public void refrescarListaRegiones(ArrayList<Region> listaRegiones) {
		podr.removerTodosLosElementos();
		podr.refrescarLista(listaRegiones);
	}
	/**
	 * Refresca la lista de puntos
	 * @param primerPunto puntos
	 */
	public void refrescarListaPuntos(Punto primerPunto) {
		podp.removerTodosLosElementos();
		podp.refrescarLista(primerPunto);
	}
}
