package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import mundo.NoEsNumeroException;

public class VentanaMatrizB extends JFrame implements ActionListener{

	private InterfazMathy principal;
	private JTextField[] matriz;
	private JButton btnCalcSolucion;
	public final static String CAL_SOLUCION="calccSo";
	public VentanaMatrizB(InterfazMathy p){
		principal=p;
		btnCalcSolucion=new JButton("Calcular solución");
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
	public void actionPerformed(ActionEvent arg0) {
		String comando=arg0.getActionCommand();
		if(comando.equals(CAL_SOLUCION)){
			principal.calcularSolucionesMatriz1();
		}
		
	}
}
