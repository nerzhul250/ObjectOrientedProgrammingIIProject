package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circunferencia extends CurvaParametrica {

	
	private Double radio;
	public Circunferencia(String form) throws FormulaParaParametrizarIncompleta {
		parsearFormula(0, form);
		
	}
	
	public void parsearFormula(int indice, String form) throws FormulaParaParametrizarIncompleta{
		if(indice<form.length()){
			if(form.charAt(indice)=='x' || form.charAt(indice)=='X'){
				String numero= buscarNumero(indice, form, "");
				Double nume=(double) 0;
				if(!numero.equals("")){
					nume= Double.parseDouble(numero);
					if(form.charAt(indice+1)=='+'){
						nume=nume*(-1);
					}
				}
				super.modificarCentroX(nume);
			}else if(form.charAt(indice)=='Y'||form.charAt(indice)=='y'){
				String numero = buscarNumero(indice, form, "");
				Double nume= (double)0;
				if(!numero.equals("")){
					nume= Double.parseDouble(numero);
					if(form.charAt(indice+1)=='+'){
						nume=nume*(-1);
					}
				}
				super.modificarCentroY(nume);
			}else if(form.charAt(indice)=='='){
				String numero = buscarNumero(indice, form, "");
				if(numero.equals("")){
					throw new FormulaParaParametrizarIncompleta("La siguiente fórmula para parametrizar está incompleta o no es válida: ", form);
				}else{
					radio= Math.sqrt(Double.parseDouble(numero));
				}
			}
			indice++;
			parsearFormula(indice, form);
		}else if(radio ==null){
			throw new FormulaParaParametrizarIncompleta("La siguiente fórmula para parametrizar está incompleta o no es válida: ", form);
		}
	}
	
	@Override
	public String toString(){
		
		String men="(x"+((super.darCentroX()*-1<0)?+super.darCentroX()*-1:"+"+super.darCentroX()*-1)+
				")^2+(y"+((super.darCentroY()*-1<0)?+super.darCentroY()*-1:"+"+super.darCentroY()*-1)+")^2="+
				(radio*radio);
		return men;
	}
	
	public String buscarNumero(int indice,String form, String num){
		String devolver= num;
		if(indice<form.length()){
			if(form.charAt(indice)!=')'){
				try{
					devolver+= Integer.parseInt(form.charAt(indice)+"");
				}catch(Exception e){
					if(form.charAt(indice)=='.'){
						devolver+='.';
					}
				}
				indice++;
				devolver= buscarNumero(indice, form, devolver);
			}
			
		}
		return devolver;
	}
	
	public double darRadio(){
		return radio;
	}

	public double darPosicionX(double t) {
		double numero =radio*Math.cos(t)+ darCentroX();
		return numero;
	}

	public double darPosicionY(double t) {
		double numero = radio*Math.sin(t)+darCentroY();
		return numero;
	}

	public void dibujarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho) {
		g2d.setColor(darColor());
		g2d.setStroke(new BasicStroke(1));
		int wx1=(int) (MathyGen.ANCHOPLANO*(darPosicionX(0)+alcance-traslX)/(2*alcance));
		int wy1=(int) (MathyGen.LARGOPLANO*(darPosicionY(0)-alcance-traslY)/(-2*alcance));
		for(int i =1; i<1001;i++){
			int wx=(int) (MathyGen.ANCHOPLANO*(darPosicionX(i*2*Math.PI/1000.0)+alcance-traslX)/(2*alcance));
			int wy=(int) (MathyGen.LARGOPLANO*(darPosicionY(i*2*Math.PI/1000.0)-alcance-traslY)/(-2*alcance));
			g2d.drawLine(wx1,wy1, wx,wy);
			wy1=wy;
			wx1=wx;
		}
	}

	
}
