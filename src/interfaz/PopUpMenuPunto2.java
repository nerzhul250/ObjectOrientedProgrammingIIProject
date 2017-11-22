package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import mundo.MathyGen;
import mundo.Punto;

public class PopUpMenuPunto2 extends JPopupMenu implements ActionListener{
	
	public final static String AGREGARLINEA="AGREGARLINEA";
	private JMenuItem itAgregarL;
	
	private InterfazMathy principal;
	
	private double m;
	private double b;
	private Color color;
	public PopUpMenuPunto2(InterfazMathy principal, ArrayList<Punto> puntos) {
		this.principal=principal;
		m=(puntos.get(1).getY()-puntos.get(0).getY())/(puntos.get(1).getX()-puntos.get(0).getX());
		b=puntos.get(1).getY()-m*puntos.get(1).getX();
		color=puntos.get(0).getColor();
        itAgregarL = new JMenuItem("Agregar linea");
        itAgregarL.addActionListener(this);
        itAgregarL.setActionCommand(AGREGARLINEA);
        add(itAgregarL);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGARLINEA)){
			DecimalFormat df=new DecimalFormat("0.00");
			String f="";
			if(m<0){
				f=df.format(b)+df.format(m)+"x^1";
			}else{
				f=df.format(b)+"+"+df.format(m)+"x^1";
			}
			principal.agregarFuncion(f,color,1,MathyGen.POLINOMIO);
		}
	}
}