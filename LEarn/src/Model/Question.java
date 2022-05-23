/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;

/**
 *
 * @author ASUS
 */
public class Question {
    
    private String category;
    private String content;
    private Date time;
    
    public String title;
    public int user_id;
    public int question_id;

    public Question(String category, String content, String title) {
        this.category = category;
        this.content = content;
        this.title = title;
    }

    public Question() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    
}
