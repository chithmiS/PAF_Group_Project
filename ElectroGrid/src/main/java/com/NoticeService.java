package com;
import model.Notice; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Notices")
public class NoticeService {
	Notice noticeObj = new Notice();

	//Add notices
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotice(@FormParam("userId") String userId, @FormParam("noticeSubject") String noticeSubject,
			@FormParam("noticeBody") String noticeBody) {
		String output = noticeObj.insertNotice(userId, noticeSubject, noticeBody);
		return output;
	}

	//View notices
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readNotices() 
	 { 
	 return noticeObj.readNotices(); 
	}
	
	
	//Update notices
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateNotice(String noticeData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String NoticeId = noticeObject.get("NoticeId").getAsString(); 
	 String userId = noticeObject.get("userId").getAsString(); 
	 String noticeSubject = noticeObject.get("noticeSubject").getAsString(); 
	 String noticeBody = noticeObject.get("noticeBody").getAsString(); 
	 String output = noticeObj.updateNotice(NoticeId, userId, noticeSubject, noticeBody); 
	return output; 
	}
	
	
	//Delete notices
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteNotice(String noticeData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String NoticeId = doc.select("NoticeId").text(); 
	 String output = noticeObj.deleteNotice(NoticeId); 
	return output; 
	}

}