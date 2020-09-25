package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class textfieldUI {
    
    
     boolean punto=false;
    
     
      //******************************************************************************************************/
     /**
      * Da formato a una JTextField
      * @param texto Texto que contendrá la caja
      * @param c1 Color del contorno de la caja
      * @param maximo Número máximo de caracteres que se pueden ingresar (si el valor es negativo no habrá limite de caracteres)
      * @param fuente Tipo de Fuente de la caja
      * @return Caja con Formato
      */
      // JTextField Predeterminadas (sino se desea tener un maximo numero de caractares asigne -1 a la variable maximo)
      public static JTextField setCajaUI(String texto, final Color c1, final int maximo, Font fuente){
          
       final JTextField campo;       
       if(maximo<0) campo = new JTextField(texto);
       
       else{ 
            campo = new JTextField(texto);
            campo.addKeyListener(new KeyListener(){

             @Override
             public void keyTyped(KeyEvent e){
                 if (campo.getText().length()== maximo) e.consume();
             }
             @Override
             public void keyPressed(KeyEvent arg0) {}
             @Override
             public void keyReleased(KeyEvent arg0) {}
             });
        }
       
        
       campo.setFont(fuente);
       campo.setHorizontalAlignment(JTextField.CENTER); 
       campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
       campo.addMouseListener(new MouseAdapter() {
            
           @Override
            public void mouseEntered(MouseEvent evt) { 
            campo.setBorder(BorderFactory.createLineBorder(c1));
           }           
           @Override
            public void mouseExited(MouseEvent evt) { 
            campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
           }
           @Override
            public void mousePressed(MouseEvent evt){  
            campo.setBorder(BorderFactory.createLineBorder(c1));  
            campo.setText("");
           }
           @Override
            public void mouseReleased(MouseEvent evt){
            campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
           }
       });
       
       return campo; 
    }    
      
      /***********************************************************************************************************/
      /**
       * Da formato a una JTextField para que no posea contorno
       * @param texto Texto que contendrá la caja
       * @param c1 Color del contorno de la caja
       * @param fuente Tipo de letra de la caja 
       * @return JTextField con Formato
       */
       public static JTextField setCajaSinContornoUI(String texto, final Color c1, Font fuente){
          
       final JTextField campo = new JTextField(texto);  
       campo.setHorizontalAlignment(JTextField.LEFT);
       campo.setFont(fuente);
       
       campo.setForeground(c1);
       campo.setBorder(null);
       campo.addMouseListener(new MouseAdapter() {
            
           @Override
            public void mousePressed(MouseEvent evt){  
            campo.setText("");
           }
       });
       
       return campo;

    }  
       
       
       //******************************************************************************************************/
       /**
        * Da formato a una JtextField para que solo permita datos de tipo entero
        * @param c1 Color del contorno de la caja
        * @param fuente Tipo de letra de la caja 
        * @return JTextField con Formato
        */
       
       public static JTextField setCajaEnterosUI(final Color c1, Font fuente){
       
       final JTextField campo;
       
       campo = new JTextField();
       campo.setFont(new Font("Segoe UI",Font.TRUETYPE_FONT,12));
       campo.setHorizontalAlignment(JTextField.CENTER); 
       campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
       campo.addMouseListener(new MouseAdapter() {
            
           @Override
            public void mouseEntered(MouseEvent evt) { 
            campo.setBorder(BorderFactory.createLineBorder(c1));
           }           
           @Override
            public void mouseExited(MouseEvent evt) { 
            campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
           }
           @Override
            public void mousePressed(MouseEvent evt){  
            campo.setBorder(BorderFactory.createLineBorder(c1));  
            campo.setText("");
           }
           @Override
            public void mouseReleased(MouseEvent evt){
            campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
           }
       });
       campo.addKeyListener(new KeyAdapter(){
       public void keyTyped(KeyEvent e)
            {
               char caracter = e.getKeyChar();

               // Verificar si la tecla pulsada no es un digito
               if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)){
                  e.consume();  // ignorar el evento de teclado
               } 
            }
        });
       return campo;

    }  
       
       //******************************************************************************************************/
       /**
        * Da formato a una JTextField para que permita ingresar unicamente datos de tipo flotantes.
        * 
        * @param c1 Color del contorno de la caja
        * @param fuente Tipo de letra de la caja
        * @return JTextField con formato
        */ 
       public static JTextField setCajaFlotantesUI(final Color c1, Font fuente){
          
            final JTextField campo;
            campo = new JTextField();

            campo.setFont(fuente);
            campo.setHorizontalAlignment(JTextField.CENTER); 
            campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
            campo.addMouseListener(new MouseAdapter() {
 
                 public void mouseEntered(MouseEvent evt) { 
                 campo.setBorder(BorderFactory.createLineBorder(c1));
                }          
                 public void mouseExited(MouseEvent evt) { 
                 campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
                } 
                 public void mousePressed(MouseEvent evt){  
                 campo.setBorder(BorderFactory.createLineBorder(c1));  
                 campo.setText("");
                } 
                 public void mouseReleased(MouseEvent evt){
                 campo.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
                }
            });
            
            campo.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e)
                 {
                    char caracter = e.getKeyChar();
                    
                    // Verificar si la tecla pulsada no es un digito 
                    if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)&& caracter!=46){
                       e.consume();  // ignorar el evento de teclado
                    }

                    if (caracter == '.' && campo.getText().contains(".")) {
                         e.consume();
                    }
 
                 }
             });
            return campo;

    }
}
