package pt.iade.dashboard;

/*
 * Created by Diogo on 06/05/2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class contains all the methods related to reading from our CSV file
 * <p>
 * This class utilize an array of objects from{@link YearCO2} to store all the data read from the CSV.
 * <p>
 * @author Diogo Nunes
 *
 */

public class CSVReader {
	private String filename,delimiter;
	private final int MAX_YEARCO2_ARRAY_SIZE=218;
	private final int MAX_COUNTRY_ARRAY_SIZE=220;


	/**
	 * Constructor
	 * <p>
	 * @param filename name of the file being read
	 * @param delimiter to separate values
	 */
	public CSVReader(String filename, String delimiter){
		this.filename=filename;
		this.delimiter=delimiter;
	}

	/**
	 * Read all of the countries in the CSV
	 * <p>
	 * @return String with all of the countries listed in the CSV
	 * @exception FileNotFoundException
	 */

	public String[] readCountries(){
		String[] countryList;
		String[] tempCountryList=new String[MAX_COUNTRY_ARRAY_SIZE];
		Scanner scanner;
		int i=0;

		try{
			String country="";

			scanner = new Scanner(new File(filename));
			scanner.nextLine();

			while(scanner.hasNextLine()){
				String data= scanner.nextLine();
				String []values=data.split(delimiter);
				if(values[0].equals(country)==false){
					country=values[0];
					tempCountryList[i]=country;
					i++;
				}
			}
			scanner.close();

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countryList=Arrays.copyOf(tempCountryList, i);
		return countryList;
	}

	/**
	 * Reads the year and correspondent CO2 value starting from the first value different from 0
	 * <p>
	 * @param country to read the data for
	 * @return YearCO2[] array with all of the data of the relevant country
	 * @exception FileNotFoundException
	 */
	public YearCO2[] readDataByCountry(String country){
		YearCO2[] listYearCo2; 																//array of our custom object
		YearCO2[] tempListYearCo2= new YearCO2[MAX_YEARCO2_ARRAY_SIZE];								//temporary array of our custom object
		Scanner scanner;																	//Scanner used for retrieving data

		int x;																				//variable where we we´ll store our year before adding it to the object
		double y;																			//variable where we we´ll store the corresponding co2 value before adding it to the object
		int i=0;
		try {
			scanner = new Scanner(new File(filename));                         				 //Initializing scanner
			scanner.nextLine();                                                 			//jump over the first line
			while(scanner.hasNextLine()){													//while there is still data to be read, we´ll keep on reading
				String data= scanner.nextLine();											//retrieve all of the data in the current line
				String []values=data.split(delimiter);										// split it into an array
				if(values[0].equals(country) && (!(values[3].equals("0")) || i!=0)){		//if the first value is equal to the country we are searching for AND its different from 0
					x=Integer.parseInt(values[2]);											//convert from string to int
					y=Double.parseDouble(values[3]);										// convert from string to double
					tempListYearCo2[i]= new YearCO2(x,y);									// add it into our object
					i++;
				}
			}
			scanner.close();																//close scanner after everything we want is read
		} catch (FileNotFoundException e) {													//generic error catching
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listYearCo2=Arrays.copyOf(tempListYearCo2, i);										//copy the contents of the temp array into the final one, this way there wont be unfilled spaces in it
		return listYearCo2;																	//return the array of objects
	}
}
