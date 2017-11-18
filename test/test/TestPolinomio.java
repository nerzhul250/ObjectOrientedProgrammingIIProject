package test;

import static org.junit.Assert.*;
import mundo.Polinomio;

import org.junit.Test;

public class TestPolinomio {
	
	private Polinomio polinomio;
	
	private void setUpEscenario1(){
		polinomio=null;
	}
	
	private void setUpEscenario2(){
		polinomio=new Polinomio("1x^2");
	}
	
	@Test
	public void testInicializacionPolinomio() {
		setUpEscenario1();
		polinomio=new Polinomio("4x^2+3x+2x");
		assertTrue(polinomio.getCoeficientes().get(0)==0 &&polinomio.getCoeficientes().get(1)==5 &&
				polinomio.getCoeficientes().get(2)==4);
		polinomio=new Polinomio("1x^1+1x^2+1x^3+1x^4+1x^1+1");
		assertTrue(polinomio.getCoeficientes().get(0)==1 &&polinomio.getCoeficientes().get(1)==2 &&
				polinomio.getCoeficientes().get(2)==1 && polinomio.getCoeficientes().get(3)==1
				&& polinomio.getCoeficientes().get(4)==1);
	}
	public void testComputarValor(){
		setUpEscenario2();
		assertTrue(polinomio.computarValor(2)==2);
		assertTrue(polinomio.computarValor(3)==9);
		assertTrue(polinomio.computarValor(4)==16);
	}
	
	public void testCompararPolinomio(){
		setUpEscenario2();
		Polinomio p2=new Polinomio("2x^2");
		assertTrue(p2.comparar(polinomio)==1);
	}

}
