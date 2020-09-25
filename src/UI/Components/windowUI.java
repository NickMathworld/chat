package UI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * 
 * @author Francisco
 * @version 1.4
 * 
 * Ventana con interfaz Modern UI
 */
public class windowUI extends JFrame implements ActionListener {
    
    int x,y;
    Point point; 
    Boolean isMainWindow;
    JPanel titlebar;
    Color tema;
    
    //Diseño de la interfaz de la ventana
    /**
     * 
     * Crea un objeto de tipo windowUI (JFrame con apariencia moderna).
     * @param esVentanaPrincipal Denota si la ventana a crear será la principal.
     * @param ancho Ancho de la ventana
     * @param alto Alto de la Ventana
     * @param color Tema Oscuro o tema Claro
     * 
     */
    public windowUI(boolean esVentanaPrincipal, int ancho, int alto, String color){  
       
        getContentPane().setLayout(new BorderLayout(0,0));
        
        //Nuevo Panel que fungirá de barra de titulo
        titlebar = new JPanel();
        titlebar.setLayout(null);
        
        
        //Elige el tema de la ventana
        switch(color){
            case "dark":
                tema = new Color(0x0F0F0F);
                break;
            default:
                tema = Color.WHITE;
                break;
        } 
        
        
        titlebar.setBackground(tema);
        //quitar marco predeterminado
        setUndecorated(true);  
        setResizable(false); 
        setVisible(true);
        
        //Al cerrar programa finalizar el proceso del mismo     
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Icono para el programa
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/UI/Images/icono.png"));
        
        //color de la ventana
        getContentPane().setBackground(tema);
        
        //Color del borde de la ventana
        ((JComponent)getContentPane()).setBorder(BorderFactory.createLineBorder(tema.darker(),3));
        
        //Elementos de la barra de titulo de la ventana
        JLabel barraTitulo = new JLabel(); 
        
        
        final ImageIcon iconoMinimizar;
        final ImageIcon iconoMinimizarHover;
        final ImageIcon iconoMinimizarPressed;
        final ImageIcon iconoCerrar;
        final ImageIcon iconoCerrarHover;
        final ImageIcon iconoCerrarPressed;
        
        //Botones para tema oscuro
        if(color.equals("dark")){
           iconoMinimizar = new ImageIcon("src/UI/Images/minimize_dark.png");        
           iconoMinimizarHover = new ImageIcon("src/UI/Images/minimize_hover_dark.png");        
           iconoMinimizarPressed = new ImageIcon("src/UI/Images/minimize_pressed_dark.png");

           iconoCerrar = new ImageIcon("src/UI/Images/close_dark.png"); 
           iconoCerrarHover = new ImageIcon("src/UI/Images/close_hover_dark.png"); 
           iconoCerrarPressed = new ImageIcon("src/UI/Images/close_pressed_dark.png"); 
         }
        
        //Botones para tema claro
        else{
           iconoMinimizar = new ImageIcon("src/UI/Images/minimize_light.png");        
           iconoMinimizarHover = new ImageIcon("src/UI/Images/minimize_hover_light.png");        
           iconoMinimizarPressed = new ImageIcon("src/UI/Images/minimize_pressed_light.png");

           iconoCerrar = new ImageIcon("src/UI/Images/close_light.png"); 
           iconoCerrarHover = new ImageIcon("src/UI/Images/close_hover_light.png"); 
           iconoCerrarPressed = new ImageIcon("src/UI/Images/close_pressed_light.png");
        }
        //no hacer la ventana demasiado pequeña
        if(ancho < (iconoMinimizar.getIconWidth() + iconoCerrar.getIconWidth() + 20)) { 
            ancho = iconoMinimizar.getIconWidth() + iconoCerrar.getIconWidth() + 20; 
        } 
        
        //setExtendedState(MAXIMIZED_BOTH);
        setPreferredSize(new Dimension (ancho,alto)); 
        
        final JButton minimizar = new JButton(iconoMinimizar); 
        final JButton cerrar = new JButton(iconoCerrar);
        
        minimizar.addActionListener(this);
        minimizar.setActionCommand("Minimizar");
        
        cerrar.addActionListener(this);
        cerrar.setActionCommand("Cerrar"); 
        
        minimizar.setBorder(null);
        minimizar.setBorderPainted(false);
        minimizar.setContentAreaFilled(false);
        minimizar.setFocusPainted(false);
        minimizar.setFocusable(false); 
        cerrar.setBorder(null);
        cerrar.setBorderPainted(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setFocusPainted(false);
        cerrar.setFocusable(false); 
        
        //Ubica la posicion de los botones y su tamaño
        minimizar.setBounds(ancho - iconoCerrar.getIconWidth() - iconoMinimizar.getIconWidth(), 0,
                            iconoMinimizar.getIconWidth(), iconoMinimizar.getIconHeight());
        cerrar.setBounds(ancho - iconoCerrar.getIconWidth(), 0, iconoCerrar.getIconWidth(), iconoCerrar.getIconHeight());
        
        
        //Personaliza botones en hover
        minimizar.addMouseListener(new MouseAdapter() {  
            public void mouseEntered(MouseEvent evt) {  
                minimizar.setIcon(iconoMinimizarHover);}
             public void mouseExited(MouseEvent evt) {  
                minimizar.setIcon(iconoMinimizar);}
             public void mousePressed(MouseEvent evt) {  
                minimizar.setIcon(iconoMinimizarPressed);}
             public void mouseReleased(MouseEvent evt) {  
                minimizar.setIcon(iconoMinimizar);}
        }); 
        
        cerrar.addMouseListener(new MouseAdapter() {  
            public void mouseEntered(MouseEvent evt) {  
                cerrar.setIcon(iconoCerrarHover);}
             public void mouseExited(MouseEvent evt) {  
                cerrar.setIcon(iconoCerrar);}
             public void mousePressed(MouseEvent evt) {  
                cerrar.setIcon(iconoCerrarPressed);}
             public void mouseReleased(MouseEvent evt) {  
                cerrar.setIcon(iconoCerrar);}
        }); 
        
        
        //Determina si la ventana es la principal 
        isMainWindow = esVentanaPrincipal;
         
        
        //Personaliza barra de titulo
        barraTitulo.setFocusable(false);
        barraTitulo.setOpaque(false);
        barraTitulo.setBounds(0, 0, ancho - iconoCerrar.getIconWidth() - iconoMinimizar.getIconWidth(), 30); 
        
        //Agrega eventos a la barra de titulo
        barraTitulo.addMouseListener(new MouseAdapter() {  
            public void mousePressed(MouseEvent evt) {  
                titlebarPressed(evt); }
        }); 
        barraTitulo.addMouseMotionListener(new MouseMotionAdapter() {
             public void mouseDragged(MouseEvent evt) { 
                 titlebarDragged(evt); }
        });
        
        //Agrega los componentes a la ventana
        titlebar.add(barraTitulo); 
        titlebar.add(minimizar);
        titlebar.add(cerrar);
        titlebar.setPreferredSize(new Dimension(ancho,30)); 
        
        
        getContentPane().add(BorderLayout.NORTH,titlebar);
        
    }
    
    //Personaliza botones para la barra de titulo
    public static JButton setDefault(JButton b){
    
        b.setBorder(null);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setFocusable(false);  
        return b;
    }
     
    
    //Analiza que boton detono el evento para ver si la ventana se minimiza o se cierra
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand().toString()){
            
            case "Minimizar":
                minimizeWindow(e);
                break;
                
            case "Cerrar":
                closeWindow(e);
                break; 
        }
    }
    
    //Minimiza la ventana
     private void minimizeWindow(ActionEvent evt) {
        setExtendedState(ICONIFIED); 
    }
    
     //Cierra la ventana, si es la ventana principal, finaliza la ejecucion del programa
    private void closeWindow(ActionEvent evt) { 
        if(isMainWindow) System.exit(0);
        else dispose();
    }
    
    //Obtiene las coordenadas de donde se presionó la barra de titulo
    private void titlebarPressed(java.awt.event.MouseEvent evt) {
        x = evt.getX(); 
        y = evt.getY();
    }
    
    //Mueve la ventana a donde se arrastre la barra de titulo    
    private void titlebarDragged(java.awt.event.MouseEvent evt) {
        point = MouseInfo.getPointerInfo().getLocation();
        
        //Permite mover la ventana arrastrando la barra de titulo
        setLocation(point.x-x, point.y-y); 
    }
     
    //Agrega objetos a windowUI
    public void add(Object o){         
        add(BorderLayout.SOUTH,(Component) o);
    }
     
}
