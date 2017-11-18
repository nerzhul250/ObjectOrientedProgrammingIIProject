package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Funcion implements Dibujable, Computable, Comparable, Serializable  {
	/**
	 * Guarda la representacion dada por el usuario
	 */
	private String forma;
	/**
	 * El color de la funcion
	 */
	private Color color;
	/**
	 * El grosor de la funcion
	 */
	private double grosor;
	/**
	 * El hijo izquierdo de la funcion
	 */
	private Funcion funIz;
	/**
	 * El hijo derecho de la funcion
	 */
	private Funcion funDe;
	/**
	 * El padre de la funcion
	 */
	private Funcion padre;
	/**
	 * da la representacion de la funcion dada por el usuario
	 * @return
	 */
	public String getForma() {
		return forma;
	}
	/**
	 * modifica la representacion de la funcion dada por el usuario
	 * @param forma
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}
	/**
	 * da el color de la funcion
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * modifica el color de la funcion
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * da el hijo izquierdo de la funcion
	 * @return
	 */
	public Funcion getFunIz() {
		return funIz;
	}
	/**
	 * modifica el hijo izquierdo de la funcion
	 * @param funIz
	 */
	public void setFunIz(Funcion funIz) {
		this.funIz = funIz;
	}
	/**
	 * da el hijo derecho de la funcion
	 * @return
	 */
	public Funcion getFunDe() {
		return funDe;
	}
	/**
	 * modifica el hijo derecho de la funcion
	 * @param funDe
	 */
	public void setFunDe(Funcion funDe) {
		this.funDe = funDe;
	}
	/**
	 * da el grosor de la funcion
	 * @return
	 */
	public double getGrosor() {
		return grosor;
	}
	/**
	 * modifica el grosor de la funcion
	 * @param grosor
	 */
	public void setGrosor(double grosor) {
		this.grosor = grosor;
	}
	/**
	 * da el padre de la funcion
	 * @return
	 */
	public Funcion getPadre() {
		return padre;
	}
	/**
	 * modifica el padre de la funcion
	 * @param padre
	 */
	public void setPadre(Funcion padre) {
		this.padre = padre;
	}
	/**
	 * Compara dos funciones segun su tipo
	 */
	public int compareTo(Object o) {
		int thisNum=0;
		int oNum=0;
		if(this instanceof Polinomio){
			thisNum=3;
		}else if(this instanceof Exponencial){
			thisNum=2;
		}else if(this instanceof Trigonometrico){
			thisNum=1;
		}
		if(o instanceof Polinomio){
			oNum=3;
		}else if(o instanceof Exponencial){
			oNum=2;
		}else if(o instanceof Trigonometrico){
			oNum=1;
		}
		int dif=thisNum-oNum;
		if(dif!=0){
			dif=(dif>0)?1:-1;
		}else{
			if(o instanceof Polinomio){
				Polinomio g1=(Polinomio)this;
				Polinomio g2=(Polinomio)o;
				dif=g1.comparar(g2);
			}else if(o instanceof Exponencial){
				Exponencial g1=(Exponencial)this;
				Exponencial g2=(Exponencial)o;
				dif=g1.comparar(g2);
			}else if(o instanceof Trigonometrico){
				Trigonometrico g1=(Trigonometrico)this;
				Trigonometrico g2=(Trigonometrico)o;
				dif=g1.comparar(g2);
			}
		}
		return dif;
	}
	public abstract void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho);
	
	public abstract double computarValor(double x);
}