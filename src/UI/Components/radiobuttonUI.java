package UI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class radiobuttonUI {
    
    
    public static JRadioButton setRadioButtonUI(String texto,Color c1){
        
        JRadioButton rb = new JRadioButton(texto,false);
        rb.setBackground(Color.WHITE);
        rb.setForeground(new Color(0x515151));
        rb.setFont(new Font("Segoe UI",Font.TRUETYPE_FONT,12));
        rb.setBorderPainted(false); 
        rb.setFocusPainted(false);
                
        return rb;
    }
}
