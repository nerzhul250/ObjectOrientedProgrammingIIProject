package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public class Region implements Dibujable {

	private double area;
	private Color color;
	private ArrayList<Punto>frontera;
	
	public Region(ArrayList<Punto> fr,Color color){
		frontera=fr;
		this.color=color;
	}
	//TODO
	//COMPLETE MOTHERFUCKER
	public void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho) {
		g2d.setColor(getColor());
		int n=frontera.size();
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

}