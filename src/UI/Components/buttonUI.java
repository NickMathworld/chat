package UI.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class buttonUI{
    
       /**
        * Da formato a un botón que contendrá texto y un fondo de color.
        * 
        * @param texto Texto que mostrará el botón.
        * @param uno Color del boton 
        * @return Boton con formato.
        */
       public static JButton setButtonTextUI(final JButton but, String texto, final Color uno, Font fuente){ 
                  
            //final JButton but = new JButton(texto); 
            but.setFont(fuente); 
            but.setBackground(uno);
            but.setForeground(Color.WHITE);
            but.setBorderPainted(false); 
            but.setFocusPainted(false);
            try{ 
                but.addMouseListener(new MouseAdapter() { 

                     public void mouseEntered(MouseEvent evt) { 
                         but.setBackground(uno.brighter());} 
                     public void mouseExited(MouseEvent evt) { 
                         but.setBackground(uno); } 
                     public void mousePressed(MouseEvent evt){  
                         but.setBackground(uno.darker());} 
                     public void mouseReleased(MouseEvent evt){ 
                         but.setBackground(uno.brighter());}
                     });
            }catch(NullPointerException ex){}
            return but; 
        } 
       
       
       /**
        * Da formato a un botón que contendrá texto y un fondo de color (especifica el color 
        * de relleno en el estado normal,hover y pressed).
        * 
        * @param texto Texto que mostrará el botón.
        * @param c1 Color del boton en estado normal
        * @param c2 Color del boton en estado hover
        * @param c3 Color del boton en estado pressed
        * @return Boton con formato.
        */
       public static JButton setButtonTextUI(final JButton but,String texto, final Color c1, final Color c2, final Color c3, Font fuente){ 
                  
            //final JButton but = new JButton(texto); 
            but.setFont(fuente); 
            but.setBackground(c1);
            but.setForeground(Color.WHITE);
            but.setBorderPainted(false); 
            but.setFocusPainted(false);
            try{ 
                but.addMouseListener(new MouseAdapter() { 

                     public void mouseEntered(MouseEvent evt) { 
                         but.setBackground(c2);} 
                     public void mouseExited(MouseEvent evt) { 
                         but.setBackground(c1); } 
                     public void mousePressed(MouseEvent evt){  
                         but.setBackground(c3);} 
                     public void mouseReleased(MouseEvent evt){ 
                         but.setBackground(c2);}
                     });
            }catch(NullPointerException ex){}
            return but; 
        } 
       
       /************************************************************************************/
       /**
        * Da formato a un botón que contendrá una imagen y un fondo de color
        * 
        * @param icon Imagen que contendrá el botón
        * @param uno Color del boton
        * @return 
        */
      public static JButton setButtonImageUI(ImageIcon icon, final Color uno, Font fuente){
           
            final JButton but=new JButton(icon); 

            but.setFont(fuente); 
            but.setBackground(uno);
            but.setForeground(Color.WHITE);
            but.setBorderPainted(false); 
            but.setFocusPainted(false);
            but.addMouseListener(new MouseAdapter() {

                 public void mouseEntered(MouseEvent evt) { 
                     but.setBackground(uno.brighter());} 
                 public void mouseExited(MouseEvent evt) { 
                     but.setBackground(uno); } 
                 public void mousePressed(MouseEvent evt){  
                     but.setBackground(uno.darker());} 
                 public void mouseReleased(MouseEvent evt){ 
                     but.setBackground(uno.brighter());}
                 });

            return but; 
        } 
        
       /************************************************************************************/
      /**
       * Da formato a un boton que contendrá tres imagenes sin fondo de color.
       * @param i1 Imagen mostrada cuando el boton este sin enfocar.
       * @param i2 Imagen mostrada cuando el boton este enfocado con el puntero.
       * @param i3 Imagen mostrada cuando haya sido presionado
       * @return Botón con formato.
       */
       public static JButton setButtonImageUI(final ImageIcon i1, final ImageIcon i2, final ImageIcon i3){
           
            final JButton but=new JButton(i1); 

            //but.setOpaque(false);
            but.setBorderPainted(false); 
            but.setBackground(Color.WHITE);
            but.setFocusPainted(false);
            but.addMouseListener(new MouseAdapter() {

                @Override
                 public void mouseEntered(MouseEvent evt) { but.setIcon(i2);}
                @Override
                 public void mouseExited(MouseEvent evt) { but.setIcon(i1); }
                @Override
                 public void mousePressed(MouseEvent evt){  but.setIcon(i3);}
                @Override
                 public void mouseReleased(MouseEvent evt){ but.setIcon(i1);}
                 });

       return but;

    }  

    public static void setButtonTextUI(JButton boton, Color BLACK, Color red, Color white) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
