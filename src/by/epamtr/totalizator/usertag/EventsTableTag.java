package by.epamtr.totalizator.usertag;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;

public class EventsTableTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private JSPListBean matchedEventsList ;

	
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
			
			pageContext.getOut().write("<th>" + "Event Name" + "</th>");
			pageContext.getOut().write("<th>" + "Team One" + "</th>");
			pageContext.getOut().write("<th>" + "Team Two" + "</th>");
			pageContext.getOut().write("<th>" + "Start Date" + "</th>");
		//	pageContext.getOut().write("<th>" + "Start Time" + "</th>");
			pageContext.getOut().write("<th>" + "End Date" + "</th>");
		//	pageContext.getOut().write("<th>" + "End Time" + "</th>");
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
				/*pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
			//	pageContext.getOut().write("<input type='date' name='end-date" + new Integer(i+1).toString() + "'/>");
			//	pageContext.getOut().write("</td>");
			//	pageContext.getOut().write("<td>");
			//	pageContext.getOut().write("<input type='number' min='0' max='23' name='end-hours" + new Integer(i+1).toString() + "'/> : <input type='number' min='0' max='59' name='end-minutes" + new Integer(i+1).toString() + "'/>");
			//	pageContext.getOut().write("</td>");
*/				pageContext.getOut().write("</tr>");
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
}
