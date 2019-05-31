package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.Industry_Type;
import lk.nsbm.shared.enums.NonIdType;

import java.util.Date;
import java.util.List;

public class WorkShop {

    @IdValue(name = "_id")
    private int _id;
    private String title;
    private Date startDate;
    private Date endDate;
    private Date dateOfPublish;
    private String description;
    private String imagePath;
    private List<Industry_Type> industrLists;
    @NonIdValue(type = NonIdType.TYPE_ONE, name = "createdUser")
    private User createdUser;

    public WorkShop() {
    }

    public WorkShop(String title, Date startDate, Date endDate, Date dateOfPublish, String description,
                    String imagePath, List<Industry_Type> industrLists, User createdUser) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateOfPublish = dateOfPublish;
        this.description = description;
        this.imagePath = imagePath;
        this.industrLists = industrLists;
        this.createdUser = createdUser;
    }

    public WorkShop(int _id, String title, Date startDate, Date endDate, Date dateOfPublish, String description,
                    String imagePath, List<Industry_Type> industrLists, User createdUser) {
        this._id = _id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateOfPublish = dateOfPublish;
        this.description = description;
        this.imagePath = imagePath;
        this.industrLists = industrLists;
        this.createdUser = createdUser;
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

    public List<Industry_Type> getIndustrLists() {
        return industrLists;
    }

    public void setIndustrLists(List<Industry_Type> industrLists) {
        this.industrLists = industrLists;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
