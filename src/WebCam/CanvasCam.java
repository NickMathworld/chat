/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCam;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 * Este Canvas una vez se haya iniciado con {@link #start()} intentará capturar
 * imágenes de la webcam y las pintará de forma continuada en pantalla. Mientras
 * se prepara el dispositivo muestra un mensaje que avisa de que está cargando.
 * Los fotogramas por segundo a los que se muestran las imágenes son 10.
 * 
 * @author <a href="http://programmingheroes.blogspot.com">ProgramminHeroes</a>
 */
public class CanvasCam extends Canvas implements Runnable {

    /**
     * Controla la ejecución del Thread encargado de pintar en el CanvasCam.
     */
    public volatile boolean running = false;
    private DatagramSocket so;
    /**
     * Imprescindible objeto para la utilización de la cámara.
     */
    private WebCam webcam;
    private InetAddress ip;
    /**
     * Permite la utilización de Gráficos acelerados.
     */
    public BufferStrategy bufferStrategy;
    
    /**
     * Imagen actual que se encuentra pintada en el CanvasCam.
     */
    public BufferedImage img;
    
    
    public CanvasCam(InetAddress ip) {
        super();
        this.setSize(640, 480);
        this.setIgnoreRepaint(true);
        this.ip = ip;
        System.out.println(ip.getHostAddress());
         try {
            so = new DatagramSocket(1234);
        } catch (SocketException ex) {
            Logger.getLogger(CanvasCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // fin de CanvasCam();

    /**
     * Inicia el nuevo hilo que va a controlar el pintado.
     */
    public void start() {
        new Thread(this).start();
    } // fin de start();
    
    /**
     * Detiene la conexión con la cámara y finaliza el hilo pintor.
     */
    public void stop() {
        webcam.close();
        running = false;
    } // fin de stop();
    
    @Override
    public void run() {
        // MODIFICAR PARA RECIBIR Y ENVIAR IMÁGENES EN UN MILISEGUNDO
        webcam = new WebCam();
       
        webcam.start();
        this.createBufferStrategy(2);
        bufferStrategy = this.getBufferStrategy();
        running = true;
        // INICIAMOS LA CÁMARA WEB PARA PODER EMPEZAR A TOMAR IMÁGENES CADA MILISEGUNDO
        while (running) {
            BufferedImage image = webcam.getImage();
            if (image != null) {
                img = image;
                File outputFile = new File( "imagenes/envia.jpg" );
                try {
                    ImageIO.write((RenderedImage) image, "jpg", outputFile);
                } catch (IOException ex) {
                    Logger.getLogger(CanvasCam.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(so != (DatagramSocket)null){
                    enviarArchivo(new File("imagenes/envia.jpg"),so);
                    recibe(so);
                }
               
            }
            
            if (!bufferStrategy.contentsLost()) {
                paint(bufferStrategy.getDrawGraphics());
            }
            bufferStrategy.show();
            try {
                Thread.sleep(10); // 1000/100 = 10 FPS
            } catch (InterruptedException ex) {}
        }
    } // fin de run();
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
        paint(g2d);
        g2d.dispose();
    } // fin de paint(Graphics g);
    public boolean enviarArchivo(File f,DatagramSocket s){
		try{
                    String nombre = f.getName();
                    String path = f.getAbsolutePath();
                    Long tam = f.length();
                    DataInputStream dis = new DataInputStream(new FileInputStream(path));
                    byte[] b;
                    // Enviamos el nombre del archivo
                    b = nombre.getBytes();
                    DatagramPacket p = new DatagramPacket(b, b.length,ip, 6666);
                    s.send(p);
                    
                    // Creamos un buffer para enviar pedazo a pedazo el archivo
                    byte[] buf = new byte[2000];
                    ByteArrayOutputStream bay = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(bay);
                    
                    dos.writeLong(tam);
                    dos.flush();
                    
                    byte[] btba = bay.toByteArray();

                    p = new DatagramPacket(btba, btba.length,ip, 8887);
                    s.send(p);
                    //Enviamos el tamaño
                    long enviados = 0;
                    int i = 0;
                    int cont = 0;
                    //System.out.println("Tamaño : " + tam);

                    while (enviados < tam) {
                        i = dis.read(buf);
                        p = new DatagramPacket(buf,i,ip,8887);
                        s.send(p);
                        enviados += i;
                        //System.out.println("Enviado " + cont++ + ": " + i);
                    }

                    //System.out.println("Archivo enviado...");
                    System.out.println("Archivo "+ nombre +" enviado");
                    dis.close();
                    dos.close();
                    return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
    public boolean recibe ( DatagramSocket s){
		try{
                    //System.out.println("Inicia descarga...");
                    DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                    s.receive(p);

                    String nombre = new String(p.getData(), 0, p.getLength());
                    //System.out.println("Nombre:" + nombre);

                    p = new DatagramPacket(new byte[2000], 2000);
                    s.receive(p);

                    DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                    long tam = 0;
                    tam = dis.readLong();
                    //System.out.println("Tamaño: " + tam);
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream("recibidas/"+nombre) );

                    int n = 0;
                    long leidos = 0;
                    byte[] buf = new byte[2000];
                    int cont = 0;

                    while (leidos < tam) {
                        p = new DatagramPacket(new byte[2000], 2000);
                        s.receive(p);
                        dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                        n = dis.read(buf);
                        //System.out.println("Recibido " + cont++ + ": " + n);
                        dos.write(buf, 0, n);
                        dos.flush();
                        leidos = leidos + n;
                    }
                    //System.out.println("Archivo recibido");
                    //System.out.println("Archivo " + nombre + " recibida");
                     
                     dis.close();
                     dos.close(); 
                       return true;
		}
		catch( Exception e ){
			e.printStackTrace();
                        return false;
		}
	}
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (img == null) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            g.drawString("Esperando conexión...",
                10, this.getHeight()-10);
            return;
        }
        g.drawImage(img, (int)((this.getWidth()-img.getWidth())/2),
                (int)((this.getHeight()-img.getHeight())/2), this);
    } // fin de paint(Graphics2D);
    
}    // fin de la clase CanvasCam

// fin de CanvasCam.java -------------------------------------------------------
