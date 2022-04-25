package com;

import model.Bill;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Bills")
public class BillService {
	
		//create Bill object
		Bill billObj = new Bill();
		
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readBills()
		{
			return billObj.readBills();
		}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertBill(
				@FormParam("acc_number") String acc_number,
				@FormParam("name") String name,
				@FormParam("month") String month,
				@FormParam("power_consumption") Double power_consumption,
				@FormParam("rate") Double rate)
				{
				
					//calling calculateAmount function
					Double amount = billObj.calculateAmount(power_consumption);
					
					//call insertBill method
					String output = billObj.insertBill(acc_number, name,month, power_consumption, rate,amount);
					return output;
				}
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateBill(String billData)
		{
			
		//Convert the input string to a JSON object
		JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		
		//Read the values from the JSON object
		String bill_id = billObject.get("bill_id").getAsString();
		String acc_number = billObject.get("acc_number").getAsString();
		String name = billObject.get("name").getAsString();
		String month = billObject.get("month").getAsString();
		Double power_consumption = billObject.get("power_consumption").getAsDouble();
		Double rate = billObject.get("rate").getAsDouble();
		//Double total_amount = billObject.get("total_amount").getAsDouble();
		String date = billObject.get("date").getAsString();
		
		//calling calculateAmount function
		Double total_amount = billObj.calculateAmount(power_consumption);
		
		String output = billObj.updateBill(bill_id,acc_number, name,month, power_consumption, rate,total_amount,date);
		return output;
		}
		
		
		
		
		
}






	
	
	
	
	
	
	
	
	
	
	
