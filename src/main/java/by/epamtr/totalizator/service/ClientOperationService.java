package by.epamtr.totalizator.service;

import java.util.List;

import by.epamtr.totalizator.bean.dto.MakeBetDTO;
import by.epamtr.totalizator.bean.dto.UserDTO;
import by.epamtr.totalizator.bean.entity.Event;
import by.epamtr.totalizator.bean.entity.GameCupoun;
import by.epamtr.totalizator.service.exception.ServiceException;

public interface ClientOperationService {
	boolean registrationUser(UserDTO userDTO, byte[] password, byte[] repPassword) throws ServiceException;

	List<Event> showEvents(int gameCouponId) throws ServiceException;
	GameCupoun getOpenedGame () throws ServiceException;
	boolean makeBet(MakeBetDTO makeBetDTO,byte[] creditCardNumber ) throws ServiceException;
}
