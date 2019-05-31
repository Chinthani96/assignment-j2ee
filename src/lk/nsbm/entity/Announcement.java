package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.enums.Announcement_Status;

import java.util.Date;

public class Announcement {

    @IdValue(name = "_id")
    private int _id;
    private User user;
    private Post post;
    private WorkShop workShop;
    private Job_Oppertunity job_oppertunity;
    private Announcement_Status announcement_status;
    private String description;
    private Date announced_date;
    private Date read_date;
    private User announcement_abt;

    public Announcement() {
    }

    public Announcement(int _id, User user, Job_Oppertunity job_oppertunity, Announcement_Status announcement_status, String description, Date announced_date, Date read_date, User announcement_abt) {
        this._id = _id;
        this.user = user;
        this.job_oppertunity = job_oppertunity;
        this.announcement_status = announcement_status;
        this.description = description;
        this.announced_date = announced_date;
        this.read_date = read_date;
        this.announcement_abt = announcement_abt;
    }

    public Announcement(int _id, User user, WorkShop workShop, Announcement_Status announcement_status, String description, Date announced_date, Date read_date, User announcement_abt) {
        this._id = _id;
        this.user = user;
        this.workShop = workShop;
        this.announcement_status = announcement_status;
        this.description = description;
        this.announced_date = announced_date;
        this.read_date = read_date;
        this.announcement_abt = announcement_abt;
    }

    public Announcement(int _id, User user, Post post, Announcement_Status announcement_status, String description, Date announced_date, Date read_date, User announcement_abt) {
        this._id = _id;
        this.user = user;
        this.post = post;
        this.announcement_status = announcement_status;
        this.description = description;
        this.announced_date = announced_date;
        this.read_date = read_date;
        this.announcement_abt = announcement_abt;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public WorkShop getWorkShop() {
        return workShop;
    }

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    public Job_Oppertunity getJob_oppertunity() {
        return job_oppertunity;
    }

    public void setJob_oppertunity(Job_Oppertunity job_oppertunity) {
        this.job_oppertunity = job_oppertunity;
    }

    public Announcement_Status getAnnouncement_status() {
        return announcement_status;
    }

    public void setAnnouncement_status(Announcement_Status announcement_status) {
        this.announcement_status = announcement_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAnnounced_date() {
        return announced_date;
    }

    public void setAnnounced_date(Date announced_date) {
        this.announced_date = announced_date;
    }

    public Date getRead_date() {
        return read_date;
    }

    public void setRead_date(Date read_date) {
        this.read_date = read_date;
    }

    public User getAnnouncement_abt() {
        return announcement_abt;
    }

    public void setAnnouncement_abt(User announcement_abt) {
        this.announcement_abt = announcement_abt;
    }
}
