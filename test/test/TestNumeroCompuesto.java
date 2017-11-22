package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mundo.Circunferencia;
import mundo.FormulaParaParametrizarIncompleta;
import mundo.NumeroCompuesto;
import mundo.NumeroPrimo;

import org.junit.Test;

public class TestNumeroCompuesto {
	private NumeroCompuesto numeroCompuesto;
	private void setupEscenario1(){
		numeroCompuesto=null;
	}
	private void setupEscenario2(){
		numeroCompuesto=new NumeroCompuesto("12");
	}
	private void setupEscenario3(){
		numeroCompuesto=new NumeroCompuesto("252");
	}
	
	@Test
	public void testInicializacionNumeroCompuesto() {
		setupEscenario1();
		numeroCompuesto=new NumeroCompuesto("456879");
		assertEquals(456879,numeroCompuesto.getNumero().intValue());
	}
	@Test
	public void testGetDivisores(){
		setupEscenario2();
		assertEquals("1 2 3 4 6 12 ",numeroCompuesto.getDivisores());
	}
	
	@Test
	public void testGetFactorizacionPrima(){
		setupEscenario3();
		ArrayList<NumeroPrimo>primosEncontrados=new ArrayList<NumeroPrimo>();
		primosEncontrados.add(new NumeroPrimo("2",1));
		primosEncontrados.add(new NumeroPrimo("3",2));
		primosEncontrados.add(new NumeroPrimo("5",3));
		primosEncontrados.add(new NumeroPrimo("7",4));
		primosEncontrados.add(new NumeroPrimo("11",5));
		primosEncontrados.add(new NumeroPrimo("13",6));
		primosEncontrados.add(new NumeroPrimo("17",7));
		primosEncontrados.add(new NumeroPrimo("23",8));
		numeroCompuesto.calcularFactoresPrimos(primosEncontrados);
		assertEquals(numeroCompuesto.getFactorizacionPrima(),"2^2 3^2 7^1");
	}
	

}