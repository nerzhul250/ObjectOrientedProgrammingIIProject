package mundo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Trigonometrico extends Funcion {
	/**
	 * Constantes del tipo de funcion trigonometrica
	 */
	public final static int SEN=1;
	public final static int COS=2;
	public final static int TAN=3;
	public final static int CSC=4;
	public final static int SEC=5;
	public final static int COT=6;
	public final static int ARCSEN=7;
	public final static int ARCCOS=8;
	public final static int ARCTAN=9;
	
	/**
	 * Numero que multiplica a la funcion trigonometrica
	 */
	private double amplitud;
	/**
	 * Numero que multiplica al argumento de la funcion trigonometrica
	 */
	private double velAng;
	/**
	 * la funcion trigonometrica
	 */
	private String funTrig;
	/**
	 * Tipo de funcion trigonometrica
	 */
	private int tipo;
	/**
	 * Construye la funcion trigonometrica
	 * @param form
	 */
	public Trigonometrico(String form){
		amplitud=0;
		velAng=0;
	     parsearTrigonometrico(0,(form+"T").toCharArray());  
	}
	/**
	 * Da la funcion trigonometrica
	 * @return
	 */
	public String getFunTrig() {
		return funTrig;
	}
	/**
	 * Extrae la informacion de la representacion de la funcion del usuario
	 * @param i
	 * @param a
	 */
	private void parsearTrigonometrico(int i, char[] a) {
		while(a[i]!='T'){
			char c=a[i];
			if(Character.isDigit(c) || c=='+' || c=='-'){
				double[] ke=parseNumber(i,a);
				amplitud=ke[0];
				i=(int) ke[1];
			}else if(c=='('){
				double[] ke=parseNumber(i+1,a);
				velAng=ke[0];
				i=(int) ke[1];
			}else if(Character.isAlphabetic(c)){
				String[] nom=parseTri(i,a);
				String nam=nom[0];
				if(nam.equalsIgnoreCase("sen") ||nam.equalsIgnoreCase("sin")){
					tipo=SEN;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("cos")){
					tipo=COS;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("tan")){
					tipo=TAN;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("csc")){
					tipo=CSC;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("sec")){
					tipo=SEC;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("cot")){
					tipo=COT;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("arcsen")){
					tipo=ARCSEN;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("arccos")){
					tipo=ARCCOS;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("arctan")){
					tipo=ARCTAN;
					funTrig=nam;
				}
				i=Integer.parseInt(nom[1]);
			}
			i++;	
		}
		if(velAng==0){
			velAng=1;
		}
		if(amplitud==0){
			amplitud=1;
		}
	}
	/**
	 * Extrae la informacion de la representacion de la funcion del usuario
	 * @param i
	 * @param a
	 */
	private String[] parseTri(int i, char[] a) {
		StringBuilder sb=new StringBuilder();
		while(Character.isAlphabetic(a[i])){
			sb.append(a[i]);
			i++;
		}
		String ka=sb.toString();
		String ke=(i-1)+"";
		String[] ki={ka,ke};
		return ki;
	}
	/**
	 * Metodo que parsea numeros en strings
	 * @param i
	 * @param a
	 * @return
	 */
	private double[] parseNumber(int i, char[] a) {
		double nam=0;
		int c=a[i];
		int integer=0;
		int neg=1;
		if (c == '-') {
            neg = -1;
            c = a[++i];
        }
		if(c =='+'){
			c = a[++i];
	    }
        while ((c >= '0' && c <= '9')) {
            integer *= 10;
            integer += c - '0';
            c=a[++i];
        }
        nam=integer;
        if(c=='.'){
        	c=a[++i];
        	int counter=1;
        	while ((c >= '0' && c <= '9')) {
        		double ke=(c - '0')/Math.pow(10,counter);
	            nam+=ke;
	            counter++;
	            c=a[++i];
	        }
        }
        double[] ans={neg * nam,i-1};
        return ans;
    }
	/**
	 * Metodo que dibuja a la funcion trigonometrica
	 */
	@Override
	public void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho) {
		g2d.setColor(getColor());
		g2d.setStroke(new BasicStroke((float) getGrosor()));
		double valx1=alcance+traslX;
		double valy1=computarValor(valx1);
		int h1=(int)((valy1-alcance-traslY)*MathyGen.LARGOPLANO/(-2*alcance));
		for (int i = 1; i < ancho; i++) {
			double valx=(2*alcance/MathyGen.ANCHOPLANO)*i-alcance+traslX;
			double valy=computarValor(valx);
			int h=(int)((valy-alcance-traslY)*MathyGen.LARGOPLANO/(-2*alcance));
			g2d.drawLine(i-1,h1,i,h);
			h1=h;
		}
	}
	/**
	 * Computa el valor de la funcion trigonometrica evaluada en un x
	 */
	@Override
	public double computarValor(double x) {
		double y=0;
		if(tipo==1){
			y=amplitud*Math.sin(velAng*x);
		}else if(tipo==2){
			y=amplitud*Math.cos(velAng*x);
		}else if(tipo==3){
			y=amplitud*Math.tan(velAng*x);
		}else if(tipo==4){
			try{
				y=amplitud/Math.sin(velAng*x);
			}catch(Exception e){
				y=0;
			}
		}else if(tipo==5){
			try{
				y=amplitud/Math.cos(velAng*x);
			}catch(Exception e){
				y=0;
			}
		}else if(tipo==6){
			try{
				y=amplitud/Math.tan(velAng*x);
			}catch(Exception e){
				y=0;
			}
		}else if(tipo==7){
			if(Math.abs(x)>1){
				y=0;
			}else{
				y=amplitud*Math.asin(x*velAng);
			}
		}else if(tipo==8){
			if(Math.abs(x)>1){
				y=0;
			}else{
				y=amplitud*Math.acos(x*velAng);
			}
		}else if(tipo==9){
			y=amplitud*Math.atan(velAng*x);
		}
		return y;
	}
	/**
	 * da la amplitud de la funcion trigonometrica
	 * @return
	 */
	public double getAmplitud() {
		return amplitud;
	}
	/**
	 * Modifica la amplitud de la funcion trigonometrica
	 * @param amplitud
	 */
	public void setAmplitud(double amplitud) {
		this.amplitud = amplitud;
	}
	/**
	 * da la velocidad angular de la funcion trigonometrica
	 * @return
	 */
	public double getVelAng() {
		return velAng;
	}
	/**
	 * modifica la velocidad angular de la funcion trigonometrica
	 * @param velAng
	 */
	public void setVelAng(double velAng) {
		this.velAng = velAng;
	}
	/**
	 * Compara dos funciones trigonometricas
	 * @param g2
	 * @return
	 */
	public int comparar(Trigonometrico g2) {
		double dif=tipo-g2.getTipo();
		if(dif!=0){
			dif=(dif>0)?-1:1;
		}else{
			dif=amplitud-g2.getAmplitud();
			if(dif!=0){
				dif=(dif>0)?1:-1;
			}else{
				dif=velAng-g2.getVelAng();
				if(dif!=0)dif=(dif>0)?1:-1;
			}
		}
		return (int)dif;
	}
	/**
	 * da el tipo de la funcion trigonometrica
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}
	/**
	 * Da la representacion estandar de esta funcion trigonometrica
	 */
	@Override
	public String toString() {
		return amplitud+funTrig+"("+velAng+"x)";
	}
}