import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PriceCheck{

	private static void kultgames(String s, Document doc){
		

	}

	private static void versusgamecenter(String s, Document doc){

	}
	
	private static void gameplay(String s, Document doc){

	}
	
	private static String productPriceAmount(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("meta");

		for (Element metaTag : metaTags) {
		  	String property = metaTag.attr("property");
		  	String price = metaTag.attr("content");

		  	if("product:price:amount".equals(property)) {
		    	return price;
		  	}
		}	
		return "";
	}

	private static String title(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("title");
		return metaTags.text();
	}

	private static String spanStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");

		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("product-availability".equals(id))
		  		metaTag.attr("class", "");
		  		System.out.println(metaTag.text());
		}	
		return "";
	}

	private static void jogonamesa(String s, Document doc){

	}
	
	private static void dracotienda(String s, Document doc){

	}
	
	private static void cultodacaixa(String s, Document doc){

	}
	
	private static void gglounge(String s, Document doc){

	}
	
	private static void diver(String s, Document doc){

	}
	
	private static void juegosdelamesaredonda(String s, Document doc){

	}
	
	private static void planetongames(String s, Document doc){

	}

	private static void amazon(String s, Document doc){

	}
	

	public static void main(String[] argv) throws IOException{
		File f = new File(argv[0]);
		f.setReadOnly();
		BufferedReader in = new BufferedReader(new FileReader(f));
		String s;
  		while ((s = in.readLine()) != null){
			Document doc = Jsoup.connect(s).get();
  			URL u = new URL(s);
  			String host = u.getHost();
  			String price = "";
  			String name = "";
  			String stock = "";
  			switch (host){
  				case "kultgames":
  				kultgames(s,doc);
  				break;

  				case "versusgamecenter":
  				versusgamecenter(s,doc);
  				break;

  				case "arenaporto.com": case "gameplay.pt":
  				price = productPriceAmount(s,doc);
  				name = title(s, doc);
  				stock = spanStock(s,doc);
  				break;

  				case "jogonamesa":
  				jogonamesa(s,doc);
  				break;

  				case "dracotienda":
  				dracotienda(s,doc);
  				break;

  				case "cultodacaixa":
  				cultodacaixa(s,doc);
  				break;

  				case "gglounge":
  				gglounge(s,doc);
  				break;

  				case "diver":
  				diver(s,doc);
  				break;

  				case "juegosdelamesaredonda":
  				juegosdelamesaredonda(s,doc);
  				break;

  				case "planetongames":
  				planetongames(s,doc);
  				break;

  				case "amazon":
  				amazon(s,doc);
  				break;

  				default:
  				System.out.println(s + " is invalid.");
  			}
  		}
	}
}