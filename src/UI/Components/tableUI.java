package UI.Components;
 
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class tableUI extends DefaultTableCellRenderer {
    Color background;
    Color foreground;
    
     public tableUI (Color background,Color foreground) {
        super();
        this.background = background;
        this.foreground = foreground;
    }
     
      public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        setBorder(new EmptyBorder(0,0,0,0));
        comp.setBackground(background);
        comp.setForeground(foreground);
        
        
        fontLibrary fL=new fontLibrary();
        comp.setFont(fL.setRobotoRegular(14));
    return comp;
    }
    
}
