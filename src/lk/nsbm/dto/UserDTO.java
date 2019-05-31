package lk.nsbm.dto;

import lk.nsbm.entity.User;
import lk.nsbm.shared.enums.Country;
import lk.nsbm.shared.enums.Industry_Type;
import lk.nsbm.shared.enums.Update_Status;
import lk.nsbm.shared.enums.User_Type;

import java.util.Date;

public class UserDTO {

    private String username;
    private Date dob;
    private String details;
    private Industry_Type industry;
    private Country country;
    private Date dateOfCreated;
    private User_Type user_type;
    private String user_profile_path;
    private User university;
    private Date created_date;
    private Date lastLoginDate;
    private int loginAttempts;
    private Update_Status update_status;

    public UserDTO() {
    }

    public UserDTO(String username, Date dob, String details, Industry_Type industry,
                   Country country, Date dateOfCreated, User_Type user_type, String user_profile_path,
                   User university, Date created_date, Date lastLoginDate, int loginAttempts) {
        this.username = username;
        this.dob = dob;
        this.details = details;
        this.industry = industry;
        this.country = country;
        this.dateOfCreated = dateOfCreated;
        this.user_type = user_type;
        this.user_profile_path = user_profile_path;
        this.university = university;
        this.created_date = created_date;
        this.lastLoginDate = lastLoginDate;
        this.loginAttempts = loginAttempts;
    }

    public UserDTO(String username, Date dob, String details, Industry_Type industry, Country country,
                   Date dateOfCreated, User_Type user_type, String user_profile_path, User university,
                   Date created_date, Date lastLoginDate, int loginAttempts, Update_Status update_status) {
        this.username = username;
        this.dob = dob;
        this.details = details;
        this.industry = industry;
        this.country = country;
        this.dateOfCreated = dateOfCreated;
        this.user_type = user_type;
        this.user_profile_path = user_profile_path;
        this.university = university;
        this.created_date = created_date;
        this.lastLoginDate = lastLoginDate;
        this.loginAttempts = loginAttempts;
        this.update_status = update_status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Industry_Type getIndustry() {
        return industry;
    }

    public void setIndustry(Industry_Type industry) {
        this.industry = industry;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(Date dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public User_Type getUser_type() {
        return user_type;
    }

    public void setUser_type(User_Type user_type) {
        this.user_type = user_type;
    }

    public String getUser_profile_path() {
        return user_profile_path;
    }

    public void setUser_profile_path(String user_profile_path) {
        this.user_profile_path = user_profile_path;
    }

    public User getUniversity() {
        return university;
    }

    public void setUniversity(User university) {
        this.university = university;
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

    public Update_Status getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(Update_Status update_status) {
        this.update_status = update_status;
    }
}
