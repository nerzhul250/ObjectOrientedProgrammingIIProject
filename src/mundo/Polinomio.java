package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Polinomio extends Funcion {

	private ArrayList<Double> coeficientes;
	
	public Polinomio(String form){
		 InputStream inputStream = System.in;
	     ScanReader in = new ScanReader(inputStream);
	}
	@Override
	public void dibujarse(Graphics2D g2d, double alcance, double traslY,
			double traslX, int ancho, int largo) {
		g2d.setColor(getColor());
		g2d.setStroke(new BasicStroke((float) getGrosor()));
		double valx1=alcance+traslX;
		double valy1=computarValor(valx1);
		int h1=(int)((valy1-alcance-traslY)*MathyGen.LARGOPLANO/(-2*alcance));
		for (int i = 1; i < MathyGen.ANCHOPLANO; i++) {
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
}