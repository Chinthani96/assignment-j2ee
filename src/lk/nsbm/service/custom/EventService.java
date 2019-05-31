package lk.nsbm.service.custom;

import lk.nsbm.dto.EventDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.service.SuperService;
import lk.nsbm.shared.enums.Update_Status;

import java.util.List;

public interface EventService extends SuperService {
    List<EventDTO> getAllEvents();

    List<EventDTO> getUsersEvent(String username);

    Update_Status addNewEvent(EventDTO eventDTO);

    Update_Status updateEvent(EventDTO eventDTO);

    List<UserDTO> getEventsUsers(EventDTO eventDTO);

    Update_Status deleeEvent(EventDTO eventDTO);

    List<EventDTO> getSubscribersEvents(UserDTO user);
}
