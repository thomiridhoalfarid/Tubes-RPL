/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.Conn.getConnection;
import Model.Tutor_Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Tutor_PaymentConn {
    public static Tutor_Payment getUserById(String uid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from user where user_id=?");
        st.setString(1, uid);
        ResultSet rs = st.executeQuery();
        Tutor_Payment tutor_Payment = new Tutor_Payment();
        while  (rs.next()){
            tutor_Payment.setInput_time(rs.getDate("input_time"));
            tutor_Payment.setPayment_time(rs.getDate("payment_time"));
            tutor_Payment.setPayment_status(rs.getString("payment_status"));
            tutor_Payment.setAmount(rs.getDouble("amount"));
        }
        return tutor_Payment;
    }
    
    public static void postTutor_Payment(Tutor_Payment t, int tid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("insert into tutor_payment (input_time, payment_time, payment_status, amount, tutor_id)"+" values(?,?,?,?,?)");
        st.setDate(1, t.getInput_time());
        st.setDate(2, t.getPayment_time());
        st.setString(3, t.getPayment_status());        
        st.setDouble(4, t.getAmount());
        st.setInt(5, tid);

        st.execute();
    }
    
    public static List<Tutor_Payment> getAllPayment() throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from tutor_payment");
        ResultSet rs = st.executeQuery();
        
        List<Tutor_Payment> payments = new ArrayList<>();
        Tutor_Payment payment = new Tutor_Payment();
        while  (rs.next()){
            payment.setInput_time(rs.getDate("input_time"));
            payment.setPayment_time(rs.getDate("input_time"));
            payment.setPayment_status(rs.getString("payment_status"));
            payment.setAmount(rs.getDouble("amount"));
            payments.add(payment);
        }
        return payments;
    }
}
