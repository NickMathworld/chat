package UI.Components;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class fontLibrary {
    
    
    /***********************************************************************************************************/    
    /**
     * Devuelve un objeto de tipo Font de tipo Roboto Thin
     * @param n Tamaño de la fuente
     * @return Fuente con formato Roboto Thin
     */
    public static Font setRobotoThin(float n){
        
        Font robotoFont = null;  
        try {
        //create the font to use. Specify the size!
        robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/UI/fonts/Roboto-Thin.ttf")).deriveFont(n);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

               //register the font
               ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Thin.ttf")));
               } catch (IOException | FontFormatException e) {}  

        return robotoFont;
    }
    
    /***********************************************************************************************************/   
    /**
     * Devuelve un objeto de tipo Font de tipo Roboto Light
     * @param n Tamaño de la fuente
     * @return Fuente con formato Roboto Light
     */
    public static Font setRobotoLight(float n){
        
        Font robotoFont = null;  
        try {
        //create the font to use. Specify the size!
        robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/UI/fonts/Roboto-Light.ttf")).deriveFont(n);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

               //register the font
               ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Light.ttf")));
               } catch (IOException | FontFormatException e) {}  

        return robotoFont;
    }
    
    
    /***********************************************************************************************************/   
   /**
     * Devuelve un objeto de tipo Font de tipo Roboto Regular
     * @param n Tamaño de la fuente
     * @return Fuente con formato Roboto Regular
     */
    public static Font setRobotoRegular(float n){
        
        Font robotoFont = null;  
        try {
        //create the font to use. Specify the size!
        robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/UI/fonts/Roboto-Regular.ttf")).deriveFont(n);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

               //register the font
               ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Regular.ttf")));
               } catch (IOException | FontFormatException e) {}  

        return robotoFont;
    }
    /*****************************************************************************************************************/
    /**
     * Devuelve un objeto de tipo Font de tipo Roboto Bold
     * @param n Tamaño de la fuente
     * @return Fuente con formato Roboto Bold
     */
    public static Font setRobotoBold(float n){
        
        Font robotoFont = null;  
        try {
        //create the font to use. Specify the size!
        robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/UI/fonts/Roboto-Bold.ttf")).deriveFont(n);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

               //register the font
               ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Bold.ttf")));
               } catch (IOException | FontFormatException e) {}  

        return robotoFont;
    }
    
    
    /***********************************************************************************************************/
    /**
     * Devuelve un objeto de tipo Font de tipo Roboto Black
     * @param n Tamaño de la fuente
     * @return Fuente con formato Roboto Black
     */
    public static Font setRobotoBlack(float n){
        
        Font robotoFont = null;  
        try {
        //create the font to use. Specify the size!
        robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/UI/fonts/Roboto-Black.ttf")).deriveFont(n);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

               //register the font
               ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Black.ttf")));
               } catch (IOException | FontFormatException e) {}  

        return robotoFont;
    }
    
    
}
