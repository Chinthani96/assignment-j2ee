package lk.nsbm.service.custom.impl;

import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.UserDAO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.dto.UserPasswordDTO;
import lk.nsbm.dto.response.LoginUserDTO;
import lk.nsbm.entity.User;
import lk.nsbm.service.ServiceFactory;
import lk.nsbm.service.custom.EncryptionService;
import lk.nsbm.service.custom.UserService;
import lk.nsbm.shared.enums.Login_Status;
import lk.nsbm.shared.enums.Update_Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private EncryptionService encryptionService;

    public UserServiceImpl() {
        userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
        encryptionService = (EncryptionService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ENCRYPTION);
    }

    @Override
    public UserDTO registerUser(UserPasswordDTO userPasswordDTO) {
        User userEntity = userDAO.findById(userPasswordDTO.getUsername());

        if (userEntity != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUpdate_status(Update_Status.FAILED);

            return userDTO;
        }

        User newUserEntity = new User(userPasswordDTO.getUsername(), userPasswordDTO.getPassword(), userPasswordDTO.getDob(),
                userPasswordDTO.getDetails(), userPasswordDTO.getIndustry(), userPasswordDTO.getCountry(), new Date(), userPasswordDTO.getUser_type(),
                userPasswordDTO.getUser_profile_path(), userPasswordDTO.getCreated_date(), userPasswordDTO.getLastLoginDate(), 0);

        newUserEntity = encryptionService.generateEncryptionValue(newUserEntity);

        System.out.println(newUserEntity.getPassword());

        User user = userDAO.save(newUserEntity);

        return new UserDTO(user.getUsername(), user.getDob(), user.getDetails(), user.getIndustry(), user.getCountry(), user.getDateOfCreated(),
                user.getUser_type(), user.getUser_profile_path(), user.getUniversity(), user.getCreated_date(), user.getLastLoginDate(), user.getLoginAttempts(), Update_Status.NEW);
    }

    @Override
    public LoginUserDTO loginUser(UserPasswordDTO userPasswordDTO) {
        User userEntity = userDAO.findById(userPasswordDTO.getUsername());

        if (userEntity == null) {
            return new LoginUserDTO(null, null, null, null, -1, Login_Status.INVALID_USERNAME);
        }

        boolean isPasswordMatch = encryptionService.isDataEqualsWithEncryption(userPasswordDTO.getPassword(), userEntity);

        if (isPasswordMatch && !(userEntity.getLoginAttempts() > 4)) {
            int attempts = userDAO.updateLoginAttempts(userEntity.getUsername(), 0);

            /*return new UserDTO(userEntity.getUsername() , userEntity.getDob() , userEntity.getDetails() , userEntity.getIndustry() , userEntity.getCountry() ,
                    userEntity.getDateOfCreated() , userEntity.getUser_type() , userEntity.getUser_profile_path() , userEntity.getUniversity() , userEntity.getCreated_date() ,
                    userEntity.getLastLoginDate() , attempts );*/

            return new LoginUserDTO(userEntity.getUsername(), userEntity.getUser_type(), userEntity.getCreated_date(),
                    userEntity.getLastLoginDate(), attempts, Login_Status.SUCCESS);

        } else if (userEntity.getLoginAttempts() > 4) {
            int attempts = userDAO.updateLoginAttempts(userEntity.getUsername(), 1);
            return new LoginUserDTO(userEntity.getUsername(), userEntity.getUser_type(), userEntity.getCreated_date(),
                    userEntity.getLastLoginDate(), attempts, Login_Status.LOCKED);
        } else if (!isPasswordMatch) {
            int attempts = userDAO.updateLoginAttempts(userEntity.getUsername(), 1);
            return new LoginUserDTO(userEntity.getUsername(), userEntity.getUser_type(), userEntity.getCreated_date(),
                    userEntity.getLastLoginDate(), attempts, Login_Status.INVALID_PASSWORD);
        }

        return new LoginUserDTO(null, null, null, null, -1, Login_Status.FAILED);
    }

    @Override
    public UserDTO changeUserData(UserDTO userDTO, String password) {
        User userEntity = userDAO.findById(userDTO.getUsername());

        if (userEntity == null) {
            userDTO.setUpdate_status(Update_Status.USER_NOT_FOUND);
            return userDTO;
        }

        boolean isPasswordMatches = encryptionService.isDataEqualsWithEncryption(password, userEntity);

        if (isPasswordMatches) {
            User updatedData = userDAO.update(new User(userEntity.getUsername(), userEntity.getPassword(), userDTO.getDob(), userDTO.getDetails(), userDTO.getIndustry(),
                    userDTO.getCountry(), userDTO.getDateOfCreated(), userDTO.getUser_type(), userDTO.getUser_profile_path(), userDTO.getCreated_date(), userDTO.getLastLoginDate(),
                    userDTO.getLoginAttempts()), userEntity.getUsername());

            if (updatedData == null) {
                userDTO.setUpdate_status(Update_Status.FAILED);

                return userDTO;
            }

            return new UserDTO(updatedData.getUsername(), updatedData.getDob(), updatedData.getDetails(), updatedData.getIndustry(), updatedData.getCountry(), updatedData.getDateOfCreated(),
                    updatedData.getUser_type(), updatedData.getUser_profile_path(), updatedData.getUniversity(), updatedData.getCreated_date(), updatedData.getLastLoginDate(),
                    updatedData.getLoginAttempts(), Update_Status.SUCCESS);
        } else {
            userDTO.setUpdate_status(Update_Status.INVALID_CREDENTIALS);

            return userDTO;
        }
    }

    @Override
    public Update_Status changePassword(UserPasswordDTO userPasswordDTO, String password) {
        User userEntity = userDAO.findById(userPasswordDTO.getUsername());

        if (userEntity == null) {
            return Update_Status.USER_NOT_FOUND;
        }

        boolean isPasswordValid = encryptionService.isDataEqualsWithEncryption(password, userEntity);

        if (isPasswordValid) {
            userEntity.setPassword(userPasswordDTO.getPassword());
            userEntity = encryptionService.generateEncryptionValue(userEntity);

            User updatedUserData = userDAO.update(userEntity, userEntity.getUsername());

            if (updatedUserData != null) {
                return Update_Status.SUCCESS;
            }

            return Update_Status.FAILED;
        }

        return Update_Status.INVALID_CREDENTIALS;
    }

    @Override
    public UserDTO getUserDetails(String username) {
        User userEntity = userDAO.findById(username);

        if (userEntity == null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUpdate_status(Update_Status.USER_NOT_FOUND);

            return userDTO;
        }

        return new UserDTO(userEntity.getUsername(), userEntity.getDob(), userEntity.getDetails(),
                userEntity.getIndustry(), userEntity.getCountry(), userEntity.getDateOfCreated(),
                userEntity.getUser_type(), userEntity.getUser_profile_path(), userEntity.getUniversity(),
                userEntity.getCreated_date(), userEntity.getLastLoginDate(), userEntity.getLoginAttempts(), Update_Status.SUCCESS);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> usersDTOlist = new ArrayList<>();
        List<User> userEntities = userDAO.findAll();

        for (User user :
                userEntities) {
            usersDTOlist.add(new UserDTO(user.getUsername(), user.getDob(), user.getDetails(), user.getIndustry(), user.getCountry(),
                    user.getDateOfCreated(), user.getUser_type(), user.getUser_profile_path(), user.getUniversity(), user.getCreated_date(),
                    user.getLastLoginDate(), user.getLoginAttempts()));
        }

        return usersDTOlist;
    }
}
