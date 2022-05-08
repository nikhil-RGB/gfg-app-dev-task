package global_clock;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
 public class ImagePanel extends JPanel
 {
   private static final long serialVersionUID=2L;
   Image icon;
   public ImagePanel(String path)
   {
	   this.icon=new ImageIcon(path).getImage();
   }
   @Override
   public void paintComponent(Graphics g)
   {
	   super.paintComponent(g);
	   if(this.icon!=null)
	   {
		   g.drawImage(this.icon,0,0,this.getWidth()+1,this.getHeight()+1,null);
	   }
   }
	 
 }
