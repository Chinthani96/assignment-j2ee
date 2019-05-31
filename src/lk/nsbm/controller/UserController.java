package lk.nsbm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.dto.UserPasswordDTO;
import lk.nsbm.dto.response.LoginUserDTO;
import lk.nsbm.dto.response.error.ImageUploadErrorDTO;
import lk.nsbm.entity.User;
import lk.nsbm.service.ServiceFactory;
import lk.nsbm.service.custom.FileUploadService;
import lk.nsbm.service.custom.UserService;
import lk.nsbm.service.custom.impl.CountServiceImpl;
import lk.nsbm.shared.enums.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserController",urlPatterns = {"/api/v1/users"})
@MultipartConfig
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER);
        if (request.getParameter("isLogin") == null) {
            Part profile_pic = request.getPart("profile_pic");
            Map<String, String[]> parameterMap = request.getParameterMap();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = null;
            try {
                dob = df.parse(parameterMap.get("dob")[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String profile_pic_path = null;
            if (profile_pic != null) {
                FileUploadService fileUploadService = (FileUploadService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.FILE_UPLOAD);
                profile_pic_path = fileUploadService.uploadImage(profile_pic, CountServiceImpl.getInstance().getCount(CountTypes.FILE_UPLOAD));

                if (profile_pic_path == null) {
                    String responseObj = new ObjectMapper().writeValueAsString(new ImageUploadErrorDTO("Image upload failed", "Failed"));
                    out.println(responseObj);
                    return;
                }
            }

            Industry_Type industry_type = Industry_Type.valueOf(parameterMap.get("industry_type")[0]);
            Country country = Country.valueOf(parameterMap.get("country")[0]);
            User_Type user_type = User_Type.valueOf(parameterMap.get("user_type")[0]);
            User university = new User();

            if (parameterMap.get("university_username") != null) {
                university.setUsername(parameterMap.get("university_username")[0]);
            }

            UserPasswordDTO newUserDTO = new UserPasswordDTO(parameterMap.get("username")[0],
                    parameterMap.get("password")[0], dob, parameterMap.get("description")[0], industry_type, country,
                    new Date(), user_type, profile_pic_path, university, new Date(), new Date(), 0 );

            UserDTO userDTO = userService.registerUser(newUserDTO);
            out.println(new ObjectMapper().writeValueAsString(userDTO));
//            out.println(new ObjectMapper().writeValueAsString(userDTO.getCreated_date()));
        } else {
            Map<String, String[]> parameterMap = request.getParameterMap();

            UserPasswordDTO userPasswordDTO = new UserPasswordDTO();
            userPasswordDTO.setUsername(parameterMap.get("username")[0]);
            userPasswordDTO.setPassword(parameterMap.get("password")[0]);
            LoginUserDTO loginUserDTO = userService.loginUser(userPasswordDTO);

            String s = new ObjectMapper().writeValueAsString(loginUserDTO);
            response.getWriter().println(s);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        UserService userService =(UserService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER);

        int action = Integer.parseInt(request.getParameter("action"));

        if (action == ActionType.ALL.getValue()) {
            List<UserDTO> allUsers = userService.getUsers();
            String allUsersJson = (new ObjectMapper()).writeValueAsString(allUsers);

            out.println(allUsersJson);
        } else if (action == ActionType.ONE.getValue()) {
            String username = request.getParameter("userId");


        } else if (action == ActionType.SUBSCRIBED.getValue()) {

        } else {

        }
    }
}
