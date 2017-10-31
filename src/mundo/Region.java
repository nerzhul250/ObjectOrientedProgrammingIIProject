package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Region implements Dibujable,Comparable {

	private double area;
	private Color color;
	private ArrayList<Punto>frontera;
	
	public Region(ArrayList<Punto> fr,Color color){
		frontera=fr;
		double sum=0;
		for (int i = 2; i < frontera.size(); i++) {
			double ux=frontera.get(0).getX()-frontera.get(i).getX();
			double uy=frontera.get(0).getY()-frontera.get(i).getY();
			double vx=frontera.get(0).getX()-frontera.get(i-1).getX();
			double vy=frontera.get(0).getY()-frontera.get(i-1).getY();
			sum+=ux*vy-vx*uy;
		}
		sum=sum/2;
		area=Math.abs(sum);
		this.color=color;
	}
	//TODO
	//COMPLETE MOTHERFUCKER
	public void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho) {
		g2d.setColor(getColor());
		int n=frontera.size();
		int[] w=new int[n];
		int[] h=new int[n];
		for (int i = 0; i < frontera.size(); i++) {
			w[i]=(int) ((frontera.get(i).getX()+alcance-traslX)*(MathyGen.ANCHOPLANO)/(2*alcance));
			h[i]=(int) ((alcance+traslY-frontera.get(i).getY())*(MathyGen.LARGOPLANO)/(2*alcance));
		}
		g2d.fillPolygon(w,h,n);
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public int compareTo(Object o) {
		Region r=(Region)o;
		double dif=area-r.getArea();
		if(dif!=0){
			dif=(dif<0)?-1:1;
		}
		return (int)dif;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}