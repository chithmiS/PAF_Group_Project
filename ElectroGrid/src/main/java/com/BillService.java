package com;

import model.Bill;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/Bills")
public class BillService {
	
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
				
					Double amount = billObj.calculateAmount(power_consumption);
					String output = billObj.insertBill(acc_number, name,month, power_consumption, rate,amount);
					return output;
				}
		
}






	
	
	
	
	
	
	
	
	
	
	
