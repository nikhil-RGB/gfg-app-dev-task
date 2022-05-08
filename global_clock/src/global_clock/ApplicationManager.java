package global_clock;
import javax.swing.*;
import java.util.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
//An object of this class controls a single instance of the Global clock app
//@author:Nikhil Narayanan
//@purpose:geeks for geeks technical testing round
public class ApplicationManager extends JFrame
{
	
	private static final long serialVersionUID=1002003L;
	//format for time displaying label
	private static final String DATE_FORMATTER= "HH:mm yyyy-MM-dd"; 
	//label used for showing current date and time
	private JLabel timer;
	private ZoneId currentZone;
	//filepath for icon background of timer panel
	private static String universal_icon;
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
		ApplicationManager.universal_icon="background.png";
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
		ZonedDateTime current_time=ZonedDateTime.now(this.currentZone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationManager.DATE_FORMATTER);
		String formatted=ApplicationManager.timeFormat(current_time.format(formatter)+" "+this.currentZone);
		//System.out.println(formatted);
		this.timer=new JLabel(formatted);
		this.timer.setBorder(border);
		this.timer.setFont(font);
		
		//initialize parent container
		this.panel=new ImagePanel(ApplicationManager.universal_icon);
	    this.panel.add(timer);	
		
	}
	//method to initialize menu bar and menu options
	public void initMenu()
	{
		
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
	
	//main method for controlling application execution
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(()->{
	 	ApplicationManager am=new ApplicationManager("Global Clock");
	 	am.add(am.panel);
	 	am.setSize(new Dimension(350,300));
	 	am.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	am.setResizable(false);
	 	am.setVisible(true);
		});
     
	}

}
