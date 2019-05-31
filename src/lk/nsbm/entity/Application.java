package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;

import java.util.Date;

public class Application {

    @IdValue(name = "_id")
    private int _id;
    private String title;
    private String description;
    private String resumePath;
    private String email;
    private String contactNo;
    private Job_Oppertunity job_oppertunity;
    private User user;
    private Date applied_time;


    public Application() {
    }

    public Application(String title, String description, String resumePath, String email, String contactNo, Job_Oppertunity job_oppertunity, User user, Date applied_time) {
        this.title = title;
        this.description = description;
        this.resumePath = resumePath;
        this.email = email;
        this.contactNo = contactNo;
        this.job_oppertunity = job_oppertunity;
        this.user = user;
        this.applied_time = applied_time;
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

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public Job_Oppertunity getJob_oppertunity() {
        return job_oppertunity;
    }

    public void setJob_oppertunity(Job_Oppertunity job_oppertunity) {
        this.job_oppertunity = job_oppertunity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getApplied_time() {
        return applied_time;
    }

    public void setApplied_time(Date applied_time) {
        this.applied_time = applied_time;
    }
}
