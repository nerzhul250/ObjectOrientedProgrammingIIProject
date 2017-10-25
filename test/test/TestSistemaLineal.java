package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.SistemaLineal;

public class TestSistemaLineal {

	private SistemaLineal sistema;
	private double[][] matriz;
	
	private void setupEscenario1(){
		double[][] ma={{2,3,4,5,6,7,5},{23,453,123,533,41223,131412,12311},
				{423,12313,4532,1231,52324,1231,62342},
				{1231,531,623,664,623,655,634},{12312,54323,23123,54323,3432,123135,6554},
				{45423,56425,42346,64564,765453,74564,23423},
				{2654,64563,3454,234234,654645,2342,645}
				
		};
		sistema= new SistemaLineal(ma,null);
		matriz=ma;
		}
	
	private void setupEscenario2(){
		double[][] ma={{2,3,4,5,6,7},{23,453,123,533,41223,131412},
				{423,12313,4532,1231,52324,1231},
				{1231,531,623,664,623,655},{12312,54323,23123,54323,3432,123135},
				{45423,56425,42346,64564,765453,74564},
				
		};
		sistema= new SistemaLineal(ma,null);
		matriz=ma;
	}
	private void setupEscenario3(){
		double[][] ma={{2,3,4},{23,453,123},
				{1231,531,623}	
		};
		sistema= new SistemaLineal(ma,null);
		matriz=ma;
	}
	private void setupEscenario4(){
		double[][] ma={{23,453},
				{1231,531}	
		};
		sistema= new SistemaLineal(ma,null);
		matriz=ma;
	}
	@Test
	public void probarMetodoCalcularDeterminante(){
		setupEscenario1();
		assertTrue(sistema.calcularDeterminante(matriz)==-1.6926967628922743E29);
		setupEscenario2();
		assertTrue(sistema.calcularDeterminante(matriz)==-1.4171993323864636E23);
		setupEscenario3();
		assertTrue(sistema.calcularDeterminante(matriz)==-1336656);
		setupEscenario4();
		assertTrue(sistema.calcularDeterminante(matriz)==-545430);
	}

}
