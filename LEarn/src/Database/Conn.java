/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;


/**
 * 
 * @author ASUS
 */
public class Conn {
    public final static String path = "jdbc:mysql://localhost:3306/l-earn";
    public final static String username = "root";
    public final static String password = "";

    /**
     * Untuk melakukan koneksi ke database
     * @return
     */
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(path, username,password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
}  