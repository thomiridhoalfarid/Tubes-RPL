/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.Conn.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Berisi function get yang berkaitan dengan class User
 * @author Anas
 */
public class UserConn {

    /**
     * Untuk mencari user menggunakan user id, output berupa object user
     * @param uid
     * @return
     * @throws SQLException
     */
    public static User getUserById(int uid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from user where user_id=?");
        st.setInt(1, uid);
        ResultSet rs = st.executeQuery();
        User user = new User();
        while  (rs.next()){
            user.setPassword(rs.getString("password"));
            user.setBio(rs.getString("bio"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }
    
    /**
     * Untuk mencari user menggunakan username, output berupa object user
     * @param uname
     * @return
     * @throws SQLException
     */
    public static User getUserByUsername(String uname) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from user where username=?");
        st.setString(1, uname);
        ResultSet rs = st.executeQuery();
        User user = new User();
        while  (rs.next()){
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setBio(rs.getString("bio"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }
    
    /**
     * Untuk mencari user menggunakan username dan password, output berupa object user
     * @param uname
     * @param pass
     * @return
     * @throws SQLException
     */
    public static User getUserByUsernameAndPassword(String uname, String pass) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from user where username=? and password=?");
        st.setString(1, uname);
        st.setString(2, pass);        
        ResultSet rs = st.executeQuery();
        User user = new User();
        while  (rs.next()){
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setBio(rs.getString("bio"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }
    
    public static int getUserId(String username, String password) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from user where username=? and password=?");
        st.setString(1, username);     
        st.setString(2, password);     
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            return rs.getInt("user_id");
        }
        return -1;        
    }
    
    public static void postUser(User u) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("insert into user (password, bio, name, username, role)"+" values(?,?,?,?,?)");
        st.setString(1, u.getPassword());
        st.setString(2, u.getBio());
        st.setString(3, u.getName());
        st.setString(4, u.getUsername());
        st.setString(5, u.getRole());
        
        st.execute();
    }
    
    public static void updateUser(User u, String password, String bio, String name) throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("update user set password=? ,bio=? , name=? where user_id=?;");
        st.setString(1, password);
        st.setString(2, bio);
        st.setString(3, name);
        st.setInt(4, u.getUserId());
        st.executeUpdate();
    }
    

    public static List<User> getAllUsers() throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from user");
        ResultSet rs = st.executeQuery();
        
        List<User> users = new ArrayList<>();
        User user = new User();
        while  (rs.next()){
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setBio(rs.getString("bio"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
            users.add(user);
        }        
        return users;
    }
}
