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
		
}






	
	
	
	
	
	
	
	
	
	
	
