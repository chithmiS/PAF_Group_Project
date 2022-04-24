package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Customer;

@Path("/Customers")
public class CustomerService {
	
	Customer customerObj = new Customer();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("NIC") String NIC,
	 @FormParam("CustomerFirstName") String CustomerFirstName,
	 @FormParam("CustomerLastName") String CustomerLastName,
	 @FormParam("HomeNo") String HomeNo,
	 @FormParam("Street") String Street,
	 @FormParam("HomeCity") String City,
	 @FormParam("CustomerPhone") String CustomerPhone,
	 @FormParam("AccountNo") String AccountNo)
	{
	 String output = customerObj.insertCustomer(NIC,CustomerFirstName,CustomerLastName,HomeNo,Street,City,CustomerPhone,AccountNo);
	return output;
	}

}
