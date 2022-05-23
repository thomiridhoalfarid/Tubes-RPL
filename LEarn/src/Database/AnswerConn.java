/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.Conn.getConnection;
import Model.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AnswerConn {
    
    public static Answer getAnswerData(ResultSet rs) throws SQLException{
        Answer answer = new Answer();
        
        answer.setTime(rs.getDate("time"));
        answer.setContent(rs.getString("content"));
        answer.setRating(rs.getDouble("rating"));
        answer.setVerified(rs.getString("verified"));
        
        answer.answer_id = rs.getInt("answer_id");
        answer.tutor_id = rs.getInt("tutor_id");
        answer.question_id = rs.getInt("question_id");
        
        return answer;
    }
    
    public static Answer getAnswerById(String id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from answer where answer_id =?");
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        
        return rs.next()? getAnswerData(rs) : null;
    }
    
    public static Answer getAnswerByQuestionId(int qid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from answer where question_id =?");
        st.setInt(1, qid);
        ResultSet rs = st.executeQuery();

        return rs.next()? getAnswerData(rs) : null;
    }

    public static void postAnswer(Answer a, String tid, String qid) throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("insert into answer (time, content, rating, tutor_id, question_id)"+" values(?,?,?,?,?)");
        st.setDate(1, a.getTime());
        st.setString(2, a.getContent());
        st.setDouble(3, a.getRating());
        st.setString(4, tid);
        st.setString(5, qid);
       
        st.execute();
    }

    public static List<Answer> getAllAnswers() throws SQLException {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from answer");
        ResultSet rs = st.executeQuery();
        
        List<Answer> answers = new ArrayList<>();
        while  (rs.next()){
            answers.add(getAnswerData(rs));
        }        
        return answers;
    }
}

