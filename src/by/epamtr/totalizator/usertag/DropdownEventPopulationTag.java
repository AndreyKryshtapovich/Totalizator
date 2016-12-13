package by.epamtr.totalizator.usertag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;

public class DropdownEventPopulationTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private JSPListBean eventsList;

	public int doStartTag() throws JspTagException {
		if (eventsList.getSize() == 0) {
			try {
				pageContext.getOut().write("<p> There are no events that could be matched to game.</p>");
				return SKIP_BODY;
			} catch (IOException e) {
				throw new JspTagException(e.getMessage());
			}
		} else {
			int size = eventsList.getSize();
			try {

				pageContext.getOut().write("<input list='events' name='event'>");

				pageContext.getOut().write("<datalist id='events'>");
				for (int i = 0; i < size; i++) {
					Event currentEvent = eventsList.getElement();
					
					pageContext.getOut().write("<option value='" + new Integer(currentEvent.getEventId()).toString()+ "'>");
					pageContext.getOut().write( new Integer(currentEvent.getEventId()).toString() + " " + currentEvent.getStartDate().toString() + " - " + currentEvent.getEndDate().toString());
					pageContext.getOut().write("</option>");
				}
				pageContext.getOut().write("</datalist>");
				//pageContext.getOut().write("<input type='submit' class='addEmailBtn' value='Submit Matching'>");
				
				pageContext.getOut().write("<button type='submit' class='addEmailBtn'>Submit Matching</button>");
			} catch (IOException e) {
				throw new JspTagException(e.getMessage());
			}
			return SKIP_BODY;
		}
	}

	public JSPListBean getEventsList() {
		return eventsList;
	}

	public void setEventsList(JSPListBean eventsList) {
		this.eventsList = eventsList;
	}

}
