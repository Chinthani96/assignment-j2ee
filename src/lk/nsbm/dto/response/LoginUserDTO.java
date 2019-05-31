package lk.nsbm.dto.response;

import lk.nsbm.entity.User;
import lk.nsbm.shared.enums.Country;
import lk.nsbm.shared.enums.Industry_Type;
import lk.nsbm.shared.enums.Login_Status;
import lk.nsbm.shared.enums.User_Type;

import java.util.Date;

public class LoginUserDTO {
    private String username;
    private User_Type user_type;
    private Date created_date;
    private Date lastLoginDate;
    private int loginAttempts;
    private Login_Status login_status;

    public LoginUserDTO() {
    }

    public LoginUserDTO(String username, User_Type user_type, Date created_date, Date lastLoginDate, int loginAttempts, Login_Status login_status) {
        this.username = username;
        this.user_type = user_type;
        this.created_date = created_date;
        this.lastLoginDate = lastLoginDate;
        this.loginAttempts = loginAttempts;
        this.login_status = login_status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User_Type getUser_type() {
        return user_type;
    }

    public void setUser_type(User_Type user_type) {
        this.user_type = user_type;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Login_Status getLogin_status() {
        return login_status;
    }

    public void setLogin_status(Login_Status login_status) {
        this.login_status = login_status;
    }
}
