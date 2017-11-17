package mundo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Trigonometrico extends Funcion {

	private double amplitud;
	private double velAng;
	private String funTrig;
	private int tipo;
	
	public Trigonometrico(String form){
		amplitud=0;
		velAng=0;
	     parsearTrigonometrico(0,(form+"T").toCharArray());  
	}
	public String getFunTrig() {
		return funTrig;
	}
	public void setFunTrig(String funTrig) {
		this.funTrig = funTrig;
	}
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
					tipo=1;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("cos")){
					tipo=2;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("tan")){
					tipo=3;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("csc")){
					tipo=4;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("sec")){
					tipo=5;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("cot")){
					tipo=6;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("arcsen")){
					tipo=7;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("arccos")){
					tipo=8;
					funTrig=nam;
				}else if(nam.equalsIgnoreCase("arctan")){
					tipo=9;
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
	public double getAmplitud() {
		return amplitud;
	}
	public void setAmplitud(double amplitud) {
		this.amplitud = amplitud;
	}
	public double getVelAng() {
		return velAng;
	}
	public void setVelAng(double velAng) {
		this.velAng = velAng;
	}
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
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return amplitud+funTrig+"("+velAng+"x)";
	}
}