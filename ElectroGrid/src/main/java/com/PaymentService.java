package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Payments") 

public class PaymentService {
	
		Payment paymentObj = new Payment();
	
		//add payment details
	
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("AccNumber") String AccNumber,
		 @FormParam("totalAmount") String totalAmount,
		 @FormParam("payDate") String payDate,
		 @FormParam("cardType") String cardType,
		 @FormParam("cardNumber") String cardNumber,
		 @FormParam("cvn") String cvn)
		{
		 String output = paymentObj.insertPayment(AccNumber, totalAmount, payDate, cardType, cardNumber, cvn);
		return output;
		}
		
		
		//read payment details
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPayments()
		 {
		 return paymentObj.readPayments();
		 }
		
		
		//update payment details
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
					
			//Convert the input string to a JSON object
					
			JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
				 
			//Read the values from the JSON object
				 
				 String payID = paymentObject.get("payID").getAsString();
				 String AccNumber = paymentObject.get("AccNumber").getAsString();
				 String totalAmount = paymentObject.get("totalAmount").getAsString();
				 String payDate = paymentObject.get("payDate").getAsString();
				 String cardType = paymentObject.get("cardType").getAsString();
				 String cardNumber = paymentObject.get("cardNumber").getAsString();
				 String cvn = paymentObject.get("cvn").getAsString();
				 String output = paymentObj.updatePayment(payID, AccNumber, totalAmount, payDate, cardType, cardNumber, cvn);
				 
			return output;
			}
		
		

}
