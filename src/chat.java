
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.net.*;
import java.util.*;
import UI.Components.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import WebCam.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template 54file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NickMadworld
 */
public class chat extends javax.swing.JFrame implements Runnable {
    HashMap<String,Integer>mapa = new HashMap<>();
    String nickname = "";
    String historial = "";
    Thread hilo;
    ArrayList<String> amigos;
    int puertoE = 8888;
    int puertoS = 8887;
    CanvasCam video;
    JFrame f;
    MulticastSocket s;
    InetAddress grupo;
    InetAddress ip_local;
    /**
     * Creates new form chat
     * @param nickname
     */
    CanvasCam c;
    public chat(String nickname) throws UnknownHostException {
        //mapa.put(nickname, WIDTH);   
        getContentPane().setBackground(Color.black);
        initComponents();
        amigos = new ArrayList<>();
        historial = "";
        conversacion.setEditable(false);
        this.nickname = nickname;
        jLabel2.setText(nickname);
        historial+="<body BGCOLOR='#E6E6FA'><font face = 'Century Gothic' size=6 color = 'green'><p ALIGN=center>Unido al chat</p></font>";
        conversacion.setText(historial+"</body>");
        buttonUI.setButtonTextUI(enviar,"Enviar", new Color(0x12345),new Color(0x12345), Color.black, new Font("Century Gothic", Font.PLAIN, 16));
        buttonUI.setButtonTextUI(jButton2,"Salir", Color.red, Color.black, Color.black, new Font("Century Gothic", Font.PLAIN, 16));
        comboboxUI.setComboUI(combito, Color.black);
        //this.frameInit();
        add(enviar);
        imagen.setIcon(new ImageIcon("src/emoticones/Chatt.png"));
        //System.out.println(nickname);
        hilo = new Thread(this);
        //jLabel3.setIcon(new ImageIcon("src/emoticones/Chatt.png"));
        //jLabel3.setBounds(400, 200, 100, 100);
        hilo.start();
        videochat.setEnabled(true);
        ip_local = Inet4Address.getLocalHost();
        unir();
        
    }
    private void unir(){
        try{
           s = new MulticastSocket(puertoE);
           String dir = "229.1.2.3";
           grupo = InetAddress.getByName(dir);
           s.joinGroup(grupo);
           // Nos unimos al grupo         
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           ObjectOutputStream oos = new ObjectOutputStream(baos);  
           mensaje msj = new mensaje(0,nickname);
           oos.writeObject(msj);
           oos.flush();
           // Mandamos un mensaje de tipo 0 con nuestro nickname
           byte[]b = baos.toByteArray();
           DatagramPacket p = new DatagramPacket(b,b.length , grupo, puertoS);
           s.send(p); 
        }catch(Exception e){
            System.out.println("No se pudo conectar al socket multicast");
        }       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        enviar = new javax.swing.JButton();
        combito = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        conversacion = new javax.swing.JEditorPane();
        jLabel2 = new javax.swing.JLabel();
        imagen = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        archivo = new javax.swing.JButton();
        videochat = new javax.swing.JButton();
        videochat1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(350, 100, 0, 0));
        setForeground(java.awt.Color.black);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setName("windows"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(102, 102, 102));
        jTextArea1.setRows(5);
        jTextArea1.setText("Escribir mensaje");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        enviar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        combito.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        combito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));
        combito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combitoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Para");

        conversacion.setBorder(null);
        conversacion.setContentType("text/html"); // NOI18N
        conversacion.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        conversacion.setText("");
        conversacion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(conversacion);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        imagen.setContentAreaFilled(false);
        imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagenActionPerformed(evt);
            }
        });

        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("jLabel3");

        archivo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        archivo.setText("ENVIAR ARCHIVO");
        archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoActionPerformed(evt);
            }
        });

        videochat.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        videochat.setText("VÍDEO CHAT");
        videochat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videochatActionPerformed(evt);
            }
        });

        videochat1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        videochat1.setText("CERRAR CHAT");
        videochat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videochat1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(videochat)
                                .addGap(37, 37, 37)
                                .addComponent(videochat1))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combito, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(videochat, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(videochat1)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(65, 65, 65))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private String formatea(int tipo){
         String res;
         String texto =jTextArea1.getText();
        //System.out.println(texto+"--");
        texto = texto.replace(":)", "<img class=img src= 'file:src/emoticones/smile.png' height=\"15\" width=\"15\">");
        texto = texto.replace(":/", "<img class=img src= 'file:src/emoticones/serio.jpg' height=\"15\" width=\"15\">");
        texto = texto.replace(":D", "<img class=img src= 'file:src/emoticones/smitetoo.png' height=\"15\" width=\"15\">");
        texto = texto.replace("kss","<img class=img src= 'file:src/emoticones/besp.png' height=\"15\" width=\"15\">");
        texto = texto.replace(":p", "<img class=img src= 'file:src/emoticones/lengua.jpg' height=\"15\" width=\"15\">");
        texto = texto.replace("meme","<br><img class=img src= 'file:src/emoticones/baia.jpg' height=\"60\" width=\"60\"><br>");
        texto = texto.replace(";)", "<img class=img src= 'file:src/emoticones/ok.png' height=\"15\" width=\"15\">");
        //System.out.println(texto);
        if(tipo == 0)
           res ="<div> <font face=\"Century Gothic\" SIZE=5 color='red'>"+nickname+"</font><font face=\"Century Gothic\" SIZE=5>";
        else res ="<div> <font face=\"Century Gothic\" SIZE=5 color='blue'>"+nickname+"</font><font face=\"Century Gothic\" SIZE=5>";
        res+=":"+texto+"</font><br></div>";
        //System.out.println(res);
        return res;
    }
    @SuppressWarnings("empty-statement")
    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        try {
         
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           ObjectOutputStream oos = new ObjectOutputStream(baos);
           mensaje msj;
           if("Todos".equals((String)combito.getSelectedItem())){
               historial+= formatea(0);
               conversacion.setText(historial);
               msj = new mensaje(2,nickname,"null",formatea(0) );
           }   else {
               historial+=formatea(1);
               conversacion.setText(historial);
               msj = new mensaje(3,nickname,(String)combito.getSelectedItem(),formatea(1) ); 
            }
             
           oos.writeObject(msj);
           oos.flush();
           // Mandamos un mensaje de tipo 0 con nuestro nickname
           byte[]b = baos.toByteArray();
           DatagramPacket p = new DatagramPacket(b,b.length , grupo, puertoS);
           s.send(p);
           jTextArea1.setText("Escribir un mensaje");
           jTextArea1.setForeground(Color.gray);
           //jTextArea1.setBackground(Color.gray);
        }catch(Exception e){
        e.printStackTrace();
        }
         // TODO add your handling code here:
    }//GEN-LAST:event_enviarActionPerformed

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        jTextArea1.setText("");
        jTextArea1.setForeground(Color.white);
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imagenActionPerformed

    private void archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoActionPerformed
        try{
			Envia2 enviar = new Envia2();
			JFileChooser fc = new JFileChooser();
			fc.setMultiSelectionEnabled(true);
			int r = fc.showOpenDialog(null);
			if( r == JFileChooser.APPROVE_OPTION ){
				File f[] = fc.getSelectedFiles();
				for(int i = 0; i < f.length; i++){
                                     ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                     ObjectOutputStream oos = new ObjectOutputStream(baos);
                                     mensaje msj;
                                     jTextArea1.setText("Ha enviado el archivo: "+f[i].getName());
                                    if("Todos".equals((String)combito.getSelectedItem())){
                                       historial+= formatea(0);
                                        conversacion.setText(historial);
                                        msj = new mensaje(4,nickname,"null",formatea(0) );
                                  }   else {
                                        historial+=formatea(1);
                                        conversacion.setText(historial);
                                        msj = new mensaje(5,nickname,(String)combito.getSelectedItem(),formatea(1) ); 
                                }
             
                                oos.writeObject(msj);
                                oos.flush();
                                // Mandamos un mensaje de tipo 0 con nuestro nickname
                                byte[]b = baos.toByteArray();
                                DatagramPacket p = new DatagramPacket(b,b.length , grupo, puertoS);
                                s.send(p);
                                enviar.enviarArchivo(f[i],s);
				}

			}
                        jTextArea1.setText("Escribir un mensaje");
                        jTextArea1.setForeground(Color.gray);
		}
		catch( Exception e ){
			e.printStackTrace();
		}
        
        // TODO add your handling code here:
    }//GEN-LAST:event_archivoActionPerformed

    private void videochatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videochatActionPerformed
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            mensaje msj = new mensaje(6,nickname,(String)combito.getSelectedItem(),ip_local.getHostAddress());
            oos.writeObject(msj);
            oos.flush();
            // Mandamos un mensaje de tipo 0 con nuestro nickname
            byte[]b = baos.toByteArray();
            DatagramPacket p = new DatagramPacket(b,b.length , grupo, puertoS);
            s.send(p);
        }catch(Exception e){
            e.printStackTrace();}
        // TODO add your handling code here:
    }//GEN-LAST:event_videochatActionPerformed

    private void combitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combitoActionPerformed
        if("Todos".equals((String)combito.getSelectedItem()))
            videochat.setEnabled(false);
        else videochat.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_combitoActionPerformed

    private void videochat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videochat1ActionPerformed
        c.stop();
        f.hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_videochat1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton archivo;
    private javax.swing.JComboBox combito;
    private javax.swing.JEditorPane conversacion;
    private javax.swing.JButton enviar;
    private javax.swing.JButton imagen;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton videochat;
    private javax.swing.JButton videochat1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try{
                MulticastSocket ss = new MulticastSocket(puertoS);
                InetAddress grupos;
                String dir = "229.1.2.3";
                grupos = InetAddress.getByName(dir);
                ss.joinGroup(grupos);
                int i=0;
                while(true){
                    // Escuchamos un mensaje;
                    DatagramPacket p = new DatagramPacket( new byte[2000] , 2000 );
                    ss.receive(p);
                    ByteArrayInputStream baos = new ByteArrayInputStream(p.getData());
                    ObjectInputStream oos = new ObjectInputStream(baos);
                    mensaje msj = (mensaje) oos.readObject();
                    // Los mensajes de tipo 0, serán aquellos que son enviados cuando alguien se Loggea 
                    
                    if(msj.getTipo() == 0 && !msj.getOrigen().equals(nickname)){
                         System.out.println(msj.getOrigen());
                         amigos.add(msj.getOrigen() );
                         combito.addItem(msj.getOrigen());
                         ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                         ObjectOutputStream oos1 = new ObjectOutputStream(baos1);  
                         mensaje msj1 = new mensaje(1,nickname,msj.getOrigen());
                         oos1.writeObject(msj1);
                         oos1.flush();
                        // Mandamos un mensaje de tipo 1 con nuestro nickname al user que se loggeo
                        byte[]b1 = baos1.toByteArray();
                        DatagramPacket p1 = new DatagramPacket(b1,b1.length , grupo, puertoS);
                        ss.send(p1); 
                    }
                    
                    if(msj.getTipo() == 1 && nickname.equals(msj.getDestino() ) && !nickname.equals(msj.getOrigen())){
                        System.out.println("-"+msj.getOrigen());
                        combito.addItem(msj.getOrigen());
                        amigos.add(msj.getOrigen());
                    }
                if(msj.getTipo() == 2 && !nickname.equals(msj.getOrigen()) ){
                        historial+=msj.getMsj();
                        conversacion.setText(historial);
                    }
                    if(msj.getTipo() == 3 && nickname.equals(msj.getDestino())){
                        historial+=msj.getMsj();
                        conversacion.setText(historial);
                    }
                    if(msj.getTipo() == 4  && !nickname.equals(msj.getOrigen())){
                        new Recibe2().recibe(ss);
                        historial+=msj.getMsj();
                        conversacion.setText(historial);
                         JOptionPane.showMessageDialog(rootPane,"ARCHIVO RECIBIDO" );
                    }
                    if(msj.getTipo() == 4  && nickname.equals(msj.getOrigen())){
                        new Recibe2().recibe(ss); 
                    }
                    if(msj.getTipo() == 5  && nickname.equals(msj.getDestino())){
                        new Recibe2().recibe(ss);
                        historial+=msj.getMsj();
                        conversacion.setText(historial);
                        JOptionPane.showMessageDialog(rootPane,"ARCHIVO RECIBIDO" );
                    }
                    if(msj.getTipo() == 5  && nickname.equals(msj.getOrigen())){
                        new Recibe2().recibe(ss); 
                    }
                    if(msj.getTipo() == 6  && nickname.equals(msj.getDestino())){
                        System.out.println(msj.getMsj());                            
                        JOptionPane.showMessageDialog(rootPane,"VÍDEO LLAMADA ENTRANTE" );
                        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                         ObjectOutputStream oos1 = new ObjectOutputStream(baos1);  
                         mensaje msj1 = new mensaje(7,nickname,msj.getOrigen(),ip_local.getHostAddress());
                         oos1.writeObject(msj1);
                         oos1.flush();       
                        byte[]b1 = baos1.toByteArray();
                        DatagramPacket p1 = new DatagramPacket(b1,b1.length , grupo, puertoS);
                        ss.send(p1);
                        f = new JFrame(msj.getOrigen());
                        JButton boton = new JButton("PRESIONAR");
                        f.add(boton);
                        
                        c = new CanvasCam(InetAddress.getByName(msj.getMsj()));
                        f.add(c);
                        f.pack();
                        f.setVisible(true);
                        c.start();
                    }
                    if(msj.getTipo() == 7  && nickname.equals(msj.getDestino())){
                        System.out.println(msj.getMsj());        
                         f = new JFrame(msj.getOrigen());
                         c = new CanvasCam(InetAddress.getByName(msj.getMsj()));
                         f.add(c);
                         f.pack();
                         f.setVisible(true);
                         c.start();
                    }
                }
                
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
               
      }
}

// todo lo escuchamos desde el hilo y escribimos sobre el proceso de la aplicación
