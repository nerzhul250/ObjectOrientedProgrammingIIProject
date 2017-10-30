package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class Punto implements Dibujable {
	
	private Color color;
	private Punto sgtPunto;
	
	private double x;
	private double y;
	
	public Punto(double x,double y){
		int R=(int) (Math.random()*256),G=(int) (Math.random()*256),B=(int) (Math.random()*256);
		color=new Color(R,G,B);
		this.x=x;
		this.y=y;
	}
	public void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho) {
		g2d.setColor(getColor());
		int w=(int) ((x+alcance-traslX)*(MathyGen.ANCHOPLANO)/(2*alcance));
		int h=(int) ((alcance+traslY-y)*(MathyGen.LARGOPLANO)/(2*alcance));
		g2d.fillOval(w-5,h-5,10,10);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Punto getSgtPunto() {
		return sgtPunto;
	}
	public void setSgtPunto(Punto sgtPunto) {
		this.sgtPunto = sgtPunto;
	}
	@Override
	public String toString() {
		DecimalFormat df=new DecimalFormat("0.00");
		return "X="+df.format(x)+" "+"Y="+df.format(y);
	}
}