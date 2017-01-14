package by.epamtr.totalizator.usertag;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;

public class EventsTableTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private JSPListBean matchedEventsList ;
	private String eventName;
	private String teamOne;
	private String teamTwo;
	private String startDate;
	private String endDate;


	public int doStartTag() throws JspTagException {
	
		if(matchedEventsList.getSize() == 0){
			try {
				pageContext.getOut().write("<p> There are no events matching this game.</p>");
				return SKIP_BODY;
			} catch (IOException e) {
				throw new JspTagException(e.getMessage());
			}
		}else{
		int size = new Integer(matchedEventsList.getSize());
		try {
			pageContext.getOut().write("<table border='1' cellpadding='5' cellspacing='3'>");
			pageContext.getOut().write("<tr><th>");
			pageContext.getOut().write("#" +"</th>");
			
			pageContext.getOut().write("<th>" + eventName + "</th>");
			pageContext.getOut().write("<th>" + teamOne + "</th>");
			pageContext.getOut().write("<th>" + teamTwo + "</th>");
			pageContext.getOut().write("<th>" + startDate + "</th>");
			pageContext.getOut().write("<th>" + endDate + "</th>");
			pageContext.getOut().write("</th></tr>");

			for(int i=0; i<size; i++) {
				Event event = matchedEventsList.getElement();
				pageContext.getOut().write("<tr>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(new Integer(i+1).toString());
				pageContext.getOut().write("</td>");
				
				pageContext.getOut().write("<td>");
				pageContext.getOut()
						.write(event.getEventName());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(event.getTeamOne());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(event.getTeamTwo());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(event.getStartDate().toString());
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(event.getEndDate().toString());
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


	public JSPListBean getMatchedEventsList() {
		return matchedEventsList;
	}


	public void setMatchedEventsList(JSPListBean matchedEventsList) {
		this.matchedEventsList = matchedEventsList;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
