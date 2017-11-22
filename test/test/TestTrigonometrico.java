package test;

import static org.junit.Assert.*;
import mundo.Trigonometrico;

import org.junit.Test;

public class TestTrigonometrico {
	private Trigonometrico trigonometrico;
	private void setUpEscenario1(){
		trigonometrico=null;
	}
	private void setUpEscenario2(){
		trigonometrico=new Trigonometrico("cos(x)");
	}
	
	@Test
	public void testInicializacionTrigonometrico() {
		setUpEscenario1();
		trigonometrico=new Trigonometrico("2sin(x3)");
		assertTrue(trigonometrico.getFunTrig().equals("sin"));
		trigonometrico=new Trigonometrico("45cos(x2)");
		assertTrue(trigonometrico.getFunTrig().equals("cos"));
		trigonometrico=new Trigonometrico("36tan(-12x)");
		assertTrue(trigonometrico.getFunTrig().equals("tan"));
	}
	@Test
	public void testComputarValor() {
		setUpEscenario2();
		assertTrue(Math.abs(trigonometrico.computarValor(3.14)+1)<1);
	}
	

}
