/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.Conn.getConnection;
import Model.Subscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class SubscriptionConn {
    public static Subscription getSubscriptionById(int id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from subscription where subs_id =?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Subscription subscription = new Subscription();
        while  (rs.next()){
            subscription.setName(rs.getString("name"));
            subscription.setPrice(rs.getDouble("price"));
            subscription.setValidity_periode(rs.getDouble("validity_periode"));            
        }
        return subscription;
    }   
    
    public static void postSubscription(Subscription s) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("insert into subscription (name, price, validity_periode)"+" values(?,?,?)");
        st.setString(1, s.getName());
        st.setDouble(2, s.getPrice());
        st.setDouble(3, s.getValidity_periode());

        st.execute();
    }
}
