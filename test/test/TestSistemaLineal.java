package test;

import static org.junit.Assert.*;

import org.junit.Test;

import hilos.HiloMultiplicacion;
import mundo.MatrizNoInvertibleException;
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
		double[][] m2={{212,43,124},{43,23,5},{423,12,42}};
		sistema= new SistemaLineal(ma,m2);
		matriz=ma;
	}
	private void setupEscenario4(){
		double[][] ma={{23,453},
				{1231,531}	
		};
		double[][]m1={{24,12},{14,23}};
		sistema= new SistemaLineal(ma,m1);
		matriz=ma;
	}
	public void setupEscenario5(){
		double[][] ma={{2,2},{2,2}};
		sistema= new SistemaLineal(ma, null);
		matriz= ma;
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
	
	@Test 
	public void probarMetodoMultiplicarMatrices(){
		setupEscenario3();
		HiloMultiplicacion hilo= new HiloMultiplicacion(sistema, matriz.length, 0);
		Thread hi= new Thread(hilo);
		hi.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double[][] m1 =sistema.darMatrizProducto();
		double[][] m2= {{2245.0,203.0,431.0},{76384.0,12884.0,10283.0},{547334.0,72622.0,181465.0}};
		for(int i =0;i<m1.length;i++){
			for(int j=0;j<m1[0].length;j++){
				assertTrue(m1[i][j]==m2[i][j]);
			}
		}
		setupEscenario4();
		hilo= new HiloMultiplicacion(sistema, matriz.length,0);
		hi= new Thread(hilo);
		hi.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m1=sistema.darMatrizProducto();
		double[][] m3={{6894,10695},{36978,26985}};
		for(int i =0;i<m1.length;i++){
			for(int j=0;j<m1[0].length;j++){
				assertTrue(m1[i][j]==m3[i][j]);
			}
		}
		
	}
	
	@Test
	public void probarMetodoCalcularSolucion(){
		setupEscenario3();
		try {
			double[] b={1.0,2.0,3.0}; 
			sistema.modificarMatrizB(b);
			sistema.determinarSolucionesSistema();
			double[] solucion =sistema.darSolucionSistema();
			assertTrue(solucion[0]==(-71029.0/445552));
			assertTrue(solucion[1]==(-64633.0/668328.0));
			assertTrue(solucion[2]==(179219.0/445552));
		} catch (MatrizNoInvertibleException e) {
			fail();
		}
		setupEscenario4();
		try {
			double[] b={1.0,2.0}; 
			sistema.modificarMatrizB(b);
			sistema.determinarSolucionesSistema();
			double[] solucion =sistema.darSolucionSistema();
			assertTrue(solucion[0]==(25.0/36362));
			assertTrue(solucion[1]==(79.0/36362));
		} catch (MatrizNoInvertibleException e) {
			fail();
		}
		setupEscenario5();
		try {
			double[] b={1.0,2.0}; 
			sistema.modificarMatrizB(b);
			sistema.determinarSolucionesSistema();
			fail();
		} catch (MatrizNoInvertibleException e) {
		}
	}

}
