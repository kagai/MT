package com.example.app.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.model.User;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "MSISDN", "SIM_TYPE", "NAME", "DATE_OF_BIRTH","GENDER","ADDRESS","ID_NUMBER" };
	
	public static boolean hasCSVFormat(MultipartFile file) {
		
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		
		return true;
	}
 // Input validators
	public static boolean isValidName(String name){
		String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}~0123456789";
		String str2[]=name.split("");
		int count=0;
		for (int i=0;i<str2.length;i++)
		{
			if (specialCharacters.contains(str2[i]))
			{
				count++;
			}
		}

		if (name!=null && count==0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public static boolean isValidGender(String gender){
		if( gender.equals("F") || gender.equals("M"))
		{
			return true;
		}
		return false;
	}

	public static  boolean isValidSimType(String sim_type){
		if(sim_type.equals("PREPAID") || sim_type.equals("POSTPAID") ){
			return true;
		}
		return false;
	}

	public static boolean isValidMsisdn(String msisdn){
		//	international phone number regex
		String str=  "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
		if (Pattern.compile(str).matcher(msisdn).matches()) {
			return true;
		} else {
			return false;
		}
	}
	// This just prints the message , to be implemented in future
	public static void sendSMS(String msisdn ,String name, String gender){
		String msg = "Thank you" + name + "for registering with MT sim reg ";
		System.out.println(msg);
	}
	
	public static List<User> csvToUsers(InputStream is) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			
			List<User> users = new ArrayList<User>();
			
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
//				validate inputs
				if(isValidName(csvRecord.get("name")) && isValidGender(csvRecord.get("gender")) && isValidSimType(csvRecord.get("sim_type")) && isValidMsisdn(csvRecord.get("msisdn")) ){
					User user = new User(
							Long.parseLong(csvRecord.get("Id")),
							csvRecord.get("name"),
							csvRecord.get("gender"),
							csvRecord.get("sim_type"),
							dateFormat.parse(csvRecord.get("date_of_birth")),
							csvRecord.get("address"),
							csvRecord.get("id_number"),
							csvRecord.get("msisdn")
					);
					users.add(user);
					// send message
					sendSMS(csvRecord.get("msisdn"),csvRecord.get("name"),csvRecord.get("name"));
				}else{
					System.out.println("input(s) value not valid");
				}
			}
			return users;
		} catch (Exception e) {
			throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
		}
	}
}
