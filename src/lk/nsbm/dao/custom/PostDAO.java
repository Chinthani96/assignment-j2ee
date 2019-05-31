package lk.nsbm.dao.custom;

import lk.nsbm.dao.CrudDAO;
import lk.nsbm.entity.Post;
import lk.nsbm.entity.User;

import java.util.List;

public interface PostDAO extends CrudDAO<Post, Integer> {

    List<Post> getUsersPostsList(User user);

    List<Post> getUsersPostsList(User user, int pgNo);

    List<Post> getPostsOfSubscriptions(List<String> subscribers);

    List<Post> getPostsOfSubscriptions(List<String> subscribers, int pgNo);

    boolean isAvailable(int postId);

}
