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
	
	private void setUpEscenario3(){
		polinomio=new Polinomio("2x^5-3x^2+4x^4-1x^3+2x^1");
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
	
	@Test
	public void testComputarValor(){
		setUpEscenario2();
		assertTrue(polinomio.computarValor(2)==4);
		assertTrue(polinomio.computarValor(3)==9);
		assertTrue(polinomio.computarValor(4)==16);
	}
	
	@Test
	public void testCompararPolinomio(){
		setUpEscenario2();
		Polinomio p2=new Polinomio("2x^2");
		assertTrue(p2.comparar(polinomio)==1);
	}
	
	@Test
	public void testToSringPolinomio(){
		setUpEscenario3();
		System.out.println(polinomio.toString());
		assertEquals(polinomio.toString(),"+2.00x^1-3.00x^2-1.00x^3+4.00x^4+2.00x^5");;
	}

}
