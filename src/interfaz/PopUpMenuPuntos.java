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

public class PopUpMenuPuntos extends JPopupMenu implements ActionListener {
	public final static String AGREGAREGION="AGREGAREGION";
	private JMenuItem itAgregarRegion;
	
	private InterfazMathy principal;

	private Color color;
	private ArrayList<Punto>frontera;
	public PopUpMenuPuntos(InterfazMathy principal, ArrayList<Punto> puntos) {
		this.principal=principal;
		frontera=puntos;
		color=puntos.get(0).getColor();
        itAgregarRegion = new JMenuItem("Fomar region");
        itAgregarRegion.addActionListener(this);
        itAgregarRegion.setActionCommand(AGREGAREGION);
        add(itAgregarRegion);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGAREGION)){
			principal.agregarRegion(frontera,color);
		}
	}
}