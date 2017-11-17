package test;

import static org.junit.Assert.*;
import mundo.Trigonometrico;

import org.junit.Test;

public class TestTrigonometrico {
	private Trigonometrico trigonometrico;
	private void setUpEscenario1(){
		trigonometrico=null;
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

}
