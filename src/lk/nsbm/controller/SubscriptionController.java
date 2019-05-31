package lk.nsbm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.nsbm.dto.SubscriptionDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.dto.response.error.InvalidRequestResponse;
import lk.nsbm.service.ServiceFactory;
import lk.nsbm.service.custom.SubscriptionService;
import lk.nsbm.shared.enums.Update_Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SubscriptionController", urlPatterns = {"/api/v1/subscriptions"})
public class SubscriptionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        String username = request.getHeader("username");
        String subscriptionUsername = request.getParameter("subscription");

        if (username == null || subscriptionUsername == null || username.equals("") || subscriptionUsername.equals("")) {
            InvalidRequestResponse invalidReqResp = new InvalidRequestResponse("Invalid request you entered", "username and subscription");
            response.setStatus(400);
            out.println((new ObjectMapper()).writeValueAsString(invalidReqResp));
            return;
        }

        SubscriptionService subscriptionService = (SubscriptionService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBSCRIPTION);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        UserDTO subscriptionUserDTO = new UserDTO();
        subscriptionUserDTO.setUsername(subscriptionUsername);
        Update_Status update_status = subscriptionService.subscribeUser(userDTO, subscriptionUserDTO);

        out.println((new ObjectMapper()).writeValueAsString(update_status));

        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        String username = request.getHeader("username");
        SubscriptionService subscriptionService = (SubscriptionService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.SUBSCRIPTION);
        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            InvalidRequestResponse invalidRequestResponse = new InvalidRequestResponse("Invalid request entered", "action");
            response.setStatus(400);
            out.print((new ObjectMapper()).writeValueAsString(invalidRequestResponse));
            return;
        }

        switch (action) {
            case "all":
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(username);
                List<SubscriptionDTO> subscriptionListOfUser = subscriptionService.getSubscriptionListOfUser(userDTO);
                out.println((new ObjectMapper()).writeValueAsString(subscriptionListOfUser));
                return;
            default:
                return;
        }
    }
}
