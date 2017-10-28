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

public class PanelObjetoDibujable extends JPanel{
	private PanelObjDibFuncion podf;
	private PanelObjDibRegion podr;
	private PanelObjDibPunto podp;
	
	private InterfazMathy principal;
	public PanelObjetoDibujable(InterfazMathy in){
		principal=in;
		setLayout(new GridLayout(3,1));
		
		podf=new PanelObjDibFuncion(principal);
		podr=new PanelObjDibRegion(principal);
		podp=new PanelObjDibPunto(principal);
		
		add(podf);
		add(podr);
		add(podp);
	}
	public void agregarFuncion(Funcion fun) {
		podf.agregarFuncion(fun);
	}
}
