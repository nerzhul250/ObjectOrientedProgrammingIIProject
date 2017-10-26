package test;

import static org.junit.Assert.*;
import mundo.Polinomio;

import org.junit.Test;

public class TestPolinomio {
	
	private Polinomio polinomio;
	
	private void setUpEscenario1(){
		polinomio=null;
	}
	@Test
	public void testInicializacionPolinomio() {
		setUpEscenario1();
		polinomio=new Polinomio("4x^2+3x+2x");
		assertTrue(polinomio.getCoeficientes().get(0)==0 &&polinomio.getCoeficientes().get(1)==5 &&
				polinomio.getCoeficientes().get(2)==4);
		polinomio=new Polinomio("1x+1x^2+1x^3+1x^4+1x+1");
		for (int i = 0; i < polinomio.getCoeficientes().size(); i++) {
			System.out.println(polinomio.getCoeficientes().get(i));
		}
	}

}
