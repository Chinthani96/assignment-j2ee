package lk.nsbm.dto;

import lk.nsbm.entity.Post;
import lk.nsbm.entity.User;

import java.util.Date;

public class CommentDTO {

    private int _id;
    private String comment;
    private Date com_date;
    private Post post;
    private User commentedUser;

    public CommentDTO() {
    }

    public CommentDTO(int _id, String comment, Date com_date, Post post, User commentedUser) {
        this._id = _id;
        this.comment = comment;
        this.com_date = com_date;
        this.post = post;
        this.commentedUser = commentedUser;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
}
