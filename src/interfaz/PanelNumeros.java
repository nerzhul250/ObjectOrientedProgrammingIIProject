package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import mundo.Funcion;
import mundo.Numero;
import mundo.NumeroCompuesto;
import mundo.NumeroPrimo;

public class PanelNumeros extends JPanel implements MouseListener,ActionListener{
	
	public final static String GENERAR="GENERAR";
	
	private JList<NumeroPrimo> primosLista;
	private DefaultListModel<NumeroPrimo> modelPrimos;
	private JList<Numero> numerosLista;
	private DefaultListModel<Numero> modelNumeros;
	
	private JLabel lbelNumero;
	private JLabel lbFactorizacionPrima;
	private JLabel lbdivisores;
	private JLabel lbformaBinaria;
	
	private JButton butGenerarPrimos;
	
	private InterfazMathy principal;
	public PanelNumeros(InterfazMathy in){
		principal=in;
		
		setLayout(new BorderLayout());
		
		modelPrimos = new DefaultListModel<NumeroPrimo>();
		primosLista = new JList<NumeroPrimo>(modelPrimos);
		
		modelNumeros = new DefaultListModel<Numero>();
		numerosLista = new JList<Numero>(modelNumeros);
		numerosLista.addMouseListener(this);
		
		JScrollPane scrollMostrar = new JScrollPane(primosLista);
		scrollMostrar.setBackground(Color.WHITE);
		scrollMostrar.setPreferredSize(new Dimension(200,0));
		
		add(scrollMostrar,BorderLayout.WEST);
		
		scrollMostrar = new JScrollPane(numerosLista);
		scrollMostrar.setBackground(Color.WHITE);
		scrollMostrar.setPreferredSize(new Dimension(200,0));
		
		add(scrollMostrar,BorderLayout.EAST);
		
		butGenerarPrimos=new JButton("Generar Primos");
		butGenerarPrimos.addActionListener(this);
		butGenerarPrimos.setActionCommand("GENERAR");
		
		lbelNumero=new JLabel("0",JLabel.CENTER);
		lbelNumero.setFont(new Font("TimesRoman", Font.PLAIN,50));
		lbFactorizacionPrima=new JLabel("Factorizacion Prima: 0");
		lbdivisores=new JLabel("Divisores: ");
		lbformaBinaria=new JLabel("Representacion Binaria: 0");
		
		JPanel aux=new JPanel(new GridLayout(5,1));
		aux.add(lbelNumero);
		aux.add(lbFactorizacionPrima);
		aux.add(lbdivisores);
		aux.add(lbformaBinaria);
		aux.add(butGenerarPrimos);
		
		add(new JScrollPane(aux),BorderLayout.CENTER);
	}
	
	public void refrescarPrimos(ArrayList<NumeroPrimo> primos) {
		modelPrimos.removeAllElements();
		for (int i = 0; i < primos.size(); i++) {
			modelPrimos.addElement(primos.get(i));;
		}
	}
	
	public void refrescarNumeros(ArrayList<Numero> numeros) {
		modelNumeros.removeAllElements();
		for (int i = 0; i < numeros.size(); i++) {
			modelNumeros.addElement(numeros.get(i));
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
			principal.abrirDialogoAgregarNumero();
		}
		if(e.getButton()==MouseEvent.BUTTON1){
			if(!numerosLista.isSelectionEmpty()){
				refrescarNumeroActual(numerosLista.getSelectedValue());
				numerosLista.clearSelection();
			}
		}
	}
	
	private void refrescarNumeroActual(Numero sv) {
		lbelNumero.setText(sv.getNumero().toString());
		lbFactorizacionPrima.setText("Factorizacion Prima: "+sv.getFactorizacionPrima());
		lbdivisores.setText("Divisores: "+sv.getDivisores());
		lbformaBinaria.setText("Representacion Binaria: "+sv.getNumero().toString(2));
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals(GENERAR)){
			if(butGenerarPrimos.getText().equals("Generar Primos")){
				butGenerarPrimos.setText("Parar de generar y mostrar nuevos primos");
			}else{
				butGenerarPrimos.setText("Generar Primos");
			}
			principal.iniciarBusquedaPrimos();
		}
	}

	public void agregarNumero(Numero numero) {
		modelNumeros.addElement(numero);
	}
	
}
