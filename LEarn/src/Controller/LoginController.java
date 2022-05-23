/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.*;
import View.Login;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import Model.User;
import Controller.MappingController.StateTransition;
import java.util.Arrays;

/**
 * Class controller yang mengatur login dari aplikasi
 * @author Anas
 */
public class LoginController extends Controller{
    
    String[] roles = new String[]{"admin", "member", "tutor"};
    StateTransition[] states = new StateTransition[]
    {
        StateTransition.LandpageAdmin,
        StateTransition.LandpageMember,
        StateTransition.LandpageTutor
    };
    
    /**
     * Constructor dari kelas ini
     * @param mappingController : global mapping controller
     */
    public LoginController(MappingController mappingController) {
        super(mappingController);
        view = new Login(this);
        Hide();
    }
    
    /**
     * Method yang berfungsi untuk melakukan login.
     * Apabila berhasil akan berindah UI sesuai dengan tipe role dari user
     * @param username : string username
     * @param password : string password
     */
    public boolean Login(String username, String password){
        StateTransition state = StateTransition.Login;
        try {
            User user = UserConn.getUserByUsernameAndPassword(username, password);
            
            int index = Arrays.asList(roles).indexOf(user.getRole());
            state = index != -1 ? states[index] : state;
            
            mappingController.Move(state, user);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return state != StateTransition.Login;
    }
    
    public void MoveToSignUp(boolean member){
        if(member)
            mappingController.Move(StateTransition.SignUpMember);
        else
            mappingController.Move(StateTransition.SignUpTutor);
    }
}
