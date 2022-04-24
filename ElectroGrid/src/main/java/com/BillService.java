package com;

import model.Bill;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

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
		
}






	
	
	
	
	
	
	
	
	
	
	
