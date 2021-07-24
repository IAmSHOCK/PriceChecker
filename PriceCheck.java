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

	private static String spanIdPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");

		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("our_price_display".equals(id)){
		  		//metaTag.children().remove();
		  		return(metaTag.text().substring(0, metaTag.text().length() - 2));
		  	}
		}	
		return "Not found.";	
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
		return "Not found.";
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
		return "Not found.";
	}

	private static String versusgamecenterStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("div");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			if("description".equals(id)){
		  		return(metaTag.text().substring(metaTag.text().indexOf("Disponibilidade: ")+17, metaTag.text().length()));
			}
		}	
		return "Not found.";
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
		return "Not found.";
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
		return "Not found.";
	}

	private static String pIdStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("p");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("product-availability".equals(id)){
		  		metaTag.children().remove();
		  		return(metaTag.text());
		  	}
		}	
		return "Not found.";
	}	
		

	private static String jogonamesaPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("div");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			String price = metaTag.attr("content");
			if("ficha-compra".equals(id)){
		  		return(metaTag.text().substring(1, 6));
			}
		}	
		return "Not found.";
	}

	private static String jogonamesaStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			if("reserva".equals(id)){
		  		return(metaTag.text());
			}
		}	
		return "Not found.";
	}
	
	private static String dracotiendaPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("div");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			String price = metaTag.attr("content");
			if("current-price".equals(id)){
		  		return(metaTag.text().substring(0, metaTag.text().length() - 2));
			}
		}	
		return "Not found.";
	}

	private static String dracotiendaStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			if("product-availability".equals(id)){
		  		return(metaTag.text().substring(metaTag.text().indexOf("Disponibilidad: ")+18, metaTag.text().length()));
			}
		}	
		return "Not found.";
	}
	
	private static String pPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("p");
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("class");
		  	if("price".equals(id)){
		  		//metaTag.children().remove();
		  		return(metaTag.text().substring(1, metaTag.text().length()));
		  	}
		}
		return "Not found.";
	}

	private static String pClassStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("p");
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("class");
		  	if("stock in-stock".equals(id)){
		  		return(metaTag.text());
		  	}
		}

		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("class");
		  	if("stock out-of-stock".equals(id)){
		  		return(metaTag.text());
		  	}
		}	

		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("class");
		  	if("stock available-on-backorder".equals(id)){
		  		return(metaTag.text());
		  	}
		}
		return "Not found.";
	}
	
	
	private static String diverPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("p");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			//String price = metaTag.attr("content");
			if("our_price_display".equals(id)){
		  		return(metaTag.text().substring(0, metaTag.text().length()-2));
			}
		}	
		return "Not found.";
	}
	private static String spanIdStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("availability_value".equals(id)){
		  		return(metaTag.text());
		  	}
		}	
		return "Not found.";
	}

	private static String amazonPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	if("price_inside_buybox".equals(id)){
			  	//System.out.println(metaTag);
		  		return(metaTag.text().substring(0, metaTag.text().length()-2));
		  	}
		}	
		return "Not found.";
	}

	private static String amazonStock(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("class");
		  	if("a-size-medium a-color-success".equals(id)){
			  	//System.out.println(metaTag);
		  		return(metaTag.text());
		  	}
		}	
		return "Not found.";
	}

	private static void toCSV(String name, String host, String price, String stock){

	}


	

	public static void main(String[] argv) throws IOException{
		File f = new File(argv[0]);
		f.setReadOnly();
		BufferedReader in = new BufferedReader(new FileReader(f));
		String s;
		String name;
		Double best = 5000.0;
  		while ((s = in.readLine()) != null){
  			if(checkNameBg(s)){
  				name = s; 
  				best = 5000.0;
  			}  
  			else{
  				Document doc = Jsoup.connect(s).get();
	  			URL u = new URL(s);
	  			String host = u.getHost();
	  			String priceS = "";
	  			Double price = 0.0;
	  			String stock = "";
	  			switch (host){
	  				case "www.kultgames.pt":
	  					priceS = spanIdPrice(s, doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = kultgamesStock(s, doc);
	  				break;

	  				case "www.versusgamecenter.pt":
	  					priceS = versusgamecenterPrice(s, doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = versusgamecenterStock(s, doc);
	  				break;

	  				case "arenaporto.com":
	  					priceS = productPriceAmount(s,doc);
	  					price = Double.parseDouble(priceS);
	  					stock = spanStock(s,doc);
	  					//checar isto BoardGame b = new BoardGame(name, host, stock, price);
	  					break;

	  				case "gameplay.pt":
	  					priceS = productPriceAmount(s,doc);
	  					price = Double.parseDouble(priceS);
	  					stock = pIdStock(s,doc);
	  				break;

	  				case "jogonamesa.pt":
	  					priceS = jogonamesaPrice(s, doc);
	  					stock = jogonamesaStock(s, doc);
	  					price = Double.parseDouble(priceS);
	  					System.out.println(stock);
	  				break;

	  				case "dracotienda.com":
	  					priceS = dracotiendaPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = dracotiendaStock(s,doc);
	  				break;

	  				case "cultodacaixa.pt":
	  					priceS = pPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = pClassStock(s,doc);
	  				break;

	  				case "gglounge.pt":
	  					priceS = pPrice(s,doc);
	  					price = Double.parseDouble(priceS);
	  					stock = pClassStock(s,doc);
	  				break;

	  				case "www.diver.pt":
	  					priceS = diverPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = spanIdStock(s,doc);
	  				break;

	  				case "juegosdelamesaredonda.com":
	  					priceS = spanIdPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = spanIdStock(s, doc);
	  				break;

	  				case "www.planetongames.com":
	  					priceS = spanIdPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = spanIdStock(s, doc);
	  				break;

	  				case "www.amazon.es":
	  					priceS = amazonPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					stock = amazonStock(s, doc);
	  				break;

	  				case "devir.pt":
	  					priceS = "not implemented yet";
	  					priceS = priceS.replace(',','.');
	  					price = 2000.0;
	  					//price = Double.parseDouble(priceS);
	  					stock = "not implemented yet";
	  					break;

	  				default:
	  					System.out.println(s + " is invalid.");
	  			}
	  			best = (price < best) ? price : best;
	  			//toCSV(name, host, price, stock);
	  			System.out.println(host);
	  			System.out.println(price);
	  			System.out.println(stock);
	  			System.out.println();
  			}	
  		}
  		System.out.println(best);
	}
}