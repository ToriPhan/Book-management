package repository;

import bean.BookBean;
import bean.CategoryBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionPool;

public class BookRepository {
    
    public List<BookBean> fetchData() {
        List<BookBean> data = new ArrayList<>();
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            String sql = "select b.id, b.name, b.author, c.name as category from book b, category c where b.categoryId = c.id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); // return map n never null
            while (rs.next()) {
                BookBean book = new BookBean();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                CategoryBean category = new CategoryBean();
                category.setName(rs.getString("category"));
                book.setCategory(category);
                data.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
            
        }
        return data;
    }
    
    public void deleteBookById(String id) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            String sql = "delete from book where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate(); // return int - 0 or 1 in JDBC
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void editBookbyId(BookBean book, int categoryID) {
        //Create Connection
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        //Create Statement
        String sql = "update book set b.name = ?, b.author = ?, b.categoryID = ? from book b where b.id= ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName()); //set gia tri vao trong cau sql
            ps.setString(2, book.getAuthor());
            ps.setInt(3, categoryID);
            ps.setInt(4, book.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            
        }
    }
    
    public BookBean findBookById(int id) {
        //Create Connection
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        //Create Statement
        String sql = "select b.id, b.name, b.author, b.categoryID, c.name as categoryName from book as b, category as c where b.id= ? and b.categoryID = c.id";
        try {
            // find book by id in tables book and category
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // transfer the found book into a new object book
            if (rs.next()) {
                BookBean b = new BookBean();
                CategoryBean c = new CategoryBean();
                b.setId(id);
                b.setName(rs.getString("name"));
                b.setAuthor(rs.getString("author"));
                c.setId(rs.getInt("categoryID"));
                c.setName(rs.getString("categoryName"));
                b.setCategory(c);
                return b;
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            
        }
        return null;
    }
    
    public List<CategoryBean> fetchCategory() {

        //Create Connection
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        //Create Statement
        String sql = "select * from category as c ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CategoryBean c = null;
            List<CategoryBean> list = new ArrayList<CategoryBean>();
            
            while (rs.next()) {
                c = new CategoryBean();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                
                list.add(c);
            }
            return list;
            
        } catch (SQLException e) {
            System.out.println(e);
            
        }
        return null;
    }
    
    public void addBookById(BookBean book, int categoryID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sql = "INSERT INTO book (name, author, categoryID) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, categoryID);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    
    public List<BookBean> searchBook(String search) {
        List<BookBean> data = new ArrayList<>();
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            String sql = "SELECT b.name, b.author, c.name as categoryName "
                    + "from book b, category c "
                    + "WHERE b.categoryID = c.id and "
                    + "(b.name LIKE '%"+search+ "%' or b.author LIKE '%"+search+ "%' or c.name LIKE '%" +search+ "%')";
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {   
                BookBean book = new BookBean();
                CategoryBean category = new CategoryBean();
                //book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                category.setName(rs.getString("categoryName"));
                book.setCategory(category);
                data.add(book);
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
}
