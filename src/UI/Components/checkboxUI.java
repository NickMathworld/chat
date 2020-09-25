
package UI;

import java.awt.Color;
import java.awt.Font; 
import javax.swing.JCheckBox;

public class checkboxUI {
    
    public static JCheckBox setChecadoUI(String texto,Color c1){
        
        JCheckBox chequito=new JCheckBox(texto,true);
        chequito.setBorderPaintedFlat(true);
        chequito.setBackground(Color.WHITE);
        chequito.setForeground(new Color(0x515151));
        chequito.setFont(new Font("Segoe UI",Font.TRUETYPE_FONT,12));
        chequito.setBorderPainted(false); 
        chequito.setFocusPainted(false);
                
        return chequito;
    }
    
}
