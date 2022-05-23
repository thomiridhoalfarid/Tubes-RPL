/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author Anas
 */
public class Controller {
    protected final MappingController mappingController;
    protected JFrame view;

    public Controller(MappingController mappingController) {
        this.mappingController = mappingController;
    }
    
    public void Show(){
        view.setVisible(true);
    };
    
    public void Show(Point location){
        view.setLocation(location);
        view.setVisible(true);
    };
    
    public Point Hide(){
        Point location = view.getLocation();
        view.dispose();
        return location;
    };

    protected void ChangeView(JFrame view){
        view.setVisible(false);
        Point loc = Hide();
        this.view = view;
        Show(loc);        
    }
}
