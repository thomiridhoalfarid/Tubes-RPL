/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.MappingController.StateTransition;
import Database.AnswerConn;
import Database.QuestionConn;
import Model.Answer;
import Model.Question;
import View.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class PostQuestionController extends Controller{
    
    public PostQuestionController(MappingController mappingController) {
        super(mappingController);
        super.view = new PostQuestions(this, mappingController.GetCurrentUser().getName());
    }
    
    public void PostQuestion(String category, String title, String question){
        // post quetion to database
        Question questionObj = new Question(category, question, title);;
        int uid = mappingController.GetCurrentUser().getUserId();
        try {
            QuestionConn.postQuestion(questionObj, uid);
        } catch (SQLException ex) {
            Logger.getLogger(PostQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // back to homepage
        BackToLandpage();
    }
    
    public void BackToLandpage(){
        mappingController.Move(StateTransition.LandpageMember);
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
