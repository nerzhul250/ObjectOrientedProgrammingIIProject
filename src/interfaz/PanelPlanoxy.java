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

import mundo.Dibujable;
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
	
	private MathyGen mundo;
	
	private int anchoAd;
	
	private ArrayList<Dibujable>objetosDibujables;
	public PanelPlanoxy(MathyGen mun){
		anchoAd=475;
		mundo=mun;
		setBorder(new TitledBorder("Plano XY"));
		setPreferredSize(new Dimension(MathyGen.ANCHOPLANO+anchoAd,MathyGen.LARGOPLANO));
		
		objetosDibujables=new ArrayList<Dibujable>();
		
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
		
		for(int i=1;i<MathyGen.ANCHOPLANO+anchoAd;i++){
			double wx=MathyGen.ANCHOPLANO*(Math.cos(i)-traslX+alcance)/(2*alcance);
			double wy=(Math.sin(i)-traslY-alcance)*MathyGen.LARGOPLANO/(-2*alcance);
			g2d.drawLine((int)(wx),(int)(wy),(int) wx,(int) wy);
		}
		for (int i = 0; i < objetosDibujables.size(); i++) {
			objetosDibujables.get(i).dibujarse(g2d,alcance,traslY,traslX,MathyGen.ANCHOPLANO+anchoAd);
		}
	}
	public void agregarObjetoDibujable(Dibujable d){
		if(!objetosDibujables.contains(d)){
			objetosDibujables.add(d);
			repaint();
		}
	}
	public void eliminarObjetoDibujable(Dibujable d){
		if(objetosDibujables.contains(d)){
			objetosDibujables.remove(d);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
