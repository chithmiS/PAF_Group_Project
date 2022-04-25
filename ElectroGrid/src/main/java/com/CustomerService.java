package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	 @FormParam("AccountNo") String AccountNo,
	 @FormParam("Password") String Password)
	{
	 String output = customerObj.insertCustomer(NIC,CustomerFirstName,CustomerLastName,HomeNo,Street,City,CustomerPhone,AccountNo,Password);
	return output;
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers()
	 {
	 return customerObj.readCustomers();
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String CustomerID = customerObject.get("CustomerID").getAsString();
	 String NIC = customerObject.get("NIC").getAsString();
	 String CustomerFirstName = customerObject.get("CustomerFirstName").getAsString();
	 String CustomerLastName = customerObject.get("CustomerLastName").getAsString();
	 String HomeNo = customerObject.get("HomeNo").getAsString();
	 String Street = customerObject.get("Street").getAsString();
	 String City = customerObject.get("HomeCity").getAsString();
	 String CustomerPhone = customerObject.get("CustomerPhone").getAsString();
	 String AccountNo= customerObject.get("AccountNo").getAsString();
	 String Password= customerObject.get("Password").getAsString();
	 String output = customerObj.updateCustomer(CustomerID,NIC, CustomerFirstName,CustomerLastName, HomeNo, Street, City,CustomerPhone,AccountNo,Password);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <CustomerID>
	 String CustomerID = doc.select("CustomerID").text();
	 String output = customerObj.deleteCustomer(CustomerID);
	return output;
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.TEXT_HTML)
	public String searchCustomers(String customerData)
	{
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());
		
		//Read the value from the element <bill_ID>
		String nic = doc.select("NIC").text();
		
		return customerObj.searchCustomers(nic);
	}


}
