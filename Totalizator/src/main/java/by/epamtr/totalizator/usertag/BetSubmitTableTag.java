package by.epamtr.totalizator.usertag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.listbean.JSPListBean;
import by.epamtr.totalizator.bean.listbean.JSPMapBean;

/**
 * Class is designed to print a table with the information about results
 * suggested by the user. User should validate his choice and submit it.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class BetSubmitTableTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	/**
	 * List of events that are currently available for betting.
	 */
	private JSPListBean list;
	/**
	 * Amount of user's bet.
	 */
	private Integer betAmount;
	/**
	 * Map with the results suggested by the user.
	 */
	private JSPMapBean userResultsMap;
	/**
	 * Name of the first column.(Event's number).
	 */
	private String colunmName1;
	/**
	 * Name of the first column.(Event's name).
	 */
	private String colunmName2;
	/**
	 * User's result.
	 */
	private String colunmName3;

	public int doStartTag() throws JspTagException {

		int size = new Integer(list.getSize());
		try {
			pageContext.getOut().write("<table border='1' cellpadding='5' cellspacing='3'>");
			pageContext.getOut().write("<tr><th>");
			pageContext.getOut().write("#" + "</th>");

			pageContext.getOut().write("<th>" + colunmName1 + "</th>");
			pageContext.getOut().write("<th>" + colunmName2 + "</th>");
			pageContext.getOut().write("</tr>");

			for (int i = 1; i < size + 1; i++) {
				Event event = list.getElement();
				pageContext.getOut().write("<tr>");
				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(new Integer(i).toString());
				pageContext.getOut().write("</td>");

				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(event.getEventName());
				pageContext.getOut().write("</td>");

				pageContext.getOut().write("<td align='center'>");
				pageContext.getOut().write(userResultsMap.get("result" + new Integer(i).toString()));
				pageContext.getOut().write("</td>");

				pageContext.getOut().write("</tr>");
			}
			pageContext.getOut().write("</tr>");
			pageContext.getOut().write("</table>");

			return SKIP_BODY;

		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}

	public JSPListBean getList() {
		return list;
	}

	public void setList(JSPListBean list) {
		this.list = list;
	}

	public Integer getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Integer betAmount) {
		this.betAmount = betAmount;
	}

	public JSPMapBean getUserResultsMap() {
		return userResultsMap;
	}

	public void setUserResultsMap(JSPMapBean userResultsMap) {
		this.userResultsMap = userResultsMap;
	}

	public String getColunmName1() {
		return colunmName1;
	}

	public void setColunmName1(String colunmName1) {
		this.colunmName1 = colunmName1;
	}

	public String getColunmName2() {
		return colunmName2;
	}

	public void setColunmName2(String colunmName2) {
		this.colunmName2 = colunmName2;
	}

	public String getColunmName3() {
		return colunmName3;
	}

	public void setColunmName3(String colunmName3) {
		this.colunmName3 = colunmName3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
