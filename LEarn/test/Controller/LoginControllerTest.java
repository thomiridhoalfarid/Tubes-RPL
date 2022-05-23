package Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginControllerTest {
    
    LoginController instance;
    public LoginControllerTest() {
    }
    
    @Before
    public void setUp() {
        MappingController controller = new MappingController(false);
        
        instance = new LoginController(controller);
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testLogin_SuccessMember() {
        String username = "anas";
        String password = "123456";
       
        boolean expResult = true;
        boolean result = instance.Login(username, password);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLogin_SuccessTutor() {
        String username = "fatiha";
        String password = "123";
       
        boolean expResult = true;
        boolean result = instance.Login(username, password);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLogin_SuccessAdmin() {
        String username = "nuril";
        String password = "123456";
       
        boolean expResult = true;
        boolean result = instance.Login(username, password);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testLogin_FailedEmptyString() {
        String username = "";
        String password = "";
       
        boolean expResult = false;
        boolean result = instance.Login(username, password);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLogin_Failed() {
        String username = "Anas";
        String password = "123";
       
        boolean expResult = false;
        boolean result = instance.Login(username, password);
        
        assertEquals(expResult, result);
    }
}
