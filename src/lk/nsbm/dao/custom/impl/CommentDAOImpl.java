package lk.nsbm.dao.custom.impl;

import com.mongodb.client.FindIterable;
import lk.nsbm.dao.CrudDAOImpl;
import lk.nsbm.dao.custom.CommentDAO;
import lk.nsbm.entity.Comment;
import lk.nsbm.entity.Post;
import lk.nsbm.entity.User;
import lk.nsbm.shared.BSONConverter;
import lk.nsbm.shared.enums.NonIdType;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl extends CrudDAOImpl<Comment, Integer> implements CommentDAO {
    @Override
    public List<Comment> getCommentsPosts(Post post) {
        List<Comment> commentList = new ArrayList<>();

        Document postDocument = BSONConverter.getDocForSingleVal(post, Comment.class, NonIdType.TYPE_ONE);
        FindIterable<Document> documents = getCollection().find(postDocument);

        for (Document doc :
                documents) {
            Comment comment = (Comment) BSONConverter.getObject(doc, Comment.class);
            commentList.add(comment);
        }

        return commentList;
    }

    @Override
    public List<Comment> getUsersComments(User user) {
        List<Comment> commentList = new ArrayList<>();

        Document userCommentDoc = BSONConverter.getDocForSingleVal(user, Comment.class, NonIdType.TYPE_TWO);
        FindIterable<Document> documents = getCollection().find(userCommentDoc);

        for (Document doc :
                documents) {
            Comment comment = (Comment) BSONConverter.getObject(doc, Comment.class);
            commentList.add(comment);
        }

        return commentList;
    }
}
