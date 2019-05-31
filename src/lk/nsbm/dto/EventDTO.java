package lk.nsbm.dto;

import java.util.Date;
import java.util.List;

public class EventDTO {

    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private Date dateOfPublish;
    private String description;
    private String imagePath;
    private List<String> industryList;
    private String createdUser;
    private List<String> attendingUsers;

    public EventDTO() {
    }

    public EventDTO(int id, String title, Date startDate, Date endDate, Date dateOfPublish, String description,
                    String imagePath, List<String> industryList, String createdUser, List<String> attendingUsers) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateOfPublish = dateOfPublish;
        this.description = description;
        this.imagePath = imagePath;
        this.industryList = industryList;
        this.createdUser = createdUser;
        this.attendingUsers = attendingUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<String> industryList) {
        this.industryList = industryList;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public List<String> getAttendingUsers() {
        return attendingUsers;
    }

    public void setAttendingUsers(List<String> attendingUsers) {
        this.attendingUsers = attendingUsers;
    }
}
