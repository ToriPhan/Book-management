/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.UserRepository;
import util.NewClass;

/**
 *
 * @author Hikari
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String encryptedPassword = NewClass.createHash(password, "2b139f070f897cc5", 1000);
        
        UserRepository userRepository = new UserRepository();
        int result = userRepository.checkLogin(username, encryptedPassword);
        String errorMessage = null;
        if (result == 1) {
            errorMessage = "Incorrect username or password";
        } else if(result == 2) {
            errorMessage = "The account has not been activated yet";
        } 
        request.setAttribute("error", errorMessage);
        
        if (result == 3) {
            request.getRequestDispatcher("index.html").include(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }

    
}
