package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import mundo.NoEsNumeroException;

public class VentanaMatrizB extends JFrame implements ActionListener{
	/**
	 * interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Es el vector de textFIELDS en donde se insertará el vector b de la matriz
	 */
	private JTextField[] matriz;
	/**
	 * Botón que da la orden de calcular la solución para la matriz 1
	 */
	private JButton btnCalcSolucion;
	/**
	 * Constante de calcular solucion
	 */
	public final static String CAL_SOLUCION="calccSo";
	/**
	 * Crea una nueva ventana que contiene casillas para ingresar el vector B de la matriz
	 * @param p interfaz principal
	 */
	public VentanaMatrizB(InterfazMathy p){
		principal=p;
		btnCalcSolucion=new JButton("Calcular solución");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		btnCalcSolucion.setActionCommand(CAL_SOLUCION);
		btnCalcSolucion.addActionListener(this);
		int tamano=principal.darTamanoMatrizB();
		setLayout(new GridLayout(tamano+1, 0));
		matriz= new JTextField[tamano];
		for(int i =0;i<tamano;i++){
			matriz[i]= new JTextField();
			add(matriz[i]);
		}
		add(btnCalcSolucion);
		pack();
	}
	/**
	 * Devuelve el vector B que el usuario ingresó
	 * @return devuelve la matriz B
	 * @throws NoEsNumeroException En caso de que una casilla no contenga un numero
	 */
	public double[] darMatrizB() throws NoEsNumeroException{
		double[]matriz= new double[this.matriz.length];
		for(int i =0;i<matriz.length;i++){
			try{
				
				matriz[i]=Integer.parseInt(this.matriz[i].getText());
			}catch(NumberFormatException e){
				throw new NoEsNumeroException("Debes ingresar un numero en la casila: ", (i+1)+"");
			}
		}
		return matriz;
	}
	/**
	 * Escucha los botones
	 */
	public void actionPerformed(ActionEvent arg0) {
		String comando=arg0.getActionCommand();
		if(comando.equals(CAL_SOLUCION)){
			principal.calcularSolucionesMatriz1();
		}
		
	}
}
