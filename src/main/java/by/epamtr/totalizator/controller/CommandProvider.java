package by.epamtr.totalizator.controller;

import java.util.HashMap;
import java.util.Map;
import by.epamtr.totalizator.command.Command;
import by.epamtr.totalizator.command.CommandName;
import by.epamtr.totalizator.command.impl.ChangeLanguageCommand;
import by.epamtr.totalizator.command.impl.CloseGameCouponCommand;
import by.epamtr.totalizator.command.impl.DeleteEventCommand;
import by.epamtr.totalizator.command.impl.EditEventCommand;
import by.epamtr.totalizator.command.impl.EditGameCouponCommand;
import by.epamtr.totalizator.command.impl.EventCreationCommand;
import by.epamtr.totalizator.command.impl.EventGameMatchingCommand;
import by.epamtr.totalizator.command.impl.GameCreationCommand;
import by.epamtr.totalizator.command.impl.GoToAdminPageCommand;
import by.epamtr.totalizator.command.impl.GoToBetSubmitCommand;
import by.epamtr.totalizator.command.impl.GoToEditSearchEventCommand;
import by.epamtr.totalizator.command.impl.GoToErrorPageCommand;
import by.epamtr.totalizator.command.impl.GoToEventCreationCommand;
import by.epamtr.totalizator.command.impl.GoToEventEditCommand;
import by.epamtr.totalizator.command.impl.GoToGameCouponDetailsCommand;
import by.epamtr.totalizator.command.impl.GoToSearchEventCommand;
import by.epamtr.totalizator.command.impl.MakeBetCommand;
import by.epamtr.totalizator.command.impl.GoToGameCreationCommand;
import by.epamtr.totalizator.command.impl.GoToRegistrationCommand;
import by.epamtr.totalizator.command.impl.RegistrationUserCommand;
import by.epamtr.totalizator.command.impl.SearchAllGameEventsCommand;
import by.epamtr.totalizator.command.impl.SearchMatchingEventsCommand;
import by.epamtr.totalizator.command.impl.ShowEventsCommand;
import by.epamtr.totalizator.command.impl.SignInCommand;
import by.epamtr.totalizator.command.impl.SignOutCommand;
import by.epamtr.totalizator.command.impl.UnknownCommand;
import by.epamtr.totalizator.command.impl.UnmatchEventCommand;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandProvider() {
		commands.put(CommandName.SIGN_IN, new SignInCommand());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
		commands.put(CommandName.REGISTRATION_USER, new RegistrationUserCommand());
		commands.put(CommandName.SIGN_OUT, new SignOutCommand());
		commands.put(CommandName.SHOW_EVENTS, new ShowEventsCommand());
		commands.put(CommandName.GO_TO_GAME_CREATION, new GoToGameCreationCommand());
		commands.put(CommandName.GAME_CREATION, new GameCreationCommand());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
		commands.put(CommandName.UNKNOWN, new UnknownCommand());
		commands.put(CommandName.GO_TO_EVENT_CREATION, new GoToEventCreationCommand());
		commands.put(CommandName.EVENT_CREATION, new EventCreationCommand());
		commands.put(CommandName.GO_TO_SEARCH_EVENT, new GoToSearchEventCommand());
		commands.put(CommandName.SEARCH_MATCHING_EVENTS, new SearchMatchingEventsCommand());
		commands.put(CommandName.EVENT_GAME_MATCHING, new EventGameMatchingCommand());
		commands.put(CommandName.GO_TO_EDIT_SEARCH_EVENT, new GoToEditSearchEventCommand());
		commands.put(CommandName.SEARCH_ALL_EVENTS, new SearchAllGameEventsCommand());
		commands.put(CommandName.GO_TO_EVENT_EDIT, new GoToEventEditCommand());
		commands.put(CommandName.EDIT_EVENT, new EditEventCommand());
		commands.put(CommandName.MAKE_BET, new MakeBetCommand());
		commands.put(CommandName.GO_TO_BET_SUBMIT, new GoToBetSubmitCommand());
		commands.put(CommandName.UNMATCH_EVENT, new UnmatchEventCommand());
		commands.put(CommandName.DELETE_EVENT, new DeleteEventCommand());
		commands.put(CommandName.CLOSE_GAME_COUPON, new CloseGameCouponCommand());
		commands.put(CommandName.GO_TO_GAME_COUPON_DETAILS, new GoToGameCouponDetailsCommand());
		commands.put(CommandName.EDIT_GAME_COUPON, new EditGameCouponCommand());
	}

	public Command getCommand(String commandName) {

		Command command = null;
		CommandName key = null;

		commandName = commandName.replace("-", "_").toUpperCase();
		try{
			key = CommandName.valueOf(commandName);
		}catch(IllegalArgumentException e){
			key = CommandName.UNKNOWN;
		}
			command = commands.get(key);
		return command;
	}
}
