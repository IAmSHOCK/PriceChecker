import java.io.*;  
import java.util.*;
import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Amazon{
	private static String amazonPrice(Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("id");
		  	/*if("priceblock_ourprice".equals(id)){
			  	//System.out.println(metaTag);
		  		return(metaTag.text());
		  	}*/
		  	System.out.println(metaTag.text());
		}
		return "";		
	}

	private static String amazonStock(Document doc){
		Elements metaTags = doc.getElementsByTag("span");
		String price;
		for (Element metaTag : metaTags) {
		  	String id = metaTag.attr("class");
		  	System.out.println(metaTag.text());
		  	/*if("a-size-medium a-color-success".equals(id)){
			  	//System.out.println(metaTag);
		  		return(metaTag.text());
		  	}*/
		}
		return "";
	}		

	public static void main(String[] args) {
		String priceS, stock, s;
		try{
			BufferedReader in = new BufferedReader(new FileReader("teste.txt"));
			while ((s = in.readLine()) != null){
			Document doc = Jsoup.connect(s).userAgent("Mozilla/49.0").get();
			priceS  = amazonPrice(doc);
			priceS = priceS.replace(',','.');
			stock = amazonStock(doc);
			System.out.println(priceS + " " + stock);
			}		
		}catch(Exception e){}
	}
}