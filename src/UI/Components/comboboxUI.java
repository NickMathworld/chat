package UI.Components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboPopup;


public class comboboxUI{
    
    
     /**
      * Combo pequeño, con fuente Segoe UI 12, con color de seleccion del acento
      *@autor Francisco Trejo
      *@param texto Cadenas a agregar al JComboBox
      *@param uno Acento de seleccion para elementos del JComboBox
      *@return JComboBox con formato
      **/
     public static void setComboUI(JComboBox combito, String texto[],final Color uno){
        combito.setBorder(BorderFactory.createEmptyBorder());
        combito.setFont(new Font("Arial",Font.BOLD,16));
        combito.setForeground(new Color(0x515151));
        combito.setBackground(Color.WHITE);
        Object child = combito.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        JList list = popup.getList();
        list.setSelectionBackground(uno);
        list.setSelectionForeground(Color.WHITE);
        ((JLabel)combito.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        combito.setSelectedIndex(0);
        combito.setFocusCycleRoot(false);
        combito.paintComponents(null);
    
    return;
    }
     
    //Combo pequeño, con fuente Segoe UI 12, con color de seleccion gris claro
     public static JComboBox setComboUI(String texto[]){
          
        final JComboBox combito = new JComboBox();
        
        for(int i=0;i<texto.length;i++){
            combito.addItem(texto[i]);
        }
        combito.setBorder(BorderFactory.createEmptyBorder());
        combito.setFont(new Font("Segoe UI",Font.TRUETYPE_FONT,12));
        combito.setForeground(new Color(0x515151));
        combito.setBackground(Color.WHITE);
        Object child = combito.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        JList list = popup.getList();
        list.setSelectionBackground(new Color(0xE0E0E0));
        
        ((JLabel)combito.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        combito.setSelectedIndex(0);
        combito.setFocusCycleRoot(false);
        combito.paintComponents(null);
    
    return combito;
    }
     
     
       // Combo Enorme con seleccion gris sin datos.
     public static void setComboUI(JComboBox combito,final Color uno){  
        //final JComboBox combito = new JComboBox();
        
        combito.setBorder(BorderFactory.createEmptyBorder());
        ((JLabel)combito.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
                
        fontLibrary fL=new fontLibrary();      
        combito.setFont(new Font("Century Gothic",Font.TRUETYPE_FONT,16));
        combito.setForeground(Color.black);
        combito.setBackground(Color.black);
        Object child = combito.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        JList list = popup.getList();
        list.setSelectionBackground((Color.DARK_GRAY) );
    
    return;
    }
    
     // Combo Enorme con seleccion gris sin datos.
     public static JComboBox setComboUI(final Color uno, final Color texto){
         
        final JComboBox combito = new JComboBox();
        
        combito.setBorder(BorderFactory.createEmptyBorder());
        ((JLabel)combito.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
                
        fontLibrary fL=new fontLibrary();      
        combito.setFont(fL.setRobotoLight(30));
        combito.setForeground(texto);
        combito.setBackground(Color.WHITE);
        Object child = combito.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        JList list = popup.getList();
        list.setSelectionBackground(uno);
        list.setSelectionForeground(texto);
    
    return combito;
    }
     
     
     //Combo pequeño, con fuente Segoe UI 12, con color de seleccion del acento, y color de fondo dos
     public static JComboBox setComboUI(String texto[],final Color uno, final Color dos){
         
        final JComboBox combito = new JComboBox();
        
        for(int i=0;i<texto.length;i++){
            combito.addItem(texto[i]);
        }
        combito.setBorder(BorderFactory.createEmptyBorder());
        combito.setFont(new Font("Segoe UI",Font.TRUETYPE_FONT,12));
        combito.setForeground(new Color(0x2E2E2E));
        combito.setBackground(dos);
        Object child = combito.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        JList list = popup.getList();
        list.setSelectionBackground(uno);
        list.setSelectionForeground(Color.WHITE); 
        ((JLabel)combito.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        combito.setSelectedIndex(0);
        combito.setFocusCycleRoot(false);
        combito.paintComponents(null);
    
    return combito;
    }
    
}
