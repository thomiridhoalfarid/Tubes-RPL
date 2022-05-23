package Database;

import static Database.Conn.getConnection;
import Model.Membership;
import java.sql.*;
import java.util.*;

public class MembershipConn {
    
    public static Membership getMembershipData(ResultSet rs, boolean usedPaymet) throws SQLException{
        Membership membership = new Membership();
        
        membership.setValidation_status(rs.getString("validation_status"));
        membership.setStart_date(rs.getDate("start_date"));
        membership.setExpired_date(rs.getDate("expired_date"));
        
        if(usedPaymet)
            membership.payment_proof = rs.getBlob("payment_proof").getBinaryStream();
        
        membership.subs_id = rs.getInt("subs_id");
        
        return membership;
    }
    
    public static Membership getMembershipById(String id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from membership where membership_id =?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        
        return rs.next() ? getMembershipData(rs, false): null;
    }
    
    public static List<Membership> getAllMembership() throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from membership"
                + "where validation_status=Non-Valid");
        ResultSet rs = st.executeQuery();
        
        List<Membership> memberships = new ArrayList<>();
        while(rs.next()){
            memberships.add(getMembershipData(rs, false));
        }
        
        return memberships;
    }
    
    public static Membership getMembershipByDate(int id) throws SQLException {
        // TODo Get member dengan id tersebut, exp date <= current date dan validation status = Valid
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from membership where user_id =? and CURRENT_DATE()<=expired_date");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        
        return rs.next() ? getMembershipData(rs, false): null;
    }
    
    public static Membership getMembershipByUserId(String id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from membership where user_id =?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        
        return rs.next() ? getMembershipData(rs, false): null;
    }
    public static Membership getMembershipBySubsId(String id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from membership where subs_id =?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        
        return rs.next() ? getMembershipData(rs, false): null;
    }
    
    public static void postMembership(Membership m, int sid, int uid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("insert into membership "
                + "(validation_status, start_date, payment_proof, expired_date, subs_id, user_id)"
                +" values(?,CURRENT_DATE(),?,?,?,?)");
        
        st.setString(1, m.getValidation_status());
        st.setBlob(2, m.payment_proof);
        st.setDate(3, m.getExpired_date());
        st.setInt(4, sid);        
        st.setInt(5, uid);

        st.execute();
    }
}
