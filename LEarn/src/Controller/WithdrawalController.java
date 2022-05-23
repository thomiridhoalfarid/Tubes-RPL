/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.TutorConn;
import Database.Tutor_PaymentConn;
import Model.Tutor;
import Model.Tutor_Payment;
import View.WithdrawalPopUp;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class WithdrawalController extends Controller{
    
    public Tutor tutor;
    public WithdrawalController(MappingController mappingController) {
        super(mappingController);
        try {
            tutor = TutorConn.getTutorByUserId(mappingController.GetCurrentUser().getUserId());
        } catch (SQLException ex) {
            //Logger.getLogger(WithdrawalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.view = new WithdrawalPopUp(this, mappingController.GetCurrentUser().getName());
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

    public void PostWithdrawal(String amountRaw){
        double amount = Double.parseDouble(amountRaw);
        Tutor_Payment t = new Tutor_Payment();
        t.setAmount(amount);
        try {
            Tutor_PaymentConn.postTutor_Payment(t, tutor.tutor_id);
        } catch (SQLException ex) {
            Logger.getLogger(WithdrawalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        WithdrawalHistory();
    }
}
