package lk.nsbm.dto;

import lk.nsbm.entity.User;
import lk.nsbm.shared.enums.Country;
import lk.nsbm.shared.enums.Industry_Type;
import lk.nsbm.shared.enums.User_Type;

import java.util.Date;

public class UserPasswordDTO {

    private String username;
    private String password;
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

    public UserPasswordDTO() {
    }

    public UserPasswordDTO(String username, String password, Date dob, String details, Industry_Type industry, Country country, Date dateOfCreated, User_Type user_type, String user_profile_path, User university, Date created_date, Date lastLoginDate, int loginAttempts) {
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
