package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Polinomio extends Funcion {
	
	
	private ArrayList<Double> coeficientes;
	private HashMap<Integer,Double>gradosACoeficientes;
	private int grado;
	
	public Polinomio(String form){
	     grado=0;
	     coeficientes=new ArrayList<Double>();
	     gradosACoeficientes=new HashMap<Integer,Double>();
	     parsearPolinomio(0,(form+"T").toCharArray());  
	     for (int i = 0; i < grado+1; i++) {
	    	double val=((gradosACoeficientes.get(i)==null)?0:gradosACoeficientes.get(i));
			coeficientes.add(val);
		}
	}
	public ArrayList<Double> getCoeficientes() {
		return coeficientes;
	}

	public void setCoeficientes(ArrayList<Double> coeficientes) {
		this.coeficientes = coeficientes;
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
		double valy=coeficientes.get(coeficientes.size()-1);
		for (int i = coeficientes.size()-2; i>=0; i--) {
			valy=x*valy+coeficientes.get(i);
		}
		return valy;
	}
	/**
	 * Metodo para extraer los coeficientes del polinomio y su grado
	 * @param i
	 * @param a
	 */
	private void parsearPolinomio(int i,char[] a) {
		double nam=0;
		int paw=0;
		boolean num=false;
		boolean pow=false;
		while((!num || !pow) && i<a.length){
			int c=a[i];
			if(c=='-' || c=='+' ||(c >= '0' && c <= '9')){
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
			    nam=neg * nam;
				num=true;
			}
			if(c=='x'){
				if(a[++i]=='^'){
					c=a[++i];
					int integer=0;
					if(c =='+'){
						c = a[++i];
				    }
			        while ((c >= '0' && c <= '9')) {
			            integer *= 10;
			            integer += c - '0';
			            c=a[++i];
			        }
				    paw=integer;
				}else{
					paw=1;
				}
				if(paw>grado){
					grado=paw;
				}
				i--;
				pow=true;
			}
			if(num &&!pow){
				paw=0;
				pow=true;
				i--;
			}
			i++;
		}
		double val=((gradosACoeficientes.get(paw)==null)?0:gradosACoeficientes.get(paw));
		gradosACoeficientes.put(paw,val+nam);
		if(i<a.length){
			parsearPolinomio(i,a);
		}
	}
	@Override
	public String toString() {
		DecimalFormat df=new DecimalFormat("0.00");
		String nim=""+df.format(coeficientes.get(0));
		for (int i = 1; i <coeficientes.size() ; i++) {
			if(coeficientes.get(i)!=0){
				if(coeficientes.get(i)<0){
					nim=nim+df.format(coeficientes.get(i))+"x^"+i;
				}else{
					nim=nim+"+"+df.format(coeficientes.get(i))+"x^"+i;
				}
			}
		}
		return nim;
	}
	public int comparar(Polinomio g2) {
		double dif=0;
		if(coeficientes.size()==g2.getCoeficientes().size()){
			for (int i = coeficientes.size()-1; i>=0; i++) {
				if(coeficientes.get(i)!=g2.getCoeficientes().get(i)){
					dif=(coeficientes.get(i)-g2.getCoeficientes().get(i));
					if(dif!=0){
						dif=(dif<0)?-1:1;
					}
					break;
				}
			}
		}else{
			dif=coeficientes.size()-g2.getCoeficientes().size();
			if(dif!=0){
				dif=(dif<0)?-1:1;
			}
		}
		return (int)dif;
	}
}