/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.BookBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.BookRepository;

/**
 *
 * @author Hikari
 */
@WebServlet(name = "SeachServlet", urlPatterns = {"/Search"})
public class SeachServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("search");
        BookRepository bookRepository = new BookRepository();
        List<BookBean> data = bookRepository.searchBook(search);
        request.setAttribute("data", data);
        request.getRequestDispatcher("/book.jsp").include(request, response);
        
    }
    
}
