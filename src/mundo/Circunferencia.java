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

	public double darPosicionX(int t) {
		double numero =radio*Math.cos(t)+ darCentroX();
		return numero;
	}

	public double darPosicionY(int t) {
		double numero = radio*Math.sin(t)+darCentroY();
		return numero;
	}

	public void dibujarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho) {
		g2d.setColor(darColor());
		for(int i =1; i<MathyGen.ANCHOPLANO*6*radio;i++){
			double wx=MathyGen.ANCHOPLANO*(darPosicionX(i)+alcance-traslX)/(2*alcance);
			double wy=MathyGen.LARGOPLANO*(darPosicionY(i)-alcance-traslY)/(-2*alcance);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawLine((int)wx, (int)wy,(int) wx, (int)wy);
			
		}
			
		
	}

	
}
