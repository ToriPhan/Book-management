/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.UserRepository;

/**
 *
 * @author Hikari
 */
@WebServlet(name = "ActivateServlet", urlPatterns = {"/activation"})
public class ActivateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String activationCode = request.getParameter("code");
        String message = null;
        UserRepository userRepository = new UserRepository();
        String isEmailHashExisting = userRepository.verifyEmailHash(activationCode);

        // verify with database
        if (isEmailHashExisting != null) {

            userRepository.updateActivationCode(activationCode);
            message = "Email has been verified successfully. Account has been activated. Click <a href=\"login.jsp\">here</a> to login";
        } else {
            message = "Wrong Email Validation Input!";
        }

        if (message != null) {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

}
