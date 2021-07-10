import java.util.*;
import java.awt.Desktop;  
import java.io.*;  
import java.net.*;

public class PriceCheck{

	private void kultgames(String s){
		Document doc = Jsoup.connect(s).get();

	}

	private void versusgamecenter(String s){

	}
	
	private void gameplay(String s){

	}
	
	private void arenaporto(String s){

	}
	
	private void jogonamesa(String s){

	}
	
	private void dracotienda(String s){

	}
	
	private void cultodacaixa(String s){

	}
	
	private void gglounge(String s){

	}
	
	private void diver(String s){

	}
	
	private void juegosdelamesaredonda(String s){

	}
	
	private void planetongames(String s){

	}
	
	

	public static void main(String[] argv)throws Exception {
		File f = new File(argv[0]);	
		f.setReadOnly();
		BufferedReader in = new BufferedReader(new FileReader(f));
		String s;
  		while ((s = in.readLine()) != null){
  			URL u = new URL(s);
  			String host = u.getHost();
  			switch host{
  				case kultgames:
  				kultgames(s);
  				break;

  				case versusgamecenter:
  				versusgamecenter(s);
  				break;

  				case gameplay:
  				gameplay(s);
  				break;

  				case arenaporto:
  				arenaporto(s);
  				break;

  				case jogonamesa:
  				jogonamesa(s);
  				break;

  				case dracotienda:
  				dracotienda(s);
  				break;

  				case cultodacaixa:
  				cultodacaixa(s);
  				break;

  				case gglounge:
  				gglounge(s);
  				break;

  				case diver:
  				diver(s);
  				break;

  				case juegosdelamesaredonda:
  				juegosdelamesaredonda(s);
  				break;

  				case planetongames:
  				planetongames(s);
  				break;

  				default:
  				System.out.println(s + " is invalid.");
  			}
  		}
	}
}