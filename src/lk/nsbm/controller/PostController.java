package lk.nsbm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.nsbm.dto.CommentDTO;
import lk.nsbm.dto.PostDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.dto.response.error.ImageUploadErrorDTO;
import lk.nsbm.dto.response.error.InvalidRequestResponse;
import lk.nsbm.entity.Post;
import lk.nsbm.entity.User;
import lk.nsbm.service.ServiceFactory;
import lk.nsbm.service.custom.FileUploadService;
import lk.nsbm.service.custom.PostsService;
import lk.nsbm.service.custom.impl.CountServiceImpl;
import lk.nsbm.shared.enums.CountTypes;
import lk.nsbm.shared.enums.Status;
import lk.nsbm.shared.enums.Update_Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PostController", urlPatterns = {"/api/v1/posts"})
@MultipartConfig
public class PostController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        PostsService postsService = (PostsService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.POSTS);
        String action = request.getParameter("action");

        if (action == null) {
            response.setStatus(400);
            out.println(
                    (new ObjectMapper()).writeValueAsString(
                            new InvalidRequestResponse("action is required", "action")
                    )
            );
            return;
        }

        switch (action) {
            case "addpost":
                Map<String, String[]> requestParams = request.getParameterMap();
                User user = new User();
                user.setUsername(request.getHeader("username"));
                Part imagePart = request.getPart("image");

                String image_path = null;
                if (imagePart != null) {
                    FileUploadService fileUploadService = (FileUploadService) ServiceFactory.getInstance()
                            .getService(ServiceFactory.ServiceType.FILE_UPLOAD);

                    image_path = fileUploadService
                            .uploadImage(imagePart, CountServiceImpl.getInstance().getCount(CountTypes.FILE_UPLOAD));

                    if (image_path == null) {
                        String responseObj = (new ObjectMapper()).writeValueAsString(
                                new ImageUploadErrorDTO("Image upload failed", "Failed")
                        );

                        out.println(responseObj);
                        return;
                    }
                }

                PostDTO postDTO = new PostDTO(
                        -1, requestParams.get("title")[0], requestParams.get("description")[0], new Date(),
                        Status.ACTIVE, user, image_path, null
                );

                postDTO = postsService.createPost(postDTO);
                out.print((new ObjectMapper()).writeValueAsString(postDTO));
                return;

            case "addcomment":
                String username = request.getHeader("username");

                if (username == null || username.equals("")) {
                    response.setStatus(400);
                    out.println(
                            (new ObjectMapper()).writeValueAsString(
                                    new InvalidRequestResponse("required header username", "username")
                            )
                    );
                    return;
                }

                if (request.getParameter("postId") == null) {
                    response.setStatus(400);
                    out.println(
                            (new ObjectMapper()).writeValueAsString(
                                    new InvalidRequestResponse("required parameter postId", "postId")
                            )
                    );

                    return;
                }

                int postId = Integer.parseInt(request.getParameter("postId"));
                String comment = request.getParameter("comment");

                if (comment == null) {
                    response.setStatus(400);
                    out.println(
                            (new ObjectMapper()).writeValueAsString(
                                    new InvalidRequestResponse("required parameter comment", "comment")
                            )
                    );
                    return;
                }

                CommentDTO commentDTO = new CommentDTO();
                Post post = new Post();
                post.set_id(postId);
                User userForDto = new User();
                userForDto.setUsername(username);
                commentDTO.setPost(post);
                commentDTO.setCommentedUser(userForDto);
                commentDTO.setComment(comment);

                Update_Status update_status = postsService.addComment(commentDTO);
                out.println((new ObjectMapper()).writeValueAsString(update_status));

                return;

            default:
                response.setStatus(400);
                out.println((new ObjectMapper()).writeValueAsString(
                        new InvalidRequestResponse("Invalid action inserted", "action"))
                );
                return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        PostsService postsService = (PostsService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.POSTS);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(request.getHeader("username"));
        String action = request.getParameter("action");

        switch (action) {
            case "user":
                List<PostDTO> usersPostsList = null;
                if (request.getParameter("pgNo") == null) {
                    usersPostsList = postsService.getUsersPost(userDTO);
                } else {
                    int pgNo = Integer.parseInt(request.getParameter("pgNo"));
                    usersPostsList = postsService.getUsersPost(userDTO, pgNo);
                }
                out.println((new ObjectMapper()).writeValueAsString(usersPostsList));
                return;
            case "subscriptions":
                List<PostDTO> postsList = null;

                if (request.getParameter("pgNo") == null) {
                    postsList = postsService.getPostsRelatedToUser(userDTO);
                } else {
                    int pgNo = Integer.parseInt(request.getParameter("pgNo"));

                    if (pgNo <= 0) {
                        response.setStatus(400);
                        out.println((new ObjectMapper()).writeValueAsString(new InvalidRequestResponse(
                                "pgNo should be greater than 0", "pgNo"
                        )));
                        return;
                    }

                    postsList = postsService.getPostsRelatedToUser(userDTO, pgNo);
                }

                out.println((new ObjectMapper()).writeValueAsString(postsList));
                return;
            case "postcomments" :
                if (request.getParameter("postId") == null || !request.getParameter("postId").matches("^\\d*$")) {
                    response.setStatus(400);
                    out.println(
                            (new ObjectMapper()).writeValueAsString(
                                    new InvalidRequestResponse("required parameter postId", "postId")
                            )
                    );

                    return;
                }

                int postId = Integer.parseInt(request.getParameter("postId"));

                List<CommentDTO> commentsList = postsService.getPostsComments(postId);
                out.println(
                        (new ObjectMapper()).writeValueAsString(commentsList)
                );
                return;
            default:
                out.println((new ObjectMapper()).writeValueAsString(
                        new InvalidRequestResponse("Invalid action entered.", "action")
                ));
                return;
        }
    }
}
