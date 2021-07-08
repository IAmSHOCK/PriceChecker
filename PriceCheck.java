import java.util.*;
import java.awt.Desktop;  
import java.io.*;  
import java.net.*;

public class PriceCheck{
	public static void main(String[] argv) {
		try  {  
			Desktop d = Desktop.getDesktop();
			File f = new File(argv[0]);    
			if(f.exists())         //checks file exists or not  
			d.open(f);              //opens the specified file  
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		}
	}
}
	
		