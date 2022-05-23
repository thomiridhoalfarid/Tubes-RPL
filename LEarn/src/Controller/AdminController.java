/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.*;
import static Database.Conn.getConnection;
import Model.Answer;
import Model.Membership;
import Model.Question;
import Model.Tutor;
import Model.Tutor_Payment;
import Model.User;
import View.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class AdminController extends Controller{
    
    public AdminController(MappingController mappingController) {
        super(mappingController);
        super.view = new Admin(this);
    }
    
    public void UserVerification(){
        // get all user
        List<User> users = new ArrayList<>();
        List<Membership> memberships = new ArrayList<>();
        
        try {
            users = UserConn.getAllUsers();
            memberships = MembershipConn.getAllMembership();
        } catch (SQLException ex) {
        }
        
        mappingController.Move(MappingController.StateTransition.LandpageAdmin);
        // show view User Verification
        
    }
    
    public void PostUserVerification(int id, String val) throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("update membership set validation_status=? where membership_id=?;");
        st.setString(1, val);
        st.setInt(2, id);
        st.execute();
    }
    
    
    public void PaymentVerification(){
        // get all payment
        List<Tutor_Payment> payment = new ArrayList<>();
        
        try {
            payment = Tutor_PaymentConn.getAllPayment();
        } catch (SQLException ex) {
        }
        
        mappingController.Move(MappingController.StateTransition.LandpageAdmin);
        // show view Payment Verification
        
    }
    
    public void PostPaymentVerification(int pid, String s) throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("update tutor_payment set payment_status=? where payment_id=?;");
        st.setString(1, s);
        st.setInt(2, pid);
        st.execute();
    }
    
    public void AnswerVerification() throws SQLException{
        // get all payment
        List<Question> questions = new ArrayList<>();
        List<Answer> answers= new ArrayList<>();
        try {
            questions = QuestionConn.getAllQuestions();
            answers = AnswerConn.getAllAnswers();
            
        } catch (SQLException ex) {
        }
        
        mappingController.Move(MappingController.StateTransition.LandpageAdmin);
        // show view Answer Verification
        
    }
    
    public void PostAnswerVerification(int id) throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("update answer set verified=yes where answer_id=?;");
        st.setInt(1, id);
        st.execute();
    }
    
    public void RemoveAnswer(int id) throws SQLException{
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("delete from answer where answer_id=?;");
        st.setInt(1, id);
        st.execute();
    }
    
    public void Logout(){
        mappingController.Move(MappingController.StateTransition.Quit);
    }
}
