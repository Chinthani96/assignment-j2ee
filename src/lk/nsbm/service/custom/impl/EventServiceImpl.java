package lk.nsbm.service.custom.impl;

import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.AttendingUserDAO;
import lk.nsbm.dao.custom.UserDAO;
import lk.nsbm.dao.custom.WorkShopDAO;
import lk.nsbm.dto.EventDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.entity.AttendingUser;
import lk.nsbm.entity.User;
import lk.nsbm.entity.WorkShop;
import lk.nsbm.service.custom.EventService;
import lk.nsbm.shared.enums.CountTypes;
import lk.nsbm.shared.enums.Industry_Type;
import lk.nsbm.shared.enums.Update_Status;

import java.util.ArrayList;
import java.util.List;

public class EventServiceImpl implements EventService {

    private WorkShopDAO workShopDAO;
    private AttendingUserDAO attendingUserDAO;
    private UserDAO userDAO;

    public EventServiceImpl() {
        workShopDAO = (WorkShopDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.WORKSHOP);
        attendingUserDAO = (AttendingUserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ATTENDING_USER);
        userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<WorkShop> all = workShopDAO.findAll();
        List<EventDTO> eventsList = this.getEventListFromWorkShop(all);

        return eventsList;
    }

    @Override
    public List<EventDTO> getUsersEvent(String username) {
        if (!userDAO.isAvailable(username)) {
            return null;
        }

        User userEntity = new User();
        userEntity.setUsername(username);

        List<WorkShop> usersWorkShops = workShopDAO.findByCreatedUser(userEntity);
        List<EventDTO> eventsList = getEventListFromWorkShop(usersWorkShops);

        return eventsList;

    }

    @Override
    public Update_Status addNewEvent(EventDTO eventDTO) {
        if (!userDAO.isAvailable(eventDTO.getCreatedUser())) {
            return Update_Status.USER_NOT_FOUND;
        }

        int workshopId = CountServiceImpl.getInstance().getCount(CountTypes.WORKSHOP);
        User createdUser = new User();
        createdUser.setUsername(eventDTO.getCreatedUser());
        List<Industry_Type> industry_types = new ArrayList<>();

        for (String industryType :
                eventDTO.getIndustryList()) {

            industry_types.add(Industry_Type.valueOf(industryType));
        }

        WorkShop workShopEntity = new WorkShop(workshopId, eventDTO.getTitle(), eventDTO.getStartDate(),
                eventDTO.getEndDate(), eventDTO.getDateOfPublish(), eventDTO.getDescription(), eventDTO.getImagePath(),
                industry_types, createdUser);

        WorkShop savedEntity = workShopDAO.save(workShopEntity);

        if (savedEntity == null) {
            return Update_Status.FAILED;
        }

        return Update_Status.SUCCESS;
    }

    @Override
    public Update_Status updateEvent(EventDTO eventDTO) {
        if (!userDAO.isAvailable(eventDTO.getCreatedUser())) {
            return Update_Status.USER_NOT_FOUND;
        }

        WorkShop workShopEntity = workShopDAO.findById(eventDTO.getId());

        if (workShopEntity == null) {
            return Update_Status.NOT_FOUND;
        }
        return null;

    }

    @Override
    public List<UserDTO> getEventsUsers(EventDTO eventDTO) {
        return null;
    }

    @Override
    public Update_Status deleeEvent(EventDTO eventDTO) {
        return null;
    }

    @Override
    public List<EventDTO> getSubscribersEvents(UserDTO user) {
        return null;
    }

    private List<EventDTO> getEventListFromWorkShop(List<WorkShop> usersWorkShops) {
        List<EventDTO> eventsList = new ArrayList<>();
        for (WorkShop workShop :
                usersWorkShops) {

            List<String> industryList = new ArrayList<>();
            List<Industry_Type> industrLists = workShop.getIndustrLists();

            if (industrLists != null) {
                for (Industry_Type industryType :
                        industrLists) {
                    industryList.add(industryType.name());
                }
            }

            List<String> attendingList = new ArrayList<>();
            List<AttendingUser> usersOfEvent = attendingUserDAO.getUsersOfEvent(workShop.get_id());

            if (usersOfEvent != null) {
                for (AttendingUser attendingUser :
                        usersOfEvent) {
                    attendingList.add(attendingUser.getUsername());
                }
            }

            eventsList.add(
                    new EventDTO(
                            workShop.get_id(), workShop.getTitle(), workShop.getStartDate(), workShop.getEndDate(),
                            workShop.getDateOfPublish(), workShop.getDescription(), workShop.getImagePath(), industryList,
                            workShop.getCreatedUser().getUsername(), attendingList
                    )
            );
        }

        return eventsList;
    }
}
