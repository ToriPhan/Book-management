/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.BookBean;
import bean.CategoryBean;
import java.io.IOException;
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
@WebServlet(name = "EditSevlet", urlPatterns = {"/EditSevlet"})
public class EditSevlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        boolean isAdd = request.getParameter("isAdd") != null ? true:false;
        BookRepository bookRepository = new BookRepository();
        BookBean book = null;
        
        if (isAdd){
//            System.out.println("isAdd");
            book = new BookBean();
            CategoryBean c = new CategoryBean();
            book.setCategory(c);
        }else {
            int id = Integer.parseInt(request.getParameter("id"));
//            System.out.println("isEdit");
            book = bookRepository.findBookById(id);
        }
        List<CategoryBean> categoryList = bookRepository.fetchCategory();
        request.setAttribute("book", book);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("add.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        BookBean book = new BookBean();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        
        BookRepository bookRepository = new BookRepository();
        
        boolean isAdd = id ==0 ? true: false;
        if (isAdd){
             bookRepository.addBookById(book,categoryID);
             
        }else {
            bookRepository.editBookbyId(book,categoryID);
        }
        
        response.sendRedirect("/BookServlet");
    }
    
    

}
