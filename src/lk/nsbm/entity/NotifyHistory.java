package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;

import java.util.Date;

public class NotifyHistory {

    @IdValue(name = "user")
    private User user;
    private Date timeOfLastChecked;

    public NotifyHistory() {
    }

    public NotifyHistory(User user, Date timeOfLastChecked) {
        this.user = user;
        this.timeOfLastChecked = timeOfLastChecked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimeOfLastChecked() {
        return timeOfLastChecked;
    }

    public void setTimeOfLastChecked(Date timeOfLastChecked) {
        this.timeOfLastChecked = timeOfLastChecked;
    }
}
