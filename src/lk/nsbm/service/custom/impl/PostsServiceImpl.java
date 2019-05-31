package lk.nsbm.service.custom.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.CommentDAO;
import lk.nsbm.dao.custom.PostDAO;
import lk.nsbm.dao.custom.SubscriptionDAO;
import lk.nsbm.dao.custom.UserDAO;
import lk.nsbm.dto.CommentDTO;
import lk.nsbm.dto.PostDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.entity.Comment;
import lk.nsbm.entity.Post;
import lk.nsbm.entity.Subscription;
import lk.nsbm.entity.User;
import lk.nsbm.service.custom.PostsService;
import lk.nsbm.shared.enums.CountTypes;
import lk.nsbm.shared.enums.Status;
import lk.nsbm.shared.enums.Update_Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostsServiceImpl implements PostsService {

    private PostDAO postDAO;
    private UserDAO userDAO;
    private SubscriptionDAO subscriptionDAO;
    private CommentDAO commentDAO;

    public PostsServiceImpl() {
        postDAO = (PostDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.POST);
        userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
        subscriptionDAO = (SubscriptionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUBSCRIPTION);
        commentDAO = (CommentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COMMENT);
    }

    @Override
    public PostDTO createPost(PostDTO post) {

        int count = CountServiceImpl.getInstance().getCount(CountTypes.POSTS);
        String username = post.getCretaedUser().getUsername();

        if (username == null || username.equals("")) {
            post.setUpdate_status(Update_Status.INVALID_CREDENTIALS);
            return post;
        }

        post.setCretaedUser(userDAO.findById(username));

        if (post.getCretaedUser() == null) {
            post.setUpdate_status(Update_Status.USER_NOT_FOUND);
            return post;
        }
        post.setCretaedUser(new User());
        post.getCretaedUser().setUsername(username);
        Post savedPost = postDAO.save(new Post(count, post.getTitle(), post.getDescription(), new Date(), Status.ACTIVE,
                post.getCretaedUser(), post.getImagePath()));

        if (savedPost == null) {
            post.setUpdate_status(Update_Status.FAILED);

            return post;
        }

        post.set_id(savedPost.get_id());
        post.setUpdate_status(Update_Status.SUCCESS);

        return post;
    }

    @Override
    public PostDTO updatePost(PostDTO post, UserDTO createdUser) {
        Post postDataEntity = postDAO.findById(post.get_id());

        if (postDataEntity == null) {
            post.setUpdate_status(Update_Status.NOT_FOUND);
            return post;
        }

        if (!(createdUser.getUsername().equals(post.getCretaedUser().getUsername()))) {
            post.setUpdate_status(Update_Status.INVALID_CREDENTIALS);
        }

        Post updatedPost = postDAO.update(new Post(post.get_id(), post.getTitle(), post.getDescription(), post.getDateCreated(), post.getStatus(), post.getCretaedUser(), post.getImagePath()), post.get_id());

        if (updatedPost == null) {
            post.setUpdate_status(Update_Status.FAILED);
            return post;
        }

        post.setUpdate_status(Update_Status.SUCCESS);

        return post;

    }

    @Override
    public List<PostDTO> getPostsRelatedToUser(UserDTO userDTO) {
        List<PostDTO> postDTOList = new ArrayList<>();
        List<String> subscriptionUsernameList = new ArrayList<>();

        if (!userDAO.isAvailable(userDTO.getUsername())) {
            return null;
        }
        User userEntity = new User();
        userEntity.setUsername(userDTO.getUsername());
        List<Subscription> usersSubscribedList = subscriptionDAO.getUsersSubscribedList(userEntity);

        for (Subscription subscription :
                usersSubscribedList) {
            if (!userDAO.isAvailable(subscription.getSubscription_pk().getSubscription_user().getUsername())) {
                System.out.println("User is unavailable :" + subscription.getSubscription_pk().getSubscription_user().getUsername());
                continue;
            }

            subscriptionUsernameList.add(subscription.getSubscription_pk().getSubscription_user().getUsername());
        }

        List<Post> postsOfSubscriptions = postDAO.getPostsOfSubscriptions(subscriptionUsernameList);

        for (Post postEnt :
                postsOfSubscriptions) {
            postDTOList.add(new PostDTO(postEnt.get_id(), postEnt.getTitle(), postEnt.getDescription(),
                    postEnt.getDateCreated(), postEnt.getStatus(), postEnt.getCretaedUser(), postEnt.getImagePath(),
                    null));
        }

        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostsRelatedToUser(UserDTO userDTO, int pgNo) {
        List<PostDTO> postDTOList = new ArrayList<>();
        List<String> subscriptionUsernameList = new ArrayList<>();

        if (!userDAO.isAvailable(userDTO.getUsername())) {
            return null;
        }
        User userEntity = new User();
        userEntity.setUsername(userDTO.getUsername());
        List<Subscription> usersSubscribedList = subscriptionDAO.getUsersSubscribedList(userEntity);

        for (Subscription subscription :
                usersSubscribedList) {
            if (!userDAO.isAvailable(subscription.getSubscription_pk().getSubscription_user().getUsername())) {
                System.out.println("User is unavailable :" + subscription.getSubscription_pk().getSubscription_user().getUsername());
                continue;
            }

            subscriptionUsernameList.add(subscription.getSubscription_pk().getSubscription_user().getUsername());
        }

        List<Post> postsOfSubscriptions = postDAO.getPostsOfSubscriptions(subscriptionUsernameList, pgNo);

        for (Post postEnt :
                postsOfSubscriptions) {
            postDTOList.add(new PostDTO(postEnt.get_id(), postEnt.getTitle(), postEnt.getDescription(),
                    postEnt.getDateCreated(), postEnt.getStatus(), postEnt.getCretaedUser(), postEnt.getImagePath(),
                    null));
        }

        return postDTOList;
    }

    @Override
    public List<PostDTO> getUsersPost(UserDTO userDTO) {
        List<PostDTO> postDTOS = new ArrayList<>();
        User userEntity = new User();

        if (!userDAO.isAvailable(userDTO.getUsername())) {
            return null;
        }

        userEntity.setUsername(userDTO.getUsername());

        try {
            System.out.println((new ObjectMapper()).writeValueAsString(userEntity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<Post> usersPostsList = postDAO.getUsersPostsList(userEntity);

        for (Post post :
                usersPostsList) {
            System.out.println(post);
            postDTOS.add(new PostDTO(post.get_id(), post.getTitle(), post.getDescription(), post.getDateCreated(), post.getStatus(), post.getCretaedUser(), post.getImagePath(), Update_Status.SUCCESS));
        }

        return postDTOS;
    }

    @Override
    public List<PostDTO> getUsersPost(UserDTO userDTO, int pgNo) {
        List<PostDTO> postDTOS = new ArrayList<>();
        User userEntity = new User();

        if (!userDAO.isAvailable(userDTO.getUsername())) {
            return null;
        }

        userEntity.setUsername(userDTO.getUsername());

        try {
            System.out.println((new ObjectMapper()).writeValueAsString(userEntity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<Post> usersPostsList = postDAO.getUsersPostsList(userEntity, pgNo);

        for (Post post :
                usersPostsList) {
            postDTOS.add(new PostDTO(post.get_id(), post.getTitle(), post.getDescription(),
                    post.getDateCreated(), post.getStatus(), post.getCretaedUser(), post.getImagePath(),
                    Update_Status.SUCCESS));
        }

        return postDTOS;
    }

    @Override
    public Update_Status deletePost(PostDTO postDTO, UserDTO userDTO) {
        Post postEntity = postDAO.findById(postDTO.get_id());
        User userEntity = userDAO.findById(userDTO.getUsername());

        if (!postEntity.getCretaedUser().getUsername().equals(userEntity.getUsername())) {
            return Update_Status.INVALID_CREDENTIALS;
        }

        boolean isDelete = postDAO.delete(postEntity);

        if (!isDelete) {
            return Update_Status.FAILED;
        }

        return Update_Status.SUCCESS;

    }

    @Override
    public Update_Status addComment(CommentDTO commentDTO) {
        Comment commentEntity = new Comment();

        if (!userDAO.isAvailable(commentDTO.getCommentedUser().getUsername())) {
            return Update_Status.USER_NOT_FOUND;
        }

        if (!postDAO.isAvailable(commentDTO.getPost().get_id())) {
            return Update_Status.INVALID_CREDENTIALS;
        }

        commentEntity.setCom_date(new Date());
        commentEntity.set_id(CountServiceImpl.getInstance().getCount(CountTypes.COMMENT));
        commentEntity.setComment(commentDTO.getComment());
        commentEntity.setCommentedUser(commentDTO.getCommentedUser());
        commentEntity.setPost(commentDTO.getPost());

        Comment savedEntity = commentDAO.save(commentEntity);

        if (savedEntity == null) {
            return Update_Status.FAILED;
        }
        return Update_Status.SUCCESS;
    }

    @Override
    public Update_Status deleteComment(CommentDTO commentDTO) {
        Comment commentEntity = commentDAO.findById(commentDTO.get_id());

        if (commentEntity == null) {
            return Update_Status.NOT_FOUND;
        }

        if (!commentEntity.getCommentedUser().getUsername().equals(commentDTO.getCommentedUser().getUsername())) {
            return Update_Status.INVALID_CREDENTIALS;
        }

        boolean isDeleted = commentDAO.delete(commentEntity);

        if (isDeleted) {
            return Update_Status.SUCCESS;
        }

        return Update_Status.FAILED;

    }

    @Override
    public List<CommentDTO> getPostsComments(int postId) {
        List<CommentDTO> commentDTOList = new ArrayList<>();

        if (!postDAO.isAvailable(postId)) {
            return null;
        }

        Post postEntity = new Post();
        postEntity.set_id(postId);
        List<Comment> commentList = commentDAO.getCommentsPosts(postEntity);

        for (Comment comment :
                commentList) {
            commentDTOList.add(
                    new CommentDTO(comment.get_id(), comment.getComment(),
                            comment.getCom_date(), comment.getPost(), comment.getCommentedUser())
            );
        }

        return commentDTOList;
    }

    @Override
    public List<CommentDTO> getAllUsersComments(String username) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if (!userDAO.isAvailable(username)) {
            return null;
        }

        User userEntity = new User();
        userEntity.setUsername(username);

        List<Comment> userCommentList = commentDAO.getUsersComments(userEntity);

        for (Comment comment :
                userCommentList) {
            commentDTOList.add(
                    new CommentDTO(comment.get_id(), comment.getComment(),
                            comment.getCom_date(), comment.getPost(), comment.getCommentedUser())
            );
        }

        return commentDTOList;
    }
}
