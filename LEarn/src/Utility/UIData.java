/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas
 */
public class UIData {

    /**
     * Custom Font class
     */
    public static class CustomFont{
        
        private Font font;
        private final float size;
        private final String pathname;
        
        /**
         * Constructor Custom Font class
         * @param size : default size font
         * @param pathname : font name
         */
        public CustomFont(float size, String pathname) {
            this.size = size;
            this.pathname = pathname;
            LoadFont();
        }
        
        private void LoadFont(){
            try{
                font = Font.createFont(Font.TRUETYPE_FONT, new File("res/" + pathname));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
            } catch (FontFormatException | IOException ex) {
                Logger.getLogger(UIData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Get Custom font by default size
         * @return
         */
        public Font getFont() {
            return font.deriveFont(size);
        }
        
        /**
         * Get Custom font by size
         * @param size : size to set in custom font
         * @return
         */
        public Font getFont(int size) {
            return font.deriveFont(size);
        }
        
        /**
         * Get font name
         * @return
         */
        public String FontName(){
            return pathname;
        }
    }
    
    // Custom Font Data
    public static final CustomFont FONT1 = new CustomFont(18, "Roboto-Regular.ttf");
    public static final CustomFont FONT2 = new CustomFont(32, "Roboto-Thin.ttf");
    
    // Custom Window Data
    public static final Dimension WINDOW_DIMENSION = new Dimension(1527, 1043);
    
    // Custom Color Data
    public static final Color COLOR_1 = new Color(255, 255, 255, 255);
    
    public static void main(String[] args) {
        System.out.println(FONT1.FontName());
        System.out.println(FONT2.FontName());
    }
}
