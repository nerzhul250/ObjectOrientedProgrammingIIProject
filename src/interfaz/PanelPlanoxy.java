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
import mundo.FormulaParaParametrizarIncompleta;
import mundo.MathyGen;

public class PanelPlanoxy extends JPanel implements MouseMotionListener,MouseWheelListener,MouseListener{
	private int centroW;
	private int centroH;
	
	private double alcance;
	
	private double mousePosx;
	private double mousePosy;
	
	private int priorPosw;
	private int priorPosh;
	
	private double traslX;
	private double traslY;
	private DecimalFormat df;
	
	private int anchoAd;
	
	private InterfazMathy principal;
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
		
		try {
			mundo.modificarCirculo(new Circunferencia("(x)^2+(y)^2=4"));
			mundo.darCirculo().modificarColor(Color.BLACK);
			mundo.darCirculo().dibujarse(g2d, alcance, traslY, traslX, 1);
			mundo.modificarCirculo(new Circunferencia("(x-2)^2+(y)^2=9"));
			mundo.darCirculo().modificarColor(Color.BLACK);
			mundo.darCirculo().dibujarse(g2d, alcance, traslY, traslX, 1);
		} catch (FormulaParaParametrizarIncompleta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < principal.darObjetosDibujables().size(); i++) {
			principal.darObjetosDibujables().get(i).dibujarse(g2d,alcance,traslY,traslX,MathyGen.ANCHOPLANO+anchoAd);
		}
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
//		mousePosx=(((e.getX()-centroW)*(alcanceX))/(MathyGen.ANCHOPLANO-centroW));
//		mousePosy=(((centroH-e.getY())*(alcanceY))/(MathyGen.LARGOPLANO-centroH));
//		centroW-=(e.getX()-centroW)*(1/2.0);
//		centroH-=(centroH-e.getY())*(1/2.0);
//		double k=e.getWheelRotation();
//		alcanceX+=k*(1/2.0);
//		alcanceY+=k*(1/2.0);
//		repaint();l
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
	public int darCentroW() {
		return centroW;
	}
	public void modificarCentroW(int centroW) {
		this.centroW = centroW;
	}
	public int darCentroH() {
		return centroH;
	}
	public void modificarCentroH(int centroH) {
		this.centroH = centroH;
	}
	public double darAlcance() {
		return alcance;
	}
	public void modificarAlcance(double alcance) {
		this.alcance = alcance;
	}
	public double darTraslX() {
		return traslX;
	}
	public void modificarTraslX(double traslX) {
		this.traslX = traslX;
	}
	public double darTraslY() {
		return traslY;
	}
	public void modificarTraslY(double traslY) {
		this.traslY = traslY;
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
