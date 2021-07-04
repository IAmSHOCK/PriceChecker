    //package com.javatpoint;  
    import java.net.*;  
    import java.io.*;  
    public class UrlConnectionReader  
    {  
      public static void main(String[] args)  
      {  
        Scanner in = new Scanner(System.in);
        String output;
        while(in.hasNext()){
          output = getUrlContents(in.next);  
          System.out.println(output);  
        }
      }  
      
      private static String getUrlContents(String theUrl)  
      {  
        StringBuilder content = new StringBuilder();  
      // Use try and catch to avoid the exceptions  
        try  
        {  
          URL url = new URL(theUrl); // creating a url object  
          URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
      
          // wrapping the urlconnection in a bufferedreader  
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
          String line;  
          // reading from the urlconnection using the bufferedreader  
          while ((line = bufferedReader.readLine()) != null)  
          {  
            content.append(line + "\n");  
          }  
          bufferedReader.close();  
        }  
        catch(Exception e)  
        {  
          e.printStackTrace();  
        }  
        return content.toString();  
      }  
    }  