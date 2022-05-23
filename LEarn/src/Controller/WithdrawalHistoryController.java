/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Anas
 */
public class WithdrawalHistoryController extends Controller{
    
    public WithdrawalHistoryController(MappingController mappingController) {
        super(mappingController);
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
    
}
