package lk.nsbm.service.custom.impl;

import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.*;
import lk.nsbm.dto.AnnouncementDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.entity.NotifyHistory;
import lk.nsbm.entity.Subscription;
import lk.nsbm.entity.User;
import lk.nsbm.service.custom.AnnouncementService;

import java.util.Date;
import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {

    private UserDAO userDAO;
    private AnnouncementDAO announcementDAO;
    private SubscriptionDAO subscriptionDAO;
    private NotifyHistoryDAO notifyHistoryDAO;
    private PostDAO postDAO;
    private WorkShopDAO workShopDAO;
    private Job_OppertunityDAO job_oppertunityDAO;

    public AnnouncementServiceImpl() {
        userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
        announcementDAO = (AnnouncementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ANNOUNCEMENT);
        subscriptionDAO = (SubscriptionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUBSCRIPTION);
        notifyHistoryDAO = (NotifyHistoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.NOTIFY_HISTORY);
        postDAO = (PostDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.POST);
        workShopDAO = (WorkShopDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.WORKSHOP);
        job_oppertunityDAO = (Job_OppertunityDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.JOB_OPPERTUNITY);
    }

    @Override
    public void loadAnnouncements(UserDTO userDTO) {
        User userEntity = userDAO.findById(userDTO.getUsername());

        if (userEntity == null) {
            return;
        }

        List<Subscription> usersSubscribedList = subscriptionDAO.getUsersSubscribedList(userEntity);
        NotifyHistory historyNotified = notifyHistoryDAO.findById(userEntity);
        if (historyNotified == null) {
            historyNotified = new NotifyHistory(userEntity,new Date());
        }
        Date lastNotifiedDate = historyNotified.getTimeOfLastChecked();

        for (Subscription sub :
                usersSubscribedList) {

        }
    }

    @Override
    public List<AnnouncementDTO> getAnnouncementListOfUser(UserDTO userDTO) {
        return null;
    }
}
