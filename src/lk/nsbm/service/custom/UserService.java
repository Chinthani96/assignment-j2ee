package lk.nsbm.service.custom;

import lk.nsbm.dto.UserDTO;
import lk.nsbm.dto.UserPasswordDTO;
import lk.nsbm.dto.response.LoginUserDTO;
import lk.nsbm.service.SuperService;
import lk.nsbm.shared.enums.Update_Status;

import java.util.List;

public interface UserService extends SuperService {

    public UserDTO registerUser(UserPasswordDTO userPasswordDTO);

    public LoginUserDTO loginUser(UserPasswordDTO userPasswordDTO);

    public UserDTO changeUserData(UserDTO userDTO , String password);

    public Update_Status changePassword(UserPasswordDTO userPasswordDTO , String password);

    UserDTO getUserDetails(String username);

    List<UserDTO> getUsers();


}
