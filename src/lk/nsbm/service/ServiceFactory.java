package lk.nsbm.service;

import lk.nsbm.service.custom.impl.*;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }

        return serviceFactory;
    }

    public SuperService getService(ServiceType serviceType) {
        switch (serviceType) {
            case ENCRYPTION:
                return new EncryptionServiceImpl();
            case USER:
                return new UserServiceImpl();
            case FILE_UPLOAD:
                return new FileUploadServiceImpl();
            case POSTS:
                return new PostsServiceImpl();
            case SUBSCRIPTION:
                return new SubscriptionServiceImpl();
            default:
                return null;
        }
    }


    public enum ServiceType {
        ENCRYPTION, USER, FILE_UPLOAD,POSTS, SUBSCRIPTION
    }
}
