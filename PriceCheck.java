import java.util.*;
import java.awt.Desktop;  
import java.io.*;  
import java.net.*;

public class PriceCheck{
	public static void main(String[] argv) {
		try{
			Desktop d = Desktop.getDesktop();
			d.browse((new URL("www.google.com")).toURI());
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println();
			System.out.println();
			e.printStackTrace();  
		}
	}
}
		
		/*try  {  
		File f = new File(argv[0]);    
		if(file.exists())         //checks file exists or not  
		desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
			}
		}*/