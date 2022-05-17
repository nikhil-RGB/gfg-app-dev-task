package global_clock;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
//An object of this class controls a single instance of the Global clock app
//@author:Nikhil Narayanan
//@purpose:geeks for geeks technical testing round
public class ApplicationManager extends JFrame
{
	//links
	private static final String WORK="https://github.com/nikhil-RGB";
	private static final String ABOUT="https://linktr.ee/nikhil_n67";
	private static final String CONTACT="javakingxi@gmail.com";
	//links
	
	private static final long serialVersionUID=1002003L;
	//format for time displaying label
	private static final String DATE_FORMATTER= "HH:mm:ss yyyy-MM-dd"; 
	//label used for showing current date and time
	private volatile JLabel timer;
	private volatile ZoneId currentZone;
	//filepath for icon background of timer panel
	private static URL universal_icon;
	//Timer-containing panel
	private ImagePanel panel;
	//default font used for all text in-app
	private static Font font;
	//default border for text based components
	private static Border border;
	//available timezones
	private static String[] timezones;
	
	//static initialization block for all static-level initializations
	static
	{
		try {
			ApplicationManager.universal_icon=new URL("https://wallpaperaccess.com/full/1783294.jpg");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to load background", "Background texture cannot load without internet!\n Please connect to the internet for an optimal experience", JOptionPane.ERROR_MESSAGE, null);
		}
		if(ApplicationManager.universal_icon==null)
		{
			JOptionPane.showMessageDialog(null, "Unable to load background", "Background texture cannot load without internet!\n Please connect to the internet for an optimal experience", JOptionPane.ERROR_MESSAGE, null);
		}
		ApplicationManager.font=new Font("SansSerif",Font.PLAIN,28);
		ApplicationManager.border=new RoundedBorder(12);
		ApplicationManager.timezones=TimeZone.getAvailableIDs();
	}
	//default constructor for ApplicationManager class
	public ApplicationManager(String title)
	{
		super(title);
		
		this.currentZone=ZoneId.systemDefault();
		//initialize JLabel with default LocalDateTime value initially
		//ZonedDateTime current_time=ZonedDateTime.now(this.currentZone);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationManager.DATE_FORMATTER);
		//String formatted=ApplicationManager.timeFormat(current_time.format(formatter)+" "+this.currentZone);
		//System.out.println(formatted);
	    String formatted=this.refreshedTime();
		this.timer=new JLabel(formatted);
		this.timer.setBorder(border);
		this.timer.setFont(font);
		
		//initialize parent container
		this.panel=new ImagePanel(ApplicationManager.universal_icon);
	    this.panel.add(timer);
	    this.initMenu();	    
		
	}
	//method to initialize menu bar and menu options
	public void initMenu()
	{
		JMenuBar jmb=new JMenuBar();
		JMenu changeLoc=new JMenu("Change Locale/Timezone");
		JMenuItem change=new JMenuItem("Access and Select Timezone of choice");
		change.addActionListener((ev)->{
		String selection=(String)(JOptionPane.showInputDialog(null, "Select new Time zone","Time Zone switch", JOptionPane.QUESTION_MESSAGE, null, timezones, currentZone));
	    currentZone=((selection==null)||(selection.isEmpty()))?currentZone:ZoneId.of(selection);    	
		timer.setText(refreshedTime());
		});
		changeLoc.add(change);
		jmb.add(changeLoc);
		//info menu initialization
	 	JMenu toAdd=ApplicationManager.prepareInfoMenu();
	 	//end
	 	jmb.add(toAdd);
		this.setJMenuBar(jmb);
	}
	
  //This method formats the time before it will be displayed
	private static String timeFormat(String tobeFormatted)
	{
	  String formatted="";
	  Scanner reader=new Scanner(tobeFormatted);
	  String time=reader.next();
	  String date=reader.next();
	  String zone=reader.next();
	  reader.close();
	  formatted="<html><strong><i><u>Time:</u></i></strong><br>"+time+"<br><strong><i><u>Date:</u></i></strong><br>"+date+"<br><strong><i><u>Current Zone:</u></i></strong><br>"+zone;
      return formatted;	  
	}
	
	//This code returns the current time for the specefic zone in String format
	public String refreshedTime()
	{
		ZonedDateTime current_time=ZonedDateTime.now(this.currentZone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationManager.DATE_FORMATTER);
		String formatted=ApplicationManager.timeFormat(current_time.format(formatter)+" "+this.currentZone);
		
		return formatted;
	}
	//main method for controlling application execution
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(()->{
	 	final ApplicationManager am=new ApplicationManager("Global Clock");
	 	am.add(am.panel);
	 	am.setSize(new Dimension(850,450));
	 	am.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	am.setResizable(false);
	 	am.setVisible(true);
	 	ActionListener updatetimer=(ae)->
	 	{
	 		String refreshed=am.refreshedTime();
	 		am.timer.setText(refreshed);
	 	};
	 	Timer th=new Timer(1000,updatetimer);
	 	th.start();
		});
     
	}
	
	//This method prepares the More Info Menu
    private static JMenu prepareInfoMenu()
    {
      JMenu info=new JMenu("More Information");
      JMenuItem about=new JMenuItem("About developer");
      JMenuItem more=new JMenuItem("More from this developer");
      JMenuItem contact=new JMenuItem("Contact developer");
      ActionListener ar=(ev)->
      {
    	  try
    	  {
    	  Desktop dt=Desktop.getDesktop();
    	  JMenuItem src=(JMenuItem)(ev.getSource());
    	  URI link=(src==about)?new URI(ApplicationManager.ABOUT):new URI(ApplicationManager.WORK);
    	  if((src==about)||(src==more))
    	  {
    		dt.browse(link);
		  }
    	  else if(src==contact)
    	  {
    		link=new URI("mailto:"+ApplicationManager.CONTACT);  
    		dt.mail(link);
    	  }
    	  
    	  }
    	  catch(Throwable obj)
    	  {
    		  JOptionPane.showMessageDialog(null, "Failed to carry out operation!","Oops!", JOptionPane.ERROR_MESSAGE, null);
    	  }
      };
      //event listeners
      about.addActionListener(ar);
      more.addActionListener(ar);
      contact.addActionListener(ar);
      //menu options
      info.add(more);
      info.add(about);
      info.add(contact);
      
      
      return info;	
    }
}
