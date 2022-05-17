package global_clock;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
 public class ImagePanel extends JPanel
 {
   private static final long serialVersionUID=2L;
   ImageIcon icon;
   public ImagePanel(URL path)
   {
	   this.icon=new ImageIcon(path);
	   //this.icon=new ImageIcon(getClass().getResource(path));
   }
   @Override
   public void paintComponent(Graphics g)
   {
	   super.paintComponent(g);
	   if(this.icon!=null)
	   {
		   g.drawImage(this.icon.getImage(),0,0,this.getWidth()+1,this.getHeight()+1,null);
	   }
   }
	 
 }
