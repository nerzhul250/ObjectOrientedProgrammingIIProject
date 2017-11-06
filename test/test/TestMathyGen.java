package test;

import static org.junit.Assert.*;

import java.awt.Color;

import mundo.FuncionYaExisteException;
import mundo.MathyGen;
import mundo.Polinomio;

import org.junit.Test;

public class TestMathyGen {
	private MathyGen mathyGen;
	
	private void setUpEscenario1(){
		mathyGen=new MathyGen();
	}
	@Test
	public void testAgregarFuncion() {
		setUpEscenario1();
		assertFalse(mathyGen.estaEnElArbol(new Polinomio("1x^2"),mathyGen.getRaizFuncion()));
		try {
			mathyGen.agregarFuncion("1x^2",Color.white,2,3);
		} catch (FuncionYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(mathyGen.estaEnElArbol(new Polinomio("1x^2"),mathyGen.getRaizFuncion()));
	}

}
