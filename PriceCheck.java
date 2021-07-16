import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class BoardGame{
	public String name;
	public String site;
	public String stock;
	public int price;

	public BoardGame(String a, String b, String c, int n){
		name = a;
		site = b;
		stock = c;
		price = n;
	}
}

public class PriceCheck{

	private static Boolean checkNameBg(String s){
		if(s.charAt(0) == 'h' && s.charAt(2) == 't' && s.charAt(2) == 't') return false;
		return true;
	}

	private static String kultgamesPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");

		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("our_price_display".equals(id)){
		  		//metaTag.children().remove();
		  		return(metaTag.text().substring(0, metaTag.text().length() - 2));
		  	}
		}	
		return "";	
	}
	private static String kultgamesStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("availability_value".equals(id)){
		  		metaTag.children().remove();
		  		return (metaTag.text());
		  	}
		}	
		return "";
	}


	private static String versusgamecenterPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("price-old".equals(id)){
		  		metaTag.children().remove();
		  		return(metaTag.text().substring(0, metaTag.text().length() - 1));
		  	}
		}	
		return "";
	}

	private static String versusgamecenterStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("div");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			if("description".equals(id)){
		  		return(metaTag.text().substring(metaTag.text().indexOf("Disponibilidade: ")+17, metaTag.text().length()));
			}
		}	
		return "";
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


	private static String spanStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("product-availability".equals(id)){
		  		metaTag.children().remove();
		  		return(metaTag.text());
		  	}
		}	
		return "";
	}

	private static String pStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("p");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("product-availability".equals(id)){
		  		metaTag.children().remove();

		  		return(metaTag.text());
		  	}
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
		String name;
  		while ((s = in.readLine()) != null){
  			if(checkNameBg(s)){
  				name = s; 
  			}  
  			else{
  				Document doc = Jsoup.connect(s).get();
	  			URL u = new URL(s);
	  			String host = u.getHost();
	  			String price = "";
	  			String stock = "";
	  			switch (host){
	  				case "www.kultgames.pt":
	  				price = kultgamesPrice(s, doc);
	  				stock = kultgamesStock(s, doc);
	  				break;

	  				case "versusgamecenter.pt":
	  				price = versusgamecenterPrice(s, doc);
	  				stock = versusgamecenterStock(s, doc);
	  				break;

	  				case "arenaporto.com":
	  					price = productPriceAmount(s,doc);
	  					stock = spanStock(s,doc);
	  					//checar isto BoardGame b = new BoardGame(name, host, stock, price);
	  					break;

	  				case "gameplay.pt":
	  					price = productPriceAmount(s,doc);
	  					stock = pStock(s,doc);
	  				break;

	  				case "jogonamesa.pt":
	  				jogonamesa(s,doc);
	  				break;

	  				case "dracotienda.com":
	  				dracotienda(s,doc);
	  				break;

	  				case "cultodacaixa.pt":
	  				cultodacaixa(s,doc);
	  				break;

	  				case "gglounge.pt":
	  				gglounge(s,doc);
	  				break;

	  				case "diver.pt":
	  				diver(s,doc);
	  				break;

	  				case "juegosdelamesaredonda.com":
	  				juegosdelamesaredonda(s,doc);
	  				break;

	  				case "planetongames.com":
	  				planetongames(s,doc);
	  				break;

	  				case "amazon.es":
	  				amazon(s,doc);
	  				break;

	  				default:
	  				System.out.println(s + " is invalid.");
	  			}
  			}
			
  		}
	}
}