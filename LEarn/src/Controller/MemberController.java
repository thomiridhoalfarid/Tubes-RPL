/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.MappingController.StateTransition;
import Database.*;
import Model.*;
import Search.LuceneManager;
import View.*;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;

/**
 * Class controller yang mengatur member page
 * @author Anas
 */
public class MemberController extends Controller{

    public MemberController(MappingController mappingController) {
        super(mappingController);
        
        User user = mappingController.GetCurrentUser();
        int idUser = user.getUserId();
        if(AuthenticationMember(idUser)){
            // Member Page
            super.view = new LandpageMember(this, user.getName());
        }else{
            // Subscription Plan page
            super.view = new Pembayaran(this);
        }
    }

    private boolean AuthenticationMember(int id){
        // Get Membership from database
        Membership member = null;
        try {
            member = MembershipConn.getMembershipByDate(id);
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member != null;
    }
    
    public void PostMemberSubscription(String subs, InputStream paymentFile){
        List<String> subsType = Arrays.asList("Santai", "Ngambis", "Langganan");
        
        int subsid = subsType.indexOf(subs) + 1;
        try {
            Membership member = new Membership();
            member.payment_proof = paymentFile;
            int uid = mappingController.GetCurrentUser().getUserId();
            Subscription sub = SubscriptionConn.getSubscriptionById(subsid);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, (int)sub.getValidity_periode());
            java.util.Date result = cal.getTime();
            java.sql.Date exp = new java.sql.Date(result.getTime());
            member.setExpired_date(exp);
            
            MembershipConn.postMembership(member, subsid, uid);
            mappingController.Move(StateTransition.LandpageMember);
        } catch (Exception ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SearchByCategory(String category){
        // get all Question from database by category
        List<Question> questions = new ArrayList<>();
        
        try {
            questions = QuestionConn.getAllQuestionsByCategory(category);
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mappingController.Move(StateTransition.QuestionMember, questions, category);
    }
    
    public void SearchByWord(String word){
        // get all Question from database
        List<Question> questions = new ArrayList<>();
        
        try {
            questions = QuestionConn.getAllQuestions();
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LuceneManager manager = LuceneManager.getInstance();
        for(int i = 0; i < questions.size(); i++)
            manager.AddItem(questions.get(i).getContent(), i);
        
        List<Question> results = new ArrayList<>();
        if(manager.SearchResult(word)){
            List<Integer> resultId = manager.ShowResult();
            for(Integer id : resultId)
                results.add(questions.get(id));
        }
                    
        mappingController.MoveByKeyword(StateTransition.QuestionMember, results, word);
    }
    
    public void ShowQuestionHistory(){
        // get all user question from database
        int uid = mappingController.GetCurrentUser().getUserId();
        List<Question> questions = null;
        try {
            questions = QuestionConn.getAllQuestions(uid);
        } catch (SQLException ex) {
            //Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mappingController.Move(StateTransition.QuestionHistory, questions, "");
    }
    
    public void Home(){
        mappingController.Move(StateTransition.LandpageMember);
    }
    
    public void PostQuestion(){
        mappingController.Move(StateTransition.PostQuestionMember);
    }
    
    public void ProfileMember(){
        mappingController.Move(StateTransition.ProfileMember);
    }
    
    public void Logout(){
        mappingController.Move(StateTransition.Quit);
    }
}
