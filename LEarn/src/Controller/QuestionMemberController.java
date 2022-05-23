/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.AnswerConn;
import Database.QuestionConn;
import Database.TutorConn;
import Model.*;
import Search.LuceneManager;
import View.DetailQuestion;
import View.SearchKategory;
import View.SearchRelevantQuestion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class QuestionMemberController extends Controller{

    public String category;
    public List<Question> questions;
    public String word;
    
    public QuestionMemberController(MappingController mappingController) {
        super(mappingController);
        category = mappingController.category;
        questions = mappingController.questions;
        word = mappingController.word;
        
        
        if(category.compareTo("") == 0)
            super.view = new SearchRelevantQuestion(this, mappingController.GetCurrentUser().getName());
        else
            super.view = new SearchKategory(this, mappingController.GetCurrentUser().getName());
        
        view.show();
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

    public void Home(){
        mappingController.Move(MappingController.StateTransition.LandpageMember);
    }

    public void ProfileMember(){
        mappingController.Move(MappingController.StateTransition.ProfileMember);
    }
    
    public void Logout(){
        mappingController.Move(MappingController.StateTransition.Quit);
    }

    public void ShowAnswerQuestion(int id){
        Question question = questions.get(id);
        Answer answer = null;
        String tutorName = "";
        try {
            answer = AnswerConn.getAnswerByQuestionId(question.question_id);
            tutorName = TutorConn.getTutorName(answer.tutor_id);
        } catch (SQLException ex) {
            //Logger.getLogger(QuestionMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Show Detail Answer view
        ChangeView(new DetailQuestion(this, question, answer, tutorName));
    }
    
    public void SearchByCategory(String category){
        // get all Question from database by category
        List<Question> questions = new ArrayList<>();
        
        try {
            questions = QuestionConn.getAllQuestionsByCategory(category);
        } catch (SQLException ex) {
            //Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mappingController.Move(MappingController.StateTransition.QuestionMember, questions, category);
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
            
        mappingController.MoveByKeyword(MappingController.StateTransition.QuestionMember, results, word);
    }
}
