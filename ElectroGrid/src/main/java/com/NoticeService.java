package com;

//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Notice;

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

}