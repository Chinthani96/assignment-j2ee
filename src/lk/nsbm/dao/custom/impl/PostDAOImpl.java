package lk.nsbm.dao.custom.impl;

import com.mongodb.client.FindIterable;
import lk.nsbm.dao.CrudDAOImpl;
import lk.nsbm.dao.custom.PostDAO;
import lk.nsbm.entity.Post;
import lk.nsbm.entity.User;
import lk.nsbm.shared.BSONConverter;
import lk.nsbm.shared.enums.NonIdType;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl extends CrudDAOImpl<Post, Integer> implements PostDAO {
    @Override
    public List<Post> getUsersPostsList(User user) {
        List<Post> posts = new ArrayList<>();
        user.setPassword(null);
        Document userJsonForPost = BSONConverter.getDocForSingleVal(user, Post.class, NonIdType.TYPE_ONE);
        FindIterable<Document> usersPostsDocs = getCollection().find(userJsonForPost);

        for (Document doc :
                usersPostsDocs) {
            Post post = (Post) BSONConverter.getObject(doc, Post.class);
            posts.add(post);
        }

        return posts;
    }

    @Override
    public List<Post> getUsersPostsList(User user, int pgNo) {
        List<Post> posts = new ArrayList<>();
        user.setPassword(null);
        Document userJsonForPost = BSONConverter.getDocForSingleVal(user, Post.class, NonIdType.TYPE_ONE);
        int maxPglimit = pgNo * 10;
        int minPgLimit = (pgNo - 1) * 10;

        FindIterable<Document> usersPostsDocs = getCollection().find(userJsonForPost)
                .limit(maxPglimit)
                .skip(minPgLimit);

        for (Document doc :
                usersPostsDocs) {

            Post post = (Post) BSONConverter.getObject(doc, Post.class);
            posts.add(post);

        }

        return posts;
    }

    @Override
    public List<Post> getPostsOfSubscriptions(List<String> subscribers) {
        List<Post> postsList = new ArrayList<>();
        String sortJson = "{\"dateCreated\" : -1}";
        Document parsedSortDoc = Document.parse(sortJson);
        Document docUsernameList =
                BSONConverter.getDocForSingleWithAll("cretaedUser.username", subscribers);

        FindIterable<Document> sortedResultArray = getCollection()
                .find(docUsernameList)
                .sort(parsedSortDoc);

        for (Document doc :
                sortedResultArray) {
            postsList.add((Post) BSONConverter.getObject(doc, Post.class));
        }

        return postsList;
    }

    @Override
    public List<Post> getPostsOfSubscriptions(List<String> subscribers, int pgNo) {
        int maxLimit = pgNo * 10;
        int minLimit = (pgNo - 1) * 10;
        List<Post> postsList = new ArrayList<>();
        String sortJson = "{\"dateCreated\" : -1}";
        Document parsedSortDoc = Document.parse(sortJson);
        Document docUsernameList =
                BSONConverter.getDocForSingleWithAll("cretaedUser.username", subscribers);

        FindIterable<Document> sortedResultArray = getCollection()
                .find(docUsernameList)
                .sort(parsedSortDoc)
                .limit(maxLimit)
                .skip(minLimit);

        for (Document doc :
                sortedResultArray) {
            postsList.add((Post) BSONConverter.getObject(doc, Post.class));
        }

        return postsList;
    }

    @Override
    public boolean isAvailable(int postId) {
        Post post = this.findById(postId);

        if (post == null) {
            return false;
        }

        return true;
    }
}
