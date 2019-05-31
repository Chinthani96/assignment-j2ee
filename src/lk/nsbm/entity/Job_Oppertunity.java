package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.enums.Industry_Type;

import java.math.BigDecimal;
import java.util.Date;

public class Job_Oppertunity {

    @IdValue(name = "_id")
    private int _id;
    private String title;
    private String description;
    private String qualifications;
    private Industry_Type industry_type;
    private String experience;
    private BigDecimal minsalary;
    private BigDecimal maxsalary;
    private User createdUser;
    private Date createdDate;

    public Job_Oppertunity() {
    }

    public Job_Oppertunity(int _id, String title, String description, String qualifications, Industry_Type industry_type,
                           String experience, BigDecimal minsalary, BigDecimal maxsalary, User createdUser, Date createdDate) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.qualifications = qualifications;
        this.industry_type = industry_type;
        this.experience = experience;
        this.minsalary = minsalary;
        this.maxsalary = maxsalary;
        this.createdUser = createdUser;
        this.createdDate = createdDate;
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

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public Industry_Type getIndustry_type() {
        return industry_type;
    }

    public void setIndustry_type(Industry_Type industry_type) {
        this.industry_type = industry_type;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public BigDecimal getMinsalary() {
        return minsalary;
    }

    public void setMinsalary(BigDecimal minsalary) {
        this.minsalary = minsalary;
    }

    public BigDecimal getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(BigDecimal maxsalary) {
        this.maxsalary = maxsalary;
    }
}
