package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.NonIdType;
import lk.nsbm.shared.enums.Status;

import java.util.Date;

public class Post {
    @IdValue(name = "_id")
    private int _id;
    private String title;
    private String description;
    private Date dateCreated;
    private Status status;
    @NonIdValue(type = NonIdType.TYPE_ONE ,name = "cretaedUser")
    private User cretaedUser;
    private String imagePath;

    public Post() {
    }

    public Post(int _id, String title, String description, Date dateCreated, Status status, User cretaedUser, String imagePath) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
        this.cretaedUser = cretaedUser;
        this.imagePath = imagePath;
    }

    public Post(String title, String description, Date dateCreated, Status status, User cretaedUser, String imagePath) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
        this.cretaedUser = cretaedUser;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getCretaedUser() {
        return cretaedUser;
    }

    public void setCretaedUser(User cretaedUser) {
        this.cretaedUser = cretaedUser;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
