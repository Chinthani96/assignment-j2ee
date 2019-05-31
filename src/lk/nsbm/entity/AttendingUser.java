package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.NonIdType;

import java.util.Date;

public class AttendingUser {
    @IdValue(name = "_id")
    private int _id;
    @NonIdValue(type = NonIdType.TYPE_ONE, name = "workshopId")
    private int workshopId;
    @NonIdValue(type = NonIdType.TYPE_TWO, name = "username")
    private String username;
    private Date dateOfAdded;

    public AttendingUser() {
    }

    public AttendingUser(int _id, int workshopId, String username, Date dateOfAdded) {
        this.set_id(_id);
        this.setWorkshopId(workshopId);
        this.setUsername(username);
        this.setDateOfAdded(dateOfAdded);
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfAdded() {
        return dateOfAdded;
    }

    public void setDateOfAdded(Date dateOfAdded) {
        this.dateOfAdded = dateOfAdded;
    }
}
