package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Punto implements Dibujable,Serializable{
	
	/**
	 * Color del punto en el planoXY
	 */
	private Color color;
	/**
	 * El punto siguiente en la lista enlazada simple
	 */
	private Punto sgtPunto;
	
	/**
	 * Componente x de las coordenadas cartesianas
	 */
	private double x;
	/**
	 * Componente y de las coordenadas cartesianas
	 */
	private double y;
	
	/**
	 * Construye un punto a partir de una coordenada XY
	 * @param x
	 * @param y
	 */
	public Punto(double x,double y){
		int R=(int) (Math.random()*256),G=(int) (Math.random()*256),B=(int) (Math.random()*256);
		color=new Color(R,G,B);
		this.x=x;
		this.y=y;
	}
	/**
	 * Metodo encargado de dibujar el punto en el planoXY
	 */
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