package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Funcion implements Dibujable, Computable, Comparable  {

	private String nombre;
	private Color color;
	private double grosor;
	private int nivelDeBelleza;
	private Funcion funIz;
	private Funcion funDe;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getNivelDeBelleza() {
		return nivelDeBelleza;
	}

	public void setNivelDeBelleza(int nivelDeBelleza) {
		this.nivelDeBelleza = nivelDeBelleza;
	}

	public Funcion getFunIz() {
		return funIz;
	}

	public void setFunIz(Funcion funIz) {
		this.funIz = funIz;
	}

	public Funcion getFunDe() {
		return funDe;
	}

	public void setFunDe(Funcion funDe) {
		this.funDe = funDe;
	}
	
	public double getGrosor() {
		return grosor;
	}

	public void setGrosor(double grosor) {
		this.grosor = grosor;
	}
	
	public int compareTo(Object o) {
		int thisNum=0;
		int oNum=0;
		if(this instanceof General){
			thisNum=4;
		}else if(this instanceof Polinomio){
			thisNum=3;
		}else if(this instanceof Exponencial){
			thisNum=2;
		}else if(this instanceof Trigonometrico){
			thisNum=1;
		}
		if(o instanceof General){
			oNum=4;
		}else if(o instanceof Polinomio){
			oNum=3;
		}else if(o instanceof Exponencial){
			oNum=2;
		}else if(o instanceof Trigonometrico){
			oNum=1;
		}
		int dif=thisNum-oNum;
		if(dif!=0){
			dif=(dif>0)?1:-1;
		}
		return dif;
	}

	public abstract void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho, int largo);
	
	public abstract double computarValor(double x);
}