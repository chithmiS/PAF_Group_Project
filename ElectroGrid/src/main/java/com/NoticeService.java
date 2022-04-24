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

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotice(@FormParam("userId") String userId, @FormParam("noticeSubject") String noticeSubject,
			@FormParam("noticeBody") String noticeBody) {
		String output = noticeObj.insertNotice(userId, noticeSubject, noticeBody);
		return output;
	}

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readNotices() 
	 { 
	 return noticeObj.readNotices(); 
	}
	
	

}