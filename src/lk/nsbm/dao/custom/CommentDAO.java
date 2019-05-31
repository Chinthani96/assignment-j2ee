package lk.nsbm.dao.custom;

import lk.nsbm.dao.CrudDAO;
import lk.nsbm.entity.Comment;
import lk.nsbm.entity.Post;
import lk.nsbm.entity.User;

import java.util.List;

public interface CommentDAO extends CrudDAO<Comment, Integer> {

    List<Comment> getCommentsPosts(Post post);

    List<Comment> getUsersComments(User user);
}
