package UI.Components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class labelUI { 
    
    
    /**********************************************************************************/
    
    /**
     * Devuelve un JLabel con formato de titulo
     * 
     * @param texto Titulo que contendra la etiqueta
     * @param size Tamaño del titulo
     * @param c1 Color del titulo
     * @return JLabel con formato de titulo
     */
    
    public static JLabel setTituloUI(String texto,float size,Color c1){ 
      JLabel etiqueta=new JLabel(texto); 
      fontLibrary fL=new fontLibrary();
      etiqueta.setFont(fL.setRobotoThin(size));
      etiqueta.setForeground(c1);
      return etiqueta;
    }    
     
    /**********************************************************************************/
    
    /**
     * Devuelve un JLabel con formato de descripción
     * 
     * @param texto Texto de la etiqueta  
     * @return JLabel con formato de descripcion
     */
    
    public static JLabel setDescripcionUI(String texto){
       JLabel etiqueta=new JLabel(texto); 
       etiqueta.setFont(new Font("Segoe UI",Font.TRUETYPE_FONT,16));
       etiqueta.setForeground(new Color(0x515151));       
       return etiqueta; 
    }
    
    /**********************************************************************************/
    /**
     * Devuelve un JLabel con formato
     * 
     * @param texto Texto de la etiqueta
     * @param c1 Color del texto de la etiqueta
     * @param fuente Fuente de la etiqueta
     * @return JLabel con formato
     */
    public static JLabel setLabelUI(String texto,Color c1, Font fuente){ 
      JLabel etiqueta=new JLabel(texto,SwingConstants.CENTER);  
      etiqueta.setFont(fuente);
      etiqueta.setForeground(c1);
      return etiqueta;
    } 
}
