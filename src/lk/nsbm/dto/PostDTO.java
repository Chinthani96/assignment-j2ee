package lk.nsbm.dto;

import lk.nsbm.entity.User;
import lk.nsbm.shared.enums.Status;
import lk.nsbm.shared.enums.Update_Status;

import java.util.Date;

public class PostDTO {

    private int _id;
    private String title;
    private String description;
    private Date dateCreated;
    private Status status;
    private User cretaedUser;
    private String imagePath;
    private Update_Status update_status;

    public PostDTO() {
    }

    public PostDTO(int _id, String title, String description, Date dateCreated, Status status, User cretaedUser,
                   String imagePath, Update_Status update_status) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
        this.cretaedUser = cretaedUser;
        this.imagePath = imagePath;
        this.update_status = update_status;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public Update_Status getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(Update_Status update_status) {
        this.update_status = update_status;
    }
}
