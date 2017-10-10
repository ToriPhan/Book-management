/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import bean.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionPool;

/**
 *
 * @author Hikari
 */
public class UserRepository {

    public boolean checkExistingEmail(String email) {
        try {

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();

            String sql = "SELECT count(*) from user WHERE email = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                return false;
            }

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public void saveUser(UserBean user) throws SQLException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            String sql = "insert into user(userName, fullName, email, password, activationCode)\n"
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getActivationCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int checkLogin(String email, String password) {
        try {

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            String sql = "SELECT * from user WHERE (email= ? or userName= ?) and password= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, email);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            boolean hasData = rs.next();
            if (!hasData) {
                return 1; // incorrect username, email, password
            } else {
                String activationCode = rs.getString("activationCode");
                if (activationCode != null) {
                    return 2; // not activated yet
                } else {
                    return 3; //login successfully
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }

    public String verifyEmailHash(String activationCode) {

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            String sql = "SELECT email from user WHERE activationCode = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, activationCode);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            
           
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }

    public void updateActivationCode(String activationCode) {

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            UserRepository userRepository = new UserRepository();
            String isEmailHashExisting = userRepository.verifyEmailHash(activationCode);
//            System.out.println("isEmail" + isEmailHashExisting);
            if (isEmailHashExisting != null) {
//                System.out.println("clean email");
                String sql = "UPDATE user set activationCode = NULL WHERE email = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, isEmailHashExisting);
                ps.executeUpdate();
               
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
