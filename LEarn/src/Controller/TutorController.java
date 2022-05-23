/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.MappingController.StateTransition;
import Database.QuestionConn;
import Model.Question;
import Model.User;
import Search.LuceneManager;
import View.LandpageTutor;
import View.TBA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class TutorController extends Controller{
    
    public User user;
    public TutorController(MappingController mappingController) {
        super(mappingController);
        user = mappingController.GetCurrentUser();
        super.view = new LandpageTutor(this);
    }
    
    public void ProfileTutor(){
        mappingController.Move(StateTransition.ProfileTutor);
    }
    
    public void WithdrawalHistory(){
        mappingController.Move(StateTransition.WithdrawalHistory);
    }
    
    public void Withdrawal(){
        mappingController.Move(StateTransition.Withdrawal);
    }
    
     public void SearchByCategory(String category){
        // get all Question from database by category
        List<Question> questions = new ArrayList<>();
        
        try {
            questions = QuestionConn.getAllQuestionsByCategory(category);
        } catch (SQLException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mappingController.Move(StateTransition.QuestionTutor, questions, category);
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
            
        mappingController.Move(StateTransition.QuestionTutor, results, "");
    }
    
    public void Logout(){
        mappingController.Move(StateTransition.Quit);
    }

    public void Home() {
        mappingController.Move(MappingController.StateTransition.LandpageTutor);
    }
}
