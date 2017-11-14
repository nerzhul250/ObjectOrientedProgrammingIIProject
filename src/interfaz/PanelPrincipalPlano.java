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
	private PanelPlanoxy ppxy;
	private PanelObjetoDibujable pod;
	public PanelPrincipalPlano(InterfazMathy in, MathyGen mundo){
		setLayout(new BorderLayout());
		ppxy=new PanelPlanoxy(in);
		pod=new PanelObjetoDibujable(in);
		
		add(pod,BorderLayout.WEST);
		add(ppxy,BorderLayout.EAST);
	}
	
	public PanelPlanoxy darPpxy() {
		return ppxy;
	}

	public void agregarFuncion(Funcion fun) {
		pod.agregarFuncion(fun);
	}
	public void agregarPunto(Punto punto) {
		pod.agregarPunto(punto);
	}
	public void agregarCurvaParametrica(CurvaParametrica cur){
		pod.agregarCurvaParametrica(cur);
	}
	public void refrescarPlano() {
		ppxy.repaint();;
	}
	public void agregarRegion(Region region) {
		pod.agregarRegion(region);
	}

	public void refrescarListaFunciones(Funcion raizFuncion) {
		pod.refrescarListaFunciones(raizFuncion);
	}

	public void refrescarListaRegiones(ArrayList<Region> listaRegiones) {
		pod.refrescarListaRegiones(listaRegiones);
	}
	public void refrescarListaCurvasParametricas(ArrayList<CurvaParametrica>c){
		pod.refrescarListaCurvasParametricas(c);
	}

	public void refrescarListaPuntos(Punto primerPunto) {
		pod.refrescarListaPuntos(primerPunto);
	}
}
