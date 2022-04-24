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
		
		
		

}
