package lk.nsbm.service.custom;

import lk.nsbm.dto.CommentDTO;
import lk.nsbm.dto.PostDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.service.SuperService;
import lk.nsbm.shared.enums.Update_Status;

import java.util.List;

public interface PostsService extends SuperService {

    PostDTO createPost(PostDTO post);

    PostDTO updatePost(PostDTO post, UserDTO createdUser);

    List<PostDTO> getPostsRelatedToUser(UserDTO userDTO);

    List<PostDTO> getPostsRelatedToUser(UserDTO userDTO, int pgNo);

    List<PostDTO> getUsersPost(UserDTO userDTO);

    List<PostDTO> getUsersPost(UserDTO userDTO, int pgNo);

    Update_Status deletePost(PostDTO postDTO, UserDTO userDTO);

    Update_Status addComment(CommentDTO commentDTO);

    Update_Status deleteComment(CommentDTO commentDTO);

    List<CommentDTO> getPostsComments(int postId);

    List<CommentDTO> getAllUsersComments(String username);

}
