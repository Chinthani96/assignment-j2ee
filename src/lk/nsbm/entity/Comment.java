package lk.nsbm.entity;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.NonIdType;

import java.util.Date;

public class Comment {

    @IdValue(name = "_id")
    private int _id;
    private String comment;
    private Date com_date;
    @NonIdValue(type = NonIdType.TYPE_ONE, name = "post")
    private Post post;
    @NonIdValue(type = NonIdType.TYPE_TWO , name = "commentedUser")
    private User commentedUser;

    public Comment() {
    }

    public Comment(String comment, Date com_date, Post post, User commentedUser) {
        this.comment = comment;
        this.com_date = com_date;
        this.post = post;
        this.commentedUser = commentedUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCom_date() {
        return com_date;
    }

    public void setCom_date(Date com_date) {
        this.com_date = com_date;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getCommentedUser() {
        return commentedUser;
    }

    public void setCommentedUser(User commentedUser) {
        this.commentedUser = commentedUser;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
