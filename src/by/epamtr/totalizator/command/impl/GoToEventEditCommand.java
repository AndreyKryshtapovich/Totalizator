package by.epamtr.totalizator.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.exception.CommandException;
import by.epamtr.totalizator.controller.PageName;

public class GoToEventEditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String url = "Controller?command=go-to-event-edit&eventId=" + request.getParameter("eventId")
		+ "&gameId=" + request.getParameter("gameId") + "&eventName=" + request.getParameter("eventName")
		+ "&teamOne=" + request.getParameter("teamOne") + "&teamTwo="
		+ request.getParameter("teamTwo") + "&resultId=" + request.getParameter("resultId") + "&startDate="
		+ request.getParameter("startDate") + "&endDate=" + request.getParameter("endDate") + "&statusId="
		+ request.getParameter("statusId");
		
		request.getSession(false).setAttribute("currentUrl", url);
		
		Map<Integer,String> results = new HashMap<>();
		results.put(1, "1");
		results.put(2, "2");
		results.put(3, "X");
		results.put(4, "Unknown");
		
		
		Map<Integer,String> status = new HashMap<>();
		status.put(1, "Opened");
		status.put(2, "In progress");
		status.put(3, "Closed");
		status.put(4, "Canceled");
		status.put(5, "In developing");
	
		String fullStartDate = request.getParameter("startDate");
		String startDate = fullStartDate.substring(0,fullStartDate.indexOf(" "));
	
		String startTimeHours = fullStartDate.substring(fullStartDate.indexOf(" ") + 1,fullStartDate.indexOf(":"));
		String startTimeMinutes = fullStartDate.substring(fullStartDate.indexOf(":") + 1,fullStartDate.indexOf(":") + 3);
		
		String fullEndDate = request.getParameter("endDate");
		String endDate = fullEndDate.substring(0,fullStartDate.indexOf(" "));
		String endTimeHours = fullEndDate.substring(fullStartDate.indexOf(" ") + 1,fullStartDate.indexOf(":"));
		String endTimeMinutes = fullEndDate.substring(fullStartDate.indexOf(":") + 1,fullStartDate.indexOf(":") + 3);
		
		
		request.setAttribute("eventId", request.getParameter("eventId"));
		request.setAttribute("eventName", request.getParameter("eventName"));
		request.setAttribute("gameId", request.getParameter("gameId"));
		request.setAttribute("teamOne", request.getParameter("teamOne"));
		request.setAttribute("teamTwo", request.getParameter("teamTwo"));
		
		request.setAttribute("selectedRes", request.getParameter("resultId"));
		request.setAttribute("resultsMap", results);
		
		request.setAttribute("startDate", startDate);
		
		request.setAttribute("startTimeHours", startTimeHours);
		request.setAttribute("startTimeMinutes", startTimeMinutes);
		request.setAttribute("endDate", endDate);
		request.setAttribute("endTimeHours", endTimeHours);
		request.setAttribute("endTimeMinutes", endTimeMinutes);
		
		request.setAttribute("selectedStatus", request.getParameter("statusId"));
		request.setAttribute("statusMap", status);

		String page = PageName.EDIT_EVENT;
		return page;
	}

}
