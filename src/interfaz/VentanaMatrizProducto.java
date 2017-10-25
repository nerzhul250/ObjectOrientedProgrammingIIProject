package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaMatrizProducto extends JFrame {


	public VentanaMatrizProducto(double[][] matriz){
		setLayout(new GridLayout(matriz.length, matriz[0].length));

		for(int i =0;i<matriz.length;i++){
			for(int j=0;j<matriz[0].length;j++){
				add(new JLabel(matriz[i][j]+""));
			}
		}
		pack();
	}
}
