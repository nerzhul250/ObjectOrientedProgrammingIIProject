package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;

import mundo.Circunferencia;
import mundo.Dibujable;
import mundo.Elipse;
import mundo.FormulaParaParametrizarIncompleta;
import mundo.MathyGen;

public class PanelPlanoxy extends JPanel implements MouseMotionListener,MouseWheelListener,MouseListener{
	/**
	 * Componente ancho del centro en pixeles del planp
	 */
	private int centroW;
	/**
	 * Componente altura del centro en pixeles del plano
	 */
	private int centroH;
	
	/**
	 * Alcance del plano
	 */
	private double alcance;
	
	/**
	 * Posicion x actual del puntero
	 */
	private double mousePosx;
	/**
	 * Posicion y actual del puntero
	 */
	private double mousePosy;
	/**
	 * Posicion anterior del puntero en terminos de anchura
	 */
	private int priorPosw;
	/**
	 * Posicion anterior del puntero en terminos de altura
	 */
	private int priorPosh;
	/**
	 * Traslacion en x del planoXY
	 */
	private double traslX;
	/**
	 * Traslacion en y del planoXY
	 */
	private double traslY;
	private DecimalFormat df;
	/**
	 * Ancho adicional del panelPlanoxy
	 */
	private int anchoAd;
	/**
	 * Ventana principal
	 */
	private InterfazMathy principal;
	/**
	 * Construye el panel que contiene al planoXY
	 * @param principal
	 */
	public PanelPlanoxy(InterfazMathy principal){
		this.principal=principal;
		anchoAd=475;
		setBorder(new TitledBorder("Plano XY"));
		setPreferredSize(new Dimension(MathyGen.ANCHOPLANO+anchoAd,MathyGen.LARGOPLANO));
		
		traslX=0;
		traslY=0;
		alcance=10;
		centroW=(int) (MathyGen.ANCHOPLANO*(alcance-traslX)/(2*alcance));
		centroH=(int) (MathyGen.LARGOPLANO*(alcance+traslY)/(2*alcance));
		
		df=new DecimalFormat("0.00");
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
	}
	/**
	 * Dibuja todos los objetos dibujables y al planoxy
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0,0,MathyGen.ANCHOPLANO+anchoAd,MathyGen.LARGOPLANO);
		g2d.setColor(Color.BLACK);
		g2d.drawLine(centroW,MathyGen.LARGOPLANO,centroW,0);
		g2d.drawLine(MathyGen.ANCHOPLANO+anchoAd,centroH,0,centroH);
		for (int i = 0; i < MathyGen.ANCHOPLANO+anchoAd; i+=50) {
			double val=(2*alcance/MathyGen.ANCHOPLANO)*i-alcance+traslX;
			g2d.drawString(df.format(val),i-15,centroH);
			g2d.drawLine(i,centroH,i,centroH+4);
		}
		for (int i = 0; i < MathyGen.LARGOPLANO; i+=50) {
			double val=(-2*alcance/MathyGen.LARGOPLANO)*i+alcance+traslY;
			g2d.drawString(df.format(val),centroW,i+5);
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString("x="+df.format(mousePosx)+" "+"y="+df.format(mousePosy),30,30);
		
		for (int i = 0; i < principal.darObjetosDibujables().size(); i++) {
			principal.darObjetosDibujables().get(i).dibujarse(g2d,alcance,traslY,traslX,MathyGen.ANCHOPLANO+anchoAd);
		}
	
	}
	/**
	 * da el ancho en pixeles del planoxy
	 * @return
	 */
	public int darCentroW() {
		return centroW;
	}
	/**
	 * da la altura en pixeles del planoxy
	 * @return
	 */
	public int darCentroH() {
		return centroH;
	}
	/**
	 * da el alcance del planoxy
	 * @return
	 */
	public double darAlcance() {
		return alcance;
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		double k=e.getWheelRotation();
		alcance=alcance*Math.pow(Math.E,k/10);
		centroW=(int) (MathyGen.ANCHOPLANO*(alcance-traslX)/(2*alcance));
		centroH=(int) (MathyGen.LARGOPLANO*(alcance+traslY)/(2*alcance));
		repaint();
	}
	public void mouseDragged(MouseEvent e) {
		double difw=e.getX()-priorPosw;
		double difh=e.getY()-priorPosh;
		traslX-=difw/50;
		traslY+=difh/50;
		centroW=(int) (MathyGen.ANCHOPLANO*(alcance-traslX)/(2*alcance));
		centroH=(int) (MathyGen.LARGOPLANO*(alcance+traslY)/(2*alcance));
		priorPosw=e.getX();
		priorPosh=e.getY();
		repaint();
	}
	public void mouseMoved(MouseEvent e) {
		mousePosx=(2*alcance/MathyGen.ANCHOPLANO)*e.getX()-alcance+traslX;
		mousePosy=(-2*alcance/MathyGen.LARGOPLANO)*e.getY()+alcance+traslY;
		repaint();
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2 && e.getButton()==MouseEvent.BUTTON1){
			int w=e.getX();
			int h=e.getY();
			double x=((2*alcance*(w))/MathyGen.ANCHOPLANO)-alcance+traslX;
			double y=((-2*alcance*(h))/MathyGen.LARGOPLANO)+alcance+traslY;
			principal.crearPunto(x,y);
		}
	}
	public void mousePressed(MouseEvent e) {
		priorPosw=e.getX();
		priorPosh=e.getY();
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
