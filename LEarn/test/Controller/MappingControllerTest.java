/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.MappingController.StateTransition;
import Model.Question;
import Model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anas
 */
public class MappingControllerTest {
    
    MappingController instance;
    
    public MappingControllerTest() {
        instance = null;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new MappingController(false);
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void test_GetCurrentUser() {
        User expResult = null;
        User result = instance.GetCurrentUser();
        assertEquals(expResult, result);
    }


    @Test
    public void testMove_Quit() {
        StateTransition state = StateTransition.Quit;
        
        instance.Move(state);
        StateTransition expResult = StateTransition.Login;
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_NullState() {
        StateTransition state = null;
        StateTransition expResult = instance.getCurrentState();
        
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_NotFoundInTransitionList() {
        StateTransition state = StateTransition.QuestionTutor;
        instance.setCurrentState(StateTransition.Login);
        
        StateTransition expResult = instance.getCurrentState();
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_LoginToSignUpMember() {
        StateTransition state = StateTransition.SignUpMember;
        instance.setCurrentState(StateTransition.Login);
        
        StateTransition expResult = StateTransition.SignUpMember;
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_LoginToSignUpTutor() {
        StateTransition state = StateTransition.SignUpTutor;
        instance.setCurrentState(StateTransition.Login);
        
        StateTransition expResult = StateTransition.SignUpTutor;
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
    
    
    @Test
    public void testMove_LoginToLandpageAdmin() {
        StateTransition state = StateTransition.LandpageAdmin;
        instance.setCurrentState(StateTransition.Login);
        
        StateTransition expResult = StateTransition.LandpageAdmin;
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_LoginToLandpageMember() {
        StateTransition state = StateTransition.LandpageMember;
        instance.setCurrentState(StateTransition.Login);
        
        StateTransition expResult = StateTransition.LandpageMember;
        instance.Move(state, new User());
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_LoginToLandpageTutor() {
        StateTransition state = StateTransition.LandpageTutor;
        instance.setCurrentState(StateTransition.Login);
        
        StateTransition expResult = StateTransition.LandpageTutor;
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_SignUpMemberToLandpageMember() {
        StateTransition state = StateTransition.LandpageMember;
        instance.setCurrentState(StateTransition.SignUpMember);
        
        StateTransition expResult = StateTransition.LandpageMember;
        instance.Move(state, new User());
        assertEquals(expResult, instance.getCurrentState());
    }
    
    @Test
    public void testMove_SignUpTutorToLogin() {
        StateTransition state = StateTransition.Login;
        instance.setCurrentState(StateTransition.SignUpTutor);
        
        StateTransition expResult = StateTransition.Login;
        instance.Move(state);
        assertEquals(expResult, instance.getCurrentState());
    }
}
