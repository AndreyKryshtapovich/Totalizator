package by.epamtr.totalizator.usertag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;

public class EventDetailsTableTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String eventName;
	private String teamOne;
	private String teamTwo;
	private String result;
	private String startDate;
	private String endDate;
	private String status;
	private String edit;
	private JSPListBean eventsList;
	
	public int doStartTag() throws JspTagException {
		
		if(eventsList.getSize() == 0){
			try {
				pageContext.getOut().write("<p> There are no events matched to this game.</p>");
				return SKIP_BODY;
			} catch (IOException e) {
				throw new JspTagException(e.getMessage());
			}
		}else{
		int size = new Integer(eventsList.getSize());
		try {
			pageContext.getOut().write("<table border='1' cellpadding='5' cellspacing='3'>");
			pageContext.getOut().write("<tr><th>");
			pageContext.getOut().write("#" +"</th>");
			
			pageContext.getOut().write("<th>" + eventName + "</th>");
			pageContext.getOut().write("<th>" + teamOne + "</th>");
			pageContext.getOut().write("<th>" + teamTwo + "</th>");
			pageContext.getOut().write("<th>" + result + "</th>");
			pageContext.getOut().write("<th>" + startDate + "</th>");
			pageContext.getOut().write("<th>" + endDate + "</th>");
			pageContext.getOut().write("<th>" + status + "</th>");
			pageContext.getOut().write("</th></tr>");

			for(int i=0; i<size; i++) {
				Event event = eventsList.getElement();
				pageContext.getOut().write("<tr>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(new Integer(i+1).toString());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<form action='Controller' method='get' name='go-to-event-edit'>");
				pageContext.getOut().write("<input type='hidden' name='command' value='go-to-event-edit' />");
				pageContext.getOut().write("<input type='hidden' name='eventId' value='" + new Integer(event.getEventId()).toString() + "'/>");
				pageContext.getOut().write("<input type='hidden' name='gameId' value='" + new Integer(event.getGameCuponId()).toString() + "'/>");
				
				pageContext.getOut().write("<input type='hidden' name='eventName' value='" + event.getEventName() + "'/>");
				pageContext.getOut().write("<input type='hidden' name='teamOne' value='" + event.getTeamOne() + "'/>");
				pageContext.getOut().write("<input type='hidden' name='teamTwo' value='" + event.getTeamTwo() + "'/>");
				pageContext.getOut().write("<input type='hidden' name='resultId' value='" + new Integer(event.getResultId()) + "'/>");
				pageContext.getOut().write("<input type='hidden' name='startDate' value='" + event.getStartDate().toString() + "'/>");
				pageContext.getOut().write("<input type='hidden' name='endDate' value='" + event.getEndDate().toString() + "'/>");
				pageContext.getOut().write("<input type='hidden' name='statusId' value='" + new Integer(event.getStatus()).toString() + "'/>");
				
				
				pageContext.getOut().write("<td>");
				pageContext.getOut()
						.write(event.getEventName());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getTeamOne());
				pageContext.getOut().write("</td align='center'>");
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getTeamTwo());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getResultAbbreviation());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getStartDate().toString());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getEndDate().toString());
				pageContext.getOut().write("</td>");
				
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getStatusDescription());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write("<button type='submit' style='top:0px; left:0px;' class='addEmailBtn'>" + edit + "</button>");
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("</form>");
				pageContext.getOut().write("</tr>");
			}
			pageContext.getOut().write("</tr>");
			pageContext.getOut().write("</table>");
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
		}
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}

	public String getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JSPListBean getEventsList() {
		return eventsList;
	}

	public void setEventsList(JSPListBean eventsList) {
		this.eventsList = eventsList;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
