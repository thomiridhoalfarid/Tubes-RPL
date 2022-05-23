/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Question;
import Model.User;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Class controller yang mengatur perpindahan antar controller
 * @author Anas
 */
public class MappingController {

    public List<Question> questions;
    private User user;
    private Controller activeController;
    public String category;
    public String word;
    
    public User GetCurrentUser(){
        return user;
    }
    
    public void SetCurrentUser(User user){
        this.user = user;
    }

    /**
     * Constructor dari kelas mapping controller
     */
    public MappingController() {
        this.activeController = new LoginController(this);
        activeController.Show();
    }
    
    public MappingController(boolean usedGui){
        this.activeController = new LoginController(this);
        
        if(usedGui)
            activeController.Show();
        else
            activeController.Hide();
    }
    
    private StateTransition currentState = StateTransition.Login;

    public void setCurrentState(StateTransition currentState) {
        this.currentState = currentState;
    }

    public StateTransition getCurrentState() {
        return currentState;
    }
    
    public enum StateTransition 
    {
        Login,
        SignUpTutor,
        SignUpMember,
        LandpageMember,
        ProfileMember,
        PostQuestionMember,
        QuestionHistory,
        QuestionMember,
        LandpageTutor,
        QuestionTutor,
        Withdrawal,
        WithdrawalHistory,
        ProfileTutor,
        LandpageAdmin,
        Quit
    }

    
    public class Transition{

        public Transition(StateTransition current, StateTransition next) {
            this.current = current;
            this.next = next;
        }
        public StateTransition current;
        public StateTransition next; 
    }
    
   private List<Transition> transitions = new ArrayList<Transition>
        (Arrays.asList
        (
                new Transition(StateTransition.Login, StateTransition.SignUpMember),
                new Transition(StateTransition.Login, StateTransition.SignUpTutor),
                new Transition(StateTransition.Login, StateTransition.LandpageAdmin),
                new Transition(StateTransition.Login, StateTransition.LandpageMember),
                new Transition(StateTransition.Login, StateTransition.LandpageTutor),
                new Transition(StateTransition.SignUpMember, StateTransition.LandpageMember),
                new Transition(StateTransition.SignUpTutor, StateTransition.Login),
                new Transition(StateTransition.LandpageMember, StateTransition.LandpageMember),
                new Transition(StateTransition.LandpageMember, StateTransition.QuestionMember),
                new Transition(StateTransition.LandpageMember, StateTransition.QuestionHistory),
                new Transition(StateTransition.LandpageMember, StateTransition.PostQuestionMember),
                new Transition(StateTransition.LandpageMember, StateTransition.ProfileMember),
                new Transition(StateTransition.QuestionMember, StateTransition.LandpageMember),
                new Transition(StateTransition.QuestionMember, StateTransition.QuestionHistory),
                new Transition(StateTransition.QuestionMember, StateTransition.QuestionMember),
                new Transition(StateTransition.QuestionMember, StateTransition.ProfileMember),
                new Transition(StateTransition.PostQuestionMember, StateTransition.LandpageMember),
                new Transition(StateTransition.PostQuestionMember, StateTransition.QuestionHistory),
                new Transition(StateTransition.PostQuestionMember, StateTransition.ProfileMember),
                new Transition(StateTransition.ProfileMember, StateTransition.LandpageMember),
                new Transition(StateTransition.ProfileMember, StateTransition.QuestionHistory),
                new Transition(StateTransition.ProfileMember, StateTransition.ProfileMember),
                new Transition(StateTransition.LandpageTutor, StateTransition.LandpageTutor),
                new Transition(StateTransition.LandpageTutor, StateTransition.QuestionTutor),
                new Transition(StateTransition.LandpageTutor, StateTransition.WithdrawalHistory),
                new Transition(StateTransition.LandpageTutor, StateTransition.Withdrawal),
                new Transition(StateTransition.LandpageTutor, StateTransition.ProfileTutor),
                new Transition(StateTransition.ProfileTutor, StateTransition.ProfileTutor),
                new Transition(StateTransition.ProfileTutor, StateTransition.LandpageTutor),
                new Transition(StateTransition.ProfileTutor, StateTransition.WithdrawalHistory),
                new Transition(StateTransition.WithdrawalHistory, StateTransition.ProfileTutor),
                new Transition(StateTransition.WithdrawalHistory, StateTransition.LandpageTutor),
                new Transition(StateTransition.WithdrawalHistory, StateTransition.WithdrawalHistory),
                new Transition(StateTransition.Withdrawal, StateTransition.ProfileTutor),
                new Transition(StateTransition.Withdrawal, StateTransition.LandpageTutor),
                new Transition(StateTransition.Withdrawal, StateTransition.WithdrawalHistory),
                new Transition(StateTransition.LandpageAdmin, StateTransition.LandpageAdmin)
        )
        );
    
    public void Move(StateTransition state, User user){
        switch(currentState){
            case Login:
                this.user = user;
                break;
            case ProfileMember:
                this.user = user;
                break;
            case ProfileTutor:
                this.user = user;
                break;
            case SignUpMember:
                this.user = user;
                break;
            default:
                
        }
        Move(state);
    }
    
    public void MoveByKeyword(StateTransition state, List<Question> questions, String word){
        this.questions = questions;
        this.word = word;
        this.category = "";
        Move(state);
    }
    
    public void Move(StateTransition state, List<Question> questions, String category){
        this.questions = questions;
        this.category = category;
        this.word = "";
        Move(state);
    }
    
    /**
     * method yang berfungsi untuk melakukan perpindahan controller
     * @param transition : State Transition yang dituju
     */
    public void Move(StateTransition state){
        if(state == StateTransition.Quit){
            currentState = StateTransition.Login;
            this.user = null;
            Point loc = activeController.Hide();
            activeController = new LoginController(this);
            activeController.Show(loc);
            return;
        }
        
        boolean isValid = false;
        for(Transition transition : transitions){
            isValid = transition.current == currentState && transition.next == state;
            if(isValid){
                isValid = true;
                currentState = state;
                break;
            }
        }
        
        if(!isValid)
            return;
        
        Controller last = activeController;
        
        switch(currentState){
            case SignUpTutor:
                activeController = new SignUpController(this, false);
                break;
            case SignUpMember:
                activeController = new SignUpController(this, true);
                break;
            case LandpageMember:
                activeController = new MemberController(this);
                break;
            case ProfileMember:
                activeController = new ProfileMemberController(this);
                break;
            case PostQuestionMember:
                activeController = new PostQuestionController(this);
                break;
            case QuestionHistory:
                activeController = new QuestionHistoryControlle(this);
                break;
            case QuestionMember:
                activeController = new QuestionMemberController(this);
                break;
            case LandpageTutor:
                activeController = new TutorController(this);
                break;
            case WithdrawalHistory:
                activeController = new WithdrawalHistoryController(this);
                break;
            case ProfileTutor:
                activeController = new ProfileTutorController(this);
                break;
            case LandpageAdmin:
                activeController = new AdminController(this);
                break;
            case Login:
                activeController = new LoginController(this);
                break;
            case Quit:
                break;
            case QuestionTutor:
                activeController = new QuestionTutorController(this);
                break;
            case Withdrawal:
                activeController = new WithdrawalController(this);
                break;
            default:
                throw new AssertionError(currentState.name());

        }
        
        Point loc = last.Hide();
        activeController.Show(loc);
    }
}
