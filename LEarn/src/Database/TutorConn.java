/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.Conn.getConnection;
import Model.Tutor;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorConn {
    
    public static Tutor getTutorById(int id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from tutor where tutor_id =?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Tutor tutor = new Tutor();
        while  (rs.next()){
            tutor.eligibility_proof = rs.getBlob("eligibility_proof").getBinaryStream();
            tutor.rating = rs.getDouble("rating");
            tutor.validation_status = rs.getString("validation_status");
            tutor.tutor_id = rs.getInt("tutor_id");
            tutor.bank = rs.getString("bank");
            tutor.rekening = rs.getString("rekening");
            tutor.namaRekening = rs.getString("nama_rekening");
        }
        return tutor;
    }   
    public static String getTutorIdByUserId(int u) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from tutor where user_id=?");
        st.setInt(1, u);     
        ResultSet rs = st.executeQuery();
        return rs.getString("tutor_id");
    }
    
    public static Tutor getTutorByUserId(int u) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from tutor where user_id=?");
        st.setInt(1, u);     
        ResultSet rs = st.executeQuery();
       Tutor tutor = new Tutor();
        while  (rs.next()){
            tutor.eligibility_proof = rs.getBlob("eligibility_proof").getBinaryStream();
            tutor.rating = rs.getDouble("rating");
            tutor.validation_status = rs.getString("validation_status");
            tutor.tutor_id = rs.getInt("tutor_id");
            tutor.bank = rs.getString("bank");
            tutor.rekening = rs.getString("rekening");
            tutor.namaRekening = rs.getString("nama_rekening");
        }
        return tutor;
    }
    
    public static void postTutor(Tutor tutor, int uid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("insert into tutor "
                + "(eligibility_proof, rating, validation_status, user_id, bank, rekening, nama_rekening)"
                +" values(?,?,?,?,?,?,?)");
        st.setBlob(1, tutor.eligibility_proof);
        st.setDouble(2, tutor.rating);
        st.setString(3, tutor.validation_status);
        st.setInt(4, uid);
        st.setString(5, tutor.bank);
        st.setString(6, tutor.rekening);
        st.setString(7, tutor.namaRekening);

        st.execute();
    }
    
    public static void updateTutor(User u, String bank, String rekening, String nama_rekening) throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("update tutor set "+"bank=?"+"rekening=?"+"nama_rekening=?"+"where userid= ?");
        st.setString(1, bank);
        st.setString(2, rekening);
        st.setString(3, nama_rekening);
        st.setInt(4, u.getUserId());
    }
    
    public static String getTutorName(int answer_id) throws SQLException{
        String syntax = "SELECT name, answer_id FROM answer\n"
                + "join tutor on answer.tutor_id = tutor.tutor_id\n"
                +"join user on tutor.user_id = user.user_id"
                +"where answer_id=?";
        
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(syntax);
        st.setInt(1, answer_id);
        ResultSet rs = st.executeQuery();
        return rs.next() ? rs.getString("name") : "";
    }
}
