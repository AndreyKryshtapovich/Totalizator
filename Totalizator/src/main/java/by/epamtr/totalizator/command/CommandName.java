package by.epamtr.totalizator.command;

public enum CommandName {
	/**
	 * Represents {@link by.epamtr.totalizator.command.impl.SignInCommand}
	 */
	SIGN_IN, 
	/**
	 * Represents {@link by.epamtr.totalizator.command.impl.GoToRegistrationCommand}
	 */
	GO_TO_REGISTRATION, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.RegistrationUserCommand}
	 */
	REGISTRATION_USER, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.SignOutCommand}
	 */
	SIGN_OUT,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.ShowEventsCommand}
	 */
	SHOW_EVENTS,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.UnknownCommand}
	 */
	UNKNOWN, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToGameCreationCommand}
	 */
	GO_TO_GAME_CREATION,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GameCreationCommand}
	 */
	GAME_CREATION,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToAdminPageCommand}
	 */
	GO_TO_ADMIN_PAGE,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToErrorPageCommand}
	 */
	 GO_TO_ERROR_PAGE, 
	 /**
	  * Represents {@link  by.epamtr.totalizator.command.impl.ChangeLanguageCommand}
	  */
	 CHANGE_LANGUAGE, 
	 /**
	  *  Represents {@link  by.epamtr.totalizator.command.impl.GoToEventCreationCommand}
	  */
	 GO_TO_EVENT_CREATION, 
	 /**
	  * Represents {@link  by.epamtr.totalizator.command.impl.EventCreationCommand}
	  */
	EVENT_CREATION,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToSearchEventCommand}
	 */
	GO_TO_SEARCH_EVENT,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.SearchMatchingEventsCommand}
	 */
	SEARCH_MATCHING_EVENTS,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.EventGameMatchingCommand}
	 */
	EVENT_GAME_MATCHING, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToEditSearchEventCommand}
	 */
	GO_TO_EDIT_SEARCH_EVENT,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.SearchAllGameEventsCommand}
	 */
	SEARCH_ALL_EVENTS,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToEventEditCommand}
	 */
	GO_TO_EVENT_EDIT, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.EditEventCommand}
	 */
	EDIT_EVENT, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.InitConnectionPoolCommand}
	 */
	INIT_CONNECTION_POOL, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.MakeBetCommand}
	 */
	MAKE_BET, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToBetSubmitCommand}
	 */
	GO_TO_BET_SUBMIT,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.UnmatchEventCommand}
	 */
	UNMATCH_EVENT,
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.DeleteEventCommand}
	 */
	DELETE_EVENT, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.CloseGameCouponCommand}
	 */
	CLOSE_GAME_COUPON, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.GoToGameCouponDetailsCommand}
	 */
	GO_TO_GAME_COUPON_DETAILS, 
	/**
	 * Represents {@link  by.epamtr.totalizator.command.impl.EditGameCouponCommand}
	 */
	EDIT_GAME_COUPON
}
