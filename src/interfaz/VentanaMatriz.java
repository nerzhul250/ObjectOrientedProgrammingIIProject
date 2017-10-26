package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VentanaMatriz extends JFrame {

	private JScrollPane scroll;
	public VentanaMatriz(double[][] matriz){
		setLayout(new BorderLayout());
		JPanel aux= new JPanel();
		aux.setLayout(new GridLayout(matriz.length, matriz[0].length));
		for(int i =0;i<matriz.length;i++){
			for(int j=0;j<matriz[0].length;j++){
				aux.add(new JLabel(matriz[i][j]+""));
			}
			System.out.println("haciendo matriz");
		}
		scroll=new JScrollPane(aux);
		add(scroll,BorderLayout.CENTER);
		pack();
	}
}
