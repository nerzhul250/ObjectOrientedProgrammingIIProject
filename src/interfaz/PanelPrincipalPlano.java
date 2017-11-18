package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import mundo.CurvaParametrica;
import mundo.Funcion;
import mundo.MathyGen;
import mundo.Punto;
import mundo.Region;

public class PanelPrincipalPlano extends JPanel{
	/**
	 * Panel en donde se dibuja el plano
	 */
	private PanelPlanoxy ppxy;
	/**
	 * Panel en donde se despliega todos los objetos que son dibujables
	 */
	private PanelObjetoDibujable pod;
	/**
	 * COnstruye el panel principal del plano
	 * @param in
	 * @param mundo
	 */
	public PanelPrincipalPlano(InterfazMathy in, MathyGen mundo){
		setLayout(new BorderLayout());
		ppxy=new PanelPlanoxy(in);
		pod=new PanelObjetoDibujable(in);
		
		add(pod,BorderLayout.WEST);
		add(ppxy,BorderLayout.EAST);
	}
	/**
	 * Agrega una nueva funcion dibujable
	 * @param fun
	 */
	public void agregarFuncion(Funcion fun) {
		pod.agregarFuncion(fun);
	}
	/**
	 * Agrega un nuevo punto dibujable
	 * @param punto
	 */
	public void agregarPunto(Punto punto) {
		pod.agregarPunto(punto);
	}
	public void agregarCurvaParametrica(CurvaParametrica cur){
		pod.agregarCurvaParametrica(cur);
	}
	/**
	 * Refresca el planxy
	 */
	public void refrescarPlano() {
		ppxy.repaint();;
	}
	/**
	 * Agrega una region dibujable al panel de dibujo
	 * @param region
	 */
	public void agregarRegion(Region region) {
		pod.agregarRegion(region);
	}
	/**
	 * Refresca todas las funciones del panel de dibujable
	 * @param raizFuncion
	 */
	public void refrescarListaFunciones(Funcion raizFuncion) {
		pod.refrescarListaFunciones(raizFuncion);
	}
	/**
	 * Refresca todas las regiones del panel dibujable
	 * @param listaRegiones
	 */
	public void refrescarListaRegiones(ArrayList<Region> listaRegiones) {
		pod.refrescarListaRegiones(listaRegiones);
	}
	public void refrescarListaCurvasParametricas(ArrayList<CurvaParametrica>c){
		pod.refrescarListaCurvasParametricas(c);
	}
	/**
	 * Refresca todos los puntos del panel de dibujable
	 * @param primerPunto
	 */
	public void refrescarListaPuntos(Punto primerPunto) {
		pod.refrescarListaPuntos(primerPunto);
	}
}
