/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Question {
    private String QuestionID;
    private String Category;
    private String Content;
    ArrayList<Answer> AnswerList = new ArrayList<>();

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public ArrayList<Answer> getAnswerList() {
        return AnswerList;
    }

    public void setAnswerList(ArrayList<Answer> AnswerList) {
        this.AnswerList = AnswerList;
    }
    
    public void addAnswer(Answer a) {
        AnswerList.add(a);
    }

    public Question(String QuestionID, String Category, String Content) {
        this.QuestionID = QuestionID;
        this.Category = Category;
        this.Content = Content;
    }
    
    
}
