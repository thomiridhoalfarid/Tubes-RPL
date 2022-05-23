/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

/**
 *
 * @author ASUS
 */
public class Answer {
    private String AnswerID;
    private String Content;
    private double Rate;

    public String getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(String AnswerID) {
        this.AnswerID = AnswerID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double Rate) {
        this.Rate = Rate;
    }

    public Answer(String AnswerID, String Content, double Rate) {
        this.AnswerID = AnswerID;
        this.Content = Content;
        this.Rate = Rate;
    }
    
}
