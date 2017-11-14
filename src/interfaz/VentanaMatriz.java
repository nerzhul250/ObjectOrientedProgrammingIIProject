package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class VentanaMatriz extends JFrame {

	private JScrollPane scroll;
	public VentanaMatriz(double[][] matriz){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JTable tabla= new JTable(matriz.length, matriz.length);
		for(int i =0; i<matriz.length;i++){
			for(int j=0;j<matriz[0].length;j++){
				tabla.setValueAt(matriz[i][j]+"", i, j);
			}
		}
		scroll=new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		add(scroll,BorderLayout.CENTER);
		pack();
	}
}
