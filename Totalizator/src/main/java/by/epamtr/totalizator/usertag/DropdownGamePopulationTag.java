package by.epamtr.totalizator.usertag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epamtr.totalizator.bean.entity.GameCoupon;
import by.epamtr.totalizator.bean.listbean.JSPGameListBean;

/**
 * Class is designed to print a dropdown for the administrator. Dropdown
 * contains information about game coupons.
 * 
 * @author Andrey Kryshtapovich
 *
 */
public class DropdownGamePopulationTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * List of game coupons.
	 */
	private JSPGameListBean gamesList;

	public int doStartTag() throws JspTagException {
		if (gamesList.getSize() == 0) {
			try {
				pageContext.getOut().write("<p> There are no games in development.</p>");
				return SKIP_BODY;
			} catch (IOException e) {
				throw new JspTagException(e.getMessage());
			}
		} else {
			int size = gamesList.getSize();
			try {

				pageContext.getOut().write("<input list='games' name='game' id='game' autocomplete='off'>");

				pageContext.getOut().write("<datalist id='games'>");
				for (int i = 0; i < size; i++) {
					GameCoupon currentGameCupoun = gamesList.getElement();
					pageContext.getOut()
							.write("<option value='" + new Integer(currentGameCupoun.getGameCupounId()).toString() + " "
									+ " " + currentGameCupoun.getStartDate().toString() + " - "
									+ currentGameCupoun.getEndDate().toString() + "'>");

					pageContext.getOut().write("</option>");
				}
				pageContext.getOut().write("</datalist>");

			} catch (IOException e) {
				throw new JspTagException(e.getMessage());
			}

			return SKIP_BODY;
		}
	}

	public JSPGameListBean getGamesList() {
		return gamesList;
	}

	public void setGamesList(JSPGameListBean gamesList) {
		this.gamesList = gamesList;
	}

}
