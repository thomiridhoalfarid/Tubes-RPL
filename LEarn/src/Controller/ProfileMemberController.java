/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.MembershipConn;
import Database.QuestionConn;
import Database.SubscriptionConn;
import Model.User;
import Database.UserConn;
import Model.Membership;
import Model.Question;
import Model.Subscription;
import View.EditProfileMember;
import View.ShowProfileMember;
import View.TBA;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class ProfileMemberController extends Controller{
    
    public User user;
    public Membership membership = null;
    public Subscription subscription = null;
    public ProfileMemberController(MappingController mappingController) {
        super(mappingController);
        user = mappingController.GetCurrentUser();
        try {
            membership = MembershipConn.getMembershipByDate(user.getUserId());
            subscription = SubscriptionConn.getSubscriptionById(membership.subs_id);
        } catch (SQLException ex) {
            //Logger.getLogger(ProfileMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.view = new ShowProfileMember(this);
    }
    
    public void EditProfile(String password, String bio, String name){
        User u = super.mappingController.GetCurrentUser();
        try {
            UserConn.updateUser(u,password,bio,name);
            user = UserConn.getUserById(u.getUserId());
            super.mappingController.SetCurrentUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ChangeView(new ShowProfileMember(this));
    }
    
    public void ToEditProfile(){
        ChangeView(new EditProfileMember(this));
    }
    
    public void BackToProfile(){
        ChangeView(new ShowProfileMember(this));   
    }
    
    public void Home(){
        mappingController.Move(MappingController.StateTransition.LandpageMember);
    }
    
    public void ProfileMember(){
        mappingController.Move(MappingController.StateTransition.ProfileMember);
    }
    
    public void Logout(){
        mappingController.Move(MappingController.StateTransition.Quit);
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
        
        mappingController.Move(MappingController.StateTransition.QuestionHistory, questions, "");
    }
}
