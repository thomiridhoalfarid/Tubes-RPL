/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.MappingController.StateTransition;
import Database.TutorConn;
import Database.UserConn;
import Model.Tutor;
import Model.User;
import View.EditProfileTutor;
import View.ShowProfileTutor;
import View.TBA;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class ProfileTutorController extends Controller{

    public User user;
    public Tutor tutor;
    public ProfileTutorController(MappingController mappingController) {
        super(mappingController);
        user = mappingController.GetCurrentUser();
        try {
            tutor = TutorConn.getTutorByUserId(user.getUserId());
        } catch (SQLException ex) {
            //Logger.getLogger(ProfileTutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.view = new ShowProfileTutor(this);
    }
    
    public void EditProfile(String password, String bio, String name, String bank, String rekening, String nama_rekening) throws SQLException{
        User u = super.mappingController.GetCurrentUser();
        UserConn.updateUser(u,password,bio,name);
        TutorConn.updateTutor(u, bank, rekening, nama_rekening);
        
        ChangeView(null);
    }
    
    public void ToEditProfile(){
        ChangeView(new EditProfileTutor(this));
        
    }
    
    public void BackToProfile(){
        ChangeView(new ShowProfileTutor(this));
        
    }

    public void ProfileTutor() {
        mappingController.Move(MappingController.StateTransition.ProfileTutor);
    }

    public void Logout() {
        mappingController.Move(MappingController.StateTransition.Quit);
    }

    public void WithdrawalHistory() {
        mappingController.Move(MappingController.StateTransition.WithdrawalHistory);
    }

    public void Home() {
        mappingController.Move(MappingController.StateTransition.LandpageTutor);
    }

    public void EditProfile(String password, String bio, String name) {
        
        mappingController.Move(StateTransition.ProfileMember);
    }

    public void EditProfile(String password, String bio, String name, String nomorRekening, String namaRekening){
        try {
            TutorConn.updateTutor(user, tutor.bank, nomorRekening, namaRekening);
            UserConn.updateUser(user, password, bio, name);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileTutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mappingController.Move(StateTransition.ProfileMember);
    }
}
