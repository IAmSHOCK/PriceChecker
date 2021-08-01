import java.io.*;  

import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVWriter;
import java.util.*;




public class PriceCheck{
	public static int ARRSIZE = 13;
	public static List<String[]> data;

	private static Boolean checkNameBg(String s){
		if(s.charAt(0) == 'h' && s.charAt(1) == 't' && s.charAt(2) == 't') return false;
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
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("class");
			if("esgotado".equals(id)){
		  		return(metaTag.text());
			}
		}	

		return "Not found.";
	}
	
	private static String dracotiendaPrice(String s, Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		for (Element metaTag : metaTags) {
			String id = metaTag.attr("itemprop");
			if("price".equals(id)){
		  		return (metaTag.text().substring(0, metaTag.text().length()-2));
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
		  		if(metaTag.text().equals("")) return "";
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
	private static String read(String url) throws IOException{
		URL urlObj = new URL(url);
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlObj .openStream()));
		String line;
		StringBuffer sbuf = new StringBuffer();

		while ((line = reader.readLine()) != null) {
		    if (line.trim().length() > 0)
		        sbuf.append(line).append("\n");
		}
		reader.close();
		return sbuf.toString();
	}

	private static String amazonPrice(String s, Document doc){
		
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
	private static void initData(){
		data = new ArrayList<String[]>();
		data.add(new String[] {"", "", "","kultgames", "", "versusgamecenter", "", "arenaporto", "", "gameplay", "",
			"jogonamesa", "", "dracotienda", "", "cultodacaixa", "", "gglounge", "", "diver", "",
			"juegosdelamesaredonda", "", "planetongames", "", "amazon.es", "", "devir"});
	}


	private static void toData(String name, Double best, String bestHost, Double[] prices, String[] stocks){
		String[] f = new String[ARRSIZE*2+3];
		f[0] = name;
		f[1] = best == 5000.0 ? "0" : String.valueOf(best);
		f[2] = bestHost;
		int j = 0;
		for (int i = 3; i < ARRSIZE*2+2; i+=2) {
			f[i] = prices[j] == 5000.0 ? "0" : String.valueOf(prices[j]);
			f[i+1] = stocks[j];
			j++;
		}
		data.add(f);
	}

	private static void CSV(){
		File file = new File("prices.csv");	
		if(file.exists()){
			File old = new File("prices-old.csv");
			try (
			      InputStream in = new BufferedInputStream(new FileInputStream(file));
			      OutputStream out = new BufferedOutputStream(new FileOutputStream(old))
			    ) 
			{
		        byte[] buffer = new byte[1024];
		        int lengthRead;
		        while ((lengthRead = in.read(buffer)) > 0) {
		            out.write(buffer, 0, lengthRead);
		            out.flush();
        		}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		try {
	  		FileWriter outputfile = new FileWriter(file);
	        CSVWriter writer = new CSVWriter(outputfile);
	  		writer.writeAll(data);
	        writer.close();
   		}
    	catch (IOException e) {
        	e.printStackTrace();
    	}
	}

	private static void log(Exception e, File file){
		try {
	  		FileWriter outputfile = new FileWriter(file);
	  		StringWriter sw = new StringWriter();
	  		PrintWriter pw = new PrintWriter(sw);
	  		e.printStackTrace(pw);
	        outputfile.write(sw.toString());
   		}
    	catch (IOException s) {
        	s.printStackTrace();
    	}
	}

	public static void main(String[] argv) throws IOException{
		File f = new File(argv[0]);
		BufferedReader in = new BufferedReader(new FileReader(f));
		String s;
		String name = "";
		String bestHost = "";
		Double best = 5000.0;
		Double[] prices = new Double[ARRSIZE];
		Arrays.fill(prices, 5000.0);
		String[] stocks = new String[ARRSIZE];
		initData();
		File l = new File("log.txt");

  		while ((s = in.readLine()) != null){
  			if(checkNameBg(s)){
  				if(!(name.equals(""))){
  					toData(name, best, bestHost, prices, stocks);
  					prices = new Double[ARRSIZE];
					Arrays.fill(prices, 5000.0);
					stocks = new String[ARRSIZE];
  				}
  				System.out.println(name);
  				name = s; 
  				best = 5000.0;	
  				bestHost = "";
  			}  
  			else{
  				Document doc;
  				try{
  					doc = Jsoup.connect(s).userAgent("Mozilla/49.0").get();
  				}
  				catch(Exception e){
  					e.printStackTrace();
  					log(e, l);
  					doc = null;
  				}

	  			URL u = new URL(s);
	  			String host = u.getHost();
	  			String priceS = "";
	  			Double price = 0.0;
	  			switch (host){
	  				case "www.kultgames.pt":
	  					priceS = spanIdPrice(s, doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					prices[0] = price;
	  					stocks[0] = kultgamesStock(s, doc);
	  				break;

	  				case "www.versusgamecenter.pt":
	  					priceS = versusgamecenterPrice(s, doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					prices[1] = prices[1] > price ? price : prices[1];
	  					stocks[1] = versusgamecenterStock(s, doc);
	  				break;

	  				case "arenaporto.com":
	  					priceS = productPriceAmount(s,doc);
	  					price = Double.parseDouble(priceS);
	  					prices[2] = prices[2] > price ? price : prices[2];
	  					stocks[2] = spanStock(s,doc);
	  					break;

	  				case "gameplay.pt":
	  					priceS = productPriceAmount(s,doc);
	  					price = Double.parseDouble(priceS);
	  					prices[3] = prices[3] > price ? price : prices[3];
	  					stocks[3] = pIdStock(s,doc);
	  				break;

	  				case "jogonamesa.pt":
	  					priceS = jogonamesaPrice(s, doc);
	  					
	  					if(priceS.equals("Not found.")){
	  						prices[4] = 0.0;
	  						stocks[4] = "esgotado";
	  						price = 5000.0;
	  					}
	  					else{
	  						price = Double.parseDouble(priceS);
	  						prices[4] = prices[4] > price ? price : prices[4];
	  						stocks[4] = jogonamesaStock(s, doc);
	  					}
	  				break;

	  				case "dracotienda.com":
	  					priceS = dracotiendaPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					prices[5] = prices[5] > price ? price : prices[5];
	  					stocks[5] = dracotiendaStock(s,doc);
	  				break;

	  				case "cultodacaixa.pt":
	  					priceS = pPrice(s,doc);
	  					if(priceS.equals("")){
	  						prices[6] = 0.0;
	  						stocks[6] = "esgotado";
	  						price = 5000.0;
	  					}
	  					else{
	  						priceS = priceS.replace(',','.');
	  						price = Double.parseDouble(priceS);
	  						prices[6] = prices[6] > price ? price : prices[6];
	  						stocks[6] = pClassStock(s,doc);
	  					}
	  				break;

	  				case "gglounge.pt":
	  					priceS = pPrice(s,doc);
	  					price = Double.parseDouble(priceS);
	  					prices[7] = prices[7] > price ? price : prices[7];
	  					stocks[7] = pClassStock(s,doc);
	  				break;

	  				case "www.diver.pt":
	  					priceS = diverPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					prices[8] = prices[8] > price ? price : prices[8];
	  					stocks[8] = spanIdStock(s,doc);
	  				break;

	  				case "juegosdelamesaredonda.com":
	  					priceS = spanIdPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					prices[9] = prices[9] > price ? price : prices[9];
	  					stocks[9] = spanIdStock(s, doc);
	  				break;

	  				case "www.planetongames.com":
	  					priceS = spanIdPrice(s,doc);
	  					priceS = priceS.replace(',','.');
	  					price = Double.parseDouble(priceS);
	  					prices[10] = prices[10] > price ? price : prices[10];
	  					stocks[10] = spanIdStock(s, doc);
	  				break;

	  				case "www.amazon.es":
	  					priceS = amazonPrice(s, doc);
	  					priceS = priceS.replace(',','.');
	  					if(priceS.equals("Not found.")){
	  						prices[11] = 10000.0;
	  						stocks[11] = "Not found.";
	  						price = 10000.0;
	  					}
	  					else{
	  						price = Double.parseDouble(priceS);
	  						prices[11] = prices[11] > price ? price : prices[11];
	  						stocks[11] = amazonStock(s, doc);
	  					}
	  				break;

	  				case "devir.pt":
	  					priceS = "not implemented yet";
	  					priceS = priceS.replace(',','.');
	  					price = 2000.0;
	  					//price = Double.parseDouble(priceS);
	  					prices[12] = prices[12] > price ? price : prices[12];
	  					stocks[12] = "not implemented yet";
	  					break;

	  				default:
	  					System.out.println(s + " is invalid.");
	  			}
	  			if(price < best){
	  				best = price;
	  				bestHost = host;
	  			}
	  			System.out.println(price);
  			}	
  		}
  		toData(name, best, bestHost, prices, stocks);
  		CSV();
	}
}