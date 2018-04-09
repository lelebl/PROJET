package Vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import javax.swing.JButton;
import java.awt.Font;
  
public class Button extends JButton implements MouseListener{
  private String name;
  private int x;
  private int y;
  private int w;
  private int h;
  private Color color_now;
  private Color color_entered;
  private Color color;
  private Boolean clicked; //true=show line / false=don't show line
  

  public Button(String str,int pos_x,int pos_y,int _w, int _h, Color c){
    super(str);
    this.name = str;
    this.x=pos_x;
    this.y=pos_y;
    this.w=_w;
    this.h=_h;
    this.color_now=c;
    this.color=color_now;
    this.color_entered=Color.GRAY;
    this.clicked=true;
    
    this.setBounds(x,y,w,h);
    this.setOpaque(true);
    this.setContentAreaFilled(true);
    this.setBorderPainted(false);
  
    this.addMouseListener(this);
  }
  	
    public void paintComponent(Graphics g){
    	Graphics2D g2d = (Graphics2D)g;GradientPaint gp = new GradientPaint(0, 0, this.color_now, 0, 45, Color.white,true);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, this.w, this.h,15,15);
        Font myfont= new Font("Times New Roman", Font.BOLD,38);
        g2d.setFont(myfont);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString(this.name, (this.w/2)-this.name.length()*10, (this.h / 2) + 10);
    }
    
    public Boolean getClicked() {
    	return this.clicked;
    }
    
    public Color getColor() {
    	return this.color_now;
    }
    
    public void mouseClicked(MouseEvent event) {
    	this.setBorderPainted(false);
    	if(this.clicked) {
    		this.clicked=false;
    	}
    	else this.clicked=true;
    }

    public void mouseEntered(MouseEvent event) { 
    	this.setBorderPainted(false);
    	this.color_now=this.color_entered;
    }

    public void mouseExited(MouseEvent event) { 
    	this.setBorderPainted(false);
    	this.color_now=this.color;
    }
    
    public void mousePressed(MouseEvent event) { }

    public void mouseReleased(MouseEvent event) { }       
   
}