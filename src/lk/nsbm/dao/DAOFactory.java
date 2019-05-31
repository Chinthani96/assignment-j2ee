package lk.nsbm.dao;

import lk.nsbm.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }

        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {

        switch (daoTypes) {
            case USER:
                return new UserDAOImpl();
            case COMMENT:
                return new CommentDAOImpl();
            case JOB_OPPERTUNITY:
                return new Job_OppertunityDAOImpl();
            case COUNT:
                return new CountDAOImpl();
            case WORKSHOP:
                return new WorkShopDAOImpl();
            case POST:
                return new PostDAOImpl();
            case SUBSCRIPTION:
                return new SubscriptionDAOImpl();
            case ANNOUNCEMENT:
                return new AnnouncementDAOImpl();
            case NOTIFY_HISTORY:
                return new NotifyHistoryDAOImpl();
            case KEY:
                return new KeyDAOImpl();
            case ATTENDING_USER:
                return new AttendingUserDAOImpl();
            default:
                return null;
        }
    }


    public enum DAOTypes {
        COMMENT, USER, JOB_OPPERTUNITY, POST, WORKSHOP, COUNT, SUBSCRIPTION, ANNOUNCEMENT, NOTIFY_HISTORY, KEY, ATTENDING_USER
    }
}
