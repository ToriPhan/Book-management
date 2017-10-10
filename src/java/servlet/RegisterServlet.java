package servlet;

import bean.UserBean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.UserRepository;
import util.MailUtilGmail;
import util.NewClass;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");

        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserBean user = new UserBean();
        user.setEmail(email);
        user.setUserName(userName);
        user.setFullName(fullName);
        
//        System.out.println("email:  "+ email);
        UserRepository userRepository = new UserRepository();
        boolean isEmailExisting = userRepository.checkExistingEmail(email);

        // email chua ton tai trong DB
        if (!isEmailExisting) {
            String activationCode = NewClass.createHash(email, "2b139f070f897cc5", 1000);
            String mailSubject = "Thanks for registration";
            String mailBody = "<h2> Hi " + email + ",</h2> <br><br>";
            mailBody += "Thanks for registration on our site. Please click on the following \n"
                    + "link to finish the registration: <br><br>";
            mailBody += "http://localhost:8080/activation?code=" + activationCode;
            mailBody += "<br><br> Regards, <br>JV19 - M06";

            MailUtilGmail.sendEmail(email, mailSubject, mailBody);

            String encryptedPassword = NewClass.createHash(password, "2b139f070f897cc5", 1000);
            user.setPassword(encryptedPassword);
            user.setActivationCode(activationCode);
            try {
                userRepository.saveUser(user);
            } catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
